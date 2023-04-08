package com.example.lovelychecker.tovar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.lovelychecker.R;

import java.util.ArrayList;
import java.util.List;

public class Tovar_Activity extends AppCompatActivity {

    RecyclerView prodList;
    ProductAdapter productAdapter;

    ImageButton find;
    EditText finder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_tovar);
        finder = (EditText) findViewById(R.id.finder);
        find = (ImageButton) findViewById(R.id.find);
        List<Product> item = new ArrayList<>();
        item.add(new Product(13000, "Xiaomi note 5"));
        item.add(new Product(60000, "Google Pixel 6 Pro"));

        setProductRecylder(item);
    }

    private void setProductRecylder(List<Product> item) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        prodList = findViewById(R.id.prodList);
        prodList.setLayoutManager(layoutManager);

        productAdapter = new ProductAdapter(this, item);
        prodList.setAdapter(productAdapter);
    }

    public void find(View view){
        String text = finder.getText().toString();
        //Вставить функцию поиска через сервер
    }
}
