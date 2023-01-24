package com.example.usedcarsstudentproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.usedcarsstudentproject.Models.IlanVerModel;
import com.example.usedcarsstudentproject.Models.IlanVerSonucModel;
import com.example.usedcarsstudentproject.RestApi.ManagerAll;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YakitActivity extends AppCompatActivity {

    EditText yakitTipiBilgiEditText,ortalamaYakitEditText,depoHacmiSuratEditText;
    Button yakitTuketimBilgisiButon,yakitTuketimBilgisiButonGeri;

    private View yakitFormView;
    private View mProgressView;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yakit);

        tanimla();
    }

    private void tanimla() {

        yakitFormView =findViewById(R.id.yakit_view);
        mProgressView = findViewById(R.id.progressYakit);

        sharedPreferences = getApplicationContext().getSharedPreferences("giris", 0);
//        IlanVerModel.setUye_id(sharedPreferences.getString("use_id", null));
        IlanVerModel.setUye_id(sharedPreferences.getString("uye_id", null));

        yakitTipiBilgiEditText = findViewById(R.id.yakitTipiBilgiEditText);
        ortalamaYakitEditText = findViewById(R.id.ortalamaYakitEditText);
        depoHacmiSuratEditText = findViewById(R.id.depoHacmiSuratEditText);

        // when go back previous Activity, than this code will make remember all information
        yakitTipiBilgiEditText.setText(IlanVerModel.getYakittipi());
        ortalamaYakitEditText.setText(IlanVerModel.getOrtalamayakit());
        depoHacmiSuratEditText.setText(IlanVerModel.getDepohacmi());

        yakitTuketimBilgisiButon = findViewById(R.id.yakitTuketimBilgisiButon);
        yakitTuketimBilgisiButonGeri = findViewById(R.id.yakitTuketimBilgisiButonGeri);

        yakitTuketimBilgisiButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!yakitTipiBilgiEditText.getText().toString().equals("") && !ortalamaYakitEditText.getText().toString().equals("") && !depoHacmiSuratEditText.getText().toString().equals("")) {

//                    showProgress(true);

                    // when go back previous Activity, than this code will make remember all information
                    IlanVerModel.setYakittipi(yakitTipiBilgiEditText.getText().toString());
                    IlanVerModel.setOrtalamayakit(ortalamaYakitEditText.getText().toString());
                    IlanVerModel.setDepohacmi(depoHacmiSuratEditText.getText().toString());

                    ilaniYayinla(IlanVerModel.getUcret(),IlanVerModel.getUye_id(),IlanVerModel.getSehir(),IlanVerModel.getIlce(),IlanVerModel.getMahalle(),
                            IlanVerModel.getMarka(),IlanVerModel.getSeri(),IlanVerModel.getModel(),IlanVerModel.getYil(),IlanVerModel.getIlantipi(),IlanVerModel.getKimden(),IlanVerModel.getBaslik(),
                            IlanVerModel.getAciklama(),IlanVerModel.getMotortipi(),IlanVerModel.getMotorhacmi(),IlanVerModel.getSurat(),IlanVerModel.getYakittipi(),IlanVerModel.getOrtalamayakit(),
                            IlanVerModel.getDepohacmi(),IlanVerModel.getKm());

                }else {
                    Toast.makeText(getApplicationContext(), "Tum Bilgileri Doldurun", Toast.LENGTH_SHORT).show();
                }

            }
        });

        yakitTuketimBilgisiButonGeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(YakitActivity.this,MotorPerformansActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in_ters,R.anim.anim_out_ters);
                finish();

            }
        });

    }

    public void ilaniYayinla(String ucret, String uye_id , String sehir, String ilce, String mahalle, String marka, String seri, String model, String yil, String ilantipi, String kimden, String baslik, String aciklama, String motortipi, String motorhacmi, String surat, String yakittipi, String ortalamayakit, String depohacmi, String km){

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Ilanlarım");
        progressDialog.setMessage("Ilanlariniz Yükleniyor Lütfen bekleyin ... ");
        progressDialog.setCancelable(false); // iptal edilebilirliği kapatıyor, sebebi ise bir Diyaloğu arka planda işlerken diyaloğun kapatılmasını engellemek
        progressDialog.show();

        Call<IlanVerSonucModel> request = ManagerAll.getInstance().ilanVer(ucret, uye_id, sehir, ilce, mahalle, marka, seri, model, yil, ilantipi, kimden, baslik, aciklama, motortipi, motorhacmi, surat, yakittipi, ortalamayakit, depohacmi, km);
        request.enqueue(new Callback<IlanVerSonucModel>() {
            @Override
            public void onResponse(Call<IlanVerSonucModel> call, Response<IlanVerSonucModel> response) {
                if (response.body().isTf()){

                    Toast.makeText(getApplicationContext(), "ilaniniz Yayinlanmistir.", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(YakitActivity.this, IlanResimlerActivity.class);

                    intent.putExtra("ilan_id",response.body().getIlanid());
                    // Eger Model Class int ise Stringe cevirmek icin (String.valueOf) kullaniyoruz...
//                    intent.putExtra("uye_id",String.valueOf(response.body().getUyeId()));
                    intent.putExtra("uye_id",response.body().getUyeid());
                    Log.i("test 1",response.body().getIlanid() + "  /// " + response.body().getUyeid());
//                    Log.i("test 2",String.valueOf(response.body().getUyeId()));

                    startActivity(intent);
                    overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
                    progressDialog.cancel();
//                    showProgress(false);

                }else {
                    Toast.makeText(getApplicationContext(), "ilaniniz yayinlanmamistir.", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<IlanVerSonucModel> call, Throwable t) {

            }
        });
    }

    /*
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            yakitFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            yakitFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    yakitFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            yakitFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
    */

}
