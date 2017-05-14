package net.arvin.sample.uis.activities;

import android.Manifest;
import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import net.arvin.afbaselibrary.uis.activities.BaseActivity;
import net.arvin.afbaselibrary.uis.activities.BaseRefreshLoadActivity;
import net.arvin.afbaselibrary.uis.helpers.IBaseContact;
import net.arvin.sample.R;
import net.arvin.sample.uis.fragments.HeaderFragment;

/**
 * Created by arvinljw on 17/5/10 12:51
 * Function：首页
 * Desc：首页，这个界面退出就是桌面即在栈底，所以需要重写onBackPressed方法，去掉向右关闭的动画
 * 在style方面若是使用了右滑关闭功能，那么应该想这个例子一样，Application的theme设为窗口透明，栈底界面的theme应设为不透明
 */
public class MainActivity extends BaseRefreshLoadActivity<Class> {

    @Override
    public int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public String getTitleText() {
        return "首页";
    }

    @Override
    public boolean isShowBackView() {
        return false;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        autoRefresh();
    }

    @Override
    public BaseQuickAdapter<Class, BaseViewHolder> getAdapter() {
        return new BaseQuickAdapter<Class, BaseViewHolder>(R.layout.item_text, mItems) {
            @Override
            protected void convert(BaseViewHolder helper, Class item) {
                helper.setText(R.id.tv_item, item.getSimpleName());
            }
        };
    }

    @Override
    public void loadData(int page) {
        mRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mItems.clear();

                mItems.add(TabDemoActivity.class);


                refreshLoadComplete(false);
            }
        }, 500);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        startActivity(mItems.get(position));
    }

    @Override
    public void onBackPressed() {
        finish();
    }

}
