package com.debin.pac.presenter.comment;

import com.debin.pac.contract.CommentContract;
import com.debin.pac.model.Comment;

import java.util.ArrayList;

public class CommentPresentImpl implements CommentContract.CommentPresenter, CommentContract.GetCommentIntractor.OnFetchFinishedListner {

   private CommentContract.CommentView commentView;
   private CommentContract.GetCommentIntractor getCommentIntractor;

    public CommentPresentImpl(CommentContract.CommentView commentView, CommentContract.GetCommentIntractor getCommentIntractor) {
        this.commentView = commentView;
        this.getCommentIntractor = getCommentIntractor;
    }

    @Override
    public void onRequestDataFromServer() {
        if(commentView != null) {
            commentView.showProgress();
        }
      getCommentIntractor.getCommentArrayList(this);
    }

    @Override
    public void onDestroy() {
      commentView = null;
    }

    @Override
    public void OnFetchFinished(ArrayList<Comment> commentArrayList) {
     if(commentView != null) {
         commentView.SetDataToRecycleView(commentArrayList);
         commentView.hideProgress();
     }
    }

    @Override
    public void OnFailure(Throwable t) {
      if(commentView != null) {
          commentView.onResponseFailure(t);
          commentView.hideProgress();
      }
    }
}
