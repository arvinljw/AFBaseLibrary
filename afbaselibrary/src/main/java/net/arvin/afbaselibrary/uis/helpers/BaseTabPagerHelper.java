package net.arvin.afbaselibrary.uis.helpers;

import android.support.v4.view.ViewPager;
import android.view.View;

import com.flyco.tablayout.SlidingTabLayout;

import net.arvin.afbaselibrary.R;
import net.arvin.afbaselibrary.data.AFConstant;
import net.arvin.afbaselibrary.uis.adapters.PagerFragmentAdapter;

/**
 * Created by arvinljw on 17/5/12 15:39
 * Function：
 * Desc：
 */
public class BaseTabPagerHelper<T extends IBaseTabPageContact.IPageTitle> extends NeedInitViewHelper<IBaseTabPageContact.IBaseTabPageView<T>> implements IBaseTabPageContact.IBaseTabPagerPresenter {

    private SlidingTabLayout mTabLayout;
    private ViewPager mPager;

    public BaseTabPagerHelper(IBaseTabPageContact.IBaseTabPageView<T> mBaseView) {
        super(mBaseView);
    }

    public BaseTabPagerHelper(IBaseTabPageContact.IBaseTabPageView<T> mBaseView, View mRoot) {
        super(mBaseView, mRoot);
    }

    @Override
    public SlidingTabLayout setTabLayout() {
        mTabLayout = getView(mBaseView.getTabId());
        return mTabLayout;
    }

    @Override
    public ViewPager setPager() {
        mPager = getView(mBaseView.getPagerId());
        return mPager;
    }

    @Override
    public int getDefaultTabId() {
        return R.id.pre_tab_layout;
    }

    @Override
    public int getDefaultPageId() {
        return R.id.pre_pager;
    }

    @Override
    public void createPager() {
        mPager.setAdapter(new PagerFragmentAdapter<T>(mBaseView.getAFFragmentManager(), mBaseView.getItems(), mBaseView));

        mPager.setOffscreenPageLimit(AFConstant.PAGE_CACHE_SIZE);

        mBaseView.setTabSpaceEqual();

        mTabLayout.setViewPager(mPager);
        mTabLayout.setOnTabSelectListener(mBaseView);
    }

    @Override
    public void setDefaultTabSpaceEqual() {
        mTabLayout.setTabSpaceEqual(mBaseView.getItems().size() < AFConstant.PAGE_CACHE_SIZE);
    }
}
