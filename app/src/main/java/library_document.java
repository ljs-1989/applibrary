/*
1、网络模块
 来自github开源项目：https://github.com/jeasonlzy/okhttp-OkGo，去掉了文件上传下载管理功能模块，因为这个功能在一般的app
 中应用的少。具体使用请看Demo。
  使用步骤：
     1）在gradle里面添加包： compile 'com.lzy.net:okhttputils:+'
     2）在自定义application 中做初始化 OkHttpUtils.init(this);
          如：
            HttpHeaders headers = new HttpHeaders();
                    headers.put("commonHeaderKey1", "commonHeaderValue1");    //所有的 header 都 不支持 中文
                    headers.put("commonHeaderKey2", "commonHeaderValue2");
                    HttpParams params = new HttpParams();
                    params.put("commonParamsKey1", "commonParamsValue1");     //所有的 params 都 支持 中文
                    params.put("commonParamsKey2", "这里支持中文参数");

                    //必须调用初始化
                    OkHttpUtils.init(this);
                    //以下都不是必须的，根据需要自行选择
                    OkHttpUtils.getInstance()//
                            .debug("OkHttpUtils")                                              //是否打开调试
                            .setConnectTimeout(OkHttpUtils.DEFAULT_MILLISECONDS)               //全局的连接超时时间
                            .setReadTimeOut(OkHttpUtils.DEFAULT_MILLISECONDS)                  //全局的读取超时时间
                            .setWriteTimeOut(OkHttpUtils.DEFAULT_MILLISECONDS)                 //全局的写入超时时间
                          //.setCookieStore(new MemoryCookieStore())                           //cookie使用内存缓存（app退出后，cookie消失）
                          //.setCookieStore(new PersistentCookieStore())                       //cookie持久化存储，如果cookie不过期，则一直有效
                            .addCommonHeaders(headers)                                         //设置全局公共头
                            .addCommonParams(params);                                          //设置全局公共参数

     3）具体 get/post/head/delete/https等请求查看github地址和demo中的使用。
     4）取消请求
        每个请求前都设置了一个参数tag，取消则通过OkHttpUtils.cancel(tag)执行。
        例如：在Activity中，当Activity销毁取消请求，可以在onDestory里面统一取消。

            @Override
            protected void onDestroy() {
                super.onDestroy();

                //根据 Tag 取消请求
                OkHttpUtils.getInstance().cancelTag(this);
            }


 2、上下拉刷新模块
   来自开源项目：https://github.com/bingoogolapple/BGARefreshLayout-Android，具体使用请看Demo。
   提供三种下拉刷新实现样式，见包com.liujs.library.view.refresh,刷新效果和使用方法见Demo。
    使用步骤：
       1）继承BGARefreshLayout，实现自己想要的刷新样式，如demo中的com.liujs.demo.view.PullRefreshView
       2)在layout文件中，用PullRefreshView包含刷新的View，如listView、RecycleView、GridView、ViewPager等。
         如：
         <com.liujs.demo.view.PullRefreshView
             android:id="@+id/rl_modulename_refresh"
             android:layout_width="match_parent"
             android:layout_height="match_parent">
         <ListView
             android:id="@+id/test_listview"
             android:layout_width="match_parent"
             android:layout_height="0dp"
             android:layout_weight="1">

         </ListView>
         </com.liujs.demo.view.PullRefreshView>
         3）在Activity实现BGARefreshLayout.BGARefreshLayoutDelegate接口


 3、数据持久化模块
    提供ContentProvider、Database、本地文件存储、系统提供的SharedPreferences存储四种数据持久化实现方式。
    所在包：com.liujs.library.data
    提供类：1）抽象类BaseContentProvider ，提供数据库创建功能。
               实现步骤（参照Demo）：
                              1、继承BaseContentProvider，实现抽象类方法及ContentProvider增删改查方法。
                              2、实现抽象方法
                              3、实现数据增删改查方法.

            2）抽象类BaseDbOperator ,提供数据库操作模板。
            3）实现类FileOperator,实现文件删除（delete）、保存（saveFile）、读取（readFile）功能。
            4）实现类PreferenceOperator，实现了String、int、long、float、boolean五种数据类型的get、put读写方法。


  4、图片裁圆描边模块
     来自github开源项目：https://github.com/hdodenhof/CircleImageView
     实现效果见demo
     使用方法：1）添加包
                 dependencies {
                     ...
                     compile 'de.hdodenhof:circleimageview:+'
                 }
                 2）<de.hdodenhof.circleimageview.CircleImageView
                       xmlns:app="http://schemas.android.com/apk/res-auto"
                       android:id="@+id/profile_image"
                       android:layout_width="96dp"
                       android:layout_height="96dp"
                       android:src="@drawable/profile"
                       app:civ_border_width="2dp"
                       app:civ_border_color="#FF000000"/>


  5、常用方法工具
     工程包：com.liujs.library.utils里罗列了我们项目中经常会用到的一些方法如：
     1）加密解密:DESUtil/MD5Util
     2）登录注册经常用到的校验:MatcherUtil
     3）对象序列化与反序列化:ObjectFileUtil
     4）日期转化：DataUtil
     5）文件相关：FileUtil
     6）dip与px格式转化：DipAndPxUtil
     7）图片的读写、bitmap与drawable的转化、图片缩放：BitmapUtil

    6、actionBar自定义
       1）继承BaseActivity或者BaseFragment
           两个类封装了实例化actionBar的方法，以及修改标题、logo、返回按钮显隐、
           右边按钮的方法，方便子类调用。
       2）在子类的layout中<include layout="@layout/actionbar_layout"/>

       3）子类调用initActionBar()方法进行实例化

*/
