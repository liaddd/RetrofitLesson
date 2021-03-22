package com.liadhorovitz.retrofitprac;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.liadhorovitz.retrofitprac.models.Comment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Liad Horovitz on 22,March,2021
 */
public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder> {

    private final List<Comment> comments = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_list_item , parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Comment comment = comments.get(position);

        holder.commentTV.setText(comment.toString());

    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public void setComments(List<Comment> newComments){
        comments.clear();
        comments.addAll(newComments);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView commentTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            commentTV = itemView.findViewById(R.id.comment_list_item_text_view);
        }
    }


}
