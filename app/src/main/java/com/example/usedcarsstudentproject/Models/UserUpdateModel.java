package com.example.usedcarsstudentproject.Models;

public class UserUpdateModel{
	private boolean tf;

	public void setTf(boolean tf){
		this.tf = tf;
	}

	public boolean isTf(){
		return tf;
	}

	@Override
 	public String toString(){
		return 
			"UserUpdateModel{" + 
			"tf = '" + tf + '\'' + 
			"}";
		}
}
