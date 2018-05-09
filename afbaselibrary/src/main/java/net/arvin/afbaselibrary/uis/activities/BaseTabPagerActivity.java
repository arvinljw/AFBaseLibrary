package net.arvin.afbaselibrary.uis.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
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
public abstract class BaseTabPagerActivity<T extends PagerFragmentAdapter.IPageTitle> extends BaseHeaderActivity implements
        PagerFragmentAdapter.IPageContent, OnTabSelectListener {

    protected SlidingTabLayout mTabLayout;
    protected ViewPager mPager;
    protected List<T> mItems = new ArrayList<>();

    @Override
    public void initHeader(Bundle savedInstanceState) {
        super.initHeader(savedInstanceState);
        initTabPager(savedInstanceState);
    }

    public void initTabPager(Bundle savedInstanceState) {
        mTabLayout = findViewById(getTabId());
        mPager = findViewById(getPagerId());
        loadData();
    }

    public int getTabId() {
        return R.id.pre_tab_layout;
    }

    public int getPagerId() {
        return R.id.pre_pager;
    }

    public FragmentManager getAFFragmentManager() {
        return getSupportFragmentManager();
    }

    public void createPager() {
        mPager.setAdapter(new PagerFragmentAdapter<T>(getAFFragmentManager(), mItems, this));

        mPager.setOffscreenPageLimit(AFConstant.PAGE_CACHE_SIZE);

        setTabSpaceEqual();

        mTabLayout.setViewPager(mPager);
        mTabLayout.setOnTabSelectListener(this);
    }

    public void setTabSpaceEqual() {
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
