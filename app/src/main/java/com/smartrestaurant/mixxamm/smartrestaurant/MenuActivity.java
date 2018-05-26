package com.smartrestaurant.mixxamm.smartrestaurant;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.smartrestaurant.mixxamm.smartrestaurent.R;

public class MenuActivity extends AppCompatActivity {


    private ListView lvLijst;
    CustomListviewAdaptor adaptor;
    Button btnBetalen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


            lvLijst = findViewById(R.id.lvLijst);

            String[] listNames = getIntent().getStringArrayExtra("listProducts");
            adaptor = new CustomListviewAdaptor(listNames, this);
            lvLijst.setAdapter(adaptor);

            btnBetalen = findViewById(R.id.btnBetalen);

            btnBetalen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int intCount = lvLijst.getAdapter().getCount();
                    int intTeller = 0;
                    while (intTeller < intCount) {
                        TextView txtAantal = lvLijst.getChildAt(intTeller).findViewById(R.id.aantal);
                        Toast.makeText(getApplicationContext(), txtAantal.getText().toString(), Toast.LENGTH_SHORT).show();
                        intTeller++;
                    }
                }
            });


    }
    @Override
    protected void onPause() {
        super.onPause();
        ScanActivity.scanned = false;
    }
}
