package net.arvin.afbaselibrary.uis.fragments;

import android.os.Bundle;

import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import net.arvin.afbaselibrary.R;
import net.arvin.afbaselibrary.data.AFConstant;

/**
 * Created by arvinljw on 17/5/11 21:09
 * Function：
 * Desc：
 */
public abstract class BaseRefreshFragment extends BaseHeaderFragment implements OnRefreshListener {
    protected SmartRefreshLayout mRefreshLayout;

    @Override
    public void initHeader(Bundle savedInstanceState) {
        super.initHeader(savedInstanceState);
        initRefresh(savedInstanceState);
    }

    protected void initRefresh(Bundle saveInstanceStatus) {
        try {
            mRefreshLayout = root.findViewById(getRefreshId());
            mRefreshLayout.setOnRefreshListener(this);
        } catch (Exception e) {
            throw new RuntimeException("未设置SmartRefreshLayout的ID为pre_refresh");
        }
        setRefreshHeader();
    }

    protected int getRefreshId() {
        return R.id.pre_refresh;
    }

    protected void setRefreshHeader() {
        mRefreshLayout.setRefreshHeader(new MaterialHeader(getAFContext()));
    }

    protected void autoRefresh() {
        mRefreshLayout.autoRefresh();
    }

    protected void refreshComplete() {
        mRefreshLayout.finishRefresh(AFConstant.REFRESH_ANIMATION_TIME);
        mRefreshLayout.finishLoadMoreWithNoMoreData();
    }
}