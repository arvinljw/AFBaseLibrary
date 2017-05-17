package net.arvin.afbaselibrary.uis.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import net.arvin.afbaselibrary.R;
import net.arvin.afbaselibrary.uis.helpers.BaseHeaderHelper;
import net.arvin.afbaselibrary.uis.helpers.IBaseHeaderContact;
import net.arvin.afbaselibrary.utils.AFLog;

/**
 * Created by arvinljw on 17/5/11 17:01
 * Function：有的标题Activity，支持右滑返回，也可禁止该功能，具体请参考{@link BaseSwipeBackActivity}
 * Desc：根据项目中的情况来看一般就只有返回按钮和标题，更多的就自行情况扩展吧
 */
public abstract class BaseHeaderActivity extends BaseSwipeBackActivity implements IBaseHeaderContact.IBaseHeaderView {
    protected TextView tvTitle;
    protected View vBack;

    private BaseHeaderHelper mBaseHeaderHelper;

    @Override
    public void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        initHeader(savedInstanceState);
    }

    @Override
    public void initHeader(Bundle savedInstanceState) {
        mBaseHeaderHelper = new BaseHeaderHelper(this);
        tvTitle = mBaseHeaderHelper.initTitleView();
        vBack = mBaseHeaderHelper.initBackView();
    }

    @Override
    public int getTitleViewId() {
        return mBaseHeaderHelper.getTitleViewId();
    }

    @Override
    public int getBackViewId() {
        return mBaseHeaderHelper.getBackViewId();
    }

    @Override
    public boolean isShowBackView() {
        return true;
    }

    @Override
    public boolean isShowTitleView() {
        return true;
    }

    @Override
    public void onTitleViewClicked(View view) {
    }

    @Override
    public void onBackViewClicked(View view) {
        onBackPressed();
    }
}
