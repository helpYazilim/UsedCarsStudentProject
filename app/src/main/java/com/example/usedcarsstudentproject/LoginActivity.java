package com.example.usedcarsstudentproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.usedcarsstudentproject.Models.LoginModel;
import com.example.usedcarsstudentproject.RestApi.ManagerAll;
import com.example.usedcarsstudentproject.Utils.Warnings;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText loginMailAdres, loginPassword;
    private Button loginButton;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences = getApplicationContext().getSharedPreferences("giris", 0);
        if (sharedPreferences.getString("uye_id", null) != null && sharedPreferences.getString("uye_Kullanici_Email", null) != null) {
            Intent ıntent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(ıntent);
        }


        tanimla();
        click();

    }

    public void tanimla(){
        loginMailAdres = findViewById(R.id.loginMailAddress);
        loginPassword = findViewById(R.id.loginPassword);
        loginButton = findViewById(R.id.loginButon);
    }

    public void click(){
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = loginMailAdres.getText().toString();
                String pass = loginPassword.getText().toString();
                login(mail,pass);
                delete();

            }
        });

    }

    public void delete(){
        loginMailAdres.setText("");
        loginPassword.setText("");
    }

    public void login(String mailAdres,String parola){
        Call<LoginModel> req = ManagerAll.getInstance().girisYap(mailAdres,parola);

        req.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                if (response.body().isTf()) {

                    Toast.makeText(getApplicationContext(), response.body().getText(), Toast.LENGTH_LONG).show();

                    String uyeId = response.body().getId().toString();
                    String kullaniciAdi = response.body().getUsername().toString();
                    String email = response.body().getMailadres().toString();
                    sharedPreferences = getApplicationContext().getSharedPreferences("giris",0);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("uye_id",uyeId);
                    editor.putString("uye_Kullanici_Adi",kullaniciAdi);
                    editor.putString("uye_Kullanici_Email",email);
                    editor.commit();
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);

                }else {
                    Toast.makeText(getApplicationContext(), response.body().getText(), Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), Warnings.internetProblemText, Toast.LENGTH_LONG).show();
            }
        });
    }

}
