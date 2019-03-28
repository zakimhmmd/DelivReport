package com.example.zaki.delivreport;

import com.github.mikephil.charting.utils.ValueFormatter;

import java.text.DecimalFormat;

public class ChartValueFormatter implements ValueFormatter {

    private DecimalFormat mformat;

    public ChartValueFormatter(){
        mformat = new DecimalFormat("###,###,##0");
    }

    @Override
    public String getFormattedValue(float value) {
        if (value > 0){
            return mformat.format(value);
        } else {
            return "";
        }
    }
}
