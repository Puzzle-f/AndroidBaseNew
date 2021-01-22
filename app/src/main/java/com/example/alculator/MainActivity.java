package com.example.alculator;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView screen;
    private Calculation calculation = new Calculation(0, 0);
    private TextView topScreen;
    private String KeyCounters = "KeyCounters";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        screen = findViewById(R.id.screen);
        topScreen = findViewById(R.id.top_screen);
        initButton();
        initButtonClear();
    }

    //    слушатель для цифр
    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        if (calculation.operator.equals("")) {
            screen.append(button.getText());
            calculation.firstNumber = Integer.valueOf(screen.getText().toString());
            Log.d("key", "firstNumber " + calculation.firstNumber);
        } else {
            screen.setText("");
            screen.append(button.getText());
            calculation.lastNumber = Integer.valueOf(screen.getText().toString());
            Log.d("key", "lastNumber " + calculation.lastNumber);
        }
        Log.d("key", "onClick " + button.getText());
    }

    //    слушатель для настроек
    public View.OnClickListener settingsListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, SettingActivity.class);
            startActivity(intent);
            Log.d("key", "Setting_button");
        }
    };

    //    слушатель для действий
    public View.OnClickListener buttonActionListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button button = (Button) v;
            calculation.operator = button.getText().toString();
            topScreen.setText(screen.getText());
            screen.setText(calculation.operator);
            Log.d("key", "Action " + calculation.operator);
        }
    };

    public View.OnClickListener buttonResultListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            screen.setText(calculation.result());
            Log.d("key", "buttonResultListener");
        }
    };

    public void initButtonClear() {
        Button buttonClear = findViewById(R.id.button_clear);
        buttonClear.setOnClickListener(v -> {
            screen.setText(null);
            topScreen.setText(null);
            calculation.firstNumber = 0;
            calculation.firstNumber = 0;
            Log.d("key", "initButtonClear");
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle instanceState) {
        super.onSaveInstanceState(instanceState);
        instanceState.putParcelable(KeyCounters, calculation);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        savedInstanceState.getParcelable(KeyCounters);
        Log.d("key", "onRestoreInstanceState firstNumber" + calculation.firstNumber + " lastNumber " + calculation.lastNumber);
    }

    private void initButton() {
        Button setting = findViewById(R.id.settings);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button button7 = findViewById(R.id.button7);
        Button button8 = findViewById(R.id.button8);
        Button button9 = findViewById(R.id.button9);
        Button button0 = findViewById(R.id.button0);
        Button buttonPoint = findViewById(R.id.button_point);
        Button buttonResult = findViewById(R.id.button_result);
        Button buttonSum = findViewById(R.id.button_sum);
        Button buttonMinus = findViewById(R.id.button_minus);
        Button buttonMulti = findViewById(R.id.button_multi);
        Button buttonDivision = findViewById(R.id.button_division);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        button0.setOnClickListener(this);
        buttonPoint.setOnClickListener(this);
        buttonResult.setOnClickListener(buttonResultListener);
        buttonSum.setOnClickListener(buttonActionListener);
        buttonMinus.setOnClickListener(buttonActionListener);
        buttonMulti.setOnClickListener(buttonActionListener);
        buttonDivision.setOnClickListener(buttonActionListener);
        setting.setOnClickListener(settingsListener);
        Log.d("key", "initButton");
    }
}








