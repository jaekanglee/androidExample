package com.ppizil.retrofit2token;

import android.util.Base64;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;

public class AuthorizationInterceptor implements Interceptor {
    private ApiService apiService;
    private Session session;

    public AuthorizationInterceptor(ApiService apiService, Session session) {
        this.apiService = apiService;
        this.session = session;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response mainResponse = chain.proceed(chain.request());
        if (mainResponse.code() == 401 || mainResponse.code() == 403) {
            session.invalidate();
        }

        /*얘는 로그아웃 없이 자동 리프레시*/
       /* Request mainRequest = chain.request();

        if (session.isLoggedIn()) {
            // if response code is 401 or 403, 'mainRequest' has encountered authentication error
            if (mainResponse.code() == 401 || mainResponse.code() == 403) {
                String authKey = getAuthorizationHeader(session.getEmail(), session.getPassword());
                // request to login API to get fresh token
                // synchronously calling login API

                retrofit2.Response<Authorization> loginResponse = apiService.loginAccount(authKey).execute();

                if (loginResponse.isSuccessful()) {
                    // login request succeed, new token generated
                    Authorization authorization = loginResponse.body();
                    // save the new token
                    session.saveToken(authorization.getToken());
                    // retry the 'mainRequest' which encountered an authentication error
                    // add new token into 'mainRequest' header and request again
                    Request.Builder builder = mainRequest.newBuilder().header("x-auth-token", session.getToken()).
                            method(mainRequest.method(), mainRequest.body());
                    mainResponse = chain.proceed(builder.build());
                }
            }
        }*/

        return mainResponse;
    }

    /**
     * this method is API implemetation specific
     * might not work with other APIs
     **/
    public static String getAuthorizationHeader(String email, String password) {
        String credential = email + ":" + password;
        return "Basic " + Base64.encodeToString(credential.getBytes(), Base64.DEFAULT);
    }
}