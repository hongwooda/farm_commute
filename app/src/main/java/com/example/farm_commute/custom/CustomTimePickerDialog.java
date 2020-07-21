package com.example.farm_commute.custom;

import android.content.Context;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.NumberPicker;
import android.widget.TimePicker;

public class CustomTimePickerDialog extends TimePickerDialog {

    private final int TIME_PICKER_INTERVAL = 10;
    private TimePicker timePicker;
    private final OnTimeSetListener callback;


    public CustomTimePickerDialog(Context context, OnTimeSetListener callBack, int hourOfDay, int minute, boolean is24HourView)
    {
        super(context, callBack, hourOfDay, minute, is24HourView);
        this.callback = callBack;
    }


    public CustomTimePickerDialog(Context context, int theme, OnTimeSetListener callBack, int hourOfDay, int minute, boolean is24HourView)
    {
        super(context, TimePickerDialog.THEME_HOLO_LIGHT, callBack, hourOfDay, minute, is24HourView);
        this.callback = callBack;
    }


    @Override
    public void onClick(DialogInterface dialog, int which)
    {
        super.onClick(dialog, which);
        if (callback != null && timePicker != null)
        {
            timePicker.clearFocus();
            callback.onTimeSet(timePicker, timePicker.getCurrentHour(), timePicker.getCurrentMinute() * TIME_PICKER_INTERVAL);
        }
    }


    @Override
    public void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        try
        {
            Class<?> classForid = Class.forName("com.android.internal.R$id");
            Field timePickerField = classForid.getField("timePicker");
            this.timePicker = (TimePicker) findViewById(timePickerField.getInt(null));
            Field mField = classForid.getField("minute");

            NumberPicker mMinuteSpinner = (NumberPicker) timePicker.findViewById(mField.getInt(null));
            mMinuteSpinner.setMinValue(0);
            mMinuteSpinner.setMaxValue((60 / TIME_PICKER_INTERVAL) - 1);
            List<String> displayedValues = new ArrayList<>();
            for (int i = 0; i < 60; i += TIME_PICKER_INTERVAL)
            {
                displayedValues.add(String.format("%02d", i));
            }
            mMinuteSpinner.setDisplayedValues(displayedValues.toArray(new String[0]));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


}
