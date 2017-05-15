package net.arvin.afbaselibrary.uis.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.Toast;

import net.arvin.afbaselibrary.R;
import net.arvin.afbaselibrary.data.AFConstant;
import net.arvin.afbaselibrary.utils.AFSizeUtil;

import butterknife.ButterKnife;

/**
 * Created by arvinljw on 17/5/15 01:38
 * Function：
 * Desc：
 */
@SuppressWarnings("WeakerAccess")
public abstract class BaseDialog extends Dialog {
    public BaseDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        setContentView(getContentView());
        ButterKnife.bind(this);

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

    protected void showToast(String message) {
        int during = Toast.LENGTH_SHORT;
        if (message.length() > AFConstant.TOAST_LONG_MESSAGE_LENGTH) {
            during = Toast.LENGTH_LONG;
        }
        Toast.makeText(getContext(), message, during).show();
    }

    protected abstract int getContentView();

    protected abstract void initView();
}
