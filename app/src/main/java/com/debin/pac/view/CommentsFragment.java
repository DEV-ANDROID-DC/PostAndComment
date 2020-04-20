package com.debin.pac.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.debin.pac.R;
import com.debin.pac.adapter.CommentAdapter;
import com.debin.pac.contract.CommentContract;
import com.debin.pac.model.Comment;
import com.debin.pac.presenter.comment.CommentPresentImpl;
import com.debin.pac.presenter.comment.GetCommentIntractorImpl;

import java.util.ArrayList;


public class CommentsFragment extends Fragment implements CommentContract.CommentView {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private CommentContract.CommentPresenter commentPresenter;
    private LinearLayoutManager linearLayoutManager;
//    private int someValue = 1;
//    public static final String SOME_KEY_VALUE = "someValueToSafe";

    public CommentsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_comments, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar = view.findViewById(R.id.progressbar);
        initializeRecycleView(view);
        commentPresenter = new CommentPresentImpl(this, new GetCommentIntractorImpl());
        commentPresenter.onRequestDataFromServer();
    }

    @Override
    public void showProgress() {
     progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
     progressBar.setVisibility(View.GONE);
    }

    @Override
    public void SetDataToRecycleView(ArrayList<Comment> commentArrayList) {
        CommentAdapter commentAdapter = new CommentAdapter(commentArrayList);
        recyclerView.setAdapter(commentAdapter);
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(getContext(),
                "Something went wrong...Error message: " + throwable.getMessage(),
                Toast.LENGTH_LONG).show();
    }

    private void initializeRecycleView(View view) {
        recyclerView = view.findViewById(R.id.rv_comment);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        //outState.putInt(SOME_KEY_VALUE, someValue);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        commentPresenter.onDestroy();
    }
}

