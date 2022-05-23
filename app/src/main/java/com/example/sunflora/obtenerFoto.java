package com.example.sunflora;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class obtenerFoto {

    FirebaseStorage firebaseStorage= FirebaseStorage.getInstance();
    StorageReference storageReference = firebaseStorage.getReference();

    public Bitmap descargarImagen(String url) {

        final Bitmap[] bitmap = new Bitmap[1];
        StorageReference reference = storageReference.child(url);

        long MAXBYTES = 1024*1024;

        reference.getBytes(MAXBYTES).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                bitmap[0] = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
        return bitmap[0];
    }
}
