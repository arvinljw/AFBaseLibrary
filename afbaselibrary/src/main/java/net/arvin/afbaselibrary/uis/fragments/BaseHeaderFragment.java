package net.arvin.afbaselibrary.uis.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import net.arvin.afbaselibrary.uis.helpers.BaseHeaderHelper;
import net.arvin.afbaselibrary.uis.helpers.IBaseHeaderContact;

/**
 * Created by arvinljw on 17/5/11 17:19
 * Function：带标题的Fragment
 * Desc：功能与代表提的Activity一致，但是在实际中应该用的较少
 */
public abstract class BaseHeaderFragment extends BaseFragment implements IBaseHeaderContact.IBaseHeaderView {
    protected TextView tvTitle;
    protected View vBack;

    private BaseHeaderHelper mBaseHeaderHelper;

    @Override
    public void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        initHeader(savedInstanceState);
    }

    @Override
    public void initHeader(Bundle savedInstanceState) {
        mBaseHeaderHelper = new BaseHeaderHelper(this, mRoot);
        tvTitle = mBaseHeaderHelper.initTitleView();
        vBack = mBaseHeaderHelper.initBackView();
    }

    @Override
    public int getTitleViewId() {
        return mBaseHeaderHelper.getTitleViewId();
    }

    @Override
    public int getBackViewId() {
        return mBaseHeaderHelper.getBackViewId();
    }

    @Override
    public boolean isShowBackView() {
        return true;
    }

    @Override
    public boolean isShowTitleView() {
        return true;
    }

    @Override
    public void onTitleViewClicked(View view) {
    }

    @Override
    public void onBackViewClicked(View view) {
        getActivity().onBackPressed();
    }
}
