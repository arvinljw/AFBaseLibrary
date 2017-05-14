package net.arvin.afbaselibrary.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RSRuntimeException;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import net.arvin.afbaselibrary.R;

import java.io.File;

/**
 * Created by arvinljw on 17/5/15 00:28
 * Function：
 * Desc：
 */
public class AFGlideUtil {
    private static int sCommonPlaceholder;
    private static int sCirclePlaceholder;
    private static int sRoundPlaceholder;


    static {
        sCommonPlaceholder = R.drawable.img_loading;
        sCirclePlaceholder = R.drawable.img_default_avatar;
        sRoundPlaceholder = R.drawable.img_loading;
    }

    public AFGlideUtil() {
    }

    /**
     * 设置圆形图片的占位图
     */
    public static void setCirclePlaceholder(int circlePlaceholder) {
        sCirclePlaceholder = circlePlaceholder;
    }

    /**
     * 设置正常图片的占位符
     */
    public static void setCommonPlaceholder(int commonPlaceholder) {
        sCommonPlaceholder = commonPlaceholder;
    }

    /**
     * 设置圆角图片的占位符
     */
    public static void setsRoundPlaceholder(int roundPlaceholder) {
        sRoundPlaceholder = roundPlaceholder;
    }

    /**
     * 加载背景图
     *
     * @param obj 要加载的资源
     * @param v   被加载背景图的组件
     */
    public static void loadImgBackground(Object obj, View v) {
        loadImgBackground(obj, v, null);
    }

