#### BaseRefreshLoadingView

这一部分包含的功能是列表的下拉刷新，上拉加载；由于涉及到上拉加载所以必定是列表，而列表通常都是RecyclerView，所以这里就没有单独抽取加载更多。

**适用场景：**

* 正常的列表包含上拉下拉；
* 列表只支持下拉刷新；
* 列表只支持上拉加载；

默认这个界面是加载的垂直线性的列表，而Adapter才用的是[BaseRecyclerViewAdapterHelper](https://github.com/CymChad/BaseRecyclerViewAdapterHelper)这个库，选择他的原因也是因为他功能强大，不了解的也可以去看看它的wiki，非常详细。

**使用方式：(参照HeaderFragment)**

* 继承相应的BaseRefreshLoadingXxx类；
* 若不使用垂直线性列表，则需要根据自己的情况重写setLayoutManager方法和addDivider，因为不同的布局，divider的添加方式是不一样的；
* 重写getAdapter方法，为列表提供adapter，可通过BaseQuickAdapter库去学习如何使用，学会后保证爱不释手；
* 重写loadData方法，加载数据；
* refreshLoadComplete，当数据加载完后，需要调用这个方法关闭刷新或加载，其参数为数据加载是否成功，这里根据我内部实现，分页请求的数据时，**若是请求失败或请求回来数据大小0，则是false；如果有数据，则为true；**

最后一点虽然看起来简单，但是比较关键，灵活的使用就能满足上文中提到的场景，因为加载数据分为分页和不分页，如果不分页，那么我们可以直接refreshLoadComplete(false)即可不再加载下一页；如果分页，就根据刚才说的规则去定义是否成功即可。

如果是我们不想支持下拉刷新的话，可以直接在contentView中不加入PtrFrameLayout，那么就只支持加载更多了。

如果都不想支持，还要使用的话，就是将前边提到的两种方式合并，即可。