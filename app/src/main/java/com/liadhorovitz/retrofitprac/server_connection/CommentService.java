package com.liadhorovitz.retrofitprac.server_connection;

import com.liadhorovitz.retrofitprac.models.Comment;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Liad Horovitz on 22,March,2021
 */
public interface CommentService {

    @GET("/comments")
    Call<List<Comment>> getComments();

    @GET("/comments/{commentId}")
    Call<Comment> getCommentById(@Path("commentId") int commentId);
}
