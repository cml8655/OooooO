package com.cml.second.app.baby.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.cml.second.app.baby.R;
import com.cml.second.app.baby.fragment.MainFragment;
import com.cml.second.app.baby.helper.menu.MenuHelper;
import com.cml.second.app.baby.widget.dialog.DefaultSelectorDialog;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends BaseActivity {

    private DrawerLayout drawer;
    private FloatingActionButton floatingActionButton;
    private MenuHelper menuHelper;
    private LinearLayout toolbarCustomLayout;//自定义toolbar背景界面

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        toolbarCustomLayout = (LinearLayout) findViewById(R.id.toolbar_custom_container);

        //设置titlebar
        initToolbar();
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        setCustomTitle(getTitle());

//        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
//        collapsingToolbarLayout.setTitle("am title");
//        collapsingToolbarLayout.setTitleEnabled(true);

        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                AlertDialog dialog = new AlertDialog.Builder(MenuActivity.this).setSingleChoiceItems(new CharSequence[]{"相机", "食品"}, -1, null).setTitle("title").create();
//                dialog.getWindow().setGravity(Gravity.BOTTOM);
//                dialog.show();
                List<DefaultSelectorDialog.SelectorItem> data = new ArrayList<DefaultSelectorDialog.SelectorItem>();
                for (int i = 0; i < 5; i++) {
                    data.add(new DefaultSelectorDialog.SelectorItem("text" + i, -1));
                }
                new DefaultSelectorDialog(MenuActivity.this,data).setGravity(Gravity.BOTTOM).setTitle("title").show();
//                new SelectorDialog(MenuActivity.this, data).setGravity(Gravity.BOTTOM).setTitle("我是他itle").show();
            }
        });

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        menuHelper = new MenuHelper(this, drawer);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(menuHelper);

        //将默认的menu栏迁移到自定义的custom title容器上，以解决title居中问题
        setupCustomNavigation();

        menuHelper.showContainer(MainFragment.class);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        item.setChecked(false);

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void hideFloatingBtn() {
        floatingActionButton.hide();
    }

    public void setFloatingBtnListener(View.OnClickListener listener) {
        floatingActionButton.setOnClickListener(listener);
    }

    public LinearLayout getToolbarCustomLayout() {
        return toolbarCustomLayout;
    }
}
