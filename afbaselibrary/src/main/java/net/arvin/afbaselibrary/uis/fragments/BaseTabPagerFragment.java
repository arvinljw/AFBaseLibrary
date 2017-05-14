package net.arvin.afbaselibrary.uis.fragments;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;

import net.arvin.afbaselibrary.uis.activities.BaseHeaderActivity;
import net.arvin.afbaselibrary.uis.helpers.BaseTabPagerHelper;
import net.arvin.afbaselibrary.uis.helpers.IBaseTabPageContact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arvinljw on 17/5/12 13:41
 * Function：
 * Desc：
 */
public abstract class BaseTabPagerFragment<T extends IBaseTabPageContact.IPageTitle> extends BaseHeaderFragment implements IBaseTabPageContact.IBaseTabPageView<T> {

    protected SlidingTabLayout mTabLayout;
    protected ViewPager mPager;
    protected List<T> mItems = new ArrayList<>();

    private BaseTabPagerHelper<T> mBaseTabPagerHelper;

    @Override
    public void initHeader(Bundle savedInstanceState) {
        super.initHeader(savedInstanceState);
        initTabPager(savedInstanceState);
    }

    @Override
    public void initTabPager(Bundle savedInstanceState) {
        mBaseTabPagerHelper = new BaseTabPagerHelper<>(this);

        mTabLayout = mBaseTabPagerHelper.setTabLayout();
        mPager = mBaseTabPagerHelper.setPager();
        getData();
    }

    @Override
    public int getTabId() {
        return mBaseTabPagerHelper.getDefaultTabId();
    }

    @Override
    public int getPagerId() {
        return mBaseTabPagerHelper.getDefaultPageId();
    }

    @Override
    public FragmentManager getAFFragmentManager() {
        return getChildFragmentManager();
    }

    @Override
    public void createPager() {
        mBaseTabPagerHelper.createPager();
    }

    @Override
    public List<T> getItems() {
        return mItems;
    }

    @Override
    public void setTabSpaceEqual() {
        mBaseTabPagerHelper.setDefaultTabSpaceEqual();
    }

    @Override
    public void onTabSelect(int position) {
    }

    @Override
    public void onTabReselect(int position) {
    }


}
