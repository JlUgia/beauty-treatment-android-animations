package ugia.io.androidbeautytreatment.viewcontroller.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wunderlist.slidinglayer.SlidingLayer;
import com.wunderlist.slidinglayer.transformer.RotationTransformer;
import com.wunderlist.slidinglayer.transformer.SlideJoyTransformer;
import ugia.io.androidbeautytreatment.R;

/**
 * Created by joseluisugia on 18/09/15.
 */
public class SlidingLayerFragment extends Fragment implements ClickableViewController {

    private SlidingLayer slidingLayerLeft;
    private SlidingLayer slidingLayerTop;
    private SlidingLayer slidingLayerRight;
    private SlidingLayer slidingLayerBottom;

    public static SlidingLayerFragment newInstance() {
        return new SlidingLayerFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_slidinglayer, container, false);

        slidingLayerLeft = (SlidingLayer) view.findViewById(R.id.fs_slidinglayer_left);
        slidingLayerTop = (SlidingLayer) view.findViewById(R.id.fs_slidinglayer_top);
        slidingLayerRight = (SlidingLayer) view.findViewById(R.id.fs_slidinglayer_right);
        slidingLayerBottom = (SlidingLayer) view.findViewById(R.id.fs_slidinglayer_bottom);

        slidingLayerLeft.setLayerTransformer(new RotationTransformer());
        slidingLayerRight.setLayerTransformer(new RotationTransformer());
        slidingLayerTop.setLayerTransformer(new SlideJoyTransformer());
        slidingLayerBottom.setLayerTransformer(new SlideJoyTransformer());

        view.post(new Runnable() {
            @Override
            public void run() {
                slidingLayerLeft.closeLayer(false);
                slidingLayerRight.closeLayer(false);
            }
        });

        return view;
    }

    @Override
    public void buttonClicked(View view) {
        openLayers();
    }

    private void openLayers() {
        slidingLayerLeft.openLayer(true);
        slidingLayerTop.openLayer(true);
        slidingLayerRight.openLayer(true);
        slidingLayerBottom.openLayer(true);
    }
}
