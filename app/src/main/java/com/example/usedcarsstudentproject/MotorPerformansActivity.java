package com.example.usedcarsstudentproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.usedcarsstudentproject.Models.IlanVerModel;

public class MotorPerformansActivity extends AppCompatActivity {

    EditText motorTipiBilgiEditText,motorHacmiEditText,azamiSuratEditText;
    Button motorBilgisiButon,motorBilgisiButonGeri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motor_performans);

        tanimla();
    }

    private void tanimla() {

        motorTipiBilgiEditText = findViewById(R.id.motorTipiBilgiEditText);
        motorHacmiEditText = findViewById(R.id.motorHacmiEditText);
        azamiSuratEditText = findViewById(R.id.azamiSuratEditText);

        // when go back previous Activity, than this code will make remember all information
        motorTipiBilgiEditText.setText(IlanVerModel.getMotortipi());
        motorHacmiEditText.setText(IlanVerModel.getMotorhacmi());
        azamiSuratEditText.setText(IlanVerModel.getSurat());

        motorBilgisiButon = findViewById(R.id.motorBilgisiButon);
        motorBilgisiButonGeri = findViewById(R.id.motorBilgisiButonGeri);

        motorBilgisiButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!motorTipiBilgiEditText.getText().toString().equals("") && !motorHacmiEditText.getText().toString().equals("") && !azamiSuratEditText.getText().toString().equals("")) {

                    // when go back previous Activity, than this code will make remember all information
                    IlanVerModel.setMotortipi(motorTipiBilgiEditText.getText().toString());
                    IlanVerModel.setMotorhacmi(motorHacmiEditText.getText().toString());
                    IlanVerModel.setSurat(azamiSuratEditText.getText().toString());

                    Intent intent = new Intent(MotorPerformansActivity.this, YakitActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(), "Tum Bilgileri Doldurun", Toast.LENGTH_SHORT).show();
                }
            }
        });

        motorBilgisiButonGeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MotorPerformansActivity.this,AracBilgileriActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in_ters,R.anim.anim_out_ters);
                finish();
            }
        });



    }
}
