package com.cml.second.app.baby.helper.menu;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import com.cml.second.app.baby.R;
import com.cml.second.app.baby.activity.MenuActivity;
import com.cml.second.app.baby.fragment.BaseFragment;
import com.cml.second.app.baby.fragment.MainFragment;
import com.cml.second.app.baby.fragment.ShareFragment;

/**
 * Created by cmlBeliever on 2016/2/24.
 */
public class MenuHelper implements NavigationView.OnNavigationItemSelectedListener {
    private MenuActivity menuActivity;
    private DrawerLayout drawer;

    public MenuHelper(MenuActivity menuActivity, DrawerLayout drawer) {
        this.menuActivity = menuActivity;
        this.drawer = drawer;
    }

    public void showContainer(BaseFragment fragment) {
        menuActivity.getSupportFragmentManager().beginTransaction().replace(R.id.layout_container, fragment).commit();
    }

    public void showContainer(Class<? extends BaseFragment> fragment) {
        menuActivity.getSupportFragmentManager().beginTransaction().replace(R.id.layout_container, Fragment.instantiate(menuActivity, fragment.getName())).commit();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        item.setChecked(false);
        item.setNumericShortcut('5');

        if (id == R.id.nav_camera) {
            // Handle the camera action
            showContainer(ShareFragment.class);
        } else if (id == R.id.nav_gallery) {
            showContainer(MainFragment.class);

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        drawer.closeDrawer(GravityCompat.START);
        menuActivity.setCustomTitle(item.getTitle());
        return true;
    }
}
