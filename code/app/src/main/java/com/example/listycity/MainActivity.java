package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    EditText cityEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //find all the visual elements - listview
        cityList = findViewById(R.id.city_list);
        cityEdit = findViewById(R.id.edt_ciity);
        String[] cities = {"Edmonton", "Paris", "London", "Ottawa"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        //create the adapter, adapts into being displayed as a listview
        cityAdapter= new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);
        dataList.add("Calgary");
        cityAdapter.notifyDataSetChanged();

        Button button = findViewById(R.id.add_button);

        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dataList.remove(position);
                cityAdapter.notifyDataSetChanged();
                //cityList.setVisibility(View.GONE);
            }
        });


        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                String value = cityEdit.getText().toString();
                dataList.add(value);
                cityAdapter.notifyDataSetChanged();
            }
        });
    }
}