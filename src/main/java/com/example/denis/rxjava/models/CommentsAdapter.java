package com.example.denis.rxjava.models;

import android.icu.util.ValueIterator;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.denis.rxjava.R;

import java.util.List;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder> {

    private List<Comment> comments;

    public CommentsAdapter(List<Comment> comments) {
        this.comments = comments;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        View view = inflater.inflate(R.layout.comment_card, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Comment comment = comments.get(i);

        viewHolder.commentBody.setText(comment.getBody());
        viewHolder.commentOwnerEmail.setText(comment.getEmail());
        viewHolder.commentOwnerUserName.setText(comment.getName());
    }

    @Override
    public int getItemCount() {
        if (comments == null) {
            return 0;
        }
        return comments.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView commentOwnerUserName;
        TextView commentOwnerEmail;
        TextView commentBody;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            commentOwnerUserName = itemView.findViewById(R.id.commentOwnerUserName);
            commentOwnerEmail = itemView.findViewById(R.id.commentOwnerEmail);
            commentBody = itemView.findViewById(R.id.commentBody);
        }
    }
}
