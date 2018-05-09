package net.arvin.sample.uis.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import net.arvin.afbaselibrary.uis.activities.BaseTabPagerActivity;
import net.arvin.sample.R;
import net.arvin.sample.entities.TabEntity;
import net.arvin.sample.uis.fragments.CategoryFragment;
import net.arvin.sample.uis.fragments.MovieListFragment;

/**
 * Created by arvinljw on 17/5/12 15:15
 * Function：
 * Desc：
 */
public class TabDemoActivity extends BaseTabPagerActivity<TabEntity> {

    @Override
    public int getContentView() {
        return R.layout.activity_tab_demo;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
    }

    @Override
    public String getTitleText() {
        return "";
    }

    @Override
    public void loadData() {
        mItems.add(new TabEntity("豆瓣电影Top250"));
        mItems.add(new TabEntity("福利"));
        mItems.add(new TabEntity("Android"));
        mItems.add(new TabEntity("IOS", "iOS"));
        mItems.add(new TabEntity("视频", "休息视频"));
        createPager();
    }

    @Override
    public Fragment getContent(int position) {
        if (position == 0){
            return new MovieListFragment();
        }
        return new CategoryFragment(mItems.get(position).getType());
    }

}
