#### BaseTabPagerView

这一部分封装了Tab＋ViewPager＋Fragment的使用，因为项目中肯定不会少了这样的结构。这里再说明一下单独使用ViewPager的情况，若是单独使用ViewPager并附加某些功能，例如增加无限循环，自动切换，切换动画等效果，控制起来比较复杂，就目前来看[BGABanner-Android](https://github.com/bingoogolapple/BGABanner-Android)这个库对这些单独的功能封装的比较完善，不了解的也可以去学习一下，也相对来说挺好使的。

言归正传，这一部分主要提供Tab＋ViewPager＋Fragment结构的使用，使用方式：

* 继承BaseTabPagerXxx界面；
* 实现getData方法，获取到数据后加入到没mItems中再调用createPager即可；
* 实现getContent方法，根据情况初始化内容Fragment；
* 内容Fragment这个根据情况自己定义即可。

这样就实现了这样的效果，扩展：

1、onTabSelect(int position)，重写这个方法，可以当某个Tab被选中时回调；

2、setTabSpaceEqual，重写这个方法，用来设置每个Tab的LayoutParams的方式，这个TabLayout默认给我们提供了两种，一是WrapContent，而是每个Tab权重相同横向排列；这里我默认使用当Tab数量小于5时，使用权重，反之则使用WrapContent。

以上。