package com.azp.blog.utils;

import com.azp.blog.api.ApiClient;
import com.azp.blog.api.ApiInterface;

public class ApiUtils {

    private ApiUtils() {}

    public static final String BASE_URL = "http://blogapi.thetpainghtut.com/api/";

    public static final String LOGIN_BASE_URL = "http://blogapi.thetpainghtut.com/";

    public static ApiInterface getAPI() {
        return ApiClient.getRetrofit(BASE_URL).create(ApiInterface.class);
    }

    public static ApiInterface getLoginAPI(){
        return ApiClient.getRetrofit(LOGIN_BASE_URL).create(ApiInterface.class);
    }
}
