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
