#### BaseSwipeView

右滑返回，该类只支持Activity，通过使用[SwipeBackLayout](https://github.com/ikew0ng/SwipeBackLayout)（版本1.0.0）库实现该功能。

使用方式：

* 继承BaseSwipeActivity，修改改Activity的主题为背景透明
* 可通过setSwipeBackEnable方法设置是否禁止该功能

其中若是在app中要使用右滑返回，那么每个Activity的主题记得要设置为背景透明（栈底界面除外），具体例子可参照Sample中theme的使用。

若是想了解更多可去看看该库是如何实现。