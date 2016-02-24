package com.cml.second.app.baby.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.cml.second.app.baby.R;
import com.cml.second.app.baby.fragment.MainFragment;
import com.cml.second.app.baby.helper.menu.MenuHelper;

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


    /**
     * 将默认的menu栏迁移到自定义的custom title容器上，以解决title居中问题
     */
    private void setupCustomNavigation() {
        int size = toolbar.getChildCount();

        //设置toolbar的菜单栏偏移问题
        ImageButton menuBtn = null;

        for (int i = 0; i < size; i++) {
            View view = toolbar.getChildAt(i);
            if (view instanceof ImageButton) {
                menuBtn = (ImageButton) view;
                break;
            }
        }

        if (null != menuBtn) {
            toolbar.removeView(menuBtn);
            RelativeLayout toolbarTitleContainer = (RelativeLayout) findViewById(R.id.toolbar_title_container);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.CENTER_VERTICAL);
            toolbarTitleContainer.addView(menuBtn, 0, params);
        }

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
