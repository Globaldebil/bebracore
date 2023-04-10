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

        Product product1 = new Product();
        product1.setTitle("Xiaomi note 5");
        product1.setPriceFrom(15000.0);
        item.add(product1);

        Product product2 = new Product();
        product2.setTitle("Xiaomi note 5");
        product2.setPriceFrom(15000.0);
        item.add(product2);

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
