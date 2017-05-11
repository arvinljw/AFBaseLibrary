package net.arvin.sample.uis.fragments;

import android.Manifest;
import android.os.Bundle;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import net.arvin.afbaselibrary.data.AFConstant;
import net.arvin.afbaselibrary.uis.fragments.BaseHeaderFragment;
import net.arvin.afbaselibrary.uis.fragments.BaseRefreshLoadFragment;
import net.arvin.afbaselibrary.uis.helpers.IBaseContact;
import net.arvin.sample.R;

/**
 * Created by arvinljw on 17/5/11 19:03
 * Function：
 * Desc：
 */
public class HeaderFragment extends BaseRefreshLoadFragment<String> {
    @Override
    public int getContentView() {
        return R.layout.fragment_header;
    }

    @Override
    public String getTitleText() {
        return "headerFragment";
    }

    @Override
    public boolean isShowBackView() {
        return false;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        autoRefresh();
    }

    @Override
    public BaseQuickAdapter<String, BaseViewHolder> getAdapter() {
        return new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_text, mItems) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                helper.setText(R.id.tv_item, item);
            }
        };
    }

    @Override
    public void loadData(final int page) {
        mRecyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (page == AFConstant.REFRESH_FIRST_PAGE) {
                    mItems.clear();
                }
                for (int i = 0; i < 15; i++) {
                    mItems.add("item" + i);
                }
                refreshLoadComplete(false);
            }
        }, 1000);
    }
}
