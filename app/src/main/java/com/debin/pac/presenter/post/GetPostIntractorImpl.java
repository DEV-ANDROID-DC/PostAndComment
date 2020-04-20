package com.debin.pac.presenter.post;

import android.util.Log;

import com.debin.pac.contract.PostContract;
import com.debin.pac.model.Post;
import com.debin.pac.network.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetPostIntractorImpl implements PostContract.GetPostIntractor {

    private static final String TAG = "GetPostIntractorImpl";

    @Override
    public void getPostArrayList(final OnFetchFinishedListener onFetchFinishedListener) {

        RetrofitInstance.getApi().getAllPosts()
                .enqueue(new Callback<ArrayList<Post>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Post>> call, Response<ArrayList<Post>> response) {
                        onFetchFinishedListener.OnFetchFinish(response.body());
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
                    public void onFailure(Call<ArrayList<Post>> call, Throwable t) {
                          onFetchFinishedListener.OnFailure(t);
                    }
                });
    }
}
