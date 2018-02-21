package com.example.gabrielepanarello.rubrica.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gabrielepanarello.rubrica.R;
import com.example.gabrielepanarello.rubrica.logic.ArrayUtils;

/**
 * Created by Gabriele Panarello on 21/02/2018.
 */

public class DetailActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_layout);

        Intent intent = getIntent();

        String inputName = intent.getStringExtra("name");
        final String inputNumber = intent.getStringExtra("number");

        Bundle bundle = getIntent().getExtras();
        int inputColor = bundle.getInt("color", -1);

        ImageView contactImage = (ImageView) findViewById(R.id.contactImageDetail);
        TextView nameText = (TextView) findViewById(R.id.nameTextViewDetail);
        TextView numberText = (TextView) findViewById(R.id.numberTextViewDetail);

        contactImage.setBackgroundColor(inputColor);
        nameText.setText(inputName);
        numberText.setText(inputNumber);

        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayUtils.writeOnSharedPreferences(inputNumber,DetailActivity.this);
                finish();
            }
        });
    }
}
