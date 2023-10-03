package com.example.dropy.ui.utils.compressimages;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class Compressbitmap {
    public static Bitmap compressImage(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 50, baos);// 100baos
        int options = 50;
        while (baos.toByteArray().length / 1024 > 100) { // 100kb,
            baos.reset();// baosbaos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// options%baos
            options -= 10;// 10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(
                baos.toByteArray());// baosByteArrayInputStream
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// ByteArrayInputStream
        return bitmap;
    }

    public static void compress(Bitmap bitmap, double maxSize) {
        // bitmapbitmap
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //
        bitmap.compress(Bitmap.CompressFormat.PNG, 70, baos);
        byte[] b = baos.toByteArray();
        // KB
        double mid = b.length / 1024;
        // bitmap
        double i = mid / maxSize;
        // bitmap
        if (i > 1) {
            //
            //
          bitmap = scale(bitmap, bitmap.getWidth() / Math.sqrt(i),
                    bitmap.getHeight() / Math.sqrt(i));
        }
    }

    public static Bitmap scale(Bitmap src, double newWidth, double newHeight) {
        // src
        float width = src.getWidth();
        float height = src.getHeight();
        // matrix
        Matrix matrix = new Matrix();
        //
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        //
        matrix.postScale(scaleWidth, scaleHeight);
        //
        return Bitmap.createBitmap(src, 0, 0, (int) width, (int) height,
                matrix, true);
    }

    public static Bitmap scale(Bitmap src, Matrix scaleMatrix) {
        return Bitmap.createBitmap(src, 0, 0, src.getWidth(),
                src.getHeight(), scaleMatrix, true);
    }

    public static Bitmap scale(Bitmap src, float scaleX, float scaleY) {
        Matrix matrix = new Matrix();
        matrix.postScale(scaleX, scaleY);
        return Bitmap.createBitmap(src, 0, 0, src.getWidth(),
                src.getHeight(), matrix, true);
    }

    public static Bitmap scale(Bitmap src, float scale) {
        return scale(src, scale, scale);
    }
}
