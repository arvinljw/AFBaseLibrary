# AFBaseLibrary

## 序

这个库，其实我之前已经有封装过一些了，但是感觉还不规范，还差了些内容，所以这次特意从开一个项目来专门做这个事情。毕竟我认为开发和搞艺术一样，我们也是追求完美的。

## 摘要

首先这个库的意图在于：能够快速高效的开发；里边会包含到大部分在一个应用软件中用到的功能点的封装，作为这样一个库，这里封装的每一个功能点都应该需要做到便于扩展。下面介绍一下这个库中包含的功能点：

* 界面（常用的Activity和Fragment）
* 提示相关（Dialog、Toast、PopUpWindow等）
* 网络模块
* 常用工具类集成
* 常用的三方包引入

之后会展开对每一个功能点进行详细的说明，这几个模块是我自己根据项目的积累所认为的一些有必要的封装。

*注：建议以Module的方式引入，这样方便根据自己项目情况进行调整。*

*这里没有太多的使用指南，更多是在Sample代码中体现。*

*若是有疑问的可随时提问，看到后就会及时处理*

## 例子
[Sample.apk]()

## 实现与介绍

### 界面

这部分包含了我在项目中所遇到的一些常见的界面结构的整理，其中Activity和Fragment都会有相匹配的功能，即Activity有的功能，Fragment也会包含，所以下文就以View作为界面的统称，有如下几个部分：

* [BaseView](readme/BaseView.md)（公共部分，包含权限申请，加载数据提示框，Toast，View注解，状态栏控制等）
* [BaseSwipeView](readme/BaseSwipeView.md)（右滑返回）
* [BaseHeaderView](readme/BaseHeaderView.md)（带标题）
* [BaseRefreshView](readme/BaseRefreshView.md)（只有下拉刷新）
* [BaseRefreshLoadingView](readme/BaseRefreshLoadingView.md)（下拉刷新和上拉加载）
* [BaseTabPagerView](readme/BaseTabPagerView.md)（TabLayout和ViewPager的结合）
* [BaseHtmlView](readme/BaseHtmlView.md)（WebView的封装）

每个界面，采用了面向接口编程，由于既有Activity又有Fragment，为了避免写的实现代码，所以我又抽取了对应的Helper去封装，而Helper提供的一些和界面类似的方法，只是为了提供一个default的值，而界面中的方法是为了让子类继承后可重写，方便修改。

*注：这种设计方法是我自己想的，感觉还差点什么，因为细心的同学能很快发现其实，Activity和Fragment中的代码几乎是一样的，只是继承的对象不同而已，但是因为目前还不知如何去更好的处理这种情况，如果哪位仁兄有高见可随时联系我，或者提issue。*


### 网络模块

在项目中网络请求肯定是必不可少的，目前来看Retrofit的使用应该是比较大众的，使用熟悉之后会觉得方便很多，目前这个模块封装的功能包括：

* 通过泛型初始化一个默认的请求接口，若想对请求接口分模块书写，也方便扩展；
* 支持https，只需传入证书名称；
* BaseNetService与BaseNet同时被继承使用，可解决请求可能产生的内存泄漏；

可扩展的功能（因为这些部分都是需要与后台写作的所以没法写固定了）：

* 支持重写convert，自定义解析；
* 错误信息可通过继承ApiCallback统一处理。

具体例子可看Sample中的CategoryFragment，每个类实现起来都很简单，很方便，其中自定义Convert解析与后台约定好后，基本是写一次就可以每次使用了。

*注：其中https请求可自行调试，Sample中暂未例子。*

### 提示相关

#### BaseDialog

在Android中自定义Dialog还是比较常见的，而我们也是有好些代码是会经常重复写的，所以这里我也简单的封装了一下：

* 宽和高的重新定义，可重写getWidth和getHeight方法改变默认值；
* 定义了从底部弹出的动画；
* 定义了一些常见的Dialog的style；

