#### BaseRefreshView

首先这里的下拉刷新使用的是[android-Ultra-Pull-To-Refresh](https://github.com/liaohuqiu/android-Ultra-Pull-To-Refresh)这个库（版本是1.0.11），我把代码提取到了项目中来，因为我认为这样更加便于调整和使用。

这里说到的下拉刷新，根据这个库的能力可以知道是基本支持所有系统提供的View的，如果是自定义的话，我们可以重写checkCanDoRefresh方法，告诉这个组件什么时候能刷新，这里就不过多的介绍这个库的一些实用方式了，不了解的可以去看看这个库的demo，想深入了解的可以去看看这篇文章[android-Ultra-Pull-To-Refresh 源码解析](http://p.codekk.com/blogs/detail/5595d64dd6459ae79349975a)。

回到正题，**这个界面的使用同样和BaseHeaderView一样，都预定义了这个组件的id，我们只需要在contentView中放入这个组件，并且保证id和预定义的一致即可**，当然是用规则需要遵循这个组件的规则，布局的时候只能有一个子View，因为Header我们在代码中动态添加了。如果你不信，估计就会遇到这个异常：

```
if (childCount > 2) {
    throw new IllegalStateException("PtrFrameLayout can only contains 2 children");
} 
```

**1、autoRefresh**

如果调用便可自动回调onRefreshBegin方法，同时会把Header显示出来;

**2、onRefresh**

这个方法会要求我们重写，我们就可以在这个方法中刷新数据;

**3、refreshComplete**

数据加载完成后，便可调用这个方法关闭header。

*注：若是使用到RecyclerView相关的刷新则推荐使用BaseRefreshLoadView，因为能减少你自己去设置RecyclerView的过程，具体怎么操作在后文中会有介绍*