package net.arvin.sample.uis.dialogs;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import net.arvin.afbaselibrary.uis.dialogs.BasePopupWindow;
import net.arvin.afbaselibrary.utils.AFSizeUtil;
import net.arvin.sample.R;

import butterknife.BindView;

/**
 * Created by arvinljw on 17/5/15 22:35
 * Function：
 * Desc：
 */
public class TipsPopupWindow extends BasePopupWindow {
    @BindView(R.id.tv_tips_title)
    TextView tvTipsTitle;
    @BindView(R.id.tv_tips_message)
    TextView tvTipsMessage;

    public TipsPopupWindow(Context context, View locationView) {
        super(context, locationView);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.pop_tips;
    }

    @Override
    protected int getContentDefaultWidth() {
        return AFSizeUtil.getScreenWidth(mContent.getContext()) / 3;
    }
}
