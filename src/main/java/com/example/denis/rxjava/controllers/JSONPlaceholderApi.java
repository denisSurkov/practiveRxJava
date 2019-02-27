package com.example.denis.rxjava.controllers;

import com.example.denis.rxjava.models.Comment;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JSONPlaceholderApi {
    String COMMENTS_URL = "comments/";

    @GET(COMMENTS_URL)
    Call<List<Comment>> getComments ();

}
