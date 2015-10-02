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
