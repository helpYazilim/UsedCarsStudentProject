package com.example.usedcarsstudentproject;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.usedcarsstudentproject.Adapters.SliderAdapter;
import com.example.usedcarsstudentproject.Models.FavoriEkleCikarModel;
import com.example.usedcarsstudentproject.Models.FavoriKontrolModel;
import com.example.usedcarsstudentproject.Models.IlanDetayModel;
import com.example.usedcarsstudentproject.Models.SliderModel;
import com.example.usedcarsstudentproject.RestApi.ManagerAll;

import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IlanDetayActivity extends AppCompatActivity {

    private TextView ilandetayBaslık, ilandetayFiyat, ilandetayMarka, ilandetayMotorTipi, ilandetayModel, ilandetaySeri, ilandetayYil, ilandetayIlanTipi, ilandetayKimden, ilandetayMotorHacmi, ilandetaySurat, ilandetayYakitTipi, ilandetayOrtalamaYakit, ilandetayDepoHacmi, ilandetayKm;
    private Button ilanMesajGonder, ilandetayFavoriyeAl;
    private ViewPager ilandetaySlider;

    String ilanId;
    List<SliderModel> list;
    SliderAdapter sliderAdapter;
    CircleIndicator circleIndicator;
    SharedPreferences sharedPreferences;
    String uye_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilan_detay);

        Bundle bundle = getIntent().getExtras();
        ilanId = bundle.getString("ilanid");
        sharedPreferences = getApplicationContext().getSharedPreferences("giris", 0);
        uye_id = sharedPreferences.getString("uye_id", null);

        tanimla();
        getIlanDetay();
        getResim();
        getText();
        action();

    }

    public void tanimla (){

        circleIndicator = findViewById(R.id.sliderCircle);


        ilandetayBaslık = findViewById(R.id.ilandetayBaslık);
        ilandetayFiyat = findViewById(R.id.ilandetayFiyat);
        ilandetayMarka = findViewById(R.id.ilandetayMarka);
        ilandetayModel = findViewById(R.id.ilandetayModel);
        ilandetaySeri = findViewById(R.id.ilandetaySeri);
        ilandetayYil = findViewById(R.id.ilandetayYil);
        ilandetayIlanTipi = findViewById(R.id.ilandetayIlanTipi);
        ilandetayKimden = findViewById(R.id.ilandetayKimden);
        ilandetayMotorHacmi = findViewById(R.id.ilandetayMotorHacmi);
        ilandetayMotorTipi = findViewById(R.id.ilandetayMotorTipi);
        ilandetaySurat = findViewById(R.id.ilandetaySurat);
        ilandetayYakitTipi = findViewById(R.id.ilandetayYakitTipi);
        ilandetayOrtalamaYakit = findViewById(R.id.ilandetayOrtalamaYakit);
        ilandetayDepoHacmi = findViewById(R.id.ilandetayDepoHacmi);
        ilandetayKm = findViewById(R.id.ilandetayKm);

        ilanMesajGonder = findViewById(R.id.ilanMesajGonder);
        ilandetayFavoriyeAl = findViewById(R.id.ilandetayFavoriyeAl);

        ilandetaySlider = findViewById(R.id.ilandetaySlider);

    }

    public void getIlanDetay(){
        Call<IlanDetayModel> request = ManagerAll.getInstance().ilanDetay(ilanId);
        request.enqueue(new Callback<IlanDetayModel>() {
            @Override
            public void onResponse(Call<IlanDetayModel> call, Response<IlanDetayModel> response) {

                Log.i( "ilanVarmi ",response.body().toString());

//                Test Github

                ilandetayBaslık.setText(response.body().getBaslik());
                ilandetayFiyat.setText(response.body().getUcret());
                ilandetayMarka.setText(response.body().getMarka());
                ilandetayModel.setText(response.body().getModel());
                ilandetaySeri.setText(response.body().getSeri());
                ilandetayYil.setText(response.body().getYil());
                ilandetayIlanTipi.setText(response.body().getIlantipi());
                ilandetayKimden.setText(response.body().getKimden());
                ilandetayMotorHacmi.setText(response.body().getMotorhacmi());
                ilandetayMotorTipi.setText(response.body().getMotortipi());
                ilandetaySurat.setText(response.body().getSurat());
                ilandetayYakitTipi.setText(response.body().getYakittipi());
                ilandetayOrtalamaYakit.setText(response.body().getOrtalamayakit());
                ilandetayDepoHacmi.setText(response.body().getDepohacmi());
                ilandetayKm.setText(response.body().getKm());

            }

            @Override
            public void onFailure(Call<IlanDetayModel> call, Throwable t) {

            }
        });
    }

    public void getResim()
    {
        Call<List<SliderModel>> request = ManagerAll.getInstance().ilanResimleri(ilanId);
        request.enqueue(new Callback<List<SliderModel>>() {
            @Override
            public void onResponse(Call<List<SliderModel>> call, Response<List<SliderModel>> response) {

                list = response.body();
                sliderAdapter = new SliderAdapter(list,getApplicationContext());
                ilandetaySlider.setAdapter(sliderAdapter);
                circleIndicator.setViewPager(ilandetaySlider);
                circleIndicator.bringToFront();
            }

            @Override
            public void onFailure(Call<List<SliderModel>> call, Throwable t) {

            }
        });
    }

    public void getText(){
        Call<FavoriKontrolModel> request = ManagerAll.getInstance().getButtonText(uye_id,ilanId);
        request.enqueue(new Callback<FavoriKontrolModel>() {
            @Override
            public void onResponse(Call<FavoriKontrolModel> call, Response<FavoriKontrolModel> response) {
                if (response.body().isTf()){
                    ilandetayFavoriyeAl.setText(response.body().getText());
                }else {
                    ilandetayFavoriyeAl.setText(response.body().getText());
                }

            }

            @Override
            public void onFailure(Call<FavoriKontrolModel> call, Throwable t) {

            }
        });
    }

    public void action(){
        ilandetayFavoriyeAl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<FavoriEkleCikarModel> request  = ManagerAll.getInstance().favoriEkleCikar(uye_id,ilanId);
                request.enqueue(new Callback<FavoriEkleCikarModel>() {
                    @Override
                    public void onResponse(Call<FavoriEkleCikarModel> call, Response<FavoriEkleCikarModel> response) {
                        if (response.body().isTf()){
                            Toast.makeText(getApplicationContext(), response.body().getText(), Toast.LENGTH_LONG).show();
                            getText();
                        }else {
                            Toast.makeText(getApplicationContext(), response.body().getText(), Toast.LENGTH_LONG).show();
                            getText();
                        }
                    }

                    @Override
                    public void onFailure(Call<FavoriEkleCikarModel> call, Throwable t) {

                    }
                });
            }
        });
    }


}
