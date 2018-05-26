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
    private double dblTotaal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


            lvLijst = findViewById(R.id.lvLijst);

            //Lijsten ophalen
            String[] listProducts = getIntent().getStringArrayExtra("listProducts");
            final String[] listPrices = getIntent().getStringArrayExtra("listPrices");
            //String[] listNames = getIntent().getStringArrayExtra("listNames");
            //String[] listCates = getIntent().getStringArrayExtra("listCates");
            adaptor = new CustomListviewAdaptor(listProducts, this);
            lvLijst.setAdapter(adaptor);

            btnBetalen = findViewById(R.id.btnBetalen);

            btnBetalen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int intCount = lvLijst.getAdapter().getCount();
                    int intTeller = 0;

                    while (intTeller < intCount) {
                        double dblPriceOne =  Double.parseDouble(listPrices[intTeller]);
                        TextView txtAantal = lvLijst.getChildAt(intTeller).findViewById(R.id.aantal);
                        int intPCount = Integer.parseInt(txtAantal.getText().toString());

                        double dblPrice = dblPriceOne * intPCount;
                        dblTotaal += Math.round(dblPrice);
                        intTeller++;
                    }

                    Toast.makeText(getApplicationContext(), "Totaal prijs: " + String.valueOf(dblTotaal), Toast.LENGTH_LONG).show();
                }
            });


    }
    @Override
    protected void onPause() {
        super.onPause();
        ScanActivity.scanned = false;
    }
}
