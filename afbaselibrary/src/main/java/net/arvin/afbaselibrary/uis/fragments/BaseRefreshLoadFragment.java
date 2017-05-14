package net.arvin.afbaselibrary.uis.fragments;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import net.arvin.afbaselibrary.R;
import net.arvin.afbaselibrary.uis.helpers.BaseRefreshLoadHelper;
import net.arvin.afbaselibrary.uis.helpers.IBaseRefreshLoadContact;
import net.arvin.afbaselibrary.utils.AFLog;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by arvinljw on 17/5/11 22:19
 * Function：下拉刷新，上拉加载
 * Desc：因为这里涉及到上拉加载所以必定是列表，而列表通常都是RecyclerView，所以这里就没有单独抽取加载更多
 * 适用场景：（在sample中体现）
 * 1、正常的列表包含上拉下拉；
 * 2、列表只支持下拉刷新；
 * 3、列表只支持上拉加载；
 */
public abstract class BaseRefreshLoadFragment<T> extends BaseRefreshFragment implements IBaseRefreshLoadContact.IBaseRefreshLoadView<T> {

    protected RecyclerView mRecyclerView;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected BaseQuickAdapter<T, BaseViewHolder> mAdapter;

    protected List<T> mItems;

    private BaseRefreshLoadHelper<T> mRefreshLoadHelper;

    @Override
    public void initRefresh(Bundle saveInstanceStatus) {
        super.initRefresh(saveInstanceStatus);
        initRefreshLoad(saveInstanceStatus);
    }

    @Override
    public void initRefreshLoad(Bundle saveInstanceStatus) {
        mItems = new ArrayList<>();

        mRefreshLoadHelper = new BaseRefreshLoadHelper<>(this, mRoot);

        mRecyclerView = mRefreshLoadHelper.setRecyclerView();
        setLayoutManager();
        setAdapter();
        addDivider();
    }

    @Override
    public int getRecyclerViewId() {
        return mRefreshLoadHelper.getDefaultRecyclerViewId();
    }

    @Override
    public void setLayoutManager() {
        mLayoutManager = mRefreshLoadHelper.getDefaultLayoutManager();
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    @Override
    public void setAdapter() {
        mAdapter = mRefreshLoadHelper.setAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public BaseQuickAdapter<T, BaseViewHolder> getGenerateAdapter() {
        return mAdapter;
    }

    @Override
    public List<T> getItems() {
        return mItems;
    }

    @Override
    public int getEmptyLayoutId() {
        return R.layout.af_layout_empty;
    }

    @Override
    public void addDivider() {
        mRefreshLoadHelper.addDivider();
    }

    @Override
    public boolean isShowDivider() {
        return true;
    }

    @Override
    public void onEmptyViewClicked(View view) {
        autoRefresh();
    }

    @Override
    public void onRefreshBegin(PtrFrameLayout frame) {
        mRefreshLoadHelper.onRefreshBegin(frame);
    }

    @Override
    public void onLoadMoreRequested() {
        mRefreshLoadHelper.onLoadMoreRequest();
    }

    @Override
    public void refreshLoadComplete(boolean success) {
        mRefreshLoadHelper.refreshLoadComplete(success);
    }

    @Override
    public boolean isSuccess(List<T> backData) {
        return mRefreshLoadHelper.isSuccess(backData);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        AFLog.d("item->" + position + " clicked");
    }
}
