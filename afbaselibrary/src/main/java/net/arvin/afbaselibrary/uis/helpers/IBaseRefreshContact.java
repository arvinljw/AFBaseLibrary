package net.arvin.afbaselibrary.uis.helpers;

import android.os.Bundle;
import android.view.View;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Created by arvinljw on 17/5/11 20:31
 * Function：
 * Desc：
 */
public class IBaseRefreshContact {
    public interface IBaseRefreshView extends IBaseContact.IBase, PtrHandler {
        void initRefresh(Bundle saveInstanceStatus);

        int getRefreshId();

        void setRefreshHeader();

        PtrFrameLayout getRefreshLayout();

        void autoRefresh();

        void refreshComplete();
    }

    public interface IBaseRefreshPresenter extends IBaseContact.IPresenter{
        PtrFrameLayout initRefreshLayout();

        int getDefaultRefreshId();

        void setRefreshHeader();

        void autoRefresh();

        void refreshComplete();

        boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header);
    }
}
