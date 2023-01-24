package com.example.usedcarsstudentproject;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class KullaniciBilgileriBuyukResimActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kullanici_bilgileri_buyuk_resim);

        String imageUrl = getIntent().getStringExtra("image");
        ImageView iv = findViewById(R.id.userImage);

        Picasso.with(getApplicationContext()).load(imageUrl).into(iv);
    }
}
