package ugia.io.androidbeautytreatment.viewcontroller.adapter;

import java.util.Arrays;
import java.util.Comparator;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ugia.io.androidbeautytreatment.R;
import ugia.io.androidbeautytreatment.model.Contact;

/**
 * Created by joseluisugia on 16/07/15.
 */
public class HeaderContactListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final static int VIEW_TYPE_HEADER = 0;
    private final static int VIEW_TYPE_ROW = 1;

    private final Contact[] contacts;

    public HeaderContactListAdapter(Contact[] contacts) {
        this.contacts = contacts;
        sortContactArray();
    }

    private void sortContactArray() {
        Arrays.sort(contacts, contactComparator);
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
        return contacts.length + 1;
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

    class ContactViewHolder extends RecyclerView.ViewHolder {

        private final TextView contactName;

        public ContactViewHolder(View view) {
            super(view);
            contactName = (TextView) view.findViewById(R.id.il_textview);
        }

        private void bindContact(Contact contact) {
            contactName.setText(contact.name);
        }
    }

    private final Comparator<Contact> contactComparator = new Comparator<Contact>() {
        @Override
        public int compare(Contact lhs, Contact rhs) {
            return lhs.name.compareTo(rhs.name);
        }
    };
}
