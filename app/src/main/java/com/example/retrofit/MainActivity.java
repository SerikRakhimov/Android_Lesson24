package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private List<Post> postList;
    InfoAdapter infoAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        postList = new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        retrofit();
    }

    public void retrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory((GsonConverterFactory.create()))
                .build();
        MessageApi messageApi = retrofit.create(MessageApi.class);
        Call<List<Post>> posts = messageApi.getPosts();
        posts.enqueue(new retrofit2.Callback<List<Post>>() {
            @Override
            public void onResponse(retrofit2.Call<List<Post>> call, retrofit2.Response<List<Post>> response) {
                postList = response.body();
                infoAdapter = new InfoAdapter(postList);
                recyclerView.setAdapter(infoAdapter);
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                t.printStackTrace();
            }
            
        });
    }

}