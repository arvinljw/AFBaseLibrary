package net.arvin.afbaselibrary.uis.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import net.arvin.afbaselibrary.R;
import net.arvin.afbaselibrary.utils.AFLog;

/**
 * Created by arvinljw on 17/5/11 17:19
 * Function：带标题的Fragment
 * Desc：功能与代表提的Activity一致，但是在实际中应该用的较少
 */
public abstract class BaseHeaderFragment extends BaseFragment{
    protected TextView tvTitle;
    protected View vBack;

    @Override
    public void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        initHeader(savedInstanceState);
    }

    protected void initHeader(Bundle savedInstanceState) {
        initTitleView();
        initBackView();
    }

    protected void initTitleView() {
        try {
            tvTitle = root.findViewById(getTitleViewId());

            tvTitle.setText(getTitleText());
            tvTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onTitleViewClicked(v);
                }
            });
            tvTitle.setVisibility(isShowTitleView() ? View.VISIBLE : View.GONE);
        } catch (Exception e) {
            AFLog.w("未设置标题id~");
        }
    }

    protected void initBackView() {
        try {
            vBack = root.findViewById(getBackViewId());

            vBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackViewClicked(v);
                }
            });
            vBack.setVisibility(isShowBackView() ? View.VISIBLE : View.GONE);
        } catch (Exception e) {
            AFLog.w("未设置返回图标id~");
        }
    }

    protected int getTitleViewId() {
        return R.id.pre_tv_title;
    }

    protected int getBackViewId() {
        return R.id.pre_v_back;
    }

    protected boolean isShowBackView() {
        return true;
    }

    protected boolean isShowTitleView() {
        return true;
    }

    protected void onTitleViewClicked(View view) {
    }

    protected void onBackViewClicked(View view) {
        getAFContext().onBackPressed();
    }

    protected abstract String getTitleText();
}
