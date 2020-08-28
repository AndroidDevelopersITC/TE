package com.example.final2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Add extends AppCompatActivity {
    EditText et_name, et_amount, et_price;
    Button btn_add;
    Access_DataBase access_dataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        et_name = findViewById(R.id.add_et_name);
        et_amount = findViewById(R.id.add_et_amount);
        et_price = findViewById(R.id.add_et_price);
        btn_add = findViewById(R.id.add_btn_adb_book);
      access_dataBase = Access_DataBase.getInstance(getBaseContext());
        access_dataBase.Open();
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Boolean b = access_dataBase.insert_itmes(new LibContans(11,et_name.getText().toString(), Integer.parseInt(et_amount.getText().toString()), Integer.parseInt(et_price.getText().toString())));
                 Toast.makeText(getBaseContext(), b + "", Toast.LENGTH_LONG).show();
                access_dataBase.Close();

            }
        });



    }
}