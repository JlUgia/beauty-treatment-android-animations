package ugia.io.androidbeautytreatment.viewcontroller.activity;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.View;

import ugia.io.androidbeautytreatment.R;
import ugia.io.androidbeautytreatment.viewcontroller.fragment.ClickableViewController;
import ugia.io.androidbeautytreatment.viewcontroller.fragment.DynamicHeaderListFragment;
import ugia.io.androidbeautytreatment.viewcontroller.fragment.NavigationDrawerFragment;
import ugia.io.androidbeautytreatment.viewcontroller.fragment.SlidingLayerFragment;
import ugia.io.androidbeautytreatment.viewcontroller.fragment.StaticHeaderListFragment;
import ugia.io.androidbeautytreatment.viewcontroller.fragment.ViewPagerFragment;

public class MainActivity extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks, ClickableViewController {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private ClickableViewController visibleFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        bindViews();
    }

    @Override
    public void buttonClicked(View view) {
        visibleFragment.buttonClicked(view);
    }

    private void bindViews() {

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);

        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {

        switch (position) {
        case 0:
            visibleFragment = ViewPagerFragment.newInstance();
            break;

        case 1:
            visibleFragment = StaticHeaderListFragment.newInstance();
            break;

        case 2:
            visibleFragment = DynamicHeaderListFragment.newInstance();
            break;

        case 3:
            visibleFragment = SlidingLayerFragment.newInstance();
            break;

        default:
            throw new IllegalArgumentException("There should be no section with this position index.");
        }

        getFragmentManager().beginTransaction()
                            .replace(R.id.container, (Fragment) visibleFragment)
                            .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }
}
