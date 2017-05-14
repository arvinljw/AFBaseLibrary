package net.arvin.afbaselibrary.uis.helpers;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.List;

/**
 * Created by arvinljw on 17/5/12 14:31
 * Function：
 * Desc：
 */
public class IBaseTabPageContact {
    public interface IBaseTabPageView<T> extends IBaseContact.IBase, IPageContent, OnTabSelectListener {
        void initTabPager(Bundle saveInstanceStatus);

        FragmentManager getAFFragmentManager();

        int getPagerId();

        int getTabId();

        List<T> getItems();

        void createPager();

        void setTabSpaceEqual();

        void getData();
    }

    public interface IBaseTabPagerPresenter extends IBaseContact.IPresenter {
        SlidingTabLayout setTabLayout();

        ViewPager setPager();

        int getDefaultTabId();

        int getDefaultPageId();

        void createPager();

        void setDefaultTabSpaceEqual();
    }

    public interface IPageTitle {
        String getTitle();
    }

    public interface IPageContent {
        Fragment getContent(int position);
    }
}
