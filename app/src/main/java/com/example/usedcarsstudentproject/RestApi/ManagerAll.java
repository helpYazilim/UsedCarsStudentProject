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

public class ManagerAll extends BaseManager{

    private  static ManagerAll ourInstance = new ManagerAll();

    public  static synchronized ManagerAll getInstance()
    {
        return  ourInstance;
    }


    public Call<LoginModel> girisYap(String mail , String parola)
    {
        Call<LoginModel> x = getRestApi().loginUser(mail,parola);
        return  x ;
    }

    public Call<IlanVerSonucModel> ilanVer(String ucret, String uye_id, String sehir, String ilce, String mahalle, String marka, String seri, String model, String yil, String ilantipi, String kimden, String baslik, String aciklama, String motortipi, String motorhacmi, String surat, String yakittipi, String ortalamayakit, String depohacmi, String km)
    {
        Call<IlanVerSonucModel> x = getRestApi().ilanVer(ucret, uye_id, sehir, ilce, mahalle, marka, seri, model, yil, ilantipi, kimden, baslik, aciklama, motortipi, motorhacmi, surat, yakittipi, ortalamayakit, depohacmi, km);
        return  x ;
    }

    public Call<ResimEkleModel> resimEkle(String uye_id, String ilan_id, String resim) {
        Call<ResimEkleModel> x = getRestApi().resimYukle(uye_id, ilan_id, resim);
        return  x ;
    }

    public Call<List<IlanlarimModel>> ilanlarim(String uyeid)
    {
        Call<List<IlanlarimModel>> x = getRestApi().ilanlarim(uyeid);
        return  x ;
    }

    public Call<IlanlarimSilModel> ilanlarimSil(String ilanid)
    {
        Call<IlanlarimSilModel> x = getRestApi().ilanlarimSil(ilanid);
        return  x ;
    }

    public Call<List<IlanlarModel>> ilanlar()
    {
        Call<List<IlanlarModel>> x = getRestApi().ilanlar();
        return  x ;
    }

    public Call<IlanDetayModel> ilanDetay(String ilanid)
    {
        Call<IlanDetayModel> x = getRestApi().ilanDetay(ilanid);
        return  x ;
    }

    public Call<List<SliderModel>> ilanResimleri(String ilanid)
    {
        Call<List<SliderModel>> x = getRestApi().ilanResimleri(ilanid);
        return  x ;
    }

    public Call<FavoriKontrolModel> getButtonText(String uyeid, String ilanid)
    {
        Call<FavoriKontrolModel> x = getRestApi().getButtonText(uyeid,ilanid);
        return  x ;
    }

    public Call<FavoriEkleCikarModel> favoriEkleCikar(String uyeid, String ilanid)
    {
        Call<FavoriEkleCikarModel> x = getRestApi().favoriEkleCikar(uyeid,ilanid);
        return  x ;
    }

    public Call<List<FavoriSliderModel>> setSlider(String uyeid)
    {
        Call<List<FavoriSliderModel>> x = getRestApi().setSlider(uyeid);
        return  x ;
    }

    public Call<UserInfoModel> getInformation(String uyeid)
    {
        Call<UserInfoModel> x = getRestApi().getInformation(uyeid);
        return  x ;
    }

    public Call<UserUpdateModel> userUpdateInformation(String uyeid, String userName, String userEmail, String pass)
    {
        Call<UserUpdateModel> x = getRestApi().userUpdateInformation(uyeid,userName,userEmail,pass);
        return  x ;
    }

}
