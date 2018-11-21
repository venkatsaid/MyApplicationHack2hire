package com.example.vs.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

public class ProductsActivity extends AppCompatActivity {
    static String category;
    static RecyclerView recyclerView1;
    String PUrl="https://venkatsaid.github.io/product.json";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        recyclerView1=findViewById(R.id.Productrecylcer);
        category=getIntent().getStringExtra("category");
        MyAsyncTask myAsyncTask=new MyAsyncTask(this,2,category);
        myAsyncTask.execute(PUrl);
    }
}
