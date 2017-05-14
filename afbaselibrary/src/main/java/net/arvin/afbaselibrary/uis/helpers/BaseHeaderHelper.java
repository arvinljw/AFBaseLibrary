package net.arvin.afbaselibrary.uis.helpers;

import android.view.View;
import android.widget.TextView;

import net.arvin.afbaselibrary.R;
import net.arvin.afbaselibrary.utils.AFLog;

/**
 * Created by arvinljw on 17/5/11 18:09
 * Function：
 * Desc：
 */
public class BaseHeaderHelper extends NeedInitViewHelper<IBaseHeaderContact.IBaseHeaderView> implements IBaseHeaderContact.IBaseHeaderPresenter {

    public BaseHeaderHelper(IBaseHeaderContact.IBaseHeaderView mBaseView) {
        super(mBaseView);
    }

    public BaseHeaderHelper(IBaseHeaderContact.IBaseHeaderView mBaseView, View mRoot) {
        super(mBaseView, mRoot);
    }

    @Override
    public int getTitleViewId() {
        return R.id.pre_tv_title;
    }

    @Override
    public int getBackViewId() {
        return R.id.pre_v_back;
    }

    @Override
    public TextView initTitleView() {
        TextView tvTitle = null;
        try {
            tvTitle = getView(mBaseView.getTitleViewId());

            tvTitle.setText(mBaseView.getTitleText());
            tvTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mBaseView.onTitleViewClicked(v);
                }
            });
            tvTitle.setVisibility(mBaseView.isShowTitleView() ? View.VISIBLE : View.GONE);
        } catch (Exception e) {
            AFLog.w("未设置标题id~");
        }
        return tvTitle;
    }

    @Override
    public View initBackView() {
        View vBack = null;
        try {
            vBack = getView(mBaseView.getBackViewId());

            vBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mBaseView.onBackViewClicked(v);
                }
            });
            vBack.setVisibility(mBaseView.isShowBackView() ? View.VISIBLE : View.GONE);
        } catch (Exception e) {
            AFLog.w("未设置返回图标id~");
        }
        return vBack;
    }
}
