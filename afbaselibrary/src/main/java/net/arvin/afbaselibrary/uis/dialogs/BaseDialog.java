package net.arvin.afbaselibrary.uis.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.Gravity;
import android.view.WindowManager;

import net.arvin.afbaselibrary.R;
import net.arvin.afbaselibrary.utils.AFSizeUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by arvinljw on 17/5/15 01:38
 * Function：
 * Desc：
 */
@SuppressWarnings("WeakerAccess")
public abstract class BaseDialog extends Dialog {
    private Unbinder unbind;

    public BaseDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        setContentView(getContentView());
        unbind = ButterKnife.bind(this);

        resetDialogSize();

        initView();
    }

    protected void resetDialogSize() {
        if (getWindow() != null) {
            WindowManager.LayoutParams params = getWindow().getAttributes();
            params.width = getWidth();
            params.height = getHeight();
            getWindow().setAttributes(params);
        }
    }

    protected int getWidth() {
        return (int) (0.8f * AFSizeUtil.getScreenWidth(getContext()));
    }

    protected int getHeight() {
        return WindowManager.LayoutParams.WRAP_CONTENT;
    }

    /**
     * 实现从底部弹出的方法，在构造方法或者initView中执行该方法即可；
     * 通常若要使用从底部弹出，则需要重写{@link #getWidth()}方法，宽就应占满屏幕更美观
     */
    protected void showFromBottom() {
        if (getWindow() != null) {
            getWindow().setGravity(Gravity.BOTTOM);
            getWindow().setWindowAnimations(R.style.af_anim_bottom_in);
        }
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        unbind.unbind();
    }

    protected abstract int getContentView();

    protected abstract void initView();
}
