package com.example.r334wang.fotag;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;

import java.util.ArrayList;

// Using recyclerview for displaying images
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    public ArrayList<ImageModel> imgmodels;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgview;
        public RatingBar ratingBar;
        public Button button;
        public ViewHolder(View v) {
            super(v);
            imgview = v.findViewById(R.id.imageView);
            ratingBar = v.findViewById(R.id.ratingBar2);
            button = v.findViewById(R.id.button);
        }
    }

    public MyAdapter(ArrayList<ImageModel> i) {
        imgmodels = i;
    }
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.imgview.setImageResource(imgmodels.get(position).images);
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgmodels.get(position).stars = 0;
                holder.ratingBar.setRating(0);
            }
        });
        holder.ratingBar.setRating(imgmodels.get(position).stars);
        holder.ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                imgmodels.get(position).stars = (int) v;
            }
        });
    }

    @Override
    public int getItemCount() {
        return imgmodels.size();
    }
}