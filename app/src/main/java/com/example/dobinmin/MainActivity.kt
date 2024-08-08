package com.example.dobinmin


import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Locale


class MainActivity : AppCompatActivity() {

    private var selecteddate : TextView? = null
    private var tvAgeMinutes : TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)


        val btnDatePicker : Button =(findViewById(R.id.btnDatePicker))
        selecteddate = findViewById(R.id.selecteddate)
        tvAgeMinutes = findViewById(R.id.tvAgeMinute)
        btnDatePicker.setOnClickListener{
            clickDatePicker()
        }
    }
    fun clickDatePicker() {

        val mycalender =Calendar.getInstance()
        val year =mycalender.get(Calendar.YEAR)
        val month=mycalender.get(Calendar.MONTH)
        val day=mycalender.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(this,
            { view, selectedyear, selectedmonth, selecteddayOfMonth ->
                Toast.makeText(this,"$selectedyear/${selectedmonth+1}/$selecteddayOfMonth",Toast.LENGTH_LONG).show()
                val selectedate = "$selecteddayOfMonth/${selectedmonth+1}/$selectedyear"

                val sdf = SimpleDateFormat("dd/mm/yyyy",Locale.ENGLISH)
                val thedate = sdf.parse(selectedate)
                val selectedDateinMinutes = thedate.time/60000
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateinMinutes = currentDate.time/60000
                val differenceInMinutes = currentDateinMinutes - selectedDateinMinutes

                tvAgeMinutes?.text= differenceInMinutes.toString()

            },year,month,day).show()


    }
}
