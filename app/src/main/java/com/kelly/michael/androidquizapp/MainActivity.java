package com.kelly.michael.androidquizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Spinner letterSpinner;
    private EditText editText;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        letterSpinner = (Spinner) findViewById(R.id.letter_spinner);
        result = (TextView)findViewById(R.id.result);
        editText = (EditText)findViewById(R.id.text_box);

        populateSpinner();

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                recount(letterSpinner.getSelectedItem().toString());
            }
        });
        letterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                recount(letterSpinner.getSelectedItem().toString());
            }

            public void onNothingSelected(AdapterView<?> parent) {
                letterSpinner.setSelection(0);
            }
        });
    }

    private void populateSpinner(){
        List<String> letterArray = new ArrayList<>();
        for(char let = 'A'; let <= 'Z'; let++){
            letterArray.add(let + "");
        }
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, letterArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        letterSpinner.setAdapter(adapter);
    }

    private void recount(String letter){
        String input = editText.getText().toString().toUpperCase();
        int count = 0;
        int index = 0;
        while(input.indexOf(letter, index) >= 0){
            count++;
            index = input.indexOf(letter, index) + 1;
        }
        result.setText(String.format("%s occured %d times", letter, count));
    }
}
