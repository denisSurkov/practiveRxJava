package com.example.denis.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;

import com.example.denis.rxjava.models.Comment;
import com.example.denis.rxjava.models.CommentsAdapter;
import com.example.denis.rxjava.models.Observable;

import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.Scheduler;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private Button loadButton;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
        initListeners();
    }

    private void initUI(){
        loadButton = findViewById(R.id.loadButton);
        recyclerView = findViewById(R.id.recycleView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initListeners() {
        Observable.setLoadCommentsButton(loadButton)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onComplete() {
                        Log.d("Completable", "Load button clicked");
                        loadCommentsList();
                    }

                    @Override
                    public void onError(Throwable e) {}
                });
    }

    private void loadCommentsList() {
        Observable.getCommentsList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Comment>>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onSuccess(List<Comment> comments) {
                        recyclerView.setAdapter(new CommentsAdapter(comments));
                    }

                    @Override
                    public void onError(Throwable e) {}
                });
    }

}
