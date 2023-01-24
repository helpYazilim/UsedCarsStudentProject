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

public class AdresBilgileriActivity extends AppCompatActivity {

    EditText sehirBilgiEditText,ilceBilgiEditText,mahalleBilgiEditText;
    Button adresBilgisiButon,adresBilgisiButonGeri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adres_bilgileri);

        tanimla();
    }

    private void tanimla() {
        sehirBilgiEditText = findViewById(R.id.sehirBilgiEditText);
        ilceBilgiEditText = findViewById(R.id.ilceBilgiEditText);
        mahalleBilgiEditText = findViewById(R.id.mahalleBilgiEditText);

        adresBilgisiButon = findViewById(R.id.adresBilgisiButon);
        adresBilgisiButonGeri = findViewById(R.id.adresBilgisiButonGeri);

        // when go back previous Activity, than this code will make remember all information
        sehirBilgiEditText.setText(IlanVerModel.getSehir());
        ilceBilgiEditText.setText(IlanVerModel.getIlce());
        mahalleBilgiEditText.setText(IlanVerModel.getMahalle());

        adresBilgisiButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("denemeee",sehirBilgiEditText.getText().toString());
                Log.i("denemeee",ilceBilgiEditText.getText().toString());
                Log.i("denemeee",mahalleBilgiEditText.getText().toString());
                if (!sehirBilgiEditText.getText().toString().equals("") && !ilceBilgiEditText.getText().toString().equals("") && !mahalleBilgiEditText.getText().toString().equals("")) {

                    // when go back previous Activity, than this code will make remember all information
                    IlanVerModel.setSehir(sehirBilgiEditText.getText().toString());
                    IlanVerModel.setIlce(ilceBilgiEditText.getText().toString());
                    IlanVerModel.setMahalle(mahalleBilgiEditText.getText().toString());

                    Intent intent = new Intent(AdresBilgileriActivity.this, AracBilgileriActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(), "Tum Bilgileri giriniz", Toast.LENGTH_LONG).show();
                }
            }
        });

        adresBilgisiButonGeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdresBilgileriActivity.this,IlanTuruActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in_ters,R.anim.anim_out_ters);
                finish();
            }
        });

    }
}
