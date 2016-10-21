package com.liujs.demo.testactivity;

import com.liujs.demo.R;
import com.liujs.demo.view.PullRefreshView;
import com.liujs.library.base.BaseActivity;
import com.liujs.library.view.refresh.BGARefreshLayout;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class TestRefreshActivity extends BaseActivity implements BGARefreshLayout.BGARefreshLayoutDelegate{
   private PullRefreshView mPullRefreshView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh);
        ListView mListView = (ListView)this.findViewById(R.id.test_listview);
        List<String> listData = new ArrayList<String>();
        for(int i=0;i<20;i++){
            listData.add("item:"+i);
        }
        mListView.setAdapter(new ArrayAdapter<>(this,R.layout.textview_list_item,R.id.text_item,listData));
          int refresh_style =  getIntent().getIntExtra("REFRESH_STYLE",0);

         mPullRefreshView = (PullRefreshView)this.findViewById(R.id.rl_modulename_refresh);
        if(refresh_style!=0){
            mPullRefreshView.initRefreshStyle(this.getApplicationContext(),refresh_style); ;
        }
         mPullRefreshView.setDelegate(this);
        //设置是否需要下拉刷新
        //mPullRefreshView.setPullDownRefreshEnable(true);
        //设置是否显示上拉加载更多的View，不影响加载更多方法的调用
        //mPullRefreshView.setIsShowLoadingMoreView(false);

        //初始化头部
        initActionBar();
        setTitle("刷新演示");
        setNextText("测试sd");
    }

    // 通过代码方式控制进入正在刷新状态。应用场景：某些应用在activity的onStart方法中调用，自动进入正在刷新状态获取最新数据
    public void beginRefreshing() {
        mPullRefreshView.beginRefreshing();
    }

    // 通过代码方式控制进入加载更多状态
    public void beginLoadingMore() {
        mPullRefreshView.beginLoadingMore();
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {

        mPullRefreshView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPullRefreshView.endRefreshing();
            }
        }, 3000);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        // 在这里加载更多数据，或者更具产品需求实现上拉刷新也可以
       boolean mIsNetworkEnabled = true;
        if (mIsNetworkEnabled) {
            // 如果网络可用，则异步加载网络数据，并返回true，显示正在加载更多
            new AsyncTask<Void, Void, Void>() {

                @Override
                protected Void doInBackground(Void... params) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    // 加载完毕后在UI线程结束加载更多
                    mPullRefreshView.endLoadingMore();
                  //  mAdapter.addDatas(DataEngine.loadMoreData());
                }
        }.execute();

            return true;
        } else {
            // 网络不可用，返回false，不显示正在加载更多
            Toast.makeText(this, "网络不可用", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
