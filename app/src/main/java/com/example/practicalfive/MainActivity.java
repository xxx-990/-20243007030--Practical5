package com.example.practicalfive;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_CITY_NAME = "city_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText etInput = findViewById(R.id.et_input_city);
        TextProcessor processor = new TextProcessor();

        etInput.setOnEditorActionListener((TextView v, int actionId, KeyEvent event) -> {
            if(actionId == EditorInfo.IME_ACTION_GO){
                String rawInput = etInput.getText().toString();
                String validCity = processor.processMunicipality(rawInput);
                if(validCity == null){
                    Toast.makeText(MainActivity.this,"Invalid municipality name!",Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(MainActivity.this, Municipality.class);
                    intent.putExtra(EXTRA_CITY_NAME, validCity);
                    startActivity(intent);
                }
                return true;
            }
            return false;
        });
    }
}
