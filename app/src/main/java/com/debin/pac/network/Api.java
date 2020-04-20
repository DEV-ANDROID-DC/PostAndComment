package com.debin.pac.network;

import com.debin.pac.model.Comment;
import com.debin.pac.model.Post;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {

    @GET("posts")
    Call<ArrayList<Post>> getAllPosts();

    @GET("posts/{post_id}/comments")
    Call<ArrayList<Comment>> getAllComments(@Path("post_id") String postId);
}
