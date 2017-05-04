package com.materialdesigndemo.android_materialdesign_view.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.materialdesigndemo.android_materialdesign_view.R;
import com.materialdesigndemo.android_materialdesign_view.adapter.ContentAdapter;
import com.materialdesigndemo.android_materialdesign_view.base.DividerItemDecoration;
import com.materialdesigndemo.android_materialdesign_view.base.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

import static com.materialdesigndemo.android_materialdesign_view.R.id.toolbar;

/**
 * RecycleView+CardView
 */
public class RecycleViewActivity extends AppCompatActivity {
    private String TAG = getClass().getSimpleName();

/*=========== 控件相关 ==========*/
    /**
     * 返回键
     */
    private RelativeLayout main;
    /**
     * 菜单栏
     */
    private Toolbar mToolbar;
    /**
     * 标题
     */
    private TextView mTvTitle;
    /**
     * 返回键
     */
    private LinearLayout mBack;
    /**
     * 列表
     */
    private RecyclerView mRvList;

    private ContentAdapter mAdapter;

    /*=========== 数据相关 ==========*/
    private List<String> mList = new ArrayList<String>();

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);
        main = (RelativeLayout) findViewById(R.id.main);
        //使用自定义的标题和返回键
        mToolbar = (Toolbar) findViewById(toolbar);
        mTvTitle = (TextView) mToolbar.findViewById(R.id.tv_title);
        mBack = (LinearLayout) mToolbar.findViewById(R.id.linear_back);

        mToolbar.setTitle("");
        mTvTitle.setText("浏览");
        //设置返回按钮
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // 要想让Toolbar本身的inflateMenu生效，则必须删去这两句代码
        //setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        // http://stackoverflow.com/questions/26511981/toolbar-inflatemenu-seems-to-do-nothing
        //设置右上角的填充菜单
        mToolbar.inflateMenu(R.menu.menu_recyclerview);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int menuItemId = item.getItemId();
                if (menuItemId == R.id.action_item1) {
                    initTestData();
                    //  Toast.makeText(RecycleViewActivity.this, "action_item1", Toast.LENGTH_SHORT).show();
                } else if (menuItemId == R.id.action_item2) {
                    click(main);
                    //    Toast.makeText(RecycleViewActivity.this, "action_item2", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

        mRvList = (RecyclerView) findViewById(R.id.rv_list);

        addListenerOnItem();

        setScrollCallback();
        initListAdapter();
        initTestData();
    }

    /**
     * 添加点击事件通过adapter方法
     */
    private void addListenerOnAdapter(){

    }

    /**
     *添加点击事件通过addOnItemTouchListener方法
     */
    private void addListenerOnItem(){

        mRvList.addOnItemTouchListener(new RecyclerItemClickListener(mRvList, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.i(TAG, "view:"+view+",onItemClick:" + position);
                Toast.makeText(RecycleViewActivity.this, "view:"+view+",onItemClick:" + position,
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Log.i(TAG,"view:"+view+",onItemLongClick:" + position);
                Toast.makeText(RecycleViewActivity.this, "view:"+view.getId()+",onItemLongClick:" + position,
                        Toast.LENGTH_SHORT).show();
            }
        }));
    }
    /**
     * 测试数据
     */
    private void initTestData() {
        String chars = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < 30; i++) {
            String randomString = "";
            for (int j = 0; j < 10; j++) {
                randomString += chars.charAt((int) (Math.random() * 26));
            }
            mList.add(randomString);
        }
        mAdapter.setDataAndRefreshUI(mList);
    }

    /**
     * 初始化Adapter
     */
    private void initListAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(RecycleViewActivity.this);
        mRvList.setLayoutManager(layoutManager);
//设置Item增加、移除动画
        mRvList.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
        mRvList.addItemDecoration(new DividerItemDecoration(
               RecycleViewActivity.this, DividerItemDecoration.VERTICAL_LIST));
        mAdapter = new ContentAdapter(RecycleViewActivity.this, mList);
        mRvList.setAdapter(mAdapter);
    }

    /**
     * 滚动事件
     */
    private void setScrollCallback() {

//        mRvList.setOnScrollCallback(new OnScrollCallback() {
//            @Override
//            public void onStateChanged(RecyclerView recyclerView, int state) {
//                if (state == 0) {
//                    Log.i(TAG, "滚动状态发生改变:停止滚动");
//                } else if (state == 1) {
//                    Log.i(TAG, "滚动状态发生改变:手动滚动");
//                } else if (state == 2) {
//                    Log.i(TAG, "滚动状态发生改变:自动滚动");
//                }
//            }
//
//            @Override
//            public void onScrollUp(RecyclerView recyclerView, int dy) {
//                Log.i(TAG, "向上滚动: " + dy);
//            }
//
//            @Override
//            public void onScrollToBottom() {
//                Log.i(TAG, "已经滚动到底部");
//            }
//
//            @Override
//            public void onScrollDown(RecyclerView recyclerView, int dy) {
//                Log.i(TAG, "向下滚动: " + dy);
//            }
//
//            @Override
//            public void onScrollToTop() {
//                Log.i(TAG, "已经滚动到顶部");
//            }
//        });
    }

    /**
     * snackbar
     * @param view
     */
    public void click(View view) {
        Snackbar.make(view, "真要点我?", Snackbar.LENGTH_LONG)
                .setAction("真的!", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(RecycleViewActivity.this, "你真点我了！",
                                Toast.LENGTH_SHORT).show();
                    }
                }).show();
    }
}


