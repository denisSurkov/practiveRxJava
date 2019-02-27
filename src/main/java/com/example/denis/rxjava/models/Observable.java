package com.example.denis.rxjava.models;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.denis.rxjava.Constants;
import com.example.denis.rxjava.controllers.JSONPlaceholderApi;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Observable {
    private static JSONPlaceholderApi jsonPlaceholderApi;

    public static Completable setLoadCommentsButton (final Button loadButton) {
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(final CompletableEmitter emitter) throws Exception {
                loadButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        emitter.onComplete();
                    }
                });
            }
        });
    }

    public static Single<List<Comment>> getCommentsList() {
        return Single.create(new SingleOnSubscribe<List<Comment>>() {
            @Override
            public void subscribe(SingleEmitter<List<Comment>> emitter) throws Exception {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Constants.BASE_API_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                jsonPlaceholderApi = retrofit.create(JSONPlaceholderApi.class);
                Response response = jsonPlaceholderApi.getComments().execute();

                Log.d("Single", "Success");

                emitter.onSuccess((List<Comment>) response.body());
            }
        });
    }

}
