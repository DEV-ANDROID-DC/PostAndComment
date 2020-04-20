package com.debin.pac.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.debin.pac.R;
import com.debin.pac.adapter.PostAdapter;
import com.debin.pac.contract.PostContract;
import com.debin.pac.model.Post;
import com.debin.pac.presenter.post.GetPostIntractorImpl;
import com.debin.pac.presenter.post.PostPresenterImpl;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PostFragment extends Fragment implements PostContract.PostView {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private PostContract.PostPresenter postPresenter;
    private LinearLayoutManager linearLayoutManager;
    public PostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_post, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar = view.findViewById(R.id.progressbar);
        initializeRecycleView(view);
        postPresenter = new PostPresenterImpl(this, new GetPostIntractorImpl());
        postPresenter.onRequestDataFromServer();
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
    public void onSetDataToRecycleView(ArrayList<Post> postArrayList) {
        PostAdapter postAdapter = new PostAdapter(postArrayList);
        recyclerView.setAdapter(postAdapter);
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(getContext(),
                "Something went wrong...Error message: " + throwable.getMessage(),
                Toast.LENGTH_LONG).show();
    }

    private void initializeRecycleView(View view) {
        recyclerView = view.findViewById(R.id.rv_post);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
       // outState.putInt(SOME_KEY_VALUE, someValue);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState != null) {

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        postPresenter.onDestroy();
    }
}
