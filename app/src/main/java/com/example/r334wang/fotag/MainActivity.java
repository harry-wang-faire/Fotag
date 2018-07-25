package com.example.r334wang.fotag;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.media.Rating;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.SearchView;
import android.widget.Toolbar;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    boolean loaded = false;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    Model m = new Model();
    int [] images = {
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,
            R.drawable.image5,
            R.drawable.image6,
            R.drawable.image7,
            R.drawable.image8,
            R.drawable.image9,
            R.drawable.image10,
            R.drawable.image11
            };
    ArrayList<ImageModel> imgmodels = new ArrayList<>();
    ArrayList<ImageModel> loadPhoto = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        for (int i = 0; i < images.length; i++){
            ImageModel im = new ImageModel(0,images[i]);
            loadPhoto.add(im);
        }
        this.setContentView(R.layout.activity_main);
        final ImageButton upload = findViewById(R.id.upload);
        recyclerView = findViewById(R.id.rv);
        final RatingBar rb = findViewById(R.id.ratingBar);
        final ImageButton reset = findViewById(R.id.clearFilter);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m.rating = 0;
                rb.setRating(0);
            }
        });
        rb.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                if (loaded) {
                    m.rating = (int) v;
                    ArrayList<ImageModel> arr = new ArrayList<>();
                    for (int i = 0; i < imgmodels.size(); i++) {
                        if (imgmodels.get(i).stars >= m.rating) arr.add(imgmodels.get(i));
                    }
                    MyAdapter temp = new MyAdapter(arr);
                    recyclerView.setAdapter(temp);
                }
            }
        });

        final SearchView searchView = findViewById(R.id.searchView);
        searchView.setMaxWidth(android.R.attr.width);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                try {
                searchView.setIconified(true);
                searchView.clearFocus();
                URL url = new URL(s);
                Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                imgmodels.add(new ImageModel(0,bmp));
                MyAdapter ma = new MyAdapter(imgmodels);
                recyclerView.setAdapter(ma);
                }catch (Exception e){
                    e.printStackTrace();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

//        ImageButton search = findViewById(R.id.search);
//        search.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                URL url = null;
//                try {
//                    url = new URL("http://image10.bizrate-images.com/resize?sq=60&uid=2216744464");
//                    Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });

//       layoutManager = new GridLayoutManager(this,2);

       layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!loaded){
                    loaded = true;
                    for (int i = 0; i < loadPhoto.size(); i++){
                        imgmodels.add(loadPhoto.get(i));
                    }
                    MyAdapter ma = new MyAdapter(imgmodels);
                    recyclerView.setAdapter(ma);

                }
            }
        });
    }


}
