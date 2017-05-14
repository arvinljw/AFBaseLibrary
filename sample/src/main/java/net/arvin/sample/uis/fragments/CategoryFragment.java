package net.arvin.sample.uis.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import net.arvin.afbaselibrary.data.AFConstant;
import net.arvin.afbaselibrary.nets.ApiCallback;
import net.arvin.afbaselibrary.uis.activities.BaseRefreshLoadActivity;
import net.arvin.afbaselibrary.uis.fragments.BaseRefreshLoadFragment;
import net.arvin.sample.R;
import net.arvin.sample.entities.CategoryEntity;
import net.arvin.sample.nets.GankService;
import net.arvin.sample.uis.adapters.CategoryListAdapter;

import java.util.List;

/**
 * Created by arvinljw on 17/5/15 00:09
 * Function：
 * Desc：
 */
public class CategoryFragment extends BaseRefreshLoadFragment<CategoryEntity> {
    private String type;

    public CategoryFragment() {
    }

    @SuppressLint("ValidFragment")
    public CategoryFragment(String type) {
        this.type = type;
    }

    @Override
    public int getContentView() {
        return R.layout.layout_refresh_load;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        autoRefresh();
    }

    @Override
    public String getTitleText() {
        return "";
    }

    @Override
    public BaseQuickAdapter<CategoryEntity, BaseViewHolder> getAdapter() {
        return new CategoryListAdapter(mItems);
    }

    @Override
    public void loadData(final int page) {
        GankService.getInstance().getData(type, page, AFConstant.REFRESH_DEFAULT_SIZE, new ApiCallback<List<CategoryEntity>>() {
            @Override
            public void onError(Throwable e) {
                refreshLoadComplete(false);
            }

            @Override
            public void onNext(List<CategoryEntity> categoryEntities) {
                if (page == AFConstant.REFRESH_FIRST_PAGE) {
                    mItems.clear();
                }
                mItems.addAll(categoryEntities);
                refreshLoadComplete(isSuccess(categoryEntities));
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        GankService.getInstance().onDestroy();
    }
}
