package com.example.vs.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    static RecyclerView recyclerView;
    TextView textView;
    private final String BUrl="https://venkatsaid.github.io/bank.json",PUrl="https://venkatsaid.github.io/product.json";
    private final String PCUrl="https://venkatsaid.github.io/product_category.json",OffUrl="https://venkatsaid.github.io/offer.json";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.a);
        recyclerView=findViewById(R.id.cycler);
        MyAsyncTask myAsyncTask=new MyAsyncTask(this,1,"");
        myAsyncTask.execute(PCUrl);

/*
        InputStream is = getResources().openRawResource(R.raw.product);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String jsonString = writer.toString();
        //textView.setText(jsonString);

        try {
            JSONObject jo=null;
            jo=new JSONObject(jsonString);
            JSONArray JA = (jo.getJSONArray("product_category"));
            for (int i = 0; i < JA.length(); i++) {
                JSONObject Obj = JA.getJSONObject(i);
                Log.i("12345",Obj.getString("productCategory"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            textView.setText(GetJson("https://venkatsaid.github.io/product.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
*/

    }
    /*public static String GetJson(String string) throws IOException {
        String data="",line="";
        URL jasonUrl= new URL(string);
        HttpURLConnection httpURLConnection= (HttpURLConnection) jasonUrl.openConnection();
        InputStream inputStream=httpURLConnection.getInputStream();
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
        while (line!=null){
            line=bufferedReader.readLine();
            data=data+line;
        }
        Log.i("data",data);
        return data;
    }*/
}
