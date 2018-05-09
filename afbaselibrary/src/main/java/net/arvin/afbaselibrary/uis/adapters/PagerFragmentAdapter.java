package net.arvin.afbaselibrary.uis.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by arvinljw on 17/5/12 14:25
 * Function：
 * Desc：
 */
public class PagerFragmentAdapter<T extends PagerFragmentAdapter.IPageTitle> extends FragmentStatePagerAdapter {
    private List<T> mItems;

    private IPageContent mPageContent;

    public PagerFragmentAdapter(FragmentManager fm, List<T> items, PagerFragmentAdapter.IPageContent pageContent) {
        super(fm);
        this.mItems = items;
        this.mPageContent = pageContent;

    }

    @Override
    public Fragment getItem(int position) {
        return mPageContent.getContent(position);
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mItems.get(position).getTitle();
    }

    public interface IPageTitle {
        String getTitle();
    }

    public interface IPageContent {
        Fragment getContent(int position);
    }
}
