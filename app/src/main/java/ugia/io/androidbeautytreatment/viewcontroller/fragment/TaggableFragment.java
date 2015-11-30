/*
 * TaggableFragment.java
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

import android.app.Fragment;
import android.os.Bundle;
import android.view.View;

/**
 * Created by joseluisugia on 25/09/15.
 */
public abstract class TaggableFragment extends Fragment {

    private static final int INVALID_TAG = -1;
    protected int viewTag = INVALID_TAG;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (viewTag != INVALID_TAG && view != null) {
            view.setTag(viewTag);
        }
    }

    public void setViewTag(int viewTag) {
        this.viewTag = viewTag;
    }
}
