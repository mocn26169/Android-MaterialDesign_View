package com.materialdesigndemo.android_materialdesign_view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.materialdesigndemo.android_materialdesign_view.Adapter.ContentAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.materialdesigndemo.android_materialdesign_view.R.id.toolbar;

public class ContentActivity extends AppCompatActivity {
/*=========== 控件相关 ==========*/
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

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
        mToolbar.inflateMenu(R.menu.menu_content);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int menuItemId = item.getItemId();
                if (menuItemId == R.id.action_item1) {
                    initTestData();
                    Toast.makeText(ContentActivity.this, "action_item1", Toast.LENGTH_SHORT).show();
                } else if (menuItemId == R.id.action_item2) {
                    Toast.makeText(ContentActivity.this, "action_item2", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

        mRvList = (RecyclerView) findViewById(R.id.rv_list);


        initListAdapter();
        initTestData();
    }

    private void initTestData() {
        String chars = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < 5; i++) {
            String randomString = "";
            for (int j = 0; j <10 ; j++) {
                randomString+= chars.charAt((int)(Math.random() * 26));
            }
            mList.add(randomString);
        }
        mAdapter.setDataAndRefreshUI(mList);
    }

    /**
     * 初始化Adapter
     */
    private void initListAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(ContentActivity.this);
        mRvList.setLayoutManager(layoutManager);

        mAdapter = new ContentAdapter(ContentActivity.this, mList);
        mRvList.setAdapter(mAdapter);

    }
}


