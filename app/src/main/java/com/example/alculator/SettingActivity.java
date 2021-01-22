package com.example.alculator;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.radiobutton.MaterialRadioButton;

public class SettingActivity extends AppCompatActivity {

    // Имя настроек
    private static final String nameSharedPreference = "LOGIN";

    // Имя параметра в настройках
    private static final String appTheme = "APP_THEME";

    private static final int codeStyleYellow = 0;
    private static final int codeStyleBlack = 1;
    private static final int codeStyleMetal = 2;
    private static final int codeLangRu = 3;
    private static final int codeLangEn = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Устанавливать тему надо только до установки макета активити
        setTheme(getAppTheme(R.style.ThemeСalculator));
        setContentView(R.layout.settings_layout);
        initThemeChooser();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
//        int style = SettingActivity.this.findViewById(R.id.getCodeStyle(int codeStyle));

    }

    // Инициализация радиокнопок
    private void initThemeChooser() {
        initRadioButton(findViewById(R.id.radioButtonYellow),
                codeStyleYellow);
        initRadioButton(findViewById(R.id.radioButtonBlack),
                codeStyleBlack);
        initRadioButton(findViewById(R.id.radioButtonMetal),
                codeStyleMetal);
        RadioGroup rg = findViewById(R.id.radio_group_color);
        ((MaterialRadioButton)rg.getChildAt(getCodeStyle(codeStyleYellow))).setChecked(true);
    }

    // Все инициализации кнопок очень похожи, поэтому создадим метод для переиспользования
    private void initRadioButton(View button, final int codeStyle){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // сохраним настройки
                setAppTheme(codeStyle);
                // пересоздадим активити, чтобы тема применилась
                recreate();
            }
        });
    }

    private int getAppTheme(int codeStyle) {
        return codeStyleToStyleId(getCodeStyle(codeStyle));
    }

    // Чтение настроек, параметр «тема»
    private int getCodeStyle(int codeStyle){
        // Работаем через специальный класс сохранения и чтения настроек
        SharedPreferences sharedPref = getSharedPreferences(nameSharedPreference, MODE_PRIVATE);
        //Прочитать тему, если настройка не найдена - взять по умолчанию
        return sharedPref.getInt(appTheme, codeStyle);
    }

    // Сохранение настроек
    private void setAppTheme(int codeStyle) {
        SharedPreferences sharedPref = getSharedPreferences(nameSharedPreference, MODE_PRIVATE);
        // Настройки сохраняются посредством специального класса editor.
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(appTheme, codeStyle);
        editor.apply();
    }

    private int codeStyleToStyleId(int codeStyle){
        switch(codeStyle){
            case codeStyleMetal:
                return R.style.style_metal;
            case codeStyleBlack:
                return R.style.style_black;
            case codeStyleYellow:
                return R.style.style_yellow;
            default:
                return R.style.ThemeСalculator;
        }
    }
}
