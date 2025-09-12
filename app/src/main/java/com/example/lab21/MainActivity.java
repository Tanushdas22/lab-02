package com.example.lab21;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;

    EditText editTextCity;
    Button buttonAdd, buttonDelete;

    int selectedPosition = -1; // Track selected item

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);
        editTextCity = findViewById(R.id.editTextCity);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonDelete = findViewById(R.id.buttonDelete);

        String[] cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};
        dataList = new ArrayList<>(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, dataList);
        cityList.setAdapter(cityAdapter);

        // Select item from list
        cityList.setOnItemClickListener((parent, view, position, id) -> {
            selectedPosition = position;
            cityList.setItemChecked(position, true);
        });

        // Add city button
        buttonAdd.setOnClickListener(v -> {
            String newCity = editTextCity.getText().toString().trim();
            if (!newCity.isEmpty()) {
                dataList.add(newCity);
                cityAdapter.notifyDataSetChanged();
                editTextCity.setText("");
            } else {
                Toast.makeText(MainActivity.this, "Enter a city name", Toast.LENGTH_SHORT).show();
            }
        });

        // Delete city button
        buttonDelete.setOnClickListener(v -> {
            if (selectedPosition != -1) {
                dataList.remove(selectedPosition);
                cityAdapter.notifyDataSetChanged();
                selectedPosition = -1;
                cityList.clearChoices();
            } else {
                Toast.makeText(MainActivity.this, "Select a city to delete", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
