package com.smartrestaurant.mixxamm.smartrestaurant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.ListView;

import com.smartrestaurant.mixxamm.smartrestaurent.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MenuActivity extends AppCompatActivity {

    public static String restaurant, restaurantID, table;
    public static List<String> listCAT, listNam;
    public static List<Double> listpr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        if (listCAT != null) {
            //Vars
            //Menu inladen
            TextView txtTest = findViewById(R.id.txtTest);

            Intent intent = getIntent();
            String qr = intent.getStringExtra("QR-code");
            try {
                JSONObject jsonobject = new JSONObject(qr);
                restaurant = jsonobject.getString("restaurant");
                table = jsonobject.getString("table");
                restaurantID = jsonobject.getString("restaurantID");
            } catch (JSONException e) {
                restaurant = "Niet gevonden";
                restaurantID = "Niet gevonden";
                table = "Niet gevonden";
                e.printStackTrace();
            }
            txtTest.setSingleLine(false);
            txtTest.setText("Restaurant: " + restaurant + " \nTafel: " + table);
            Order order = new Order(MenuActivity.this);
            order.execute("getMenu", restaurantID);
        }else{
            //TODO
        }


    }
    @Override
    protected void onPause() {
        super.onPause();
        ScanActivity.scanned = false;
    }
}
