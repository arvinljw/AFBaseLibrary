# AFBaseLibrary

## 序

这个库，其实我之前已经有封装过一些了，但是感觉还不规范，还差了些内容，所以这次特意从开一个项目来专门做这个事情。毕竟我认为开发和搞艺术一样，我们也是追求完美的。

## 摘要

首先这个库的意图在于：能够快速高效的开发；里边会包含到大部分在一个应用软件中用到的功能点的封装，作为这样一个库，这里封装的每一个功能点都应该需要做到便于扩展。下面介绍一下这个库中包含的功能点：

* 界面（常用的Activity和Fragment）
* Adapter
* 提示相关（Dialog、Toast、PopUpWindow等）
* 网络模块
* 常用工具类继承
* 常用的三方包引入

之后会展开对每一个功能点进行详细的说明，这几个模块是我自己根据项目的积累所认为的一些有必要的封装。

*注：建议以Module的方式引入，这样方便根据自己项目情况进行调整*

## 例子
[Sample.apk]()

### 界面

这部分包含了我在项目中所遇到的一些常见的界面结构的整理，其中Activity和Fragment都会有相匹配的功能，即Activity有的功能，Fragment也会包含，所以下文就以View作为界面的统称，有如下几个部分：

* BaseView（公共部分，包含权限申请，加载数据提示框，Toast，View注解，状态栏控制等）
* BaseSwipeView（右滑返回）
* BaseHeaderView（带标题）
* BaseRefreshView（只有下拉刷新）
* BaseRefreshLoadingView（下拉刷新和上拉加载）
* BasePagerView（ViewPager使用）
* BaseTabPagerView（TabLayout和ViewPager的结合）
* BaseHtmlView（WebView的封装）

每个界面，采用了面向接口编程，由于既有Activity又有Fragment，为了避免写的实现代码，所以我又抽取了对应的Helper去封装，而Helper提供的一些和界面类似的方法，只是为了提供一个default的值，而界面中的方法是为了让子类继承后可重写，方便修改。

*注：这种设计方法是我自己想的，感觉还差点什么，因为细心的同学能很快发现其实，Activity和Fragment中的代码几乎是一样的，只是继承的对象不同而已，但是因为目前还不知如何去更好的处理这种情况，如果哪位仁兄有高见可随时联系我，或者提issue。*

#### [BaseView](readme/BaseView.md)

#### [BaseHeaderView](readme/BaseHeaderView.md)

#### [BaseRefreshView](readme/BaseRefreshView.md)

#### [BaseRefreshLoadingView](readme/BaseRefreshLoadingView.md)

#### [BasePagerView](readme/BasePagerView.md)

#### [BaseTabPagerView](readme/BaseTabPagerView.md)

#### [BaseHtmlView](readme/BaseHtmlView.md)








