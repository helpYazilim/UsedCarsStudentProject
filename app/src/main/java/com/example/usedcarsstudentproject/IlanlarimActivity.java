package com.example.usedcarsstudentproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.usedcarsstudentproject.Adapters.IlanlarimAdapter;
import com.example.usedcarsstudentproject.Models.IlanlarimModel;
import com.example.usedcarsstudentproject.Models.IlanlarimSilModel;
import com.example.usedcarsstudentproject.RestApi.ManagerAll;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IlanlarimActivity extends AppCompatActivity {

    ListView listView;
    IlanlarimAdapter ilanlarimAdapter;
    List<IlanlarimModel> ilanlarimModels;
    SharedPreferences sharedPreferences;
    String uye_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilanlarim);

        listView = findViewById(R.id.ilanlarimListView);
        sharedPreferences = getApplicationContext().getSharedPreferences("giris", 0);
        uye_id = sharedPreferences.getString("uye_id", null);

        ilanlarimiGoruntule();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Log.i( "testttt ",ilanlarimModels.get(position).getBaslik());
//                Log.i( "test ",ilanlarimModels.get(position).getIlanid());
                ilanlarimAlertDialog(IlanlarimActivity.this, ilanlarimModels.get(position).getIlanid());

            }
        });

    }

    public void ilanlarimiGoruntule(){

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Ilanlarım");
        progressDialog.setMessage("Ilanlariniz Yükleniyor Lütfen bekleyin ... ");
        progressDialog.setCancelable(false); // iptal edilebilirliği kapatıyor, sebebi ise bir Diyaloğu arka planda işlerken diyaloğun kapatılmasını engellemek
        progressDialog.show();

        ilanlarimModels = new ArrayList<>();
        Call<List<IlanlarimModel>> request = ManagerAll.getInstance().ilanlarim(uye_id);
        request.enqueue(new Callback<List<IlanlarimModel>>() {
            @Override
            public void onResponse(Call<List<IlanlarimModel>> call, Response<List<IlanlarimModel>> response) {
                if (response.isSuccessful()){
//                    Log.i("test 1",response.body().getIlanid() + "  /// " + response.body().getUyeid());
                    Log.i( "ilanVarmi ",response.body().toString());

                    ilanlarimModels = response.body();
//                    Log.i("kessttt",""+ilanlarimModels.size());
//                    if (ilanlarimModels.size()>0) {
                    if (response.body().get(0).isTf()) {
                        ilanlarimAdapter = new IlanlarimAdapter(ilanlarimModels, getApplicationContext(), IlanlarimActivity.this);
                        listView.setAdapter(ilanlarimAdapter);
                        Toast.makeText(getApplicationContext(), response.body().get(0).getSayi() + " tane ilanınız bulunmaktadır...", Toast.LENGTH_LONG).show();
                        progressDialog.cancel();
                    }else {
                        Toast.makeText(getApplicationContext(),"ilanınız bulunmamaktadır...", Toast.LENGTH_LONG).show();
                        progressDialog.cancel();
                        Intent intent = new Intent(IlanlarimActivity.this, MainActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
                        finish();
                    }
                }

            }

            @Override
            public void onFailure(Call<List<IlanlarimModel>> call, Throwable t) {

            }
        });

    }

    //////////////////////////////////// AlertDialog ilan sil


    public void ilanlarimAlertDialog(Activity activity, final String ilan_id){

        LayoutInflater ınflater = activity.getLayoutInflater();
        View view = ınflater.inflate(R.layout.alertlayout, null);

        Button button = (Button)view.findViewById(R.id.buton);
        Button buttonCik = (Button)view.findViewById(R.id.buton2);

        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setView(view);
        alert.setCancelable(false);
        final AlertDialog dialog = alert.create();

        buttonCik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sil(ilan_id);
                dialog.cancel();

            }
        });
        dialog.show();
    }

    public void sil(String ilanId){
        Call<IlanlarimSilModel> request = ManagerAll.getInstance().ilanlarimSil(ilanId);
        request.enqueue(new Callback<IlanlarimSilModel>() {
            @Override
            public void onResponse(Call<IlanlarimSilModel> call, Response<IlanlarimSilModel> response) {

                if (response.body().isTf()){
                    ilanlarimiGoruntule();
                }
            }

            @Override
            public void onFailure(Call<IlanlarimSilModel> call, Throwable t) {

            }
        });
    }

    //////////////////////////////////// AlertDialog ilan sil

}
