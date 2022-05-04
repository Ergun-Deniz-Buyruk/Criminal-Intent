package com.example.criminalintent.views;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DatePickerFragment extends DialogFragment {

    public static final String ARG_DATE = "date";

    public DatePickerFragment(Date date) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_DATE, date);
        this.setArguments(bundle);
    }

    interface Callbacks {
        void onDateSelected(Date date);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        Date date = null;
        if(this.getArguments() != null) {
            date = (Date) this.getArguments().getSerializable(ARG_DATE);
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int initialYear = calendar.get(Calendar.YEAR);
        int initialMonth = calendar.get(Calendar.MONTH);
        int initialDay = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog.OnDateSetListener onDateSetListener =  (new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                Date resultDate = new GregorianCalendar(i, i1, i2).getTime();
                ( (Callbacks) getTargetFragment()).onDateSelected(resultDate);
            }
        });
        return new DatePickerDialog(requireContext(), onDateSetListener, initialYear, initialMonth, initialDay);
    }
}
