package com.materialdesigndemo.android_materialdesign_view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.materialdesigndemo.android_materialdesign_view.R;

/**
 *
 */
public class MainActivity extends AppCompatActivity {

    /**
     * 菜单栏
     */
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("主页");
        setSupportActionBar(toolbar);

        Intent intent = new Intent(MainActivity.this,RecycleViewActivity.class);
        startActivity(intent);
//        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_item1) {
            return true;
        } else if (id == R.id.action_item2) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
