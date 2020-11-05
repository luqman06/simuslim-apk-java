package com.example.sinergimuslim.api;

public class UntilsApi {
    public static final String BASE_URL="http://sinergimuslim.sidakwah.com/api/";

    public static BaseApiService getApiService(){
        return RetrofiClient.getClient(BASE_URL).create(BaseApiService.class);
    }
}
