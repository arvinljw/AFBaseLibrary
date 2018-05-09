package net.arvin.sample.uis.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import net.arvin.afbaselibrary.data.AFConstant;
import net.arvin.afbaselibrary.uis.fragments.BaseFragment;
import net.arvin.sample.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by arvinljw on 17/7/28 17:46
 * Function：
 * Desc：
 */
public class RecyclerViewFragment extends BaseFragment implements BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.pre_recycler_view)
    RecyclerView mRecyclerView;

    private List<String> mItems = new ArrayList<>();
    private BaseQuickAdapter<String, BaseViewHolder> mAdapter;

    private int page = AFConstant.REFRESH_FIRST_PAGE;

    @Override
    public int getContentView() {
        return R.layout.fragment_recycler_view;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getAFContext()));

        mRecyclerView.setAdapter(getAdapter());

        mAdapter.setOnLoadMoreListener(this, mRecyclerView);

        onLoadMoreRequested();
    }

    public BaseQuickAdapter<String, BaseViewHolder> getAdapter() {
        mAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_text, mItems) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                helper.setText(R.id.tv_item, item);
            }
        };
        return mAdapter;
    }

    @Override
    public void onLoadMoreRequested() {
        mRecyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (page == AFConstant.REFRESH_FIRST_PAGE) {
                    mItems.clear();
                }
                for (int i = (page - 1) * AFConstant.REFRESH_DEFAULT_SIZE; i < page * AFConstant.REFRESH_DEFAULT_SIZE; i++) {
                    mItems.add("item" + i);
                }
                mAdapter.notifyDataSetChanged();
                page++;
            }
        }, 500);
    }
}
