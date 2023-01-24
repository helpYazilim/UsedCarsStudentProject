package com.example.usedcarsstudentproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.usedcarsstudentproject.Models.ResimEkleModel;
import com.example.usedcarsstudentproject.RestApi.ManagerAll;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IlanResimlerActivity extends AppCompatActivity {

    Button resimSecButn, resimEkleButon, ilanYayınlaButon;
    ImageView secilenIlanResmiImageView;
    Bitmap bitmap;
    String uye_id, ilan_id, image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilan_resimler);

        tanimla();
        // Activityler arasi gonderilen verileri almak icin Bundle getExtras kullaniyoruz
        Bundle bundle = getIntent().getExtras();
        // Eger Yakit Activity icindeki ,bu sekilde olsaydi intent.putExtra("uye_id",String.valueOf(response.body().getUyeId()));
        //uye_id = bundle.getString("uye_id");  // o zaman bu kod bu sekilde olabilirdi
        uye_id =  bundle.getString("uye_id");
        ilan_id = bundle.getString("ilan_id");

        Log.i("use_id",uye_id);


    }

    private void tanimla() {

        resimSecButn = findViewById(R.id.resimSecButn);
        resimEkleButon = findViewById(R.id.resimEkleButon);
        ilanYayınlaButon = findViewById(R.id.ilanYayınlaButon);
        secilenIlanResmiImageView = findViewById(R.id.secilenIlanResmiImageView);

        resimSecButn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resimGoster();
            }
        });

        resimEkleButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yukle();
            }
        });

        ilanYayınlaButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IlanResimlerActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    // Resmi secip Ekranda gostermeye yarayan fonksiyon
    public void resimGoster(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,777);
    }

    public void yukle(){

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Resim Yukle");
        progressDialog.setMessage("Resminiz Yükleniyor Lütfen bekleyin ... ");
        progressDialog.setCancelable(false); // iptal edilebilirliği kapatıyor, sebebi ise bir Diyaloğu arka planda işlerken diyaloğun kapatılmasını engellemek
        progressDialog.show();

        image = imageToString();
        Call<ResimEkleModel> request = ManagerAll.getInstance().resimEkle(uye_id,ilan_id,image);
        request.enqueue(new Callback<ResimEkleModel>() {
            @Override
            public void onResponse(Call<ResimEkleModel> call, Response<ResimEkleModel> response) {
                if (response.body().isTf()){
                    Toast.makeText(getApplicationContext(), response.body().getSonuc(), Toast.LENGTH_LONG).show();
                    progressDialog.cancel();
                }else {
                    Toast.makeText(getApplicationContext(), response.body().getSonuc(), Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<ResimEkleModel> call, Throwable t) {

            }
        });
    }

    // Resmi Bitmap'e cevirmeye yarayan fonksiyon
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 777 && resultCode == RESULT_OK && data != null){
            Uri path = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),path);
                secilenIlanResmiImageView.setImageBitmap(bitmap);
                secilenIlanResmiImageView.setVisibility(View.VISIBLE);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    // Resmin Base64 String'e cevirmeye yarayan fonksiyon
    public  String imageToString(){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100, byteArrayOutputStream);
        byte[] byt = byteArrayOutputStream.toByteArray();
        String imageToString = Base64.encodeToString(byt, Base64.DEFAULT);
        return imageToString;
    }

}
