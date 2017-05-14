package net.arvin.afbaselibrary.uis.helpers;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import net.arvin.afbaselibrary.R;
import net.arvin.afbaselibrary.data.AFConstant;
import net.arvin.afbaselibrary.uis.views.HorizontalDividerItemDecoration;

import java.util.List;

import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by arvinljw on 17/5/11 22:05
 * Function：
 * Desc：
 */
public class BaseRefreshLoadHelper<T> extends BaseRefreshHelper<IBaseRefreshLoadContact.IBaseRefreshLoadView<T>> implements IBaseRefreshLoadContact.IBaseRefreshLoadPresenter<T> {

    protected int mCurrPage = AFConstant.REFRESH_FIRST_PAGE;

    private RecyclerView mRecyclerView;

    public BaseRefreshLoadHelper(IBaseRefreshLoadContact.IBaseRefreshLoadView<T> mBaseView) {
        super(mBaseView);
    }

    public BaseRefreshLoadHelper(IBaseRefreshLoadContact.IBaseRefreshLoadView<T> mBaseView, View mRoot) {
        super(mBaseView, mRoot);
    }

    @Override
    public int getCurrPage() {
        return mCurrPage;
    }

    @Override
    public RecyclerView setRecyclerView() {
        mRecyclerView = getView(mBaseView.getRecyclerViewId());
        return mRecyclerView;
    }

    @Override
    public int getDefaultRecyclerViewId() {
        return R.id.pre_recycler_view;
    }

    @Override
    public RecyclerView.LayoutManager getDefaultLayoutManager() {
        return new LinearLayoutManager(mBaseView.getAFContext());
    }

    @Override
    public BaseQuickAdapter<T, BaseViewHolder> setAdapter() {
        BaseQuickAdapter<T, BaseViewHolder> mAdapter = mBaseView.getAdapter();
        mAdapter.setEmptyView(setEmptyView());
        mAdapter.setOnLoadMoreListener(mBaseView, mRecyclerView);
        mAdapter.setOnItemClickListener(mBaseView);
        return mAdapter;
    }

    @Override
    public View setEmptyView() {
        View emptyView = LayoutInflater.from(mBaseView.getAFContext()).inflate(mBaseView.getEmptyLayoutId(), mRecyclerView, false);
        emptyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBaseView.onEmptyViewClicked(v);
            }
        });
        return emptyView;
    }

    @Override
    public void addDivider() {
        if (mBaseView.isShowDivider()) {
            mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(mBaseView.getAFContext())
                    .color(mBaseView.getAFContext().getResources().getColor(R.color.black_divider))
                    .sizeResId(R.dimen.spacing_divider)
                    .build());
        }
    }

    @Override
    public void onRefreshBegin(PtrFrameLayout frame) {
        mCurrPage = AFConstant.REFRESH_FIRST_PAGE;
        mBaseView.loadData(mCurrPage);
    }

    @Override
    public void onLoadMoreRequest() {
        mBaseView.loadData(++mCurrPage);
    }

    @Override
    public void refreshLoadComplete(boolean success) {
        if (mBaseView.getAFContext() == null) {
            return;
        }
        if (mCurrPage > AFConstant.REFRESH_FIRST_PAGE) {
            if (!success) {
                mCurrPage--;
            }
            mBaseView.getGenerateAdapter().loadMoreComplete();
        } else {
            mBaseView.getGenerateAdapter().notifyDataSetChanged();
            mBaseView.refreshComplete();
        }
        boolean enableLoadMore = mBaseView.getItems().size() % AFConstant.REFRESH_DEFAULT_SIZE == 0;
        mBaseView.getGenerateAdapter().setEnableLoadMore(enableLoadMore && success);
    }

    @Override
    public boolean isSuccess(List<T> backData) {
        return backData != null && backData.size() > 0;
    }

    @Override
    public void destroyIt() {
        super.destroyIt();
        mRecyclerView = null;
    }
}
