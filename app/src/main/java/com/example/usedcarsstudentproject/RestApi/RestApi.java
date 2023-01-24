package com.example.usedcarsstudentproject.RestApi;


import com.example.usedcarsstudentproject.Models.FavoriEkleCikarModel;
import com.example.usedcarsstudentproject.Models.FavoriKontrolModel;
import com.example.usedcarsstudentproject.Models.FavoriSliderModel;
import com.example.usedcarsstudentproject.Models.IlanDetayModel;
import com.example.usedcarsstudentproject.Models.IlanVerSonucModel;
import com.example.usedcarsstudentproject.Models.IlanlarModel;
import com.example.usedcarsstudentproject.Models.IlanlarimModel;
import com.example.usedcarsstudentproject.Models.IlanlarimSilModel;
import com.example.usedcarsstudentproject.Models.LoginModel;
import com.example.usedcarsstudentproject.Models.ResimEkleModel;
import com.example.usedcarsstudentproject.Models.SliderModel;
import com.example.usedcarsstudentproject.Models.UserInfoModel;
import com.example.usedcarsstudentproject.Models.UserUpdateModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RestApi {

    @FormUrlEncoded
    @POST("/BuySellAra/girisyap.php")
    Call<LoginModel> loginUser(@Field("mailadres") String mailAdres, @Field("sifre") String pass);

    @FormUrlEncoded
    @POST("/BuySellAra/ilanver.php")
    Call<IlanVerSonucModel> ilanVer(@Field("ucret") String ucret, @Field("uye_id") String uye_id, @Field("sehir") String sehir, @Field("ilce") String ilce, @Field("mahalle") String mahalle, @Field("marka") String marka, @Field("seri") String seri, @Field("model") String model, @Field("yil") String yil, @Field("ilantipi") String ilantipi, @Field("kimden") String kimden, @Field("baslik") String baslik, @Field("aciklama") String aciklama, @Field("motortipi") String motortipi, @Field("motorhacmi") String motorhacmi, @Field("surat") String surat, @Field("yakittipi") String yakittipi, @Field("ortalamayakit") String ortalamayakit, @Field("depohacmi") String depohacmi, @Field("km") String km);

    @FormUrlEncoded
    @POST("/BuySellAra/ilanResmiEkle.php")
    Call<ResimEkleModel> resimYukle(@Field("uye_id") String uye_id, @Field("ilan_id") String ilan_id, @Field("resim") String base64StringResim);

    @GET("/BuySellAra/ilanlarim.php")
    Call<List<IlanlarimModel>> ilanlarim(@Query("uyeid") String uyeid);

    @GET("/BuySellAra/ilanlarimdanSil.php")
    Call<IlanlarimSilModel> ilanlarimSil(@Query("ilan_id") String ilanid);

    @GET("/BuySellAra/ilanlar.php")
    Call<List<IlanlarModel>> ilanlar();

    @GET("/BuySellAra/ilanDetay.php")
    Call<IlanDetayModel> ilanDetay(@Query("ilanid") String ilanid);

    @GET("/BuySellAra/ilanResimleri.php")
    Call<List<SliderModel>> ilanResimleri(@Query("ilanid") String ilanid);

    @GET("/BuySellAra/tumIlanlarFavoriFetch.php")
    Call<FavoriKontrolModel> getButtonText(@Query("uye_id") String uyeid, @Query("ilan_id") String ilanid);

    @GET("/BuySellAra/tumIlanlarFavoriEkleFavoridenCikar.php")
    Call<FavoriEkleCikarModel> favoriEkleCikar(@Query("uye_id") String uyeid, @Query("ilan_id") String ilanid);

    @GET("/BuySellAra/favoriIlanSlider.php")
    Call<List<FavoriSliderModel>> setSlider(@Query("uye_id") String uyeid);

    @GET("/BuySellAra/kullaniciBilgileri.php")
    Call<UserInfoModel> getInformation(@Query("uyeid") String uyeid);

    @GET("/BuySellAra/kullaniciBilgileriGuncelle.php")
    Call<UserUpdateModel> userUpdateInformation(@Query("uyeid") String uyeid, @Query("userName") String userName, @Query("userEmail") String userEmail, @Query("pass") String pass);


}
