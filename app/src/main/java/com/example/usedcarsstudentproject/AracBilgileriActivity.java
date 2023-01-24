package com.example.usedcarsstudentproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.usedcarsstudentproject.Models.IlanVerModel;

public class AracBilgileriActivity extends AppCompatActivity {

    EditText markaBilgiEditText, seriBilgiEditText, modelBilgiEditText,
            yilBilgiEditText, kmBilgiEditText;

    Button aracBilgisiButon, aracBilgisiButonGeri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arac_bilgileri);

        tanimla();
    }

    private void tanimla() {

        markaBilgiEditText = findViewById(R.id.markaBilgiEditText);
        seriBilgiEditText = findViewById(R.id.seriBilgiEditText);
        modelBilgiEditText = findViewById(R.id.modelBilgiEditText);
        yilBilgiEditText = findViewById(R.id.yilBilgiEditText);
        kmBilgiEditText = findViewById(R.id.kmBilgiEditText);

        // when go back previous Activity, than this code will make remember all information
        markaBilgiEditText.setText(IlanVerModel.getMarka());
        seriBilgiEditText.setText(IlanVerModel.getSeri());
        modelBilgiEditText.setText(IlanVerModel.getModel());
        yilBilgiEditText.setText(IlanVerModel.getYil());
        kmBilgiEditText.setText(IlanVerModel.getKm());

        aracBilgisiButon = findViewById(R.id.aracBilgisiButon);
        aracBilgisiButonGeri = findViewById(R.id.aracBilgisiButonGeri);

        aracBilgisiButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!markaBilgiEditText.getText().toString().equals("") && !seriBilgiEditText.getText().toString().equals("") && !modelBilgiEditText.getText().toString().equals("") &&
                        !yilBilgiEditText.getText().toString().equals("") && !kmBilgiEditText.getText().toString().equals("")) {

                    // when go back previous Activity, than this code will make remember all information
                    IlanVerModel.setMarka(markaBilgiEditText.getText().toString());
                    IlanVerModel.setSeri(seriBilgiEditText.getText().toString());
                    IlanVerModel.setModel(modelBilgiEditText.getText().toString());
                    IlanVerModel.setYil(yilBilgiEditText.getText().toString());
                    IlanVerModel.setKm(kmBilgiEditText.getText().toString());


                    Intent intent = new Intent(AracBilgileriActivity.this, MotorPerformansActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Tum Bilgileri Doldurun", Toast.LENGTH_SHORT).show();
                }
            }
        });

        aracBilgisiButonGeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AracBilgileriActivity.this, AdresBilgileriActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in_ters, R.anim.anim_out_ters);
                finish();
            }
        });


    }
}
