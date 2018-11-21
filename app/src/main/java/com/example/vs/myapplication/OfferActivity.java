package com.example.vs.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

public class OfferActivity extends AppCompatActivity {
static String id;
static RecyclerView recyclerView2;
static TextView bankfinal;
String OffUrl="https://venkatsaid.github.io/offer.json";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer);
        recyclerView2=findViewById(R.id.recycler2);
        bankfinal=findViewById(R.id.suggestbank);
        id=getIntent().getStringExtra("pID");
        MyAsyncTask myAsyncTask=new MyAsyncTask(this,3,id);
        myAsyncTask.execute(OffUrl);
    }
}
