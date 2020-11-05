package com.example.sinergimuslim.pojo.login_si;


import com.google.gson.annotations.SerializedName;

public class LoginSi{

	@SerializedName("data")
	private Data data;

	@SerializedName("api_key")
	private String apiKey;

	public void setData(Data data){
		this.data = data;
	}

	public Data getData(){
		return data;
	}

	public void setApiKey(String apiKey){
		this.apiKey = apiKey;
	}

	public String getApiKey(){
		return apiKey;
	}

	@Override
 	public String toString(){
		return 
			"LoginSi{" + 
			"data = '" + data + '\'' + 
			",api_key = '" + apiKey + '\'' + 
			"}";
		}
}