package net.arvin.afbaselibrary.uis.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import net.arvin.afbaselibrary.R;
import net.arvin.afbaselibrary.data.AFConstant;
import net.arvin.afbaselibrary.nets.BaseNetService;
import net.arvin.afbaselibrary.utils.AFLog;
import net.arvin.afbaselibrary.utils.AFPermissionUtil;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by arvinljw on 17/5/11 16:53
 * Function：公共部分
 * Desc：包含权限申请，加载数据提示框，Toast，View注解，状态栏控制等
 */
public abstract class BaseFragment extends Fragment implements AFPermissionUtil.PermissionCallbacks {
    protected View root;

    private Unbinder unbinder;
    private ProgressDialog progressDialog;
    private AFPermissionUtil.IRequestPermissionCallback mPermissionCallback;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = LayoutInflater.from(getAFContext()).inflate(getContentView(), container, false);
        unbinder = ButterKnife.bind(root);
        init(savedInstanceState);
        initViews(savedInstanceState);
        return root;
    }

    public FragmentActivity getAFContext() {
        return getActivity();
    }

    public void init(Bundle savedInstanceState) {
    }

    public void controlStatusBar() {
    }

    public void showToast(String message) {
        if (getAFContext() == null || TextUtils.isEmpty(message)) {
            return;
        }
        int during = Toast.LENGTH_SHORT;
        if (message.length() > AFConstant.TOAST_LONG_MESSAGE_LENGTH) {
            during = Toast.LENGTH_LONG;
        }
        Toast.makeText(getAFContext(), message, during).show();
    }

    public void showProgressDialog(String message) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getAFContext());
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(true);
        }
        progressDialog.setMessage(message != null ? message : "");
        progressDialog.show();
    }

    public void hideProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    protected void startActivity(Class clazz) {
        startActivity(clazz, null);
    }

    protected void startActivity(Class clazz, Bundle bundle) {
        Intent intent = new Intent(getAFContext(), clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        getAFContext().overridePendingTransition(R.anim.af_right_in, 0);
    }

    protected void startActivityForResult(int requestCode, Class clazz) {
        startActivityForResult(requestCode, clazz, null);
    }

    protected void startActivityForResult(int requestCode, Class clazz, Bundle bundle) {
        Intent intent = new Intent(getAFContext(), clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
        getAFContext().overridePendingTransition(R.anim.af_right_in, 0);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        AFPermissionUtil.onRequestPermissionsResult(requestCode, permissions, grantResults, getAFContext());
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        AFPermissionUtil.checkDeniedPermissionsNeverAskAgain(getAFContext(),
                getAFContext().getString(R.string.af_permission_tips), getAFContext().getString(R.string.setting),
                getAFContext().getString(R.string.cancel), perms);
    }

    @Override
    public void onPermissionsAllGranted() {
        if (mPermissionCallback != null) {
            mPermissionCallback.agreeAll();//同意了全部权限的回调
            AFLog.w("request permission all agreed");
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AFConstant.PERMISSION_OPEN_SETTING_REQUEST_CODE) {
            permissionRequestBackFromSetting();
        }
    }

    /**
     * 这个只处理权限请求从设置界面返回，这时候应该再次调用请求权限部分的方法
     */
    protected void permissionRequestBackFromSetting() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        BaseNetService.onDestroy(this);
    }

    protected abstract int getContentView();

    protected abstract void initViews(Bundle savedInstanceState);

}
