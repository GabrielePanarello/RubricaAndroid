package com.example.gabrielepanarello.rubrica.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.gabrielepanarello.rubrica.R;
import com.example.gabrielepanarello.rubrica.data.Contact;
import com.example.gabrielepanarello.rubrica.logic.ArrayUtils;
import com.example.gabrielepanarello.rubrica.ui.adapter.CustomArrayAdapter;

/**
 * Created by Gabriele Panarello on 15/02/2018.
 */

public class AddActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contact);

        final EditText nameText = (EditText) findViewById(R.id.nameText);
        final EditText numberText = (EditText) findViewById(R.id.numberText);

        Button saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                CustomArrayAdapter adapter;
                String nameValue = String.valueOf(nameText.getText());
                String numberValue = String.valueOf(numberText.getText());

                int id = 0;

                if(!ArrayUtils.getContactsList().isEmpty()) {
                    id = ArrayUtils.getContactsList().get(ArrayUtils.getContactsList().size()-1).getId() + 1;
                }

                ArrayUtils.addContact(new Contact(id,nameValue,numberValue));
                //MainActivity.refreshList(ArrayUtils.getContactsList());
                finish();
            }
        });
    }
}
