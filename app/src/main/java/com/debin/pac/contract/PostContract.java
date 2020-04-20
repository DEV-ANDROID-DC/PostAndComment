package com.debin.pac.contract;

import com.debin.pac.model.Post;

import java.util.ArrayList;

public interface PostContract {

    interface PostView{
      void showProgress();
      void hideProgress();
      void onSetDataToRecycleView(ArrayList<Post> postArrayList);
      void onResponseFailure(Throwable throwable);
    }

    interface PostPresenter{

        void onRequestDataFromServer();
        void onDestroy();

    }

    interface GetPostIntractor{

        interface OnFetchFinishedListener{
            void OnFetchFinish(ArrayList<Post> postArrayList);
            void OnFailure(Throwable t);
        }

        void getPostArrayList(OnFetchFinishedListener onFetchFinishedListener);

    }
}
