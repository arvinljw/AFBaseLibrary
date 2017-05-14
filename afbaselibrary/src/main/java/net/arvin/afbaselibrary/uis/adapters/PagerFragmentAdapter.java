package net.arvin.afbaselibrary.uis.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import net.arvin.afbaselibrary.uis.helpers.IBaseTabPageContact;

import java.util.List;

/**
 * Created by arvinljw on 17/5/12 14:25
 * Function：
 * Desc：
 */
public class PagerFragmentAdapter<T extends IBaseTabPageContact.IPageTitle> extends FragmentStatePagerAdapter {
    private List<T> mItems;

    private IBaseTabPageContact.IPageContent mPageContent;

    public PagerFragmentAdapter(FragmentManager fm, List<T> items, IBaseTabPageContact.IPageContent pageContent) {
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
}
