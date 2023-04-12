package com.example.lovelychecker.tovar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.lovelychecker.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Fitler extends AppCompatActivity {

    private Button okey;

    private final Set<String> brand = new HashSet<>();

    private final Set<String> ozy = new HashSet<>();
    private FilterAdapter filterAdapter;

    private RecyclerView brandList;

    public Fitler() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitler);
        okey = (Button) findViewById(R.id.set);
        brand.add("Xiaomi");
        brand.add("Apple");
        brand.add("Samsung");
        ozy.add("4 GB");
        ozy.add("2 GB");
        ozy.add("8 GB");
        okey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Fitler.this, Tovar_Activity.class);
                startActivity(intent);
            }
        });
        setBrandRecylder(brand);
        setOzyRecylder(ozy);
    }
    private void setBrandRecylder(Set<String> set) {
        List<String> item = new ArrayList<String>(set);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        brandList = findViewById(R.id.brandList);
        brandList.setLayoutManager(layoutManager);

        filterAdapter  = new FilterAdapter(this, item);
        brandList.setAdapter(filterAdapter);
    }
    private void setOzyRecylder(Set<String> set) {
        List<String> item = new ArrayList<String>(set);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        brandList = findViewById(R.id.ozy);
        brandList.setLayoutManager(layoutManager);

        filterAdapter  = new FilterAdapter(this, item);
        brandList.setAdapter(filterAdapter);
    }
}