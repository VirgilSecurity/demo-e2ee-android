package com.android.virgilsecurity.jwtworkexample.di;

import com.android.virgilsecurity.jwtworkexample.ui.login.LogInActivityComponent;
import com.android.virgilsecurity.jwtworkexample.util.UiUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

/**
 * Created by Danylo Oliinyk on 3/22/18 at Virgil Security.
 * -__o
 */

@Module(subcomponents = LogInActivityComponent.class)
public class NetworkModule {

    private static final String INTERCEPTOR_LOG = "INTERCEPTOR_LOG";

    @Provides @Singleton static HttpLoggingInterceptor provideInterceptor() {
        return new HttpLoggingInterceptor(message -> {
            UiUtils.log(INTERCEPTOR_LOG, message);
        });
    }

    @Provides @Singleton static OkHttpClient provideHttpClient(HttpLoggingInterceptor interceptor) {
        return new OkHttpClient.Builder().addInterceptor(interceptor)
                                         .build();
    }

    @Provides @Singleton static Retrofit provideRetrofit(OkHttpClient httpClient) {
        return new Retrofit.Builder()
                .client(httpClient)
                .baseUrl("http://google.com") // FIXME: 3/22/18 add BaseUrl
                .build();
    }
}
