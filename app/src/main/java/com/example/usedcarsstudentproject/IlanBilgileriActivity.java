package com.example.usedcarsstudentproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.usedcarsstudentproject.Models.IlanVerModel;

public class IlanBilgileriActivity extends AppCompatActivity {

    Button ilanBilgisiButon,ilanBilgisiButonGeri;
    EditText ilanAciklamaEditText,ilanBaslikEditText,ilanFiyatEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilan_bilgileri);

        tanimla();

    }

    private void tanimla() {
        ilanAciklamaEditText = findViewById(R.id.ilanAciklamaEditText);
        ilanBaslikEditText = findViewById(R.id.ilanBaslikEditText);
        ilanFiyatEditText = findViewById(R.id.ilanFiyatEditText);

        ilanBilgisiButon = findViewById(R.id.ilanBilgisiButon);
        ilanBilgisiButonGeri = findViewById(R.id.ilanBilgisiButonGeri);

        ilanAciklamaEditText.setText(IlanVerModel.getAciklama());
        ilanBaslikEditText.setText(IlanVerModel.getBaslik());
        ilanFiyatEditText.setText(IlanVerModel.getUcret());

        ilanBilgisiButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("denemeee",ilanAciklamaEditText.getText().toString());
                Log.i("denemeee",ilanBaslikEditText.getText().toString());
                if (!ilanAciklamaEditText.getText().toString().equals("") && !ilanAciklamaEditText.getText().toString().equals("") && !ilanFiyatEditText.getText().toString().equals("")) {
                    IlanVerModel.setAciklama(ilanAciklamaEditText.getText().toString());
                    IlanVerModel.setBaslik(ilanBaslikEditText.getText().toString());
                    IlanVerModel.setUcret(ilanFiyatEditText.getText().toString());

                    Intent intent = new Intent(IlanBilgileriActivity.this, IlanTuruActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(), "Tum Bilgileri Giriniz", Toast.LENGTH_LONG).show();
                }
            }
        });

        ilanBilgisiButonGeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IlanBilgileriActivity.this,MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in_ters,R.anim.anim_out_ters);
                finish();
            }
        });

    }
}
