package com.example.final2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Search extends AppCompatActivity {
    EditText et_search_name;
    Button btn_search_name, btn_search_all;
    Access_DataBase access_dataBase;
    RecyclerView recyclerView;
    Apadter_Lib apadter_lib;
    ArrayList<LibContans> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        et_search_name = findViewById(R.id.search_et_search_name);
        btn_search_all = findViewById(R.id.search_btn_all_books);
        btn_search_name = findViewById(R.id.search_btn_search_name);
        recyclerView = findViewById(R.id.search_rv);
        arrayList = new ArrayList<>();
        access_dataBase = Access_DataBase.getInstance(getApplicationContext());
        access_dataBase.Open();

        btn_search_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                arrayList = access_dataBase.search_items(et_search_name.getText().toString());
                Toast.makeText(getBaseContext(), access_dataBase.itmes_count() + " ", Toast.LENGTH_LONG).show();

            }
        });
//        btn_search_all.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
        arrayList = access_dataBase.get_items();
//            }
//        });
        access_dataBase.Close();

        apadter_lib = new Apadter_Lib(arrayList, getBaseContext());

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(apadter_lib);
    }
}