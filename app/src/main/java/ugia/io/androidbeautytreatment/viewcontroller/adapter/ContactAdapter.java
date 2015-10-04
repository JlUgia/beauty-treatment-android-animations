/*
 * ContactAdapter.java
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

import java.util.Arrays;
import java.util.Comparator;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import ugia.io.androidbeautytreatment.R;
import ugia.io.androidbeautytreatment.model.Contact;

/**
 * This base class holds a basic adapter that takes care of populating a list of contacts. This class may be
 * extended to add extra funcionality
 * <p/>
 * Created by joseluisugia on 16/07/15.
 */
abstract class ContactAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected final static int VIEW_TYPE_ROW = 0;

    protected final Contact[] contacts;

    public ContactAdapter(Contact[] contacts) {
        this.contacts = contacts;
        sortContactArray();
    }

    private void sortContactArray() {
        Arrays.sort(contacts, contactComparator);
    }

    @Override
    public int getItemCount() {
        return contacts.length;
    }

    class ContactViewHolder extends RecyclerView.ViewHolder {

        private final TextView contactName;

        public ContactViewHolder(View view) {
            super(view);
            contactName = (TextView) view.findViewById(R.id.il_textview);
        }

        protected void bindContact(Contact contact) {
            contactName.setText(contact.getName());
        }
    }

    private final Comparator<Contact> contactComparator = new Comparator<Contact>() {
        @Override
        public int compare(Contact lhs, Contact rhs) {
            return lhs.getName().compareTo(rhs.getName());
        }
    };
}
