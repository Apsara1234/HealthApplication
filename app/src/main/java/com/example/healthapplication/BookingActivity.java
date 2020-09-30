package com.example.healthapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.healthapplication.API.HealthBookingAPI;
import com.example.healthapplication.Model.HealthBooking;
import com.example.healthapplication.URL.url;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingActivity extends AppCompatActivity {
private int counter = 1;
private EditText cfullname, ccontact, cvehiclemodel;
    private Spinner spin;
    private Button btncdate, btntime, btncbook;
    private Calendar date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        cfullname = findViewById(R.id.cfullname);
        ccontact = findViewById(R.id.ccontact);
        spin = findViewById(R.id.spin);
        cvehiclemodel = findViewById(R.id.cvehiclemodel);
        btncdate = findViewById(R.id.btncdate);
        btntime = findViewById(R.id.btntime);
        btncbook = findViewById(R.id.btncbook);

        String gender [] = {"Male","Female","Other"};
        ArrayAdapter arrayAdapter1= new ArrayAdapter(this, android.R.layout.select_dialog_item,gender);
        spin.setAdapter(arrayAdapter1);

        btncbook.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(TextUtils.isEmpty(cfullname.getText())){
                cfullname.setError("Please enter your fullname");
                return;
            }

            else if(TextUtils.isEmpty(ccontact.getText())){
                ccontact.setError("Please enter your contact number");
                return;
            }
            else if(TextUtils.isEmpty(cvehiclemodel.getText())){
                ccontact.setError("Please enter your gender");
                return;
            }
            else if(TextUtils.isEmpty(btncdate.getText())){
                btncdate.setError("Please enter date");
                return;
            }

            else if(TextUtils.isEmpty(btntime.getText())){
                btntime.setError("Please enter time");
                return;
            }

            else {
                registerbooking();
            }
        }
    });
        btncdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDatePicker();
            }
        });
        btntime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadTime();
            }
        });
    }

    private void loadTime() {
        Calendar calendar = Calendar.getInstance();
        int HOUR = calendar.get(Calendar.HOUR);
        int Minute = calendar.get(Calendar.MINUTE);

        boolean is24HourFormat = DateFormat.is24HourFormat(this);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String timeString = "hour:" + hourOfDay + "minute :" + minute;
                btntime.setText(timeString);
            }
        }, HOUR,Minute,is24HourFormat);
        timePickerDialog.show();

    }

    private void loadDatePicker() {
        final Calendar currentDate = Calendar.getInstance();
        date = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                date.set(year, monthOfYear, dayOfMonth);
                String date = "Select Date:" + (monthOfYear+1) + "/" + dayOfMonth + "/" + year;
                btncdate.setText(date);
            }
        };
        DatePickerDialog datePickerDialog = new  DatePickerDialog(BookingActivity.this, dateSetListener, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH),   currentDate.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();

    }

    private void registerbooking() {
    String name = cfullname.getText().toString();
    String contact = ccontact.getText().toString();
    String gender = spin.getSelectedItem().toString();
    String age = cvehiclemodel.getText().toString();
    String date = btncdate.getText().toString();
    String time = btntime.getText().toString();

        HealthBooking healthBooking = new HealthBooking(name,contact,gender,age,date,time);
        HealthBookingAPI healthBookingAPI = url.getInstance().create(HealthBookingAPI.class);
        Call<HealthBooking> healthBookingCall = healthBookingAPI.registerbooking(healthBooking);
        healthBookingCall.enqueue(new Callback<HealthBooking>() {
            @Override
            public void onResponse(Call<HealthBooking> call, Response<HealthBooking> response) {
                if (! response.isSuccessful()) {
                    Toast.makeText(BookingActivity.this, "Error : API is not responding " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(BookingActivity.this, "Booked", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<HealthBooking> call, Throwable t) {
                Toast.makeText(BookingActivity.this, "Error : Network Problem  and Error : " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }
}