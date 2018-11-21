package com.example.vs.myapplication;
//Movie details adapter class
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    Context ct;
    ArrayList<ProductCategory> ProductCategory = new ArrayList<>();
    int[] img={R.drawable.electronics,R.drawable.lifestyle,R.drawable.fitness,R.drawable.books};
    public MyAdapter(MainActivity context, ArrayList<ProductCategory> list) {
        this.ct=context;
        this.ProductCategory=list;
    }

   @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        
        View v1=LayoutInflater.from(ct).inflate(R.layout.category_list,parent,false);
        return new MyViewHolder(v1);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //Picasso.with(ct).load(movie.get(position).poster_url).error(R.mipmap.no_image).into(holder.images);
        if(position==0){
            holder.images.setImageResource(R.drawable.electronics);
        }
        else if(position==1){
            holder.images.setImageResource(R.drawable.lifestyle);
        }
        else if(position==2){
            holder.images.setImageResource(R.drawable.fitness);
        }
        else if(position==3){
            holder.images.setImageResource(R.drawable.books);
        }
        holder.textView.setText(ProductCategory.get(position).getProductCategory());
        final String cat=ProductCategory.get(position).getProductCategory();
        holder.images.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(ct,ProductsActivity.class);
                intent.putExtra("category",cat);
                ct.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return (ProductCategory == null) ? 0 : ProductCategory.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView images;
        TextView textView;
        public MyViewHolder(View itemView) {
            super(itemView);
            images=itemView.findViewById(R.id.cat_image);
            textView=itemView.findViewById(R.id.cat_name);
            /*images.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ArrayList<String> movieinfo=new ArrayList<>();
                    int position=getLayoutPosition();
                    Intent intent=new Intent(ct,Main2Activity.class);
                    intent.putStringArrayListExtra("Movie info",movieinfo);
                    v.getContext().startActivity(intent);
                }
            });*/
            }
    }
}
