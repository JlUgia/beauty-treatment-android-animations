package ugia.io.androidbeautytreatment.viewcontroller.adapter;

import android.app.Fragment;
import android.app.FragmentManager;

import ugia.io.androidbeautytreatment.viewcontroller.fragment.TaggableFragment;
import ugia.io.androidbeautytreatment.viewcontroller.fragment.ViewPagerTextViewFragment;

/**
 * Created by joseluisugia on 18/09/15.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    private int[] backgroundColorResources = new int[] {
            android.R.color.holo_green_light,
            android.R.color.holo_blue_dark,
            android.R.color.holo_orange_dark,
            android.R.color.holo_red_dark,
            android.R.color.holo_purple };

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        int color = backgroundColorResources[position];
        
        TaggableFragment fragment = ViewPagerTextViewFragment.newInstance(position + 1, color);
        fragment.setViewTag(position);
        return fragment;
    }

    @Override
    public int getCount() {
        return backgroundColorResources.length;
    }
}
