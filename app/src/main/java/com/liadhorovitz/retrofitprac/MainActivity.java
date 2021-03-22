package com.liadhorovitz.retrofitprac;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.liadhorovitz.retrofitprac.models.Comment;
import com.liadhorovitz.retrofitprac.server_connection.CommentService;
import com.liadhorovitz.retrofitprac.server_connection.RetrofitImpl;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private CommentsAdapter commentsAdapter;
    private ProgressBar progressBar;
    private CommentService commentService;
    private final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progress_bar);
        initRecyclerAndAdapter();

        commentService = RetrofitImpl.getRetrofit().create(CommentService.class);

        setListeners();
    }

    private void setListeners() {
        findViewById(R.id.appCompatButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //loadComments();
                EditText editText = findViewById(R.id.edit_text);
                getCommentById(Integer.parseInt(editText.getText().toString()));
            }
        });
    }

    // Fetching all comments
    private void loadComments() {
        showProgressBar(true);
        commentService.getComments().enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> comments, Response<List<Comment>> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, response.body().toString());
                    commentsAdapter.setComments(response.body());
                }
                showProgressBar(false);
            }

            @Override
            public void onFailure(Call<List<Comment>> comments, Throwable t) {
                t.printStackTrace();
                showProgressBar(false);
            }
        });
    }

    // Fetch Comment by {id}
    private void getCommentById(int commentId){
        commentService.getCommentById(commentId).enqueue(new Callback<Comment>() {
            @Override
            public void onResponse(Call<Comment> call, Response<Comment> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, response.body().toString());
                    List<Comment> comments = new ArrayList<>();
                    comments.add(response.body());
                    commentsAdapter.setComments(comments);
                }
                showProgressBar(false);
            }

            @Override
            public void onFailure(Call<Comment> call, Throwable t) {
                t.printStackTrace();
                showProgressBar(false);
            }
        });
    }

    private void initRecyclerAndAdapter() {
        commentsAdapter = new CommentsAdapter();
        RecyclerView recyclerView = findViewById(R.id.comments_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(commentsAdapter);
    }

    private void showProgressBar(boolean show){
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}