package com.liujs.demo.okhttpdemo.okhttputils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.liujs.demo.R;
import com.liujs.demo.okhttpdemo.base.BaseActivity;
import com.liujs.demo.okhttpdemo.base.BaseRecyclerAdapter;
import com.liujs.demo.okhttpdemo.base.DividerItemDecoration;
import com.liujs.demo.okhttpdemo.cache.CacheDemoActivity;
import java.util.ArrayList;
import butterknife.Bind;
import butterknife.OnClick;

public class OkHttpUtilsActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    private ArrayList<OkHttpModel> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.okhttp_layout);
        initToolBar(toolbar, true, "");

        initData();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(new MainAdapter(this));
    }

    @OnClick(R.id.fab)
    public void fab(View view) {
     //   WebActivity.runActivity(this, "我的Github,欢迎star", "https://github.com/jeasonlzy0216");
    }

    private void initData() {
        items = new ArrayList<>();
        OkHttpModel model1 = new OkHttpModel();
        model1.title = "";
        model1.des = "下面是OkHttpUtils包的使用方法";
        model1.type = 1;
        items.add(model1);

        OkHttpModel model2 = new OkHttpModel();
        model2.title = "基本功能(OkHttpUtils)";
        model2.des = "1.GET，HEAD，OPTIONS，POST，PUT，DELETE 请求方法演示\n" +
                "2.请求服务器返回bitmap对象\n" +
                "3.支持https请求\n" +
                "4.支持同步请求\n" +
                "5.支持301重定向";
        model2.type = 0;
        items.add(model2);

        OkHttpModel model3 = new OkHttpModel();
        model3.title = "自动解析Json对象";
        model3.des = "1.自动解析JavaBean对象\n" + //
                "2.自动解析List<JavaBean>集合对象";
        model3.type = 0;
        items.add(model3);

        OkHttpModel model4 = new OkHttpModel();
        model4.title = "文件下载";
        model4.des = "1.支持大文件或小文件下载，无论多大文件都不会发生OOM\n" +
                "2.支持监听下载进度和下载网速\n" +
                "3.支持自定义下载目录和下载文件名";
        model4.type = 0;
        items.add(model4);

        OkHttpModel model5 = new OkHttpModel();
        model5.title = "文件上传";
        model5.des = "1.支持上传单个文件\n" +
                "2.支持同时上传多个文件\n" +
                "3.支持多个文件多个参数同时上传\n" +
                "4.支持大文件上传,无论多大都不会发生OOM\n" +
                "5.支持监听上传进度和上传网速";
        model5.type = 0;
        items.add(model5);

        OkHttpModel model6 = new OkHttpModel();
        model6.title = "强大的缓存示例 -- 先联网获取数据,然后断开网络再进试试";
        model6.des = "1.OkHttpUtils的强大的缓存功能,让你代码无需关心数据来源,专注于业务逻辑的实现\n" +
                "2.共有五种缓存模式满足你各种使用场景\n" +
                "3.支持自定义缓存过期时间";
        model6.type = 0;
        items.add(model6);

    }

    @Override
    protected boolean translucentStatusBar() {
        return true;
    }

    private class MainAdapter extends BaseRecyclerAdapter<OkHttpModel, ViewHolder> {

        public MainAdapter(Context context) {
            super(context, items);
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view;
            if (viewType == 0) {
                view = inflater.inflate(R.layout.item_main_list, parent, false);
            } else {
                view = inflater.inflate(R.layout.item_main_type, parent, false);
            }
            return new ViewHolder(view);
        }

        @Override
        public int getItemViewType(int position) {
            return items.get(position).type;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.bind(position, items.get(position));
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title;
        TextView des;
        TextView divider;
        private int position;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            des = (TextView) itemView.findViewById(R.id.des);
            divider = (TextView) itemView.findViewById(R.id.divider);
        }

        public void bind(int position, OkHttpModel model) {
            this.position = position;
            if (model.type == 0) {
                title.setText(model.title);
                des.setText(model.des);
            } else {
                divider.setText(model.des);
            }
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (position == 1) startActivity(new Intent(OkHttpUtilsActivity.this, OkHttpActivity.class));
            if (position == 2) startActivity(new Intent(OkHttpUtilsActivity.this, JsonRequestActivity.class));
            if (position == 3) startActivity(new Intent(OkHttpUtilsActivity.this, FileDownloadActivity.class));
            if (position == 4) startActivity(new Intent(OkHttpUtilsActivity.this, FormUploadActivity.class));
            if (position == 5) startActivity(new Intent(OkHttpUtilsActivity.this, CacheDemoActivity.class));

        }
    }

    private class OkHttpModel {
        public String title;
        public String des;
        public int type;
    }
}