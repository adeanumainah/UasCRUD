package com.dean.uascrud.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dean.uascrud.R;
import com.dean.uascrud.activity.ProductActivity;
import com.dean.uascrud.model.PersonItem;

import java.util.List;

public class ProductAdapter extends ArrayAdapter<PersonItem> {
    private Context context;
    private List<PersonItem> personItem;

    public ProductAdapter(@NonNull Context context, int resource,
                          @NonNull List<PersonItem> objects) {
        super(context, resource, objects);
        this.context = context;
        this.personItem = objects;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.list_item, parent, false);

        TextView tvIdProduct = v.findViewById(R.id.tv_product_id);
        TextView tvNameProduct = v.findViewById(R.id.tv_product_name);
        TextView tvClassProduct = v.findViewById(R.id.tv_product_class);
        TextView tvLessonProduct = v.findViewById(R.id.tv_product_lesson);
        TextView tvDateProduct = v.findViewById(R.id.tv_product_date);
        TextView tvKetProduct = v.findViewById(R.id.tv_product_ket);

        tvIdProduct.setText(String.valueOf(personItem.get(position).getId()));
        tvNameProduct.setText(String.valueOf(personItem.get(position).getName()));
        tvClassProduct.setText(String.valueOf(personItem.get(position).getClas()));
        tvLessonProduct.setText(String.valueOf(personItem.get(position).getLesson()));
        tvDateProduct.setText(String.valueOf(personItem.get(position).getDate()));
        tvKetProduct.setText(String.valueOf(personItem.get(position).getKet()));

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductActivity.class);
                intent.putExtra("id", String.valueOf(personItem.get(position).getId()));
                intent.putExtra("name", personItem.get(position).getName());
                intent.putExtra("class", personItem.get(position).getClas());
                intent.putExtra("lesson", personItem.get(position).getLesson());
                intent.putExtra("date", personItem.get(position).getDate());
                intent.putExtra("ket", personItem.get(position).getKet());
                context.startActivity(intent);
            }
        });

        return v;
    }
}
