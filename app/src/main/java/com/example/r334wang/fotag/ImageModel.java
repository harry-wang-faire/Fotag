package com.example.r334wang.fotag;

import android.graphics.Bitmap;

public class ImageModel {
        public int stars;
        public int images;
        boolean isbit;
        Bitmap bm;
        ImageModel(int s, int i) {
            stars = s;
            images = i;
            isbit = false;
        }
        ImageModel(int s,Bitmap b){
            stars = s;
            bm = b;
            isbit = true;
        }
}
