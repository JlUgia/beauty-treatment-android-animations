/*
 * ViewPagerAdapter.java
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
