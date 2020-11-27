package com.example.retrofit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.InfoViewHolder> {

    private List<Post> posts;

    public InfoAdapter(List<Post> posts) {
        this.posts = posts;
    }

    @NonNull
    @Override
    public InfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false);
        return new InfoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull InfoViewHolder holder, int position) {
        Post post = posts.get(position);

        holder.id.setText("id: " + Integer.toString(post.getId()));
        holder.userId.setText("userId: " + Integer.toString(post.getUserId()));
        holder.title.setText("title: " + post.getTitle());
        holder.body.setText("body: " + post.getBody());

    }


    @Override
    public int getItemCount() {
        return posts.size();
    }

    static class InfoViewHolder extends RecyclerView.ViewHolder {

        public TextView id, userId, title, body;

        public InfoViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id);
            userId = itemView.findViewById(R.id.userId);
            title = itemView.findViewById(R.id.title);
            body = itemView.findViewById(R.id.body);
        }

    }

    Post getItem(int id) {
        return posts.get(id);
    }


}
