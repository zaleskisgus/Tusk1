package com.example.tusk1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.radiobutton.MaterialRadioButton;

public class ChooseTheme extends AppCompatActivity {

    // Имя настроек
    private static final String NameSharedPreference = "Calculator";
    // Имя параметра в настройках
    private static final String AppTheme = "APP_THEME";
    protected static final int AppThemeLightCodeStyle = 0;
    protected static final int AppThemeDarkCodeStyle = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_theme);
        initThemeChooser();
    }
    // Инициализация радиокнопок
    private void initThemeChooser() {
        initRadioButton(findViewById(R.id.radioButtonMaterialDark), AppThemeDarkCodeStyle);
        initRadioButton(findViewById(R.id.radioButtonMaterialLight), AppThemeLightCodeStyle);
        RadioGroup rg = findViewById(R.id.radioButtons);
        ((MaterialRadioButton)rg.getChildAt(getCodeStyle(AppThemeLightCodeStyle))).setChecked(true);
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
    protected int getCodeStyle(int codeStyle){
    // Работаем через специальный класс сохранения и чтения настроек
        SharedPreferences sharedPref = getSharedPreferences(NameSharedPreference,
                MODE_PRIVATE);
    //Прочитать тему, если настройка не найдена - взять по умолчанию
        return sharedPref.getInt(AppTheme, codeStyle);
    }
    // Сохранение настроек
    protected void setAppTheme(int codeStyle) {
        SharedPreferences sharedPref = getSharedPreferences(NameSharedPreference,
                MODE_PRIVATE);
    // Настройки сохраняются посредством специального класса editor.
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(AppTheme, codeStyle);
        editor.apply();
    }
    private int codeStyleToStyleId(int codeStyle){
        switch(codeStyle) {
            case AppThemeDarkCodeStyle:
                return R.style.DarkTheme;
            default:
                return R.style.LightTheme;
        }
    }
}