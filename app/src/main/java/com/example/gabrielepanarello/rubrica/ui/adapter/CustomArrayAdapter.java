package com.example.gabrielepanarello.rubrica.ui.adapter;

import android.content.Context;
import android.media.Image;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gabrielepanarello.rubrica.R;
import com.example.gabrielepanarello.rubrica.data.Contact;
import com.example.gabrielepanarello.rubrica.logic.ArrayUtils;

import java.util.List;

/**
 * Created by Gabriele Panarello on 15/02/2018.
 */

public class CustomArrayAdapter extends ArrayAdapter<Contact> {

    private final Context context;
    private List<Contact> values;


    public CustomArrayAdapter(Context context, List<Contact> values) {
        super(context, R.layout.contacts_layout, values);
        this.context = context;
        this.values = values;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getViewOptimize(position, convertView, parent);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public View getViewOptimize(int position, View convertView, ViewGroup parent) {

        String favouriteRef = ArrayUtils.readOnSharedPreferences(this.context);
        Log.d("Favourite", "String OUT " + favouriteRef);
        ViewHolder viewHolder = null;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.contacts_layout, null);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.nameLabel);
            viewHolder.number = (TextView) convertView.findViewById(R.id.numberLabel);
            viewHolder.contactImg = (ImageView) convertView.findViewById(R.id.imgContact);
            viewHolder.favourite = (ImageView) convertView.findViewById(R.id.favouriteImg);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Da qui in poi si lavora con il viewHolder, esempio:
        Contact contatto = getItem(position);
        viewHolder.name.setText(contatto.getName());
        viewHolder.number.setText(contatto.getNumber());
        viewHolder.contactImg.setBackgroundColor(ArrayUtils.getColorForPosition(context, position));

        if (favouriteRef != "" && contatto.getNumber().equals(favouriteRef)) {
            viewHolder.favourite.setVisibility(View.VISIBLE);
        } else {
            viewHolder.favourite.setVisibility(View.INVISIBLE);
        }

        //ritorno la view
        return convertView;
    }

    private class ViewHolder {
        public TextView name;
        public TextView number;
        public ImageView contactImg;
        public ImageView favourite;
    }
}
