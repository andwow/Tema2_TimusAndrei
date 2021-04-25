package com.example.tema2_timusandrei.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tema2_timusandrei.R;
import com.example.tema2_timusandrei.models.Album;

import java.util.ArrayList;

public class AlbumAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Album> albumList;
    public AlbumAdapter(ArrayList<Album> albumList) { this.albumList = albumList; }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.album_cell, parent, false);
        AlbumAdapter.AlbumViewHolder albumViewHolder = new AlbumAdapter.AlbumViewHolder(view);
        return albumViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Album album = (Album) albumList.get(position);
        ((AlbumAdapter.AlbumViewHolder) holder).bind(album);
    }

    @Override
    public int getItemCount() { return albumList.size(); }

    class AlbumViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        public AlbumViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
        }
        public void bind(Album album) {
            title.setText(album.getTitle());
        }
    }
}
