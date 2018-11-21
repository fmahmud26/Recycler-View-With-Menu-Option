package com.example.firoz.recycler_view_with_menu_option.activity;

import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.firoz.recycler_view_with_menu_option.R;
import com.example.firoz.recycler_view_with_menu_option.adapter.UserAdapter;
import com.example.firoz.recycler_view_with_menu_option.model.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements UserAdapter.ItemClickListener {

    private RecyclerView recyclerView;
    private List<User> users = new ArrayList<>();
    private UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        preparedData();
    }


    private void initView() {
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recylerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    private void preparedData() {

        // load iamge and name from string.xml file
        String[] names = getResources().getStringArray(R.array.user_list);
        TypedArray images = getResources().obtainTypedArray(R.array.image_list);

        for (int i = 0; i < names.length; i++) {
            // add all user into users
            users.add(new User(images.getResourceId(i, -1), names[i]));
        }

        adapter = new UserAdapter(this, users);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void itemClick(View view, int position) {


        switch (view.getId()) {

            case R.id.pictureImageView:
                Toast.makeText(this, "you clicked picture : " + users.get(position).name, Toast.LENGTH_SHORT).show();
                break;

            case R.id.nameTextView:
                Toast.makeText(this, "you clicked name : " + users.get(position).name, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void popUpMenuClick(MenuItem item, int position) {

        switch (item.getItemId()) {

            case R.id.action_delete:
                users.remove(position);
                adapter.notifyDataSetChanged();
                Toast.makeText(this, "deleted", Toast.LENGTH_SHORT).show();
                break;

            case R.id.action_rate:
                Toast.makeText(this, "rating", Toast.LENGTH_SHORT).show();
                break;

            case R.id.action_about:
                Toast.makeText(this, "about", Toast.LENGTH_SHORT).show();
                break;


        }
    }
}
