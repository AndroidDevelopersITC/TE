package com.example.final2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Buy extends AppCompatActivity {
    EditText et_book_buy;
    Button btn_buy;
    Access_DataBase access_dataBase;
    LibContans  libContans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        et_book_buy = findViewById(R.id.buy_et_buy_name);
        btn_buy = findViewById(R.id.buy_btn_buy_name);
        access_dataBase = Access_DataBase.getInstance(this);
         btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                access_dataBase.Open();

                libContans = access_dataBase.search_items(et_book_buy.getText().toString());
                if (libContans!=null) {
                    int x = libContans.getAmount_book();
                    if (x > 0) {
                        libContans.setAmount_book(--x);
                        access_dataBase.update_itmes(libContans, et_book_buy.getText().toString());
                        if (x < 1) {
                            access_dataBase.remove_itmes(libContans.getName_book() + "");

                        }
                    } else {
                        Toast.makeText(getBaseContext(), "the amount ended", Toast.LENGTH_LONG).show();


                    }
                }else {
                    Toast.makeText(getBaseContext(), "not fonded", Toast.LENGTH_LONG).show();



                }
                access_dataBase.Close();

            }
        });


    }
}