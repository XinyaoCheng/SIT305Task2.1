package com.example.task21project;

/*
name: Xinyao Cheng; ID:223122637
courseName:SIT305
 */
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    Spinner spinner1;
    Spinner spinner2;
    EditText editText;
    Button button;

    ImageButton exchangeButton;
    TextView textView;
    TextView errorView;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //find this views that the program needs
        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);
        button = findViewById(R.id.button);
        exchangeButton = findViewById(R.id.exchange);
        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get two units---input unit is unit1, output unit is unit2.
                String unit1 = spinner1.getSelectedItem().toString();
                String unit2 = spinner2.getSelectedItem().toString();

                //print two units for test.
//                Log.v("checking input spinner value", unit1);
//                Log.v("checking output spinner value", unit2);
                String input = editText.getText().toString();
                //print input value for test.
                double inputDoubleValue =0;
                    inputDoubleValue = Double.parseDouble(input);

                Log.v("Input num value",String.valueOf(inputDoubleValue));

                //recognize which kind of input unit is.
                if(inputDoubleValue!=0){
                    converter(unit1,unit2,inputDoubleValue);
                }

            }
        });

        exchangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //exchange two spinners
                String selectedValue1 = spinner1.getSelectedItem().toString();
                String selectedValue2 = spinner2.getSelectedItem().toString();
                String temp = selectedValue1;
                selectedValue1 = selectedValue2;
                selectedValue2 = temp;
                spinner1.setSelection(((ArrayAdapter<String>) spinner1.getAdapter()).getPosition(selectedValue1));
                spinner2.setSelection(((ArrayAdapter<String>) spinner2.getAdapter()).getPosition(selectedValue2));

            }
        });


    }
    protected void converter(String unit1, String unit2,double inputDoubleValue){
        //which means input number is valid.
        switch (unit1) {
            case "Celsius":
                if (unit2.equals("Fahrenheit")) {
                    Log.v("unit1:",unit1);
                    Log.v("unit2:",unit2);
                    //change value from Celsius  to Fahrenheit
                    double output = (inputDoubleValue-32)/1.8;
                    textView.setText(String.valueOf(output));
                } else if(unit2.equals("Kelvin")){
                    Log.v("unit1:",unit1);
                    Log.v("unit2:",unit2);
                    //change value from Celsius to Kelvin
                    double output = inputDoubleValue - 273.15;
                    textView.setText(String.valueOf(output));
                }else{
                    textView.setText((String.valueOf(inputDoubleValue)));
                }
                break;
            case "Fahrenheit":
                if (unit2.equals("Celsius")) {
                    Log.v("unit1:",unit1);
                    Log.v("unit2:",unit2);
                    double output = (inputDoubleValue * 1.8) + 32;
                    textView.setText(String.valueOf(output));
                } else if(unit2.equals("Kelvin")){
                    Log.v("unit1:",unit1);
                    Log.v("unit2:",unit2);
                    double output = (inputDoubleValue - 273.15)*1.8 + 32;
                    textView.setText(String.valueOf(output));
                    }else{
                textView.setText((String.valueOf(inputDoubleValue)));
            }
                break;

            case "Kelvin":
                if (unit2.equals("Celsius ")) {
                    Log.v("unit1:",unit1);
                    Log.v("unit2:",unit2);
                    double output = inputDoubleValue +273.15;
                    textView.setText(String.valueOf(output) );
                } else if(unit2.equals("Fahrenheit")){
                    Log.v("unit1:",unit1);
                    Log.v("unit2:",unit2);
                    double output = (inputDoubleValue -32)/1.8 + 273.15;
                    textView.setText(String.valueOf(output) );
                }else{
                    textView.setText((String.valueOf(inputDoubleValue)));
                }
                break;
        }
    }

}