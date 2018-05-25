package com.smartrestaurant.mixxamm.smartrestaurant;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.security.ProviderInstaller;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.net.ssl.HttpsURLConnection;

public class Order extends AsyncTask<String, Void, String> {
    private Context context;
    Order(Context context1){
        context = context1;
    }

    private String category, name, price;
    @Override
    protected String doInBackground(String... params) {
        try {
            ProviderInstaller.installIfNeeded(context);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
        String type = params[0];
        if("getMenu".equals(type)){
            String restaurantID = params[1];
            String urlString = "https://smartpass.one/smartrestaurant/getmenu.php";
            try {
                URL url = new URL(urlString);
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
                httpsURLConnection.setRequestMethod("POST");
                httpsURLConnection.setDoOutput(true);
                httpsURLConnection.setDoInput(true);
                OutputStream outputStream = httpsURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("restaurantID", "UTF-8") + "=" + URLEncoder.encode(restaurantID, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpsURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line;
                while((line = bufferedReader.readLine()) != null){
                    result += line;
                }

                JSONObject jsonObject = new JSONObject(result);
                category = jsonObject.getString("category");
                name = jsonObject.getString("name");
                price = jsonObject.getString("price");
                bufferedReader.close();
                inputStream.close();
                httpsURLConnection.disconnect();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    protected void onPostExecute(String result){

        Log.d("Test", name);

        List<String> listCategories = new ArrayList<>();
        List<String> listNames = new ArrayList<>();
        List<Double> listPrijzen = new ArrayList<>();


        StringTokenizer STNAM = new StringTokenizer(name, "|");
        StringTokenizer STPR = new StringTokenizer(price, "|");
        StringTokenizer STCAT = new StringTokenizer(category, "|");
        while(STNAM.hasMoreTokens()) {
            String product = STCAT.nextToken() + ": " + STNAM.nextToken() + ", €" + STPR.nextToken().replace(".", ",");
            listNames.add(product);
        }

        String[] strListNames = new String[listNames.size()];
        strListNames = listNames.toArray(strListNames);



        Intent intent = new Intent(context, MenuActivity.class);
        intent.putExtra("listName", strListNames);
        context.startActivity(intent);
    }


}
