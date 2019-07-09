package com.ppizil.retrofit2token;

import android.app.Application;

import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyApplication extends Application {

    private Session session;
    private ApiService apiService;
    private AuthenticationListener authenticationListener;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public Session getSession() {
        if (session == null) {
            session = new Session() {
                @Override
                public boolean isLoggedIn() {
                    return true;
                }

                @Override
                public void saveToken(String token) {
                    //쉐어드 토큰 저장
                }

                @Override
                public String getToken() {
                    //쉐어드 토큰 로드
                    return null;
                }

                @Override
                public void saveEmail(String email) {

                }

                @Override
                public String getEmail() {
                    return null;
                }

                @Override
                public void savePassword(String password) {

                }

                @Override
                public String getPassword() {
                    return null;
                }

                @Override
                public void invalidate() {

                    if (authenticationListener != null) {
                        authenticationListener.onUserLoggedOut();
                    }
                }
            };
        }
        return session;
    }

    public interface AuthenticationListener {
        void onUserLoggedOut();
    }
    public void setAuthenticationListener(AuthenticationListener listener) {
        this.authenticationListener = listener;
    }

    public ApiService getApiService() {
        if (apiService == null) {
            apiService = provideRetrofit(ApiService.URL).create(ApiService.class);
        }
        return apiService;
    }

    private Retrofit provideRetrofit(String url) {
        return new Retrofit.Builder()
                .baseUrl(url)
                .client(provideOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
    }

    private OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder okhttpClientBuilder = new OkHttpClient.Builder();
        okhttpClientBuilder.connectTimeout(30, TimeUnit.SECONDS);
        okhttpClientBuilder.readTimeout(30, TimeUnit.SECONDS);
        okhttpClientBuilder.writeTimeout(30, TimeUnit.SECONDS);
        okhttpClientBuilder.addInterceptor(new TokenRenewInterceptor(getSession()));
        okhttpClientBuilder.addInterceptor(new AuthorizationInterceptor(getApiService(), getSession()));

        return okhttpClientBuilder.build();
    }
}
