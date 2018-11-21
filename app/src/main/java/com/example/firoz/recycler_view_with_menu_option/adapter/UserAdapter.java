package com.example.firoz.recycler_view_with_menu_option.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.firoz.recycler_view_with_menu_option.R;
import com.example.firoz.recycler_view_with_menu_option.model.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private Context context;
    private List<User> users;
    private ItemClickListener itemClickListener;

    public UserAdapter(Context context, List<User> users) {
        this.context = context;
        this.users = users;
        itemClickListener = (ItemClickListener) context;
    }

    public interface ItemClickListener {

        void itemClick(View view, int position);

        void popUpMenuClick(MenuItem item, int position);
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {

        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_layout, null);

        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder userViewHolder, final int position) {

        userViewHolder.nameTextView.setText(users.get(position).name);
        userViewHolder.userImageView.setImageResource(users.get(position).image);

        userViewHolder.userImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.itemClick(v, position);
            }
        });

        userViewHolder.nameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.itemClick(v, position);
            }
        });

        userViewHolder.menuImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popupMenu = new PopupMenu(context, v);
                popupMenu.inflate(R.menu.my_menu);
                popupMenu.show();

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        itemClickListener.popUpMenuClick(item, position);
                        return false;
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {

        ImageView userImageView;
        ImageButton menuImageView;
        TextView nameTextView;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            userImageView = itemView.findViewById(R.id.pictureImageView);
            menuImageView = itemView.findViewById(R.id.menuImageView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
        }
    }
}
