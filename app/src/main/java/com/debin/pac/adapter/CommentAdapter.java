package com.debin.pac.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.debin.pac.R;
import com.debin.pac.model.Comment;


import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.MyViewHolder> {

    private ArrayList<Comment> commentArrayList;

    public CommentAdapter(ArrayList<Comment> commentArrayList) {
        this.commentArrayList = commentArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
          holder.bind(commentArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return commentArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvBody;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_name);
            tvBody = itemView.findViewById(R.id.tv_body);
        }

        public void bind(Comment comment) {
            tvName.setText(comment.getName());
            tvBody.setText(comment.getBody());
        }
    }
}
