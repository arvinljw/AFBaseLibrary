package net.arvin.afbaselibrary.uis.helpers;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.widget.Toast;

import net.arvin.afbaselibrary.R;
import net.arvin.afbaselibrary.data.AFConstant;
import net.arvin.afbaselibrary.utils.AFLog;
import net.arvin.afbaselibrary.utils.AFPermissionUtil;

import java.util.List;

/**
 * Created by arvinljw on 17/5/11 15:13
 * Function：
 * Desc：
 */
public class BaseHelper extends NeedInitViewHelper<IBaseContact.IBaseView> implements IBaseContact.IBasePresenter {
    private FragmentActivity mActivity;

    private IBaseContact.IRequestPermissionCallback mPermissionCallback;

    private ProgressDialog mProgressDialog;

    public BaseHelper(IBaseContact.IBaseView view) {
        super(view);
        mActivity = mBaseView.getAFContext();
    }

    @Override
    public void showToast(String message) {
        if (mActivity == null || TextUtils.isEmpty(message)) {
            return;
        }
        int during = Toast.LENGTH_SHORT;
        if (message.length() > AFConstant.TOAST_LONG_MESSAGE_LENGTH) {
            during = Toast.LENGTH_LONG;
        }
        Toast.makeText(mActivity, message, during).show();
    }

    @Override
    public void showProgressDialog(String message) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(mActivity);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.setCancelable(true);
        }
        mProgressDialog.setMessage(message != null ? message : "");
        mProgressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void startActivity(Class clazz, Bundle bundle) {
        Intent intent = new Intent(mActivity, clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        mActivity.startActivity(intent);
        mActivity.overridePendingTransition(R.anim.af_right_in, 0);
    }

    @Override
    public void startActivityForResult(Class clazz, Bundle bundle, int requestCode) {
        Intent intent = new Intent(mActivity, clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        mActivity.startActivityForResult(intent, requestCode);
        mActivity.overridePendingTransition(R.anim.af_right_in, 0);
    }

    @Override
    public void requestPermission(IBaseContact.IRequestPermissionCallback permissionCallback, String reqStr, String... permissions) {
        mPermissionCallback = permissionCallback;
        if (AFPermissionUtil.hasPermissions(mActivity, permissions)) {
            onPermissionsAllGranted();
        } else {
            AFPermissionUtil.requestPermissions(mActivity, reqStr, AFConstant.PERMISSION_REQUEST_CODE, permissions);
        }
    }

    /**
     * 用户权限处理,
     * 如果全部获取, 则直接过.
     * 如果权限缺失, 则提示Dialog.
     *
     * @param requestCode  请求码
     * @param permissions  权限
     * @param grantResults 结果
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        AFPermissionUtil.onRequestPermissionsResult(requestCode, permissions, grantResults, mActivity);
    }

    public boolean isPermissionRequestBackFromSetting(int requestCode) {
        return requestCode == AFConstant.PERMISSION_OPEN_SETTING_REQUEST_CODE;
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        // 只同意了部分权限
    }

    @Override
    public void onPermissionsAllGranted() {
        if (mPermissionCallback != null) {
            mPermissionCallback.agreeAll();//同意了全部权限的回调
            AFLog.d("request permission all agreed");
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        AFPermissionUtil.checkDeniedPermissionsNeverAskAgain(mActivity,
                mActivity.getString(R.string.af_permission_tips), mActivity.getString(R.string.setting),
                mActivity.getString(R.string.cancel), perms);
    }

    @Override
    public void destroyIt() {
        super.destroyIt();
        mActivity = null;
    }
}