有了这些，我们就能更容易的实现一个Dialog，当然如果没有特别要求的Dialog使用v7包下的AlertDialog也是挺好的。

其中在Sample中自定义了一个简单的dialog（TipsDialog），简单介绍如何使用。

#### BasePopupWindow

这里简单的封装了一下PopupWindow，直接定义好了一些常见的配置，调整了位置的控制方式，提供了一个灵活的在View的上下（2种），或者是在屏幕的某个方位（上中下x左中右的9种组合），共计11种组合，也支持相应的偏移量。

其中X方向的偏移量都是，大于0则向右偏，小于0向左偏；
Y方向的偏移量通常都是，大于0向下，小于0向上；

**使用方式：**

* 继承BasePopupWindow，例如为PopA；
* 重写抽象方法，其中getContentViewId为内容布局，getContentDefaultWidth为内容的宽度，需要设置为具体数值，构造方法无需修改。
* 在界面中显示，创建一个PopA实例，其中需要传入context和一个在界面中最上方添加的宽或高都为0的View（用于保证PopupWindow的位置），并调用自定义的11种show方法；

这样是用起来就很简单了，其中还包含了像Dialog一样都会有阴影，更多细节可以看看Sample中的TipsPopupWindow如何使用即可。

*注：因为设置了阴影，所以弹出动画的方法会有些问题，所以不建议使用弹出动画*

#### ProgressBar与ProgressDialog

待实现...

### 常用工具类

#### AFGlideUtil

通过封装Glide，实现图片加载，包含：

* 加载图片
* 加载高斯模糊的图片
* 加载圆角图片
* 加载圆形图片

#### AFSizeUtil 

这个是用来获取屏幕大小以及各类尺寸之间的转换所用

#### AFLog

这个是用于打印日志，简单封装Logger日志库，可在Application中设置是否为Debug，不是Debug时，就能不打印

#### WeakHandler

避免内存泄露的Handler，使用时需要实现IWeakHandler接口，用于处理消息回调

*注：工具类待续...*

### 常用的三方包

引入的依赖包包：

```
def supportVersion = "25.3.1"
compile 'com.android.support:appcompat-v7:'.concat(supportVersion)
compile 'com.android.support:recyclerview-v7:'.concat(supportVersion)
compile 'com.android.support:support-v4:'.concat(supportVersion)
compile 'com.android.support:percent:'.concat(supportVersion)
compile 'com.android.support:design:'.concat(supportVersion)
//retrofit+rxjava
compile 'com.squareup.retrofit2:retrofit:2.2.0'
compile 'com.squareup.retrofit2:converter-gson:2.2.0'
compile 'com.squareup.retrofit2:adapter-rxjava:2.2.0'
compile 'io.reactivex:rxjava:1.2.1'
compile 'io.reactivex:rxandroid:1.2.1'
compile 'com.google.code.gson:gson:2.7'
//图片加载
compile 'com.github.bumptech.glide:okhttp3-integration:1.4.0@aar'
compile 'com.github.bumptech.glide:glide:3.7.0'
//页面事件交互
compile 'org.greenrobot:eventbus:3.0.0'
//view注解
compile 'com.jakewharton:butterknife:8.4.0'
compile 'com.jakewharton:butterknife-compiler:8.4.0'
//日志
compile 'com.orhanobut:logger:1.15'
//BGABanner
compile 'cn.bingoogolapple:bga-banner:2.1.7@aar'
```

### 代码混淆

若是要使用代码混淆，则可以在[proguard-rules.pro](afbaselibrary/proguard-rules.pro)中查看，除了第三方包的混淆外，混淆所需的基本的配置也在里边。

## 感谢

本库中引用了一些第三方包（或依赖或下载的源码），在此表示感谢！！

有任何问题或者建议都可以联系我～

## License

```
   Copyright 2017 arvinljw

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```














