package com.debin.pac.contract;

import com.debin.pac.model.Comment;

import java.util.ArrayList;

public interface CommentContract {

    interface CommentView{
        void showProgress();
        void hideProgress();
        void SetDataToRecycleView(ArrayList<Comment> commentArrayList);
        void onResponseFailure(Throwable throwable);
    }

    interface CommentPresenter{
        void onRequestDataFromServer();
        void onDestroy();
    }

    interface GetCommentIntractor{

        interface OnFetchFinishedListner {
            void OnFetchFinished(ArrayList<Comment> commentArrayList);
            void OnFailure(Throwable t);
        }

        void getCommentArrayList(OnFetchFinishedListner onFetchFinishedListner);

    }
}
