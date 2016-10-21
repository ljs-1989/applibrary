package com.liujs.demo.okhttpdemo.cache;

import com.liujs.demo.R;
import com.liujs.demo.okhttpdemo.base.BaseActivity;
import com.liujs.demo.okhttpdemo.utils.GlideImageLoader;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.lzy.ninegrid.NineGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class CacheDemoActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.viewPager)
    ViewPager viewPager;
    @Bind(R.id.tab) TabLayout tab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cache_demo);
        initToolBar(toolbar, true, "强大的缓存");

        NineGridView.setImageLoader(new GlideImageLoader());

        ArrayList<NewsTabFragment> fragments = new ArrayList<>();
        NewsTabFragment fragment1 = NewsTabFragment.newInstance();
        fragment1.setTitle("国内最新");
        fragments.add(fragment1);
        NewsTabFragment fragment2 = NewsTabFragment.newInstance();
        fragment2.setTitle("游戏焦点");
        fragments.add(fragment2);
        NewsTabFragment fragment3 = NewsTabFragment.newInstance();
        fragment3.setTitle("娱乐焦点");
        fragments.add(fragment3);
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(fragments.size());
        tab.setupWithViewPager(viewPager);
    }


    public class MyPagerAdapter extends FragmentPagerAdapter {

        private List<NewsTabFragment> fragments;

        public MyPagerAdapter(FragmentManager fm, List<NewsTabFragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragments.get(position).getTitle();
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}