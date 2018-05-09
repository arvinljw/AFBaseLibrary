package net.arvin.afbaselibrary.uis.fragments;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import net.arvin.afbaselibrary.R;
import net.arvin.afbaselibrary.data.AFConstant;
import net.arvin.afbaselibrary.uis.adapters.PagerFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arvinljw on 17/5/12 13:41
 * Function：
 * Desc：
 */
public abstract class BaseTabPagerFragment<T extends PagerFragmentAdapter.IPageTitle> extends BaseHeaderFragment implements PagerFragmentAdapter.IPageContent, OnTabSelectListener {

    protected SlidingTabLayout mTabLayout;
    protected ViewPager mPager;
    protected List<T> mItems = new ArrayList<>();

    @Override
    public void initHeader(Bundle savedInstanceState) {
        super.initHeader(savedInstanceState);
        initTabPager(savedInstanceState);
    }

    protected void initTabPager(Bundle savedInstanceState) {
        mTabLayout = root.findViewById(getTabId());
        mPager = root.findViewById(getPagerId());
        loadData();
    }

    protected int getTabId() {
        return R.id.pre_tab_layout;
    }

    protected int getPagerId() {
        return R.id.pre_pager;
    }

    protected void createPager() {
        mPager.setAdapter(new PagerFragmentAdapter<T>(getChildFragmentManager(), mItems, this));

        mPager.setOffscreenPageLimit(AFConstant.PAGE_CACHE_SIZE);

        setTabSpaceEqual();

        mTabLayout.setViewPager(mPager);
        mTabLayout.setOnTabSelectListener(this);
    }

    protected void setTabSpaceEqual() {
        mTabLayout.setTabSpaceEqual(mItems.size() < AFConstant.PAGE_CACHE_SIZE);
    }

    @Override
    public void onTabSelect(int position) {
    }

    @Override
    public void onTabReselect(int position) {
    }

    protected abstract void loadData();


}
