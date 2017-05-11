package net.arvin.afbaselibrary.uis.helpers;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by arvinljw on 17/5/11 18:22
 * Function：
 * Desc：
 */
public class IBaseHeaderContact {
    public interface IBaseHeaderView extends IBaseContact.IBase{
        void initHeader(Bundle savedInstanceState);

        int getTitleViewId();

        int getBackViewId();

        boolean isShowBackView();

        boolean isShowTitleView();

        void onTitleViewClicked(View view);

        void onBackViewClicked(View view);

        String getTitleText();
    }

    public interface IBaseHeaderPresenter extends IBaseContact.IPresenter{
        TextView initTitleView();

        View initBackView();

        int getTitleViewId();

        int getBackViewId();
    }
}
