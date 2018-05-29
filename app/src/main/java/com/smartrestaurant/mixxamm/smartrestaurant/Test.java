package com.smartrestaurant.mixxamm.smartrestaurant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.smartrestaurant.mixxamm.smartrestaurent.R;

public class Test extends AppCompatActivity {

    public static String category, name, price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        TextView textView = findViewById(R.id.textViewTest);
        textView.setText("Bestelling is geplaatst.");
    }
}
