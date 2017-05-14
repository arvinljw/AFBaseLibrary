package net.arvin.afbaselibrary.uis.helpers;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import net.arvin.afbaselibrary.utils.AFPermissionUtil;

/**
 * Created by arvinljw on 17/5/11 15:25
 * Function：
 * Desc：
 */
public class IBaseContact {
    public interface IBase {
        FragmentActivity getAFContext();
    }

    public interface IPresenter {
        void destroyIt();
    }

    public interface IBaseView extends IBase, AFPermissionUtil.PermissionCallbacks {

        int getContentView();

        void init(Bundle savedInstanceState);

        void initViews(Bundle savedInstanceState);

        void controlStatusBar();

        void showToast(String message);

        void showProgressDialog(String message);

        void hideProgressDialog();

        void startActivity(Class clazz);

        void startActivity(Class clazz, Bundle bundle);

        void startActivityForResult(Class clazz, int requestCode);

        void startActivityForResult(Class clazz, Bundle bundle, int requestCode);

        void requestPermission(IBaseContact.IRequestPermissionCallback permissionCallback, String reqStr, String... permissions);
    }

    public interface IBasePresenter extends IPresenter, AFPermissionUtil.PermissionCallbacks {
        void showToast(String message);

        void showProgressDialog(String message);

        void hideProgressDialog();

        void startActivity(Class clazz, Bundle bundle);

        void startActivityForResult(Class clazz, Bundle bundle, int requestCode);

        void requestPermission(IBaseContact.IRequestPermissionCallback permissionCallback, String reqStr, String... permissions);
    }

    public interface IRequestPermissionCallback {
        void agreeAll();
    }
}
