package net.arvin.sample.uis.dialogs;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import net.arvin.afbaselibrary.uis.dialogs.BaseDialog;
import net.arvin.sample.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by arvinljw on 17/5/15 16:09
 * Function：
 * Desc：
 */
public class TipsDialog extends BaseDialog {
    @BindView(R.id.tv_tips_title)
    TextView tvTipsTitle;
    @BindView(R.id.tv_tips_message)
    TextView tvTipsMessage;
    @BindView(R.id.btn_left)
    TextView btnLeft;
    @BindView(R.id.btn_right)
    TextView btnRight;

    private OnLeftClickListener mOnLeftClickListener;
    private OnRightClickListener mOnRightClickListener;

    public TipsDialog(@NonNull Context context) {
        super(context, R.style.normal_dialog_corner);
    }

    @Override
    protected int getContentView() {
        return R.layout.dialog_tips;
    }

    @Override
    protected void initView() {
    }

    public TipsDialog setTipsTitle(String title) {
        tvTipsTitle.setText(title);
        return this;
    }

    public TipsDialog setMessage(String message) {
        tvTipsMessage.setText(message);
        return this;
    }

    public TipsDialog setOnLeftClickListener(String leftBtn, OnLeftClickListener onLeftClickListener) {
        btnLeft.setText(leftBtn);
        this.mOnLeftClickListener = onLeftClickListener;
        return this;
    }

    public TipsDialog setOnRightClickListener(String rightBtn, OnRightClickListener onRightClickListener) {
        btnRight.setText(rightBtn);
        this.mOnRightClickListener = onRightClickListener;
        return this;
    }

    @OnClick(R.id.btn_left)
    public void onBtnLeftClicked(View v) {
        if (mOnLeftClickListener != null) {
            mOnLeftClickListener.onLeftClicked(v);
        }
        dismiss();
    }

    @OnClick(R.id.btn_right)
    public void onBtnRightClicked(View v) {
        if (mOnRightClickListener != null) {
            mOnRightClickListener.onRightClicked(v);
        }
        dismiss();
    }

    public interface OnLeftClickListener {
        void onLeftClicked(View v);
    }

    public interface OnRightClickListener {
        void onRightClicked(View v);
    }
}
