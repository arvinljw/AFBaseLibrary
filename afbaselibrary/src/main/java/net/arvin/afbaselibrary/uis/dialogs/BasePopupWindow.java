package net.arvin.afbaselibrary.uis.dialogs;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import net.arvin.afbaselibrary.utils.AFLog;
import net.arvin.afbaselibrary.utils.AFSizeUtil;

import butterknife.ButterKnife;

/**
 * Created by arvinljw on 17/5/15 21:53
 * Function：
 * Desc：
 */
@SuppressWarnings("WeakerAccess")
public abstract class BasePopupWindow extends PopupWindow {
    protected RelativeLayout mRoot;
    protected View mContent;
    /**
     * 在布局中定义一个宽或高为0的View作为locationView
     */
    protected View mLocationView;

    public BasePopupWindow(Context context, View locationView) {
        super(context);

        setDefaultSize();

        setLocationView(locationView);

        initRootView(context);

        mContent = LayoutInflater.from(context).inflate(getContentViewId(), null);
        mContent.setClickable(true);
        mRoot.addView(mContent, new RelativeLayout.LayoutParams(getContentDefaultWidth(), getContentDefaultHeight()));
        ButterKnife.bind(this, mContent);

        setNormalConfig();
    }

    private int getContentDefaultHeight() {
        return RelativeLayout.LayoutParams.WRAP_CONTENT;
    }

    private void initRootView(Context context) {
        mRoot = new RelativeLayout(context);
        mRoot.setBackgroundColor(Color.parseColor("#66000000"));
        mRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        setContentView(mRoot);
    }

    protected void setDefaultSize() {
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
    }

    /**
     * 一般Pop内容是需要被点击的，而且点击外部是需要消失的
     */
    protected void setNormalConfig() {
        setTouchable(true);
        setBackgroundDrawable(new BitmapDrawable());
        setOutsideTouchable(true);

        setAnimationStyle(android.R.style.Animation_Dialog);
    }

    /**
     * @param locationView 在布局中定义一个宽或高为0的View作为locationView
     */
    public void setLocationView(View locationView) {
        this.mLocationView = locationView;
    }

    public void showBelow(View anchor, int offsetX, int offsetY) {
        int[] location = new int[2];
        anchor.getLocationOnScreen(location);
        int anchorCenterX = location[0] + anchor.getWidth() / 2;
        int anchorBottomY = location[1] + anchor.getHeight();

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mContent.getLayoutParams();
        params.topMargin = anchorBottomY + offsetY - getStatusHeight();
        int contentX = anchorCenterX - getContentDefaultWidth() / 2;
        params.leftMargin = (contentX < 0 ? 0 : contentX) + offsetX;
        mContent.setLayoutParams(params);

        show();
    }

    public void showAbove(View anchor, int offsetX, int offsetY) {
        int[] location = new int[2];
        anchor.getLocationOnScreen(location);
        int anchorCenterX = location[0] + anchor.getWidth() / 2;
        int anchorTopY = location[1];

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mContent.getLayoutParams();
        params.topMargin = anchorTopY + offsetY - getStatusHeight();
        int contentX = anchorCenterX - getContentDefaultWidth() / 2;
        params.leftMargin = (contentX < 0 ? 0 : contentX) + offsetX;
        mContent.setLayoutParams(params);

        show();
    }

    /**
     * 若是没有沉浸式，则需要减去状态栏的高度
     */
    protected int getStatusHeight() {
        return AFSizeUtil.dp2px(24);
    }

    public void showAtTopLeft(int offsetX, int offsetY) {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mContent.getLayoutParams();
        params.topMargin = offsetY;
        params.leftMargin = offsetX;
        mContent.setLayoutParams(params);
        show();
    }

    public void showAtTopCenter(int offsetY) {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mContent.getLayoutParams();
        params.topMargin = offsetY;
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        mContent.setLayoutParams(params);
        show();
    }

    public void showAtTopRight(int offsetX, int offsetY) {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mContent.getLayoutParams();
        params.topMargin = offsetY;
        params.leftMargin = AFSizeUtil.getScreenWidth(mContent.getContext()) - getContentDefaultWidth() + offsetX;
        mContent.setLayoutParams(params);
        show();
    }


    public void showAtCenterLeft(int offsetX) {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mContent.getLayoutParams();
        params.leftMargin = offsetX;
        params.addRule(RelativeLayout.CENTER_VERTICAL);
        show();
    }

    public void showInCenter() {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mContent.getLayoutParams();
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        show();
    }


    public void showAtCenterRight(int offsetX) {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mContent.getLayoutParams();
        params.leftMargin = AFSizeUtil.getScreenWidth(mContent.getContext()) - getContentDefaultWidth() + offsetX;
        params.addRule(RelativeLayout.CENTER_VERTICAL);
        show();
    }

    public void showAtBottomLeft(int offsetX, int offsetY) {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mContent.getLayoutParams();
        params.bottomMargin = -offsetY;
        params.leftMargin = offsetX;
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        mContent.setLayoutParams(params);
        show();
    }

    public void showAtBottomCenter(int offsetY) {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mContent.getLayoutParams();
        params.bottomMargin = -offsetY;
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        mContent.setLayoutParams(params);
        show();
    }

    public void showAtBottomRight(int offsetX, int offsetY) {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mContent.getLayoutParams();
        params.bottomMargin = -offsetY;
        params.leftMargin = AFSizeUtil.getScreenWidth(mContent.getContext()) - getContentDefaultWidth() + offsetX;
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        mContent.setLayoutParams(params);
        show();
    }

    private void show() {
        if (mLocationView == null) {
            AFLog.d("never set locationView!");
            return;
        }
        showAsDropDown(mLocationView);
    }

    protected abstract int getContentViewId();

    /**
     * 不能为WrapContent
     */
    protected abstract int getContentDefaultWidth();
}
