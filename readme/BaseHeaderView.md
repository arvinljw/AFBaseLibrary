#### BaseHeaderView

这一部分也只是根据自己项目的经验，标题一般就只有返回按钮和标题，更多的内容也不方便封装，而自行情况扩展也比较容易，故这个模块还是比较简单的。其中BaseHeaderActivity继承自BaseSwipeActivity，所以带有右滑返回的功能，可禁止。

*对于之前的版本完全没有任何影响。*

在Lib中预定义了标题和返回按钮的id，在BaseHeaderHelper中，若是想要改变这个id，也可重写获取这两个id的方法；

```
int getTitleViewId();
int getBackViewId();
```

而一般为了快速开发，我习惯根据项目情况定义一个layout_header.xml，方便用到的地方直接引入，也比较简单，具体内容如下：

```
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
                android:layout_width="match_parent"
                android:layout_height="@dimen/spacing_app_bar"
                android:background="@color/colorPrimary">

    <ImageView
        android:id="@+id/pre_v_back"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:background="@drawable/af_selector_tab"
        android:padding="@dimen/spacing_default"
        android:src="@drawable/ic_back_white"/>

    <TextView
        android:id="@+id/pre_tv_title"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerInParent="true"
        android:ellipsize="end"
        android:maxLength="11"
        android:text="@string/app_name"
        android:textColor="@color/white_normal"
        android:textSize="@dimen/txt_title"/>

</RelativeLayout>
```

而这其中，样式什么都不重要，主要是id和标题的类型对应上就ok了；

这其中还包含了是否显示标题和返回按钮的方法，默认是显示的，重写返回false即可不显示；

```
boolean isShowBackView();
boolean isShowTitleView();
```

返回键以及标题的点击也是有回调的：

```
void onTitleViewClicked(View view);
void onBackViewClicked(View view);
```

默认返回键点了就是关闭Activity，在Fragment也是。