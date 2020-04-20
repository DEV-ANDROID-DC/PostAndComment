package com.debin.pac.presenter.post;

import com.debin.pac.contract.PostContract;
import com.debin.pac.model.Post;
import com.debin.pac.network.Api;
import com.debin.pac.network.RetrofitInstance;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.verification.Calls;

import java.text.ParseException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.*;

public class PostPresenterImplTest {

    @Mock
    private PostContract.PostView postView;

    @Mock
    private Api api;

    @Mock
    RetrofitInstance retrofitInstance;

    @Mock
    private PostContract.GetPostIntractor.OnFetchFinishedListener onFetchFinishedListener;

    @Mock
    private PostContract.GetPostIntractor getPostIntractor;

    @Captor
    private ArgumentCaptor<PostContract.GetPostIntractor> argumentCaptor;

    private PostPresenterImpl postPresenter;

    private Call<ArrayList<Post>> response = null;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        postPresenter = new PostPresenterImpl(postView, getPostIntractor);
    }

    @Test
    public void onRequestDataFromServer() {
        postPresenter.onRequestDataFromServer();
        //ArrayList<Post> arrayList = new ArrayList<>();
        getPostIntractor.getPostArrayList(onFetchFinishedListener);

        response = api.getAllPosts();
        Mockito.when(api.getAllPosts()).thenReturn(response);

        Assert.assertEquals(1,getList().size());
        Assert.assertEquals("sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
                getList().get(0).getTitle());
    }

    private ArrayList<Post> getList() {
        ArrayList<Post> arrayList = new ArrayList<>();
        arrayList.add(new Post(1,1,
                "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
                "quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto"));

        return arrayList;
    }
}