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
import android.widget.AdapterView;
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

    Spinner unitSpinner;
    EditText editText;
    Button button;

    ImageButton exchangeButton;
    TextView textView;
    TextView erroTextView;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //find this views that the program needs
        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);
        unitSpinner = findViewById(R.id.unitSpinner);
        button = findViewById(R.id.button);
        exchangeButton = findViewById(R.id.exchange);
        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);
        erroTextView = findViewById(R.id.erroTextView);

        //select the type of unit(by unitSpinner)
        unitSpinner.setEnabled(true);
        unitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                updatePageContent(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


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

        //click the exchange button
        exchangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //store two values for exchanging two values later
                String selectedValue1 = spinner1.getSelectedItem().toString();
                String selectedValue2 = spinner2.getSelectedItem().toString();
                String temp = selectedValue1;
                selectedValue1 = selectedValue2;
                selectedValue2 = temp;

                //exchange two spinner array
                ArrayAdapter<String> adapter1 = (ArrayAdapter<String>) spinner1.getAdapter();
                ArrayAdapter<String> adapter2 = (ArrayAdapter<String>) spinner2.getAdapter();
                spinner1.setAdapter(adapter2);
                spinner2.setAdapter(adapter1);


                //exchange two values
                spinner1.setSelection(((ArrayAdapter<String>) spinner1.getAdapter()).getPosition(selectedValue1));
                spinner2.setSelection(((ArrayAdapter<String>) spinner2.getAdapter()).getPosition(selectedValue2));

            }
        });


    }
    //change spinner1 and spinner2 by position of unitSpinner
    private void updatePageContent(int position){
        switch(position){
            case 0:
                //length is selected
                String[] lengthUnits1 = getResources().getStringArray(R.array.lengthArray1);
                String[] lengthUnits2 = getResources().getStringArray(R.array.lengthArray2);
                ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, lengthUnits1);
                ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, lengthUnits2);
                spinner1.setAdapter(adapter1);
                spinner2.setAdapter(adapter2);
                break;
            case 1:
                //Weight is selected
                String[] weightUnit1 = getResources().getStringArray(R.array.weightArray1);
                String[] weightUnit2 = getResources().getStringArray(R.array.weightArray2);
                ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, weightUnit1);
                ArrayAdapter<String> adapter4 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, weightUnit2);
                spinner1.setAdapter(adapter3);
                spinner2.setAdapter(adapter4);
                break;
            case 2:
                //tempurature is selected
                String[] tempuratureArray = getResources().getStringArray(R.array.lengthArray1);
                ArrayAdapter<String> adapter5 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, tempuratureArray);
                spinner1.setAdapter(adapter5);
                spinner2.setAdapter(adapter5);
                break;
            default:

                break;
        }
    }

    //convet units function
    protected void converter(String unit1, String unit2,double inputDoubleValue){
        //which means input number is valid.
        switch (unit1) {
            //tempurature part
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

                //length part
            case "inch":
                if (unit2.equals("cm")) {
                    Log.v("unit1:",unit1);
                    Log.v("unit2:",unit2);
                    double output = inputDoubleValue*2.54;
                    textView.setText(String.valueOf(output) );
                } else{
                    erroTextView.setText("Please select a correct unit");
                }
                break;
            case "foot":
                if (unit2.equals("cm")) {
                    Log.v("unit1:",unit1);
                    Log.v("unit2:",unit2);
                    double output = inputDoubleValue*30.48;
                    textView.setText(String.valueOf(output) );
                } else{
                    erroTextView.setText("Please select a correct unit");
                }
                break;
            case "yard":
                if (unit2.equals("cm")) {
                    Log.v("unit1:",unit1);
                    Log.v("unit2:",unit2);
                    double output = inputDoubleValue*91.44;
                    textView.setText(String.valueOf(output) );
                } else{
                    erroTextView.setText("Please select a correct unit");
                }
                break;
            case "cm":
                if (unit2.equals("inch")) {
                    Log.v("unit1:",unit1);
                    Log.v("unit2:",unit2);
                    double output = inputDoubleValue/2.54;
                    textView.setText(String.valueOf(output) );
                } else if(unit2.equals("foot")){
                    Log.v("unit1:",unit1);
                    Log.v("unit2:",unit2);
                    double output = inputDoubleValue/30.28;
                    textView.setText(String.valueOf(output) );
                } else if (unit2.equals("yard")) {
                    Log.v("unit1:",unit1);
                    Log.v("unit2:",unit2);
                    double output = inputDoubleValue/91.44;
                    textView.setText(String.valueOf(output) );
                } else{
                    erroTextView.setText("Please select a correct unit");
                }
                break;
            //Weight part
            case "pound":
                if (unit2.equals("kg")) {
                    Log.v("unit1:",unit1);
                    Log.v("unit2:",unit2);
                    double output = inputDoubleValue*0.45;
                    textView.setText(String.valueOf(output) );
                } else{
                    erroTextView.setText("Please select a correct unit");
                }
                break;
            case "ounce":
                if (unit2.equals("g")) {
                    Log.v("unit1:",unit1);
                    Log.v("unit2:",unit2);
                    double output = inputDoubleValue*28.35;
                    textView.setText(String.valueOf(output) );
                } else{
                    erroTextView.setText("Please select a correct unit");
                }
                break;
            case "ton":
                if (unit2.equals("kg")) {
                    Log.v("unit1:",unit1);
                    Log.v("unit2:",unit2);
                    double output = inputDoubleValue*907.19;
                    textView.setText(String.valueOf(output) );
                } else{
                    erroTextView.setText("Please select a correct unit");
                }
                break;
            case "kg":
                if (unit2.equals("pound")) {
                    Log.v("unit1:",unit1);
                    Log.v("unit2:",unit2);
                    double output = inputDoubleValue/0.45;
                    textView.setText(String.valueOf(output) );
                } else if (unit2.equals("ton")) {
                    Log.v("unit1:",unit1);
                    Log.v("unit2:",unit2);
                    double output = inputDoubleValue/907.19;
                    textView.setText(String.valueOf(output) );
                } else{
                    erroTextView.setText("Please select a correct unit");
                }
                break;
            case "g":
                if (unit2.equals("ounce")) {
                    Log.v("unit1:",unit1);
                    Log.v("unit2:",unit2);
                    double output = inputDoubleValue/28.35;
                    textView.setText(String.valueOf(output) );
                } else{
                    erroTextView.setText("Please select a correct unit");
                }
                break;

        }
    }

}