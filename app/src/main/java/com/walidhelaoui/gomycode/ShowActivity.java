package com.walidhelaoui.gomycode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ShowActivity extends AppCompatActivity {

    TextView nameTXT,placeTXT,priceTXT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        nameTXT = findViewById(R.id.nameTXT);
        priceTXT = findViewById(R.id.priceTXT);
        placeTXT = findViewById(R.id.placeTXT);

        String name = getIntent().getStringExtra("name");
        String price = getIntent().getStringExtra("price");
        String place = getIntent().getStringExtra("place");

        nameTXT.setText("Name: "+name);
        priceTXT.setText("Price: "+price);
        placeTXT.setText("Place: "+place);
    }
}
