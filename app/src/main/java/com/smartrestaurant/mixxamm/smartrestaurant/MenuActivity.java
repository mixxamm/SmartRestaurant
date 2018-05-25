package com.smartrestaurant.mixxamm.smartrestaurant;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.smartrestaurant.mixxamm.smartrestaurent.R;

public class MenuActivity extends AppCompatActivity {

    public static String restaurant, restaurantID, table;

    private ListView lvLijst;
    CustomListviewAdaptor adaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


            lvLijst = findViewById(R.id.lvLijst);

            String[] listNames = getIntent().getStringArrayExtra("listName");
            adaptor = new CustomListviewAdaptor(listNames, this);
            lvLijst.setAdapter(adaptor);

    }
    @Override
    protected void onPause() {
        super.onPause();
        ScanActivity.scanned = false;
    }
}
