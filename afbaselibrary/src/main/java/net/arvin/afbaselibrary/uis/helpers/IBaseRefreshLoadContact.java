package net.arvin.afbaselibrary.uis.helpers;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by arvinljw on 17/5/11 21:56
 * Function：
 * Desc：
 */
public class IBaseRefreshLoadContact {
    public interface IBaseRefreshLoadView<T> extends IBaseContact.IBase, IBaseRefreshContact.IBaseRefreshView,
            BaseQuickAdapter.RequestLoadMoreListener, BaseQuickAdapter.OnItemClickListener {

        int getRecyclerViewId();

        void initRefreshLoad(Bundle saveInstanceStatus);

        void setLayoutManager();

        void setAdapter();

        BaseQuickAdapter<T, BaseViewHolder> getAdapter();
        
        BaseQuickAdapter<T, BaseViewHolder> getGenerateAdapter();

        int getEmptyLayoutId();

        boolean isShowDivider();

        void addDivider();

        void onEmptyViewClicked(View view);

        List<T> getItems();

        void refreshLoadComplete(boolean success);

        boolean isSuccess(List<T> backData);

        void loadData(final int page);
    }

    public interface IBaseRefreshLoadPresenter<T> extends IBaseContact.IPresenter {
        RecyclerView setRecyclerView();

        int getDefaultRecyclerViewId();

        RecyclerView.LayoutManager getDefaultLayoutManager();

        RecyclerView.Adapter setAdapter();

        View setEmptyView();

        void addDivider();

        int getCurrPage();

        void onRefreshBegin(PtrFrameLayout frame);

        void onLoadMoreRequest();

        void refreshLoadComplete(boolean success);

        boolean isSuccess(List<T> backData);
    }
}
