package com.example.vs.myapplication;
//Adapter for Trailers and Reviews
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class TrailerReviewAdapter extends RecyclerView.Adapter<TrailerReviewAdapter.TrailerViewHolder> {

    Context ct;
    int c=0;
    int id;
    ArrayList<ProductPojo> trailer=new ArrayList<>();
    ArrayList<OfferPojo> offerlist=new ArrayList<>();
    ArrayList<Bank> reviews=new ArrayList<>();
    String cat;

    public TrailerReviewAdapter(ProductsActivity context, ArrayList<ProductPojo> trailer, ArrayList<Bank> reviews, int id,String cat) {
        this.ct=context;
        this.trailer=trailer;
        this.reviews=reviews;
        this.id=id;
        this.cat=cat;
        Log.i("test",cat);
    }
    public TrailerReviewAdapter(OfferActivity context, ArrayList<OfferPojo> offerlist, ArrayList<Bank> reviews, int id,String cat) {
        this.ct=context;
        this.offerlist=offerlist;
        this.reviews=reviews;
        this.id=id;
        this.cat=cat;
        Log.i("test",cat);
    }
    @NonNull
    @Override
    public TrailerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v1=null;
        if (id==10) {//for trailers
            v1 = LayoutInflater.from(ct).inflate(R.layout.productdetail, parent, false);
        }
        else if (id==11){//for reviews
            v1 = LayoutInflater.from(ct).inflate(R.layout.offer_list, parent, false);
        }
        return new TrailerViewHolder(v1);

    }

    @Override
    public void onBindViewHolder(@NonNull TrailerViewHolder holder, final int position) {
        if (id==10) {//for reviews
            if(trailer.get(position).getProductCategory().equals(cat)) {
                Log.i("test",trailer.get(position).getProductName());
                holder.ProductName.setText(trailer.get(position).getProductName());
                holder.spec.setText(trailer.get(position).getProductSpecification());
                holder.price.setText(trailer.get(position).getProductPrice());
                final String id= trailer.get(position).getProductId();
                holder.ProductName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ct, OfferActivity.class);
                        intent.putExtra("pID", id);
                        ct.startActivity(intent);

                    }
                });
            }else {
                holder.ProductName.setText(null);
                holder.spec.setText(null);
                holder.price.setText(null);
            }
        }
        else if (id==11){//for reviews
            String s=offerlist.get(position).getBankId();
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
            holder.bankname.setText(bname);
            holder.dprice.setText(offerlist.get(position).getProductDiscountedPrice());
//            holder.author.setText(reviews.get(position).author);
//            holder.content.setText(reviews.get(position).content);
//            holder.url.setText(reviews.get(position).url);
        }
    }

    @Override
    public int getItemCount() {
        int count=0;
        if (id==10){ count=trailer.size();}//for trailers
        else if (id==11){ count=reviews.size();}//for reviews
        return count;
    }

    public class TrailerViewHolder extends RecyclerView.ViewHolder {
        ImageView trailerImage;
        TextView ProductName,spec,price,bankname,dprice;
        public TrailerViewHolder(View itemView) {
            super(itemView);
            if (id==10){//for trailers
                //trailerImage= (ImageView) itemView.findViewById(R.id.button);
                ProductName=(TextView) itemView.findViewById(R.id.productName);
                spec=itemView.findViewById(R.id.productSpec);
                price=itemView.findViewById(R.id.productPrice);
            }
            else if (id==11){//for  Reviews
                /*author=itemView.findViewById(R.id.action_author);
                content=itemView.findViewById(R.id.action_content);
                url=itemView.findViewById(R.id.action_url);*/
                bankname=itemView.findViewById(R.id.bankName);
                dprice=itemView.findViewById(R.id.dprice);
            }

        }
    }
}

