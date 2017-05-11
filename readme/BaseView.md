#### BaseView

在Sample中以MainActivity为例，因为一般这类界面，处于栈底，即退出后就是手机桌面，所以有两点需要注意：

* 重写onBackPressed方法，去掉关闭动画
* 若开启了右滑关闭功能，那么需要为Application的theme设置为窗口半透明，然后再为栈底的Activity的theme设置为窗口不透明；

然后对于使用，Activity与Fragment就一致了。

**1、权限申请**

```
private void needCameraPermission() {
    String perms[] = {Manifest.permission.CAMERA};
    requestPermission(new IBaseContact.IRequestPermissionCallback() {
        @Override
        public void agreeAll() {
            //do something
        }
    }, "该应用需要使用相机，请允许", perms);
}

@Override
protected void permissionRequestBackFromSetting() {
    needCameraPermission();
}
```

使用方式很简单分为两步：

* 主要是调用requestPermission方法，然后在回调中处理需要用到权限的操作
* 重写permissionRequestBackFromSetting方法，再次执行请求权限的方法即可

Fragment中一样这样使用，例子中没有体现但是经过了测试。

**2、各类提示**

a、加载数据提示框

```
showProgressDialog("加载中");//显示

hideProgressDialog();//隐藏
```

b、Toast

```
showToast("已获得所有申请权限");
//会自动根据长度调整显示时间，默认以大于10个字就会显示Toast.LENGTH_LONG的时间
```

c、状态栏控制

*暂缺*

d、注解

在继承自BaseXxx的界面都能直接使用ButterKnife的所有注解

