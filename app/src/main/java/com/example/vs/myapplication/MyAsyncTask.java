package com.example.vs.myapplication;
//Async Task for MovieDetails,Trailers and Reviews
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.vs.myapplication.ProductsActivity.category;

public class MyAsyncTask extends AsyncTask<String, Void, Void>{

   // private final String RESULTS="results",ID="id",ORIGINAL_TITLE="original_title",OVERVIEW="overview",POSTER_PATH="poster_path",BACKDROP_PATH="backdrop_path",RELEASE_DATE="release_date",VOTE_AVERAGE="vote_average",POPULARITY="popularity",NAME="name",KEY="key",CONTENT="content",AUTHOR="author",URL="url";
    private final String BUrl="https://venkatsaid.github.io/bank.json",PUrl="https://venkatsaid.github.io/product.json";
    private final String PCUrl="https://venkatsaid.github.io/product_category.json",OffUrl="https://venkatsaid.github.io/offer.json";

    private String data="";
    private ArrayList<ProductPojo> Products=new ArrayList<ProductPojo>();
    private ArrayList<ProductCategory> Pcategory=new ArrayList<ProductCategory>();
    private ArrayList<OfferPojo> Offers=new ArrayList<OfferPojo>();
    private ArrayList<Bank> Bank=new ArrayList<Bank>();
    MyAdapter adapterObj;
    TrailerReviewAdapter trailerReviewAdapter;
    //TrailerReviewAdapter trailerReviewAdapter;
    private ProgressDialog progressDialog;
    Context ct;
    int url_check;
    String cats;
    List<Integer> prices=new ArrayList<>();
    List<String> bId=new ArrayList<>();

    HashMap<String,Integer> bfinal= new HashMap<>();

    public MyAsyncTask(Context context,int id,String category) {
        this.ct=context;
        this.url_check=id;
        this.cats=category;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(ct);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    protected Void doInBackground(String... strings) {
    //    final String img_p_url=ct.getString(R.string.image_poster_path),img_b_url=ct.getString(R.string.image_backdrop_path);
        try {
            if (url_check==1) {//to get movieDetails data

                data = NetworkUtilities.GetJson(strings);
                Log.i("data",data);
                JSONObject jo = null;
                jo = new JSONObject(data);
                JSONArray JA = (jo.getJSONArray("product_category"));
                for (int i = 0; i < JA.length(); i++) {
                    JSONObject Obj = JA.getJSONObject(i);
                    Log.i("12345", Obj.getString("productCategory"));
                    ProductCategory productCategory= new ProductCategory();
                    productCategory.setCategoryId(Obj.getInt("categoryId"));
                    productCategory.setProductCategory(Obj.getString("productCategory"));
                    Pcategory.add(productCategory);
                }

            }
            else if (url_check==2){//to get trailers data
                data = NetworkUtilities.GetJson(strings);
                JSONObject JO = null;
                JO = new JSONObject(data);
                JSONArray JA = (JO.getJSONArray("product"));
                for (int i = 0; i < JA.length(); i++) {
                    JSONObject po = JA.getJSONObject(i);
                    ProductPojo productPojo=new ProductPojo();
                    productPojo.setProductId(po.getString("productId"));
                    productPojo.setProductName(po.getString("productName"));
                    productPojo.setProductCategory(po.getString("productCategory"));
                    productPojo.setProductSpecification(po.getString("productSpecification"));
                    productPojo.setProductPrice(po.getString("productPrice"));
                    productPojo.setProductPrice(po.getString("productImageURL"));
                    productPojo.setProductPrice(po.getString("productSearchTextURL"));

                    Products.add(productPojo);}
            }
            else if (url_check==3){//to get reviews data
                data = NetworkUtilities.GetJson(strings);
                JSONObject JO = null;
                JO = new JSONObject(data);
                JSONArray JA = (JO.getJSONArray("offer"));
                for (int i = 0; i < JA.length(); i++) {
                    JSONObject oj = JA.getJSONObject(i);
                    OfferPojo offers = new OfferPojo();
                    offers.setBankId(oj.getString("bankId"));
                    bId.add(oj.getString("bankId"));
                    offers.setProductDiscountedPrice(oj.getString("productDiscountedPrice"));
                    prices.add(Integer.parseInt(oj.getString("productDiscountedPrice")));
                    offers.setProductDiscountedPrice(oj.getString("productId"));
                    bfinal.put(oj.getString("productId"),Integer.parseInt(oj.getString("productDiscountedPrice")));
                    Offers.add(offers);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
    @Override
    protected void onPostExecute(Void v) {
        super.onPostExecute(v);
        if (url_check==1) {
            progressDialog.dismiss();
            /*if (ct.getApplicationContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                MainActivity.recyclerView.setLayoutManager(new GridLayoutManager(ct, 2));
            } else {
                MainActivity.recyclerView.setLayoutManager(new GridLayoutManager(ct, 3));
            }*/
            MainActivity.recyclerView.setLayoutManager(new LinearLayoutManager(ct));
            adapterObj = new MyAdapter((MainActivity) ct, Pcategory);
            MainActivity.recyclerView.setAdapter(adapterObj);
        }
        else if (url_check==2){
            progressDialog.dismiss();
            if (!Products.isEmpty()) {
                ProductsActivity.recyclerView1.setLayoutManager(new LinearLayoutManager(ct));
                trailerReviewAdapter = new TrailerReviewAdapter((ProductsActivity) ct,Products, Bank, 10,cats);
                ProductsActivity.recyclerView1.setAdapter(trailerReviewAdapter);
            }/*
            else {
                Main2Activity.trailer_tv.setText(R.string.No_Trailers);
            }*/
        }
        else if (url_check==3){
            progressDialog.dismiss();
            Collections.sort(prices);
            int min=Collections.min(bfinal.values());
            Log.i("min",String.valueOf(min));
            String s= (String) getKeyFromValue(bfinal,min);
            String bname="";
            if(s.equals("1")){
                bname="SBI";
            }else if(s.equals("2")){
                bname="DBS";
            }else if(s.equals("3")){
                bname="ICICI";
            }else if(s.equals("4")){
                bname="PayTM";
            }else if(s.equals("5")){
                bname="Mobikwik";
            }
            OfferActivity.bankfinal.setText(bname);
            if (!Offers.isEmpty()){
                OfferActivity.recyclerView2.setLayoutManager(new LinearLayoutManager(ct));
                trailerReviewAdapter =new TrailerReviewAdapter((OfferActivity) ct,Offers, Bank,11,cats);
                OfferActivity.recyclerView2.setAdapter(trailerReviewAdapter);}

        }

    }

    public static Object getKeyFromValue(Map hm, Object value) {
        for (Object o : hm.keySet()) {
            if (hm.get(o).equals(value)) {
                return o;
            }
        }
        return null;
    }
}
