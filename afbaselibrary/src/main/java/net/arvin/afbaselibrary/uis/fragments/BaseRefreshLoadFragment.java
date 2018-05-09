package net.arvin.afbaselibrary.uis.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;

import net.arvin.afbaselibrary.R;
import net.arvin.afbaselibrary.data.AFConstant;
import net.arvin.afbaselibrary.uis.views.HorizontalDividerItemDecoration;
import net.arvin.afbaselibrary.utils.AFLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arvinljw on 17/5/11 22:19
 * Function：下拉刷新，上拉加载
 * Desc：因为这里涉及到上拉加载所以必定是列表，而列表通常都是RecyclerView，所以这里就没有单独抽取加载更多
 * 适用场景：（在sample中体现）
 * 1、正常的列表包含上拉下拉；
 * 2、列表只支持下拉刷新；
 * 3、列表只支持上拉加载；
 */
public abstract class BaseRefreshLoadFragment<T> extends BaseRefreshFragment implements OnLoadMoreListener, BaseQuickAdapter.OnItemClickListener {
    protected RecyclerView mRecyclerView;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected BaseQuickAdapter<T, BaseViewHolder> mAdapter;

    protected List<T> mItems;
    protected int mCurrPage = getDefaultFirstPage();

    @Override
    public void initRefresh(Bundle saveInstanceStatus) {
        super.initRefresh(saveInstanceStatus);
        mRefreshLayout.setOnLoadMoreListener(this);
        setRefreshFooter();
        initRecyclerView();
    }

    protected void initRecyclerView() {
        mItems = new ArrayList<>();

        mRecyclerView = root.findViewById(getRecyclerViewId());
        setLayoutManager();
        setAdapter();
        addDivider();
    }

    protected int getRecyclerViewId() {
        return R.id.pre_recycler_view;
    }

    protected void setLayoutManager() {
        mLayoutManager = new LinearLayoutManager(getAFContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    protected void setAdapter() {
        mAdapter = getAdapter();
        mAdapter.setEmptyView(setEmptyView());
        mAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    protected void setRefreshFooter() {
        mRefreshLayout.setRefreshFooter(new ClassicsFooter(getAFContext()));
    }

    protected View setEmptyView() {
        View emptyView = LayoutInflater.from(getAFContext()).inflate(getEmptyLayoutId(), mRecyclerView, false);
        emptyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEmptyViewClicked(v);
            }
        });
        return emptyView;
    }

    protected void onEmptyViewClicked(View view) {
        autoRefresh();
    }

    protected int getEmptyLayoutId() {
        return R.layout.af_layout_empty;
    }

    protected void addDivider() {
        if (isShowDivider()) {
            mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getAFContext())
                    .color(getAFContext().getResources().getColor(R.color.black_divider))
                    .sizeResId(R.dimen.spacing_divider)
                    .build());
        }
    }

    protected boolean isShowDivider() {
        return true;
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mCurrPage = AFConstant.REFRESH_FIRST_PAGE;
        loadData(mCurrPage);
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        loadData(++mCurrPage);
    }

    protected void refreshLoadComplete(boolean success, boolean hasNextPage) {
        if (getAFContext() == null) {
            return;
        }
        if (mCurrPage > AFConstant.REFRESH_FIRST_PAGE) {
            if (!success) {
                mCurrPage--;
            }
            mRefreshLayout.finishLoadMore(AFConstant.REFRESH_ANIMATION_TIME);
        } else {
            mRefreshLayout.finishRefresh(AFConstant.REFRESH_ANIMATION_TIME);
        }
        if (!hasNextPage) {
            mRefreshLayout.finishLoadMoreWithNoMoreData();
        }
        mAdapter.notifyDataSetChanged();
    }

    protected int getDefaultFirstPage() {
        return AFConstant.REFRESH_FIRST_PAGE;
    }

    protected int getDefaultPageSize() {
        return AFConstant.REFRESH_DEFAULT_SIZE;
    }

    protected boolean isSuccess(List<T> backData) {
        return backData != null && backData.size() > 0;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        AFLog.w("item->" + position + " clicked");
    }

    protected abstract BaseQuickAdapter<T, BaseViewHolder> getAdapter();

    protected abstract void loadData(final int page);

}
