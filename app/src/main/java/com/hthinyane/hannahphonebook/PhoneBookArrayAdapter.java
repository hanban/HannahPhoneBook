package com.hthinyane.hannahphonebook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hthinyane.hannahphonebook.Contacts.Contact;

import java.util.ArrayList;

/**
 * Created by h.thinyane on 2016-05-13.
 */
public class PhoneBookArrayAdapter extends ArrayAdapter<Contact> {


    private final Context context;
    private final ArrayList<Contact> values;


    public Contact getContact (int position) {
        if (position < values.size()) return values.get(position);

        return null;
    }

    public void updateContact(Contact toUpdate) {
        int pos = Integer.parseInt(toUpdate.id);

        if (pos < values.size()) {
            // then it is a valid position
            values.set(pos, toUpdate);
        }

        notifyDataSetChanged();
    }

    public void addContact(Contact toAdd) {
        int pos = Integer.parseInt(toAdd.id);

        if (pos == -1) {
            toAdd.id = ""+ values.size();
            this.add(toAdd);
            notifyDataSetChanged();
        }
    }

    public PhoneBookArrayAdapter (Context context, ArrayList<Contact> values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_layout, parent, false);
        TextView nameView = (TextView) rowView.findViewById(R.id.nameLabel);
        TextView detailsView = (TextView) rowView.findViewById(R.id.detailsLabel);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.rowImage);
        nameView.setText(values.get(position).name);
        detailsView.setText(values.get(position).phone);

        // get the resource ID from the context for the particular filename
        imageView.setImageResource(R.drawable.contact);

        return rowView;

    }
}
