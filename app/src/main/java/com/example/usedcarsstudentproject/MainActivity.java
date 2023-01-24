package com.example.usedcarsstudentproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.example.usedcarsstudentproject.Adapters.FavoriSliderAdapter;
import com.example.usedcarsstudentproject.Models.FavoriSliderModel;
import com.example.usedcarsstudentproject.Models.UserInfoModel;
import com.example.usedcarsstudentproject.RestApi.ManagerAll;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    SharedPreferences sharedPreferences;

    String navHeaderTextUserName,navHeaderTextUserEmail;
    TextView navHeaderTextViewUserName,navHeaderTextViewEmail;

    SharedPreferences.Editor editor;
    Button ilanVerButton,ilanlarimMenuButon,ilanlarButon,iletisimBilgileri;
    ViewPager mainActivityViewPagerSliderFavori;
    CircleIndicator mainActivitySliderCircleIndicatorFavori;
    String uye_id;
    FavoriSliderAdapter favoriSliderAdapter;

    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sharedPreferences = getApplicationContext().getSharedPreferences("giris", 0);
        navHeaderTextUserName = sharedPreferences.getString("uye_Kullanici_Adi", null);
        navHeaderTextUserEmail = sharedPreferences.getString("uye_Kullanici_Email", null);
        uye_id = sharedPreferences.getString("uye_id", null);  //  Login Activity kullanicilarini hatirlayan SharedPreferences dir...


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        navHeaderTextViewUserName = headerView.findViewById(R.id.navHeaderTextUserName);
        navHeaderTextViewUserName.setText(navHeaderTextUserName);

        navHeaderTextViewEmail = headerView.findViewById(R.id.navHeaderTextEmail);
        navHeaderTextViewEmail.setText(navHeaderTextUserEmail);

//        notifyDataSetChanged();
        tanimla();
        getMainSlider();


        getNavHeaderResim(uye_id);

    }

    public void getNavHeaderResim(String uye_id){
        Call<UserInfoModel> request = ManagerAll.getInstance().getInformation(uye_id);
        request.enqueue(new Callback<UserInfoModel>() {
            @Override
            public void onResponse(Call<UserInfoModel> call, Response<UserInfoModel> response) {
                if (response.isSuccessful()){

                    imageView = findViewById(R.id.imageViewDrawer);

//                    Picasso.with(getApplicationContext()).load(response.body().getResim()).into(imageView);
                    Picasso.with(getApplicationContext()).load(response.body().getResim()).resize(200,180).into(imageView);

//                    Log.i("resim:",response.body().getResim());
//                    Log.i("Username:",response.body().getKadi());
//                    Log.i("Email:",response.body().getMailAdres());
//                    Log.i("sifre:",response.body().getSifre());
                }
            }

            @Override
            public void onFailure(Call<UserInfoModel> call, Throwable t) {

            }
        });
    }

    public void tanimla(){

        mainActivityViewPagerSliderFavori = findViewById(R.id.mainActivitySliderFavori);
        mainActivitySliderCircleIndicatorFavori = findViewById(R.id.mainActivitysliderCircle);

        ilanVerButton = findViewById(R.id.ilanVerButon);
        ilanVerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,IlanBilgileriActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
            }
        });

        ilanlarimMenuButon = findViewById(R.id.ilanlarimMenuButon);
        ilanlarimMenuButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, IlanlarimActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
            }
        });

        ilanlarButon = findViewById(R.id.ilanlarButon);
        ilanlarButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, IlanlarActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
            }
        });

        iletisimBilgileri = findViewById(R.id.iletisimBilgileri);
        iletisimBilgileri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, UserUpdateActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
            }
        });

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.cikisYap) {

            editor = sharedPreferences.edit();
            editor.clear();
            editor.commit();
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void getMainSlider(){
        Call<List<FavoriSliderModel>> request = ManagerAll.getInstance().setSlider(uye_id);
        request.enqueue(new Callback<List<FavoriSliderModel>>() {
            @Override
            public void onResponse(Call<List<FavoriSliderModel>> call, Response<List<FavoriSliderModel>> response) {
                if (response.body().get(0).isTf()){

                    if (response.body().size()>0) {
                        favoriSliderAdapter = new FavoriSliderAdapter(response.body(), MainActivity.this, MainActivity.this);
                        mainActivityViewPagerSliderFavori.setAdapter(favoriSliderAdapter);
                        mainActivitySliderCircleIndicatorFavori.setViewPager(mainActivityViewPagerSliderFavori);
                        mainActivitySliderCircleIndicatorFavori.bringToFront();
                    }

                }

                else {  // Favori resmi bos oldugunda yada , favori resmi olmadiginda, serverdan bos.jpg resmini ceker ekrana
                    favoriSliderAdapter = new FavoriSliderAdapter(response.body(), MainActivity.this, MainActivity.this);
                    mainActivityViewPagerSliderFavori.setAdapter(favoriSliderAdapter);
                    mainActivitySliderCircleIndicatorFavori.setViewPager(mainActivityViewPagerSliderFavori);
                    mainActivitySliderCircleIndicatorFavori.bringToFront();
                } // Favori resmi bos oldugunda yada , favori resmi olmadiginda, serverdan bos.jpg resmini ceker ekrana


            }

            @Override
            public void onFailure(Call<List<FavoriSliderModel>> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getMainSlider();
    }

}
