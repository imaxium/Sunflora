package com.example.sunflora.RoomDatabase;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

public class ConversorDeDatos {

    public static byte[] convertirBitmapAByteArray(Bitmap bitmap){
        ByteArrayOutputStream  stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    public static Bitmap convertirByteArrayABitmap(byte [] array){
        return BitmapFactory.decodeByteArray(array, 0, array.length);
    }
}
