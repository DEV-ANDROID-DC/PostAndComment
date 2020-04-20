package com.debin.pac.presenter.comment;

import android.util.Log;

import com.debin.pac.contract.CommentContract;
import com.debin.pac.model.Comment;
import com.debin.pac.network.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetCommentIntractorImpl implements CommentContract.GetCommentIntractor {

    private static final String TAG = "GetCommentIntractorImpl";
    @Override
    public void getCommentArrayList(final OnFetchFinishedListner onFetchFinishedListner) {

        RetrofitInstance.getApi().getAllComments("1")
                .enqueue(new Callback<ArrayList<Comment>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Comment>> call, Response<ArrayList<Comment>> response) {
                        onFetchFinishedListner.OnFetchFinished(response.body());
                        if(response.raw().cacheResponse()!=null) {
                            // true: response was served from cache
                            Log.i(TAG,"Response from cache");
                        } else if (response.raw().networkResponse() != null
                                && response.raw().networkResponse() == null) {
                            // true: response was served from network/server
                            Log.i(TAG,"Response from server");
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Comment>> call, Throwable t) {
                         onFetchFinishedListner.OnFailure(t);
                    }
                });
    }
}
