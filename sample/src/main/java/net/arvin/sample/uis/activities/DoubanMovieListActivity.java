package net.arvin.sample.uis.activities;

import android.os.Bundle;

import net.arvin.afbaselibrary.uis.activities.BaseHeaderActivity;
import net.arvin.sample.R;
import net.arvin.sample.uis.fragments.MovieListFragment;

/**
 * Created by arvinljw on 18/3/5 13:49
 * Function：
 * Desc：
 */
public class DoubanMovieListActivity extends BaseHeaderActivity {
    @Override
    public boolean isShowBackView() {
        return false;
    }

    @Override
    public int getContentView() {
        return R.layout.layout_frame;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        setSwipeBackEnable(false);
        getSupportFragmentManager().beginTransaction().add(R.id.layout_content, new MovieListFragment()).commitAllowingStateLoss();
    }

    @Override
    public String getTitleText() {
        return "豆瓣电影Top250";
    }
}
