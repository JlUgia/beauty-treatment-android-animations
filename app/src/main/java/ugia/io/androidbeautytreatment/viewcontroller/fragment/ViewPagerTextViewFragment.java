/*
 * ViewPagerTextViewFragment.java
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

package ugia.io.androidbeautytreatment.viewcontroller.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ugia.io.androidbeautytreatment.R;

/**
 * Created by joseluisugia on 22/09/15.
 */
public class ViewPagerTextViewFragment extends TaggableFragment {

    private static final String PAGE_COLOR_RESOURCE = "pageColorResource";
    private static final String PAGE_INDEX_KEY = "pageIndex";

    protected int pageColor;
    private int pageIndex;

    public static ViewPagerTextViewFragment newInstance(int pageIndex, int color) {

        ViewPagerTextViewFragment fragment = new ViewPagerTextViewFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(PAGE_INDEX_KEY, pageIndex);
        arguments.putInt(PAGE_COLOR_RESOURCE, color);

        fragment.setArguments(arguments);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = getArguments();
        pageIndex = arguments != null ? arguments.getInt(PAGE_INDEX_KEY) : 1;
        pageColor = arguments != null ? getResources().getColor(arguments.getInt(PAGE_COLOR_RESOURCE)) : 0;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_viewpager_container, container, false);
        view.setBackgroundColor(pageColor);

        TextView textView = (TextView) view.findViewById(R.id.fvc_textview);
        textView.setText("I am #" + pageIndex);

        return view;
    }
}
