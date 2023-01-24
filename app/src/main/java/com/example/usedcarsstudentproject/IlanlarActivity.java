package com.example.usedcarsstudentproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.usedcarsstudentproject.Adapters.IlanlarAdapter;
import com.example.usedcarsstudentproject.Models.IlanlarModel;
import com.example.usedcarsstudentproject.RestApi.ManagerAll;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IlanlarActivity extends AppCompatActivity {

    ListView listView;
    List<IlanlarModel>  ilanlarModelList;
    IlanlarAdapter ilanlarAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilanlar);

        listView = findViewById(R.id.ilanlarListView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(IlanlarActivity.this, IlanDetayActivity.class);
                intent.putExtra("ilanid", ilanlarModelList.get(position).getIlanid());
                startActivity(intent);
            }
        });

        ilanlarimiGoruntule();
    }

    public void ilanlarimiGoruntule() {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Ilanlarım");
        progressDialog.setMessage("Ilanlariniz Yükleniyor Lütfen bekleyin ... ");
        progressDialog.setCancelable(false); // iptal edilebilirliği kapatıyor, sebebi ise bir Diyaloğu arka planda işlerken diyaloğun kapatılmasını engellemek
        progressDialog.show();

        Call<List<IlanlarModel>> request = ManagerAll.getInstance().ilanlar();
        request.enqueue(new Callback<List<IlanlarModel>>() {
            @Override
            public void onResponse(Call<List<IlanlarModel>> call, Response<List<IlanlarModel>> response) {
                if (response.isSuccessful()){
                    if (response.body().get(0).isTf()){

                        ilanlarModelList = response.body();
                        ilanlarAdapter =new IlanlarAdapter(ilanlarModelList,getApplicationContext());
                        listView.setAdapter(ilanlarAdapter);
                        Toast.makeText(getApplicationContext(), response.body().get(0).getSayi() + " tane ilan listelenmistir...", Toast.LENGTH_LONG).show();
                        progressDialog.cancel();
                    }else {
                        Toast.makeText(getApplicationContext(),"ilanınız bulunmamaktadır...", Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                        Intent intent = new Intent(IlanlarActivity.this, MainActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
                        finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<IlanlarModel>> call, Throwable t) {

            }
        });

    }
}
