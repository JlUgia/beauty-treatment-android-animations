/*
 * StaticHeaderListFragment.java
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
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import ugia.io.androidbeautytreatment.R;
import ugia.io.androidbeautytreatment.model.Contact;
import ugia.io.androidbeautytreatment.view.NoisySwipeRefreshLayout;
import ugia.io.androidbeautytreatment.viewcontroller.adapter.ContactListAdapter;

/**
 * Created by joseluisugia on 26/09/15.
 */
public class StaticHeaderListFragment extends Fragment implements ClickableViewController {

    private static final int INVALID_INDEX = -1;

    private Contact[] contacts;

    private NoisySwipeRefreshLayout swipeRefreshLayout;

    private int currentAnimationStageIndex = INVALID_INDEX;
    private ViewGroup swipeRefreshAnimationContainer;
    private ImageView[] swipeRefreshImageViews;
    private AnimatedVectorDrawable[] swipeRefreshDrawables;

    private float[] animationCuePoints = new float[] { .05f, .1f, .1f, .15f, .16f, .18f, .2f, .22f, .221f, .222f,
            .5f, .52f, .55f, .7f, .72f, 1 };

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private ContactListAdapter contactListAdapter;

    private ContactListHeaderViewHolder headerViewHolder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        contacts = Poets.generate();
        contactListAdapter = new ContactListAdapter(contacts);
    }

    public static StaticHeaderListFragment newInstance() {
        return new StaticHeaderListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_static_header_list, container, false);
        bindViews(rootView);

        return rootView;
    }

    private void bindViews(View view) {

        swipeRefreshLayout = (NoisySwipeRefreshLayout) view.findViewById(R.id.fshl_swipe_container);
        swipeRefreshAnimationContainer = (ViewGroup) view.findViewById(R.id.fshl_swipe_refresh_container);
        initializeSwipeRefreshLayout();

        recyclerView = (RecyclerView) view.findViewById(R.id.fl_recyclerview);
        initializeRecyclerView();

        View contactHeader = view.findViewById(R.id.fv_header_1);
        headerViewHolder = new ContactListHeaderViewHolder(contactHeader);
    }

    private void initializeSwipeRefreshLayout() {

        int swipeRefreshDimensions = (int) getResources().getDimension(R.dimen.swipe_refresh_height);

        ViewGroup.LayoutParams layoutParams = swipeRefreshAnimationContainer.getLayoutParams();
        layoutParams.height = swipeRefreshDimensions;
        layoutParams.width = swipeRefreshDimensions;

        swipeRefreshDrawables = new AnimatedVectorDrawable[] {
                loadVectorDrawable(R.drawable.branch_minified_grow_branch1),
                loadVectorDrawable(R.drawable.branch_minified_grow_leave1),
                loadVectorDrawable(R.drawable.branch_minified_grow_branch3),
                loadVectorDrawable(R.drawable.branch_minified_grow_leave2),
                loadVectorDrawable(R.drawable.branch_minified_grow_branch2),
                loadVectorDrawable(R.drawable.branch_minified_grow_leave3),
                loadVectorDrawable(R.drawable.branch_minified_grow_leave4),
                loadVectorDrawable(R.drawable.branch_minified_grow_leave5),
                loadVectorDrawable(R.drawable.branch_minified_grow_branch4),
                loadVectorDrawable(R.drawable.branch_minified_grow_leave6),
                loadVectorDrawable(R.drawable.branch_minified_grow_leave7),
                loadVectorDrawable(R.drawable.branch_minified_grow_branch5),
                loadVectorDrawable(R.drawable.branch_minified_grow_leave8),
                loadVectorDrawable(R.drawable.branch_minified_grow_branch6),
                loadVectorDrawable(R.drawable.branch_minified_grow_leave9),
                loadVectorDrawable(R.drawable.branch_minified_grow_source)
        };

        swipeRefreshImageViews = new ImageView[16];
        for (int i = 0; i < 16; i++) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setLayoutParams(layoutParams);
            swipeRefreshAnimationContainer.addView(imageView);
            swipeRefreshImageViews[i] = imageView;
        }

        swipeRefreshLayout.setOnRefreshListener(refreshListener);
    }

    private AnimatedVectorDrawable loadVectorDrawable(int resourceId) {
        return (AnimatedVectorDrawable) getResources().getDrawable(resourceId);
    }

    private void initializeRecyclerView() {

        recyclerView.setHasFixedSize(true);
        recyclerView.addOnScrollListener(scrollListener);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(contactListAdapter);
    }

    @Override
    public void buttonClicked(View view) {

    }

    private class ContactListHeaderViewHolder {

        private ImageView contactAvatarImageView;
        private TextView contactNameTextView;
        private TextView contactNumberTextView;

        private View activeConversationsView;

        public ContactListHeaderViewHolder(View view) {

            contactAvatarImageView = (ImageView) view.findViewById(R.id.vrh_avatar_imageview);
            contactNameTextView = (TextView) view.findViewById(R.id.vrh_name_textview);
            contactNumberTextView = (TextView) view.findViewById(R.id.vrh_number_textview);

            activeConversationsView = view.findViewById(R.id.vrh_active_conversation_imageview);
        }

        private void bindContact(Contact contact) {

            contactNameTextView.setText(contact.getName());
            contactNumberTextView.setText(contact.getPhoneNumber());

            int visibility = contact.hasRecentConversation() ? View.VISIBLE : View.GONE;
            activeConversationsView.setVisibility(visibility);
        }
    }

    private RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() {

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            int position = layoutManager.findFirstVisibleItemPosition();
            headerViewHolder.bindContact(contacts[position]);
        }
    };

    private NoisySwipeRefreshLayout.OnRefreshListener refreshListener =
            new NoisySwipeRefreshLayout.OnRefreshListener() {

                @Override
                public void onCancel() {
                    resetAnimations();
                }

                @Override
                public void onRefresh() {
                    swipeRefreshLayout.setRefreshing(false);
                    resetAnimations();
                }

                @Override
                public void onProgress(float progress) {

                    int animationStateIndex = animationStateForProgress(progress);

                    if (animationStateIndex > INVALID_INDEX && animationStateIndex > currentAnimationStageIndex) {

                        for (int i = animationStateIndex; i > currentAnimationStageIndex; i--) {
                            swipeRefreshImageViews[i].setImageDrawable(swipeRefreshDrawables[i]);
                            swipeRefreshDrawables[i].start();
                        }
                        currentAnimationStageIndex = animationStateIndex;
                    }
                }

                private void resetAnimations() {

                    currentAnimationStageIndex = INVALID_INDEX;
                    for (ImageView imageView : swipeRefreshImageViews) {
                        imageView.setImageDrawable(null);
                    }
                }

                private int animationStateForProgress(float progress) {

                    int length = animationCuePoints.length;
                    for (int i = 0; i < length; i++) {
                        if (animationCuePoints[i] >= progress) {
                            return i;
                        }
                    }

                    return INVALID_INDEX;
                }
            };
}