    public static void loadImgBackground(Object obj, final View v, SimpleTarget simpleTarget) {
        DrawableTypeRequest drawableTypeRequest = getDrawableTypeRequest(obj, v);
        if (drawableTypeRequest == null) {
            drawableTypeRequest = getDrawableTypeRequest(sCommonPlaceholder, v);
        }
        if (drawableTypeRequest != null) {
            drawableTypeRequest
                    .asBitmap()
                    .centerCrop()
                    .dontAnimate()
                    .error(sCommonPlaceholder)
                    .placeholder(sCommonPlaceholder)
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .into(simpleTarget != null ? simpleTarget : new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                            BitmapDrawable drawable = new BitmapDrawable(resource);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                v.setBackground(drawable);
                            } else {
                                v.setBackgroundDrawable(drawable);
                            }
                        }
                    });
        }
    }

    public static void loadImgBlur(Object obj, ImageView img) {
        DrawableTypeRequest drawableTypeRequest = getDrawableTypeRequest(obj, img);
        if (drawableTypeRequest == null) {
            drawableTypeRequest = getDrawableTypeRequest(sCommonPlaceholder, img);
        }
        if (drawableTypeRequest != null) {
            drawableTypeRequest
                    .centerCrop()
                    .dontAnimate()
                    .error(sCommonPlaceholder)
                    .placeholder(sCommonPlaceholder)
                    .bitmapTransform(new BlurTransformation(img.getContext(), 16, 4))
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .into(img);
        }
    }

    public static void loadImage(Object obj, ImageView img) {
        DrawableTypeRequest drawableTypeRequest = getDrawableTypeRequest(obj, img);
        if (drawableTypeRequest == null) {
            drawableTypeRequest = getDrawableTypeRequest(sCommonPlaceholder, img);
        }
        if (drawableTypeRequest != null) {
            drawableTypeRequest
                    .centerCrop()
                    .dontAnimate()
                    .error(sCommonPlaceholder)
                    .placeholder(sCommonPlaceholder)
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .into(img);
        }
    }

    /**
     * 加载圆形图片
     *
     * @param obj 要加载的资源
     * @param img 被加载的图片
     */
    public static void loadCircleImg(Object obj, ImageView img) {
        DrawableTypeRequest drawableTypeRequest = getDrawableTypeRequest(obj, img);
        if (drawableTypeRequest == null) {
            drawableTypeRequest = getDrawableTypeRequest(sCirclePlaceholder, img);
        }
        if (drawableTypeRequest != null) {
            drawableTypeRequest
                    .centerCrop()
                    .dontAnimate()
                    .transform(new AFGlideUtil.GlideCircleTransform(img.getContext()))
                    .error(sCirclePlaceholder)
                    .placeholder(sCirclePlaceholder).
                    diskCacheStrategy(DiskCacheStrategy.RESULT).into(img);
        }
    }

    /**
     * 加载圆形图片
     *
     * @param obj 要加载的资源
     * @param img 被加载的图片
     */
    public static void loadCircleImgNoPlace(Object obj, ImageView img) {
        DrawableTypeRequest drawableTypeRequest = getDrawableTypeRequest(obj, img);
        if (drawableTypeRequest != null) {
            drawableTypeRequest
                    .centerCrop()
                    .dontAnimate()
                    .transform(new AFGlideUtil.GlideCircleTransform(img.getContext()))
                    .diskCacheStrategy(DiskCacheStrategy.RESULT).into(img);
        }
    }

    public static void loadImgBlurNoPlace(Object obj, ImageView img) {
        DrawableTypeRequest drawableTypeRequest = getDrawableTypeRequest(obj, img);
        if (drawableTypeRequest != null) {
            drawableTypeRequest
                    .centerCrop()
                    .dontAnimate()
                    .bitmapTransform(new BlurTransformation(img.getContext(), 16, 4))
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .into(img);
        }
    }

    /**
     * 加载圆角图片
     *
     * @param obj 要加载的资源
     * @param img 被加载的图片
     * @param dp  圆角大小
     */
    public static void loadRoundImg(Object obj, ImageView img, int dp) {
        DrawableTypeRequest drawableTypeRequest = getDrawableTypeRequest(obj, img);
        if (drawableTypeRequest == null) {
            drawableTypeRequest = getDrawableTypeRequest(sRoundPlaceholder, img);
        }
        if (drawableTypeRequest != null) {
            drawableTypeRequest
                    .centerCrop()
                    .dontAnimate()
                    .transform(new BitmapTransformation[]{new AFGlideUtil.GlideRoundTransform(img.getContext(), dp)})
                    .error(sRoundPlaceholder)
                    .placeholder(sRoundPlaceholder).
                    diskCacheStrategy(DiskCacheStrategy.RESULT).into(img);
        }
    }

    public static void loadRoundImg(Object obj, ImageView img) {
        loadRoundImg(obj, img, 4);
    }

    @Nullable
    private static DrawableTypeRequest getDrawableTypeRequest(Object obj, View img) {
        if (img == null || obj == null) return null;
        Context context = img.getContext();
        RequestManager manager = Glide.with(context);
        DrawableTypeRequest drawableTypeRequest = null;
        if (obj instanceof String) {
            drawableTypeRequest = manager.load((String) obj);
        } else if (obj instanceof Integer) {
            drawableTypeRequest = manager.load((Integer) obj);
        } else if (obj instanceof Uri) {
            drawableTypeRequest = manager.load((Uri) obj);
        } else if (obj instanceof File) {
            drawableTypeRequest = manager.load((File) obj);
        }
        return drawableTypeRequest;
    }

    private static class GlideCircleTransform extends BitmapTransformation {
        GlideCircleTransform(Context context) {
            super(context);
        }

        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            return circleCrop(pool, toTransform);
        }

        private static Bitmap circleCrop(BitmapPool pool, Bitmap source) {
            if (source == null) {
                return null;
            } else {
                int size = Math.min(source.getWidth(), source.getHeight());
                int x = (source.getWidth() - size) / 2;
                int y = (source.getHeight() - size) / 2;
                Bitmap squared = Bitmap.createBitmap(source, x, y, size, size);
                Bitmap result = pool.get(size, size, Bitmap.Config.ARGB_8888);
                if (result == null) {
                    result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
                }

                Canvas canvas = new Canvas(result);
                Paint paint = new Paint();
                paint.setShader(new BitmapShader(squared, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
                paint.setAntiAlias(true);
                float r = (float) size / 2.0F;
                canvas.drawCircle(r, r, r, paint);
                return result;
            }
        }

        public String getId() {
            return this.getClass().getName();
        }
    }

    private static class GlideRoundTransform extends BitmapTransformation {
        float radius = 0f;

        GlideRoundTransform(Context context, int dp) {
            super(context);
            this.radius = Resources.getSystem().getDisplayMetrics().density * dp;
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            return roundCrop(pool, toTransform);
        }

        private Bitmap roundCrop(BitmapPool pool, Bitmap toTransform) {
            if (toTransform == null) return null;

            Bitmap result = pool.get(toTransform.getWidth(), toTransform.getHeight(), Bitmap.Config.ARGB_8888);
            if (result == null) {
                result = Bitmap.createBitmap(toTransform.getWidth(), toTransform.getHeight(), Bitmap.Config.ARGB_8888);
            }
            Canvas canvas = new Canvas(result);
            Paint paint = new Paint();
            paint.setShader(new BitmapShader(toTransform, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
            paint.setAntiAlias(true);
            RectF rectF = new RectF(0f, 0f, toTransform.getWidth(), toTransform.getHeight());
            canvas.drawRoundRect(rectF, radius, radius, paint);
            return result;
        }

        @Override
        public String getId() {
            return this.getClass().getName();
        }
    }

    private static class BlurTransformation implements Transformation<Bitmap> {

        private static int MAX_RADIUS = 25;
        private static int DEFAULT_DOWN_SAMPLING = 1;

        private Context mContext;
        private BitmapPool mBitmapPool;

        private int mRadius;
        private int mSampling;

        public BlurTransformation(Context context) {
            this(context, Glide.get(context).getBitmapPool(), MAX_RADIUS, DEFAULT_DOWN_SAMPLING);
        }

        public BlurTransformation(Context context, BitmapPool pool) {
            this(context, pool, MAX_RADIUS, DEFAULT_DOWN_SAMPLING);
        }

        public BlurTransformation(Context context, BitmapPool pool, int radius) {
            this(context, pool, radius, DEFAULT_DOWN_SAMPLING);
        }

        public BlurTransformation(Context context, int radius) {
            this(context, Glide.get(context).getBitmapPool(), radius, DEFAULT_DOWN_SAMPLING);
        }

        BlurTransformation(Context context, int radius, int sampling) {
            this(context, Glide.get(context).getBitmapPool(), radius, sampling);
        }

        BlurTransformation(Context context, BitmapPool pool, int radius, int sampling) {
            mContext = context.getApplicationContext();
            mBitmapPool = pool;
            mRadius = radius;
            mSampling = sampling;
        }

        @Override
        public Resource<Bitmap> transform(Resource<Bitmap> resource, int outWidth, int outHeight) {
            Bitmap source = resource.get();

            int width = source.getWidth();
            int height = source.getHeight();
            int scaledWidth = width / mSampling;
            int scaledHeight = height / mSampling;

            Bitmap bitmap = mBitmapPool.get(scaledWidth, scaledHeight, Bitmap.Config.ARGB_8888);
            if (bitmap == null) {
                bitmap = Bitmap.createBitmap(scaledWidth, scaledHeight, Bitmap.Config.ARGB_8888);
            }

            Canvas canvas = new Canvas(bitmap);
            canvas.scale(1 / (float) mSampling, 1 / (float) mSampling);
            Paint paint = new Paint();
            paint.setFlags(Paint.FILTER_BITMAP_FLAG);
            canvas.drawBitmap(source, 0, 0, paint);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                try {
                    bitmap = blur(mContext, bitmap, mRadius);
                } catch (RSRuntimeException e) {
                    bitmap = blur(bitmap, mRadius, true);
                }
            } else {
                bitmap = blur(bitmap, mRadius, true);
            }

            return BitmapResource.obtain(bitmap, mBitmapPool);
        }

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
        static Bitmap blur(Context context, Bitmap bitmap, int radius) throws RSRuntimeException {
            RenderScript rs = null;
            try {
                rs = RenderScript.create(context);
                rs.setMessageHandler(new RenderScript.RSMessageHandler());
                Allocation input =
                        Allocation.createFromBitmap(rs, bitmap, Allocation.MipmapControl.MIPMAP_NONE,
                                Allocation.USAGE_SCRIPT);
                Allocation output = Allocation.createTyped(rs, input.getType());
                ScriptIntrinsicBlur blur = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));

                blur.setInput(input);
                blur.setRadius(radius);
                blur.forEach(output);
                output.copyTo(bitmap);
            } finally {
                if (rs != null) {
                    rs.destroy();
                }
            }

            return bitmap;
        }

        static Bitmap blur(Bitmap sentBitmap, int radius, boolean canReuseInBitmap) {
            Bitmap bitmap;
            if (canReuseInBitmap) {
                bitmap = sentBitmap;
            } else {
                bitmap = sentBitmap.copy(sentBitmap.getConfig(), true);
            }

            if (radius < 1) {
                return (null);
            }

            int w = bitmap.getWidth();
            int h = bitmap.getHeight();

            int[] pix = new int[w * h];
            bitmap.getPixels(pix, 0, w, 0, 0, w, h);

            int wm = w - 1;
            int hm = h - 1;
            int wh = w * h;
            int div = radius + radius + 1;

            int r[] = new int[wh];
            int g[] = new int[wh];
            int b[] = new int[wh];
            int rsum, gsum, bsum, x, y, i, p, yp, yi, yw;
            int vmin[] = new int[Math.max(w, h)];

            int divsum = (div + 1) >> 1;
            divsum *= divsum;
            int dv[] = new int[256 * divsum];
            for (i = 0; i < 256 * divsum; i++) {
                dv[i] = (i / divsum);
            }

            yw = yi = 0;

            int[][] stack = new int[div][3];
            int stackpointer;
            int stackstart;
            int[] sir;
            int rbs;
            int r1 = radius + 1;
            int routsum, goutsum, boutsum;
            int rinsum, ginsum, binsum;

            for (y = 0; y < h; y++) {
                rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
                for (i = -radius; i <= radius; i++) {
                    p = pix[yi + Math.min(wm, Math.max(i, 0))];
                    sir = stack[i + radius];
                    sir[0] = (p & 0xff0000) >> 16;
                    sir[1] = (p & 0x00ff00) >> 8;
                    sir[2] = (p & 0x0000ff);
                    rbs = r1 - Math.abs(i);
                    rsum += sir[0] * rbs;
                    gsum += sir[1] * rbs;
                    bsum += sir[2] * rbs;
                    if (i > 0) {
                        rinsum += sir[0];
                        ginsum += sir[1];
                        binsum += sir[2];
                    } else {
                        routsum += sir[0];
                        goutsum += sir[1];
                        boutsum += sir[2];
                    }
                }
                stackpointer = radius;

                for (x = 0; x < w; x++) {

                    r[yi] = dv[rsum];
                    g[yi] = dv[gsum];
                    b[yi] = dv[bsum];

                    rsum -= routsum;
                    gsum -= goutsum;
                    bsum -= boutsum;

                    stackstart = stackpointer - radius + div;
                    sir = stack[stackstart % div];

                    routsum -= sir[0];
                    goutsum -= sir[1];
                    boutsum -= sir[2];

                    if (y == 0) {
                        vmin[x] = Math.min(x + radius + 1, wm);
                    }
                    p = pix[yw + vmin[x]];

                    sir[0] = (p & 0xff0000) >> 16;
                    sir[1] = (p & 0x00ff00) >> 8;
                    sir[2] = (p & 0x0000ff);

                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];

                    rsum += rinsum;
                    gsum += ginsum;
                    bsum += binsum;

                    stackpointer = (stackpointer + 1) % div;
                    sir = stack[(stackpointer) % div];

                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];

                    rinsum -= sir[0];
                    ginsum -= sir[1];
                    binsum -= sir[2];

                    yi++;
                }
                yw += w;
            }
            for (x = 0; x < w; x++) {
                rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
                yp = -radius * w;
                for (i = -radius; i <= radius; i++) {
                    yi = Math.max(0, yp) + x;

                    sir = stack[i + radius];

                    sir[0] = r[yi];
                    sir[1] = g[yi];
                    sir[2] = b[yi];

                    rbs = r1 - Math.abs(i);

                    rsum += r[yi] * rbs;
                    gsum += g[yi] * rbs;
                    bsum += b[yi] * rbs;

                    if (i > 0) {
                        rinsum += sir[0];
                        ginsum += sir[1];
                        binsum += sir[2];
                    } else {
                        routsum += sir[0];
                        goutsum += sir[1];
                        boutsum += sir[2];
                    }

                    if (i < hm) {
                        yp += w;
                    }
                }
                yi = x;
                stackpointer = radius;
                for (y = 0; y < h; y++) {
                    // Preserve alpha channel: ( 0xff000000 & pix[yi] )
                    pix[yi] = (0xff000000 & pix[yi]) | (dv[rsum] << 16) | (dv[gsum] << 8) | dv[bsum];

                    rsum -= routsum;
                    gsum -= goutsum;
                    bsum -= boutsum;

                    stackstart = stackpointer - radius + div;
                    sir = stack[stackstart % div];

                    routsum -= sir[0];
                    goutsum -= sir[1];
                    boutsum -= sir[2];

                    if (x == 0) {
                        vmin[y] = Math.min(y + r1, hm) * w;
                    }
                    p = x + vmin[y];

                    sir[0] = r[p];
                    sir[1] = g[p];
                    sir[2] = b[p];

                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];

                    rsum += rinsum;
                    gsum += ginsum;
                    bsum += binsum;

                    stackpointer = (stackpointer + 1) % div;
                    sir = stack[stackpointer];

                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];

                    rinsum -= sir[0];
                    ginsum -= sir[1];
                    binsum -= sir[2];

                    yi += w;
                }
            }

            bitmap.setPixels(pix, 0, w, 0, 0, w, h);

            return (bitmap);
        }

        @Override
        public String getId() {
            return "BlurTransformation(radius=" + mRadius + ", sampling=" + mSampling + ")";
        }
    }
}
