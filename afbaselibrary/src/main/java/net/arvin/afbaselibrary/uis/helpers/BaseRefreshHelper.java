package net.arvin.afbaselibrary.uis.helpers;

import android.view.View;

import net.arvin.afbaselibrary.R;
import net.arvin.afbaselibrary.utils.AFLog;
import net.arvin.afbaselibrary.utils.AFSizeUtil;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.header.MaterialHeader;

/**
 * Created by arvinljw on 17/5/11 20:40
 * Function：
 * Desc：
 */
public class BaseRefreshHelper<T extends IBaseRefreshContact.IBaseRefreshView> extends NeedInitViewHelper<T> implements IBaseRefreshContact.IBaseRefreshPresenter {

    private PtrFrameLayout mRefreshLayout;

    public BaseRefreshHelper(T mBaseView) {
        super(mBaseView);
    }

    public BaseRefreshHelper(T mBaseView, View mRoot) {
        super(mBaseView, mRoot);
    }

    @Override
    public PtrFrameLayout initRefreshLayout() {
        try {
            mRefreshLayout = getView(mBaseView.getRefreshId());

        } catch (Exception e) {
            AFLog.d("未设置PtrFrameLayout的ID为pre_refresh");
        }
        return mRefreshLayout;
    }

    @Override
    public int getDefaultRefreshId() {
        return R.id.pre_refresh;
    }

    @Override
    public void setRefreshHeader() {
        if (mRefreshLayout == null) {
            return;
        }

        MaterialHeader header = new MaterialHeader(mBaseView.getAFContext());
        header.setColorSchemeColors(new int[]{mBaseView.getAFContext().getResources().getColor(R.color.colorAccent)});
        header.setLayoutParams(new PtrFrameLayout.LayoutParams(-1, -2));
        header.setPadding(0, AFSizeUtil.dp2px(12), 0, AFSizeUtil.dp2px(12));
        header.setPtrFrameLayout(mRefreshLayout);

        mRefreshLayout.setPinContent(true);
        mRefreshLayout.setHeaderView(header);
        mRefreshLayout.addPtrUIHandler(header);
        mRefreshLayout.setPtrHandler(mBaseView);
    }

    @Override
    public void autoRefresh() {
        if (mRefreshLayout == null) {
            mBaseView.onRefreshBegin(null);
            return;
        }
        mRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mRefreshLayout.autoRefresh();
            }
        }, 50);
    }

    @Override
    public void refreshComplete() {
        if (mRefreshLayout == null) {
            return;
        }
        mRefreshLayout.refreshComplete();
    }

    @Override
    public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
        return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
    }

    @Override
    public void destroyIt() {
        super.destroyIt();
        mRefreshLayout = null;
    }
}
