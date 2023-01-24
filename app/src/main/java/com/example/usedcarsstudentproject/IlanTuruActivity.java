package com.example.usedcarsstudentproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.usedcarsstudentproject.Models.IlanVerModel;

import java.util.ArrayList;
import java.util.List;

public class IlanTuruActivity extends AppCompatActivity {

    Spinner ilanturuSpinner,kimdenSpinner;
    Button ilanTuruButton,ilanTuruButtonGeri;
    List<String> turList;
    List<String> sahipList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilan_turu);

        listeDoldur();
        tanimla();
    }

    private void tanimla() {
        ilanturuSpinner = findViewById(R.id.ilanturuSpinner);
        kimdenSpinner = findViewById(R.id.kimdenSpinner);

        ArrayAdapter<String> turListAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item,turList);
        turListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ilanturuSpinner.setAdapter(turListAdapter);

        ArrayAdapter<String> sahipListAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item,sahipList);
        sahipListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        kimdenSpinner.setAdapter(sahipListAdapter);

        ilanTuruButton = findViewById(R.id.ilanTuruButton);
        ilanTuruButtonGeri = findViewById(R.id.ilanTuruButtonGeri);

        ilanTuruButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                IlanVerModel.setKimden(kimdenSpinner.getSelectedItem().toString());
                IlanVerModel.setIlantipi(ilanturuSpinner.getSelectedItem().toString());
                Intent intent = new Intent(IlanTuruActivity.this,AdresBilgileriActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
                finish();
            }
        });

        ilanTuruButtonGeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IlanTuruActivity.this,IlanBilgileriActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in_ters,R.anim.anim_out_ters);
                finish();
            }
        });
    }
    public void listeDoldur(){

        turList = new ArrayList<>();
        sahipList = new ArrayList<>();
        turList.add("Satılık");
        turList.add("Kiralık");
        sahipList.add("Sahibinden");
        sahipList.add("Galeriden");

    }
}
