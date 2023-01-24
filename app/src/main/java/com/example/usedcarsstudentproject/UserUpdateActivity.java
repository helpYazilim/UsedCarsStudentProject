package com.example.usedcarsstudentproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.usedcarsstudentproject.Models.UserInfoModel;
import com.example.usedcarsstudentproject.Models.UserUpdateModel;
import com.example.usedcarsstudentproject.RestApi.ManagerAll;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserUpdateActivity extends AppCompatActivity {

    EditText userbilgi_userName,userbilgi_userEmail, userbilgi_pass;
    Button userbilgi_buton;

    SharedPreferences sharedPreferences;
    String uye_id;

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_update);

        tanimla();

        sharedPreferences = getApplicationContext().getSharedPreferences("giris", 0);
        uye_id = sharedPreferences.getString("uye_id", null);

        getKullaniciBilgileri(uye_id);


    }

    public void tanimla(){

        userbilgi_userName = findViewById(R.id.userbilgi_name);
        userbilgi_userEmail = findViewById(R.id.userbilgi_email);

        imageView = findViewById(R.id.circleImageView);

        userbilgi_pass = findViewById(R.id.userbilgi_pass);

        userbilgi_buton = findViewById(R.id.userbilgi_buton);

        userbilgi_buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userUpdate(uye_id,userbilgi_userName.getText().toString(),userbilgi_userEmail.getText().toString(),userbilgi_pass.getText().toString());
            }
        });

    }

    public void getKullaniciBilgileri(String uye_id){
        Call<UserInfoModel> request = ManagerAll.getInstance().getInformation(uye_id);
        request.enqueue(new Callback<UserInfoModel>() {
            @Override
            public void onResponse(Call<UserInfoModel> call, final Response<UserInfoModel> response) {
                if (response.isSuccessful()){
                    userbilgi_userName.setText(response.body().getKadi());
                    userbilgi_userEmail.setText(response.body().getMailAdres());
                    imageView = findViewById(R.id.circleImageView);
                    userbilgi_pass.setText(response.body().getSifre());

                    Picasso.with(getApplicationContext()).load(response.body().getResim()).into(imageView);

                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.i("image:",response.body().getResim());
                            Intent intent = new Intent(UserUpdateActivity.this, KullaniciBilgileriBuyukResimActivity.class);
                            intent.putExtra("image",response.body().getResim());
                            startActivity(intent);

                        }
                    });

                    Log.i("email",response.body().getMailAdres());
                    Log.i("userName",response.body().getKadi());
                    Log.i("user",response.body().getSifre());

                    Log.i("resim",response.body().getResim());
                }
            }

            @Override
            public void onFailure(Call<UserInfoModel> call, Throwable t) {

            }
        });
    }

    public void userUpdate(String uyeid, String userName, String userEmail, String pass){

        Call<UserUpdateModel> request = ManagerAll.getInstance().userUpdateInformation(uyeid,userName,userEmail,pass);
        request.enqueue(new Callback<UserUpdateModel>() {
            @Override
            public void onResponse(Call<UserUpdateModel> call, Response<UserUpdateModel> response) {
                if (response.body().isTf()){
                    Intent intent = new Intent(UserUpdateActivity.this, UserUpdateActivity.class);
                    startActivity(intent);
                    finish();

                }
            }

            @Override
            public void onFailure(Call<UserUpdateModel> call, Throwable t) {

            }
        });
    }
}
