package com.dean.uascrud.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.dean.uascrud.R;
import com.dean.uascrud.model.PersonItem;
import com.dean.uascrud.remote.APIUtils;
import com.dean.uascrud.remote.ProductServices;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductActivity extends AppCompatActivity {

    ProductServices productService;
    EditText etName, etClass, etLesson, etDate, etKet, etId;
    Button btnSave, btnDel;
    TextView tvId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etName = findViewById(R.id.et_name);
        etClass = findViewById(R.id.et_class);
        etLesson = findViewById(R.id.et_lesson);
        etDate = findViewById(R.id.et_date);
        etKet = findViewById(R.id.et_ket);
        btnSave = findViewById(R.id.btn_save);
        btnDel = findViewById(R.id.btn_delete);
        etId = findViewById(R.id.et_id);
        tvId = findViewById(R.id.tv_id);

        productService = APIUtils.getProductServices();

        Bundle extras = getIntent().getExtras();
        String productName = extras.getString("name");
        String productClass = extras.getString("class");
        String productLesson = extras.getString("lesson");
        String productDate = extras.getString("date");
        String productKet = extras.getString("ket");
        final String productID = extras.getString("id");

        etId.setText(productID);
        etName.setText(productName);
        etClass.setText(productClass);
        etLesson.setText(productLesson);
        etDate.setText(productDate);
        etKet.setText(productKet);

        if (productID != null && productID.trim().length() > 0){
            etId.setFocusable(false);
        } else {
            tvId.setVisibility(View.INVISIBLE);
            etId.setVisibility(View.INVISIBLE);
            btnDel.setVisibility(View.INVISIBLE);
        }

        btnSave.setOnClickListener(view -> {

            String name = etName.getText().toString();
            String clas = etClass.getText().toString();
            String lesson = etLesson.getText().toString();
            String date = etDate.getText().toString();
            String ket = etKet.getText().toString();

            if (productID != null && productID.trim().length() >0){
                updateProduct(Integer.parseInt(productID), name, clas, lesson, date, ket);
            } else {
                addProduct(name, clas, lesson, date, ket);
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteProduct(Integer.parseInt(productID));
                Intent intent = new Intent(ProductActivity.this,
                        MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void addProduct(String name, String clas, String lesson, String date, String ket) {
        Call<PersonItem> call = productService.addProduct(name, clas, lesson, date, ket);
        call.enqueue(new Callback<PersonItem>() {
            @Override
            public void onResponse(Call<PersonItem> call, Response<PersonItem> response) {
                if (response.isSuccessful()){
                    Toast.makeText(ProductActivity.this, "product added",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ProductActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<PersonItem> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    private void updateProduct(int id, String name, String clas, String lesson, String date, String ket) {
        Call<PersonItem> call = productService.updateProduct(id, name, clas, lesson, date, ket);
        call.enqueue(new Callback<PersonItem>() {
            @Override
            public void onResponse(Call<PersonItem> call, Response<PersonItem> response) {
                if (response.isSuccessful()){
                    Toast.makeText(ProductActivity.this, "Product Updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ProductActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<PersonItem> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    private void deleteProduct(int id) {
        Call<PersonItem> call = productService.deleteProduct(id);
        call.enqueue(new Callback<PersonItem>() {
            @Override
            public void onResponse(Call<PersonItem> call, Response<PersonItem> response) {
                if (response.isSuccessful()){
                    Toast.makeText(ProductActivity.this, "Product deleted",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PersonItem> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}