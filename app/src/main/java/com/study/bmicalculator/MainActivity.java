package com.study.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import java.text.DecimalFormat;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {


    private TextView resultText;
    private RadioButton maleButton;
    private RadioButton femaleButton;
    private EditText ageEditText;
    private EditText feetEditText;
    private EditText inchesEditText;
    private EditText weightEditText;
    private Button calculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // For Actoin bar color change
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.purple_700)));

        findViews();
        setupButtonClickListener();
        // Invisible result text initialy



    }

    private void findViews(){

        resultText = findViewById(R.id.text_view_result);
        maleButton = findViewById(R.id.radio_button_male);
        femaleButton = findViewById(R.id.radio_button_female);
        ageEditText = findViewById(R.id.edit_text_age);
        feetEditText = findViewById(R.id.edit_text_feet);
        inchesEditText = findViewById(R.id.edit_text_inches);
        weightEditText = findViewById(R.id.edit_text_weight);
        calculateButton = findViewById(R.id.button_calculate);
    }

    private void setupButtonClickListener() {
        calculateButton.setOnClickListener(view -> {
            String ages=ageEditText.getText().toString().trim();
            String feet=feetEditText.getText().toString().trim();
            String inches=inchesEditText.getText().toString().trim();
            String weight=weightEditText.getText().toString().trim();


            if (TextUtils.isEmpty(ages)){
                ageEditText.setError("Fill this");
                return;
            }
            if (TextUtils.isEmpty(feet)){
                feetEditText.setError("Fill this");
                return;
            }
            if (TextUtils.isEmpty(inches)){
                inchesEditText.setError("Fill this");
                return;
            }
            if (TextUtils.isEmpty(weight)){
                weightEditText.setError("Fill this");
                return;
            }

                double bmiResult = calculateBmi();

                String ageText = ageEditText.getText().toString();
                int age = Integer.parseInt(ageText);

                if (age >= 18) {
                    displayResult(bmiResult);
                } else {
                    displayGuidance(bmiResult);

        }});
    }


    private double calculateBmi() {

        String feetText= feetEditText.getText().toString();
        String inchesText= inchesEditText.getText().toString();
        String weightText= weightEditText.getText().toString();

        // Convert no. from 'String' to 'int'
        int feet=Integer.parseInt(feetText);
        int inches=Integer.parseInt(inchesText);
        int weight=Integer.parseInt(weightText);

        int totalInches= (feet * 12)+ inches;

        // Convert inches into meters by multiply by 0.0254
        double heightInMeters = totalInches * 0.0254;

        // calculate bmi formula = weight divide by height in meters square
        return weight / (heightInMeters * heightInMeters);
    }

    private void displayResult(double bmi) {

        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormatter.format(bmi);


        String fullResultString;
        if (bmi < 18.5) {
            // Underweight
            fullResultString = bmiTextResult + " :- You are underweight";
        } else if (bmi > 25) {
            // Overweight
            fullResultString = bmiTextResult + " :- You are overweight";
        } else {
            // Healthy
            fullResultString = bmiTextResult + " :- You are Healthy";
        }

        resultText.setText(fullResultString);
    }

    private void displayGuidance(double bmi) {
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormatter.format(bmi);

        String fullResultString;
        if (maleButton.isChecked()){
            //Display boy guidance
            fullResultString = bmiTextResult+" :- You are under 18 please consult your doctor for the range of boys.";
        }else if(femaleButton.isChecked())
        {
            //Display girls guidance
            fullResultString = bmiTextResult+" :- You are under 18 please consult your doctor for the range of girls.";
        }
        else {
            //Diplay general guidance
            fullResultString = bmiTextResult+" :- You are under 18 please consult your doctor for the range.";
        }
        resultText.setText(fullResultString);
    }


}





