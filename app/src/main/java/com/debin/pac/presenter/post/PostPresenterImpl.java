package com.debin.pac.presenter.post;

import android.util.Log;

import com.debin.pac.contract.PostContract;
import com.debin.pac.model.Post;

import java.util.ArrayList;

public class PostPresenterImpl implements PostContract.PostPresenter, PostContract.GetPostIntractor.OnFetchFinishedListener {

    private PostContract.PostView postView;
    private PostContract.GetPostIntractor getpostIntractor;
    private static final String TAG = "PostPresenterImpl";


    public PostPresenterImpl(PostContract.PostView postView, PostContract.GetPostIntractor getpostIntractor) {
        this.postView = postView;
        this.getpostIntractor = getpostIntractor;
    }

    @Override
    public void onRequestDataFromServer() {
        if(postView != null){
            postView.showProgress();
           // Log.i(TAG, "Post view is not null");
        }
        getpostIntractor.getPostArrayList(this);
    }

    @Override
    public void onDestroy() {
         postView = null;
    }

    @Override
    public void OnFetchFinish(ArrayList<Post> postArrayList) {
      if(postView != null) {
         postView.onSetDataToRecycleView(postArrayList);
         postView.hideProgress();
      }
    }

    @Override
    public void OnFailure(Throwable t) {
      if(postView != null) {
          postView.onResponseFailure(t);
          postView.hideProgress();
      }
    }
}
