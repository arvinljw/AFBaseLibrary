package net.arvin.sample.uis.activities;

import android.Manifest;
import android.os.Bundle;

import net.arvin.afbaselibrary.uis.activities.BaseActivity;
import net.arvin.afbaselibrary.uis.helpers.IBaseContact;
import net.arvin.sample.R;
import net.arvin.sample.uis.fragments.HeaderFragment;

/**
 * Created by arvinljw on 17/5/10 12:51
 * Function：首页
 * Desc：首页，这个界面退出就是桌面即在栈底，所以需要重写onBackPressed方法，去掉向右关闭的动画
 * 在style方面若是使用了右滑关闭功能，那么应该想这个例子一样，Application的theme设为窗口透明，栈底界面的theme应设为不透明
 */
public class MainActivity extends BaseActivity {

    @Override
    public int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        getSupportFragmentManager().beginTransaction().add(R.id.layout_content,new HeaderFragment()).commit();
        needCameraPermission();
    }

    private void needCameraPermission() {
        String perms[] = {Manifest.permission.CAMERA};
        requestPermission(new IBaseContact.IRequestPermissionCallback() {
            @Override
            public void agreeAll() {
                //do something
                showToast("已获得所有申请权限");
            }
        }, "该应用需要使用相机，请允许", perms);
    }

    @Override
    protected void permissionRequestBackFromSetting() {
        needCameraPermission();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
