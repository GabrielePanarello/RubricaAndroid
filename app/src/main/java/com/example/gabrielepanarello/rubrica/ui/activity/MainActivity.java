package com.example.gabrielepanarello.rubrica.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.gabrielepanarello.rubrica.R;
import com.example.gabrielepanarello.rubrica.data.Contact;
import com.example.gabrielepanarello.rubrica.data.Main_Singleton;
import com.example.gabrielepanarello.rubrica.logic.ArrayUtils;
import com.example.gabrielepanarello.rubrica.ui.adapter.CustomArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CustomArrayAdapter adapter;

    /*protected static void refreshList(List<Contact> newContactList){
        adapter.clear();
        adapter.addAll(newContactList);
        adapter.notifyDataSetChanged();
    }*/

    @Override
    protected void onResume() {
        super.onResume();
        adapter.clear();
        adapter.addAll(ArrayUtils.getContactsList());
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add: {
                Intent intent = new Intent(this, AddActivity.class);
                startActivity(intent);
                return true;
            }

            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayUtils.init();
        List<Contact> contactsList = new ArrayList<Contact>(ArrayUtils.getContactsList());

        adapter = new CustomArrayAdapter(this,contactsList);
        ListView listView = (ListView) findViewById(R.id.listView);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.M)
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String name = ArrayUtils.getContactsList().get(position).getName();
                String number = ArrayUtils.getContactsList().get(position).getNumber();
                int color = ArrayUtils.getColorForPosition(MainActivity.this,position);

                Intent intent = new Intent(MainActivity.this,DetailActivity.class);
                intent.putExtra("name",name);
                intent.putExtra("number",number);
                intent.putExtra("color",color);
                startActivity(intent);
            }
        });

    }
}
