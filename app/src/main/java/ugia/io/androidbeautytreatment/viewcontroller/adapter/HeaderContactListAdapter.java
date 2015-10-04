/*
 * HeaderContactListAdapter.java
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

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ugia.io.androidbeautytreatment.R;
import ugia.io.androidbeautytreatment.model.Contact;

/**
 * This class adds on top of @{ContactAdapter} and includes a header on top of the list
 * <p/>
 * Created by joseluisugia on 16/07/15.
 */
public class HeaderContactListAdapter extends ContactAdapter {

    private final static int VIEW_TYPE_HEADER = 1;

    public HeaderContactListAdapter(Contact[] contacts) {
        super(contacts);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        if (viewType == VIEW_TYPE_ROW) {
            View view = layoutInflater.inflate(R.layout.item_list, parent, false);
            return new ContactViewHolder(view);
        } else {
            return new HeaderViewHolder(createHeader(parent));
        }
    }

    private View createHeader(ViewGroup parent) {

        View view = new View(parent.getContext());
        parent.addView(view);

        ViewGroup.LayoutParams lp = view.getLayoutParams();
        lp.height = (int) parent.getContext().getResources()
                                .getDimension(R.dimen.recyclerview_dynamic_header_big_height);
        view.setLayoutParams(lp);

        return view;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        int viewType = getItemViewType(position);

        if (viewType == VIEW_TYPE_ROW) {
            Contact contact = contacts[position - 1];
            ((ContactViewHolder) holder).bindContact(contact);
        }
    }

    @Override
    public int getItemCount() {
        return super.getItemCount() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return VIEW_TYPE_HEADER;
        } else {
            return VIEW_TYPE_ROW;
        }
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {

        public HeaderViewHolder(View view) {
            super(view);
        }
    }
}
