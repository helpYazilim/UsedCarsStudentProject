package com.example.usedcarsstudentproject.Models;

public class SliderModel{
	private String resim;

	public void setResim(String resim){
		this.resim = resim;
	}

	public String getResim(){
		return resim;
	}

	@Override
 	public String toString(){
		return 
			"SliderModel{" + 
			"resim = '" + resim + '\'' + 
			"}";
		}
}
