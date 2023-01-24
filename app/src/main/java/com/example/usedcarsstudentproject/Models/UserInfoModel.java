package com.example.usedcarsstudentproject.Models;

public class UserInfoModel {

	private String resim;
	private String mailAdres;
	private String sifre;
	private String kadi;

	public void setResim(String resim){
		this.resim = resim;
	}

	public String getResim(){
		return resim;
	}

	public void setMailAdres(String mailAdres){
		this.mailAdres = mailAdres;
	}

	public String getMailAdres(){
		return mailAdres;
	}

	public void setSifre(String sifre){
		this.sifre = sifre;
	}

	public String getSifre(){
		return sifre;
	}

	public void setKadi(String kadi){
		this.kadi = kadi;
	}

	public String getKadi(){
		return kadi;
	}

	@Override
	public String toString(){
		return
				"UserInfoModelTest{" +
						"resim = '" + resim + '\'' +
						",mailAdres = '" + mailAdres + '\'' +
						",sifre = '" + sifre + '\'' +
						",kadi = '" + kadi + '\'' +
						"}";
	}

}
