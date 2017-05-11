package net.arvin.afbaselibrary.uis.fragments;

import android.os.Bundle;
import android.view.View;

import net.arvin.afbaselibrary.uis.helpers.BaseRefreshHelper;
import net.arvin.afbaselibrary.uis.helpers.IBaseRefreshContact;

import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by arvinljw on 17/5/11 21:09
 * Function：
 * Desc：
 */
public abstract class BaseRefreshFragment extends BaseHeaderFragment implements IBaseRefreshContact.IBaseRefreshView {
    protected PtrFrameLayout mRefreshLayout;

    private BaseRefreshHelper<IBaseRefreshContact.IBaseRefreshView> mRefreshHelper;

    @Override
    public void initHeader(Bundle savedInstanceState) {
        super.initHeader(savedInstanceState);
        initRefresh(savedInstanceState);
    }

    @Override
    public void initRefresh(Bundle saveInstanceStatus) {
        mRefreshHelper = new BaseRefreshHelper<IBaseRefreshContact.IBaseRefreshView>(this, mRoot);
        mRefreshLayout = mRefreshHelper.initRefreshLayout();
        setRefreshHeader();
    }

    @Override
    public PtrFrameLayout getRefreshLayout() {
        return mRefreshLayout;
    }

    @Override
    public int getRefreshId() {
        return mRefreshHelper.getDefaultRefreshId();
    }

    @Override
    public void setRefreshHeader() {
        mRefreshHelper.setRefreshHeader();
    }

    @Override
    public void autoRefresh() {
        mRefreshHelper.autoRefresh();
    }

    @Override
    public void refreshComplete() {
        mRefreshHelper.refreshComplete();
    }

    @Override
    public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
        return mRefreshHelper.checkCanDoRefresh(frame, content, header);
    }
}
