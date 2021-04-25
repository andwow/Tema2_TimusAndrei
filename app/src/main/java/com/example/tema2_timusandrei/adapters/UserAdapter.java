package com.example.tema2_timusandrei.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tema2_timusandrei.R;
import com.example.tema2_timusandrei.interfaces.OnUserItemClick;
import com.example.tema2_timusandrei.models.User;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<User> userList;
    private OnUserItemClick onUserItemClick;
    public UserAdapter(ArrayList<User> userList, OnUserItemClick onUserItemClick) {
        this.userList = userList;
        this.onUserItemClick = onUserItemClick;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.user_cell, parent, false);
        UserViewHolder userViewHolder = new UserViewHolder(view);
        return userViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        User user = (User) userList.get(position);
        ((UserViewHolder) holder).bind(user);
    }

    @Override
    public int getItemCount() { return userList.size(); }

    class UserViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView userName;
        private View view;
        public UserViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.title);
            userName = view.findViewById(R.id.subtitle);
            this.view = view;
        }
        public void bind(User user) {
            name.setText(user.getName());
            userName.setText(user.getUserName());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onUserItemClick != null) {
                        onUserItemClick.onClick(user);
                    }
                }
            });
        }
    }
}
