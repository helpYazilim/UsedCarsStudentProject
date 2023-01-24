package com.example.usedcarsstudentproject.Models;

import java.io.Serializable;

public class RegisterPojo implements Serializable {
	private boolean tf;
	private String text;

	public void setTf(boolean tf){
		this.tf = tf;
	}

	public boolean isTf(){
		return tf;
	}

	public void setText(String text){
		this.text = text;
	}

	public String getText(){
		return text;
	}

	@Override
	public String toString(){
		return
				"RegisterPojo{" +
						"tf = '" + tf + '\'' +
						",text = '" + text + '\'' +
						"}";
	}
}
	/*private String text;
	private boolean tf;

	public void setText(String text){
		this.text = text;
	}

	public String getText(){
		return text;
	}

	public void setTf(boolean tf){
		this.tf = tf;
	}

	public boolean isTf(){
		return tf;
	}

	@Override
 	public String toString(){
		return 
			"RegisterPojo{" + 
			"text = '" + text + '\'' + 
			",tf = '" + tf + '\'' + 
			"}";
		}
}*/


