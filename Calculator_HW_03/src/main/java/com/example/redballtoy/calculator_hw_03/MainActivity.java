package com.example.redballtoy.calculator_hw_03;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etShowResult;
    private TextView tvShowUseMemory;

    private SwitchCompat swOnOff;
    private SwitchCompat swChangePrecision;

    private Button btMemoryAdd;
    private Button btMemorySub;
    private Button btMemoryClear;
    private Button btClear;
    private Button btN_00;
    private Button btN_0;
    private Button btN_01;
    private Button btN_02;
    private Button btN_03;
    private Button btN_04;
    private Button btN_05;
    private Button btN_06;
    private Button btN_07;
    private Button btN_08;
    private Button btN_09;
    private Button btLeft_bracket;
    private Button btRight_bracket;
    private Button btPercent;
    private Button btEquals;
    private Button btExp;
    private Button btSqr;
    private Button btDiv;
    private Button btMult;
    private Button bt_Sub;
    private Button bt_Add;
    private Button btBackSpace;

    private String result;
    private boolean hasDoublePrecision = false;
    private String keyName;
    private String memoryLabel;

    CalcCore calcCore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUIelements();
        setOnclickListenerToUIelements();


    }


    private void initUIelements() {
        swOnOff = findViewById(R.id.sw_off_on);
        swChangePrecision = findViewById(R.id.sw_precision);

        etShowResult = findViewById(R.id.et_show_result);
        tvShowUseMemory = findViewById(R.id.tv_show_memory_on);
        memoryLabel = "";
        tvShowUseMemory.setText(memoryLabel);


        btMemoryAdd = findViewById(R.id.bt_m_add);
        btMemorySub = findViewById(R.id.bt_m_sub);
        btMemoryClear = findViewById(R.id.bt_m_clear);
        btClear = findViewById(R.id.bt_clesr);
        btN_00 = findViewById(R.id.bt_00);
        btN_0 = findViewById(R.id.bt_0);
        btN_01 = findViewById(R.id.bt_01);
        btN_02 = findViewById(R.id.bt_02);
        btN_03 = findViewById(R.id.bt_03);
        btN_04 = findViewById(R.id.bt_04);
        btN_05 = findViewById(R.id.bt_05);
        btN_06 = findViewById(R.id.bt_06);
        btN_07 = findViewById(R.id.bt_07);
        btN_08 = findViewById(R.id.bt_08);
        btN_09 = findViewById(R.id.bt_09);
        btLeft_bracket = findViewById(R.id.bt_left_bracket);
        btRight_bracket = findViewById(R.id.bt_right_bracket);
        btPercent = findViewById(R.id.bt_percent);
        btEquals = findViewById(R.id.bt_equals);
        btExp = findViewById(R.id.bt_exp);
        btSqr = findViewById(R.id.bt_sqr);
        btDiv = findViewById(R.id.bt_div);
        btMult = findViewById(R.id.bt_mult);
        bt_Sub = findViewById(R.id.bt_sub);
        bt_Add = findViewById(R.id.bt_add);
        btBackSpace = findViewById(R.id.bt_back_space);

        //отключаем встроенную клавиатуру
        etShowResult.setShowSoftInputOnFocus(false);

        calcCore = new CalcCore();

    }

    private void setOnclickListenerToUIelements() {
        swOnOff.setOnClickListener(this);
        swChangePrecision.setOnClickListener(this);

        btMemoryAdd.setOnClickListener(this);
        btMemorySub.setOnClickListener(this);
        btMemoryClear.setOnClickListener(this);
        btClear.setOnClickListener(this);
        btN_00.setOnClickListener(this);
        btN_0.setOnClickListener(this);
        btN_01.setOnClickListener(this);
        btN_02.setOnClickListener(this);
        btN_03.setOnClickListener(this);
        btN_04.setOnClickListener(this);
        btN_05.setOnClickListener(this);
        btN_06.setOnClickListener(this);
        btN_07.setOnClickListener(this);
        btN_08.setOnClickListener(this);
        btN_09.setOnClickListener(this);
        btLeft_bracket.setOnClickListener(this);
        btRight_bracket.setOnClickListener(this);
        btPercent.setOnClickListener(this);
        btEquals.setOnClickListener(this);
        btExp.setOnClickListener(this);
        btSqr.setOnClickListener(this);
        btDiv.setOnClickListener(this);
        btMult.setOnClickListener(this);
        bt_Sub.setOnClickListener(this);
        bt_Add.setOnClickListener(this);
        btBackSpace.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int keyId = v.getId();
        switch (keyId) {
            case R.id.sw_off_on:
                checkOnOff();
                break;
            case R.id.sw_precision:
                checkPrecision();
            default:
                getInfoFromLcd();
                setInfoForCalculate(v);
                showResult();
        }
    }

    private void deleteLastSymbol() {
        String textOnLcd = etShowResult.getText().toString();
        int lenghttextOnLcd = textOnLcd.length();
        if (lenghttextOnLcd > 0) {
            result = (textOnLcd.substring(0, lenghttextOnLcd - 1));
        }

    }


    private void setInfoForCalculate(View v) {
        Button bt = (Button) v;
        calcCore.setResult(result);
        calcCore.setKeyName(bt.getText().toString());
        calcCore.setHasDoublePrecission(hasDoublePrecision);
        calcCore.setMemoryLabel(tvShowUseMemory.getText().toString());
    }

    private void showResult() {
        etShowResult.setText(calcCore.getResult());
        tvShowUseMemory.setText(calcCore.getMemoryLabel());
        changeTextSize();

    }

    private void changeTextSize() {
        float textSize = getResources().getDimension(R.dimen.lsd_text_sixe) /
                getResources().getDisplayMetrics().scaledDensity;
        int rowCols = etShowResult.getLineCount();
        etShowResult.setTextSize(textSize / rowCols);
    }


    private void checkPrecision() {
        if (swChangePrecision.isChecked()) {
            swChangePrecision.setText(R.string._0_00);
            swChangePrecision.setTextColor(getResources().getColor(R.color.teal_200));
            hasDoublePrecision = true;
            showToast(R.string.round_00);
        } else {
            swChangePrecision.setText(R.string._0);
            swChangePrecision.setTextColor(getResources().getColor(R.color.teal_700));
            hasDoublePrecision = false;
            showToast(R.string.round_0);
        }

    }

    private void checkOnOff() {
        if (!swOnOff.isChecked()) {
            swOnOff.setTextColor(getResources().getColor(R.color.grey_text));
            //TODO потом сделать snack bar
            showToast(R.string.message_exit);
            System.exit(1);
        }
    }

    private void getInfoFromLcd() {
        String nullSymbol = getString(R.string._0_comma);
        result = etShowResult.getText().toString();
        if (result.equals(nullSymbol) || result.equals("0")) {
            result = "";
        }
        memoryLabel = tvShowUseMemory.getText().toString();
    }

    private void showToast(int messageId) {
        Toast.makeText(this, getString(messageId)
                , Toast.LENGTH_SHORT).show();
    }

    private void clearLCD() {
        etShowResult.getText().clear();
    }
}