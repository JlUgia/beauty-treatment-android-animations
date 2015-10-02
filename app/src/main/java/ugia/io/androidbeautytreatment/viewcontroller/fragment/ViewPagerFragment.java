package ugia.io.androidbeautytreatment.viewcontroller.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wunderlist.slidinglayer.SlidingLayer;
import com.wunderlist.slidinglayer.utils.Transitions;
import ugia.io.androidbeautytreatment.R;
import ugia.io.androidbeautytreatment.viewcontroller.adapter.ViewPagerAdapter;

/**
 * Created by joseluisugia on 18/09/15.
 */
public class ViewPagerFragment extends Fragment implements ClickableViewController {

    private ViewPager viewPager;
    private ViewPagerAdapter pagerAdapter;

    private TextView[] userDetailsTextViews;
    private int viewPagerHeaderHeight;

    private SlidingLayer slidingLayer;

    public static ViewPagerFragment newInstance() {
        return new ViewPagerFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewPagerHeaderHeight = (int) getResources().getDimension(R.dimen.viewpager_header_height);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_viewpager, container, false);

        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        pagerAdapter = new ViewPagerAdapter(getFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        viewPager.setPageTransformer(true, new SlideJoyPageTransformer());

        viewPager.setEnabled(false);

        userDetailsTextViews = new TextView[5];
        userDetailsTextViews[0] = (TextView) view.findViewById(R.id.fv_header_text_1);
        userDetailsTextViews[1] = (TextView) view.findViewById(R.id.fv_header_text_2);
        userDetailsTextViews[2] = (TextView) view.findViewById(R.id.fv_header_text_3);
        userDetailsTextViews[3] = (TextView) view.findViewById(R.id.fv_header_text_4);
        userDetailsTextViews[4] = (TextView) view.findViewById(R.id.fv_header_text_5);
        setupHeaderTextViews();

        return view;
    }

    private void setupHeaderTextViews() {

        int length = userDetailsTextViews.length;
        for (int i = 0; i < length; i++) {
            userDetailsTextViews[i].setText(String.format("Are you #%s?", i + 1));
        }
    }

    @Override
    public void buttonClicked(View view) {
        slidingLayer.openLayer(true);
    }


    private class RotationalPageTransformer implements ViewPager.PageTransformer {

        private final float[] alphaValues = new float[] { 0, 1, 1, 0 };
        private final float[] alphaCuePoints = new float[] { -.7f, -.5f, .5f, .7f };

        private final float[] scaleValues = new float[] { .5f, 1, .5f };
        private final float[] scaleCuePoints = new float[] { -1, 0, 1 };

        public void transformPage(View page, float position) {

            transformTextViews(position, (int) page.getTag());

            // Alpha
            float alpha = Transitions.intermediateValueForRange(position, alphaCuePoints, alphaValues);
            page.setAlpha(alpha);

            // Scale
            float scaleValue = Transitions.intermediateValueForRange(position, scaleCuePoints, scaleValues);
            page.setScaleX(scaleValue);
            page.setScaleY(scaleValue);

            // Periodic translation
            double pi = position * Math.PI;
            float bouncingPosition = (float) Math.sin(pi) / 2;

            int pageWidth = page.getWidth();

            // Calculate basic translation cancelling out natural effect of viewPager
            float translationX = pageWidth * (bouncingPosition - position);
            page.setTranslationX(translationX);
        }
    }

    private class SlideJoyPageTransformer implements ViewPager.PageTransformer {

        private final float[] rotationYValues = new float[] { 0, -2.8f, 0, 2.8f, 0 };
        private final float[] rotationYCuePoints = new float[] { -.3f, -.1f, 0, .1f, .3f };

        private final float[] scaleValues = new float[] { .9f, 1, .9f };
        private final float[] scaleCuePoints = new float[] { -.1f, 0, .1f };

        @Override
        public void transformPage(View page, float position) {

            transformTextViews(position, (int) page.getTag());

            // Scale
            float scaleValue = Transitions.intermediateValueForRange(position, scaleCuePoints, scaleValues);
            page.setScaleX(scaleValue);
            page.setScaleY(scaleValue);

            // Prepare pivots for rotation
            page.setPivotY(page.getMeasuredHeight() / 2);
            if (position >= 0) {
                page.setPivotX(page.getMeasuredWidth());
            } else {
                page.setPivotX(0);
            }

            // Perform actual rotation
            float rotationY = Transitions.intermediateValueForRange(position, rotationYCuePoints, rotationYValues);
            page.setRotationY(rotationY);
        }
    }

    private void transformTextViews(float progress, int currentPage) {

        TextView textView = userDetailsTextViews[currentPage];
        float translationY;

        if (progress < -1) { // [-Infinity,-1)
            translationY = viewPagerHeaderHeight * 2;

        } else if (progress <= 1) { // (0,1]
            translationY = progress * -viewPagerHeaderHeight * 2;

        } else { // (1,+Infinity]
            translationY = -viewPagerHeaderHeight * 2;
        }

        textView.setTranslationY(translationY);
    }
}
