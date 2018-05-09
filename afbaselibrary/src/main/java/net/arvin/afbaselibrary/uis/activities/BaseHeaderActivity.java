package net.arvin.afbaselibrary.uis.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import net.arvin.afbaselibrary.R;
import net.arvin.afbaselibrary.utils.AFLog;

import butterknife.BindView;

/**
 * Created by arvinljw on 17/5/11 17:01
 * Function：有的标题Activity，支持右滑返回，也可禁止该功能，具体请参考{@link BaseSwipeBackActivity}
 * Desc：根据项目中的情况来看一般就只有返回按钮和标题，更多的就自行情况扩展吧
 */
public abstract class BaseHeaderActivity extends BaseSwipeBackActivity {
    protected TextView tvTitle;
    protected View vBack;

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        initHeader(savedInstanceState);
    }

    protected void initHeader(Bundle savedInstanceState) {
        initTitleView();
        initBackView();
    }

    protected void initTitleView() {
        try {
            tvTitle = findViewById(getTitleViewId());

            tvTitle.setText(getTitleText());
            tvTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onTitleViewClicked(v);
                }
            });
            tvTitle.setVisibility(isShowTitleView() ? View.VISIBLE : View.GONE);
        } catch (Exception e) {
            AFLog.w("未设置标题id~");
        }
    }

    protected void initBackView() {
        try {
            vBack = findViewById(getBackViewId());

            vBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackViewClicked(v);
                }
            });
            vBack.setVisibility(isShowBackView() ? View.VISIBLE : View.GONE);
        } catch (Exception e) {
            AFLog.w("未设置返回图标id~");
        }
    }

    protected int getTitleViewId() {
        return R.id.pre_tv_title;
    }

    protected int getBackViewId() {
        return R.id.pre_v_back;
    }

    protected boolean isShowBackView() {
        return true;
    }

    protected boolean isShowTitleView() {
        return true;
    }

    protected void onTitleViewClicked(View view) {
    }

    protected void onBackViewClicked(View view) {
        onBackPressed();
    }

    protected abstract String getTitleText();
}
