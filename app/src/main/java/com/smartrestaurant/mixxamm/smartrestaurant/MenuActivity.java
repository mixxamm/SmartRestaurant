package com.smartrestaurant.mixxamm.smartrestaurant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.smartrestaurant.mixxamm.smartrestaurent.R;

import org.json.JSONException;
import org.json.JSONObject;

public class MenuActivity extends AppCompatActivity {

    public static String restaurant, table;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //Vars
        TextView txtTest = findViewById(R.id.txtTest);

        Intent intent = getIntent();
        String qr = intent.getStringExtra("QR-code");
        try {
            JSONObject jsonobject = new JSONObject(qr);
            restaurant = jsonobject.getString("restaurant");
            table = jsonobject.getString("table");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        txtTest.setSingleLine(false);
        txtTest.setText("Restaurant: " + restaurant + " \nTafel: " + table);



    }
}
