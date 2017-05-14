package net.arvin.afbaselibrary.uis.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

import net.arvin.afbaselibrary.R;
import net.arvin.afbaselibrary.uis.helpers.BaseHelper;
import net.arvin.afbaselibrary.uis.helpers.IBaseContact;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by arvinljw on 17/5/11 15:09
 * Function：公共部分
 * Desc：包含权限申请，加载数据提示框，Toast，View注解，状态栏控制等
 */
public abstract class BaseActivity extends AppCompatActivity implements IBaseContact.IBaseView {
    private BaseHelper mBaseHelper;
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        unbinder = ButterKnife.bind(this);
        mBaseHelper = new BaseHelper(this);
        init(savedInstanceState);
        initViews(savedInstanceState);
    }

    @Override
    public FragmentActivity getAFContext() {
        return this;
    }

    @Override
    public void init(Bundle savedInstanceState) {
    }

    @Override
    public void controlStatusBar() {
    }

    @Override
    public void showToast(String message) {
        mBaseHelper.showToast(message);
    }

    @Override
    public void showProgressDialog(String message) {
        mBaseHelper.showProgressDialog(message);
    }

    @Override
    public void hideProgressDialog() {
        mBaseHelper.hideProgressDialog();
    }

    @Override
    public void startActivity(Class clazz) {
        startActivity(clazz, null);
    }

    @Override
    public void startActivity(Class clazz, Bundle bundle) {
        mBaseHelper.startActivity(clazz, bundle);
    }

    @Override
    public void startActivityForResult(Class clazz, int requestCode) {
        startActivityForResult(clazz, null, requestCode);
    }

    @Override
    public void startActivityForResult(Class clazz, Bundle bundle, int requestCode) {
        mBaseHelper.startActivityForResult(clazz, bundle, requestCode);
    }

    @Override
    public void requestPermission(IBaseContact.IRequestPermissionCallback permissionCallback, String reqStr, String... permissions) {
        mBaseHelper.requestPermission(permissionCallback, reqStr, permissions);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mBaseHelper.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        mBaseHelper.onPermissionsGranted(requestCode, perms);
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        mBaseHelper.onPermissionsDenied(requestCode, perms);
    }

    @Override
    public void onPermissionsAllGranted() {
        mBaseHelper.onPermissionsAllGranted();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mBaseHelper.isPermissionRequestBackFromSetting(requestCode)) {
            permissionRequestBackFromSetting();
        }
    }

    /**
     * 这个只处理权限请求从设置界面返回，这时候应该再次调用请求权限部分的方法
     */
    protected void permissionRequestBackFromSetting() {
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(0, R.anim.af_right_out);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBaseHelper.destroyIt();
        unbinder.unbind();
    }
}
