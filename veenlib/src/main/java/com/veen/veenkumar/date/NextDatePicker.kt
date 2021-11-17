package com.veen.veenkumar.date

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import java.util.*

class NextDatePicker : DialogFragment(), DatePickerDialog.OnDateSetListener {
    private var date: StringBuilder? = null
    private var day: Int? = null
    private var setOnDateOfBirth: SetOnDateOfBirth? = null
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        day = c.get(Calendar.DAY_OF_MONTH)
        val hideUnusedDate = DatePickerDialog(requireContext(), this, year, month, day!! - 0)
        hideUnusedDate.datePicker.minDate = c.timeInMillis
        return hideUnusedDate
    }

    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, day: Int) {
        val userAge: Calendar = GregorianCalendar(year, month, day)
        val minAdultAge: Calendar = GregorianCalendar()
        minAdultAge.add(Calendar.DAY_OF_MONTH, -1)
        if (minAdultAge.after(userAge)) {
            Toast.makeText(
                requireContext(),
                "Please Select Correct Date.",
                Toast.LENGTH_LONG
            )
                .show()
        } else {
            date = StringBuilder().append(year)
                .append("-").append(month + 1).append("-").append(day)
                .append(" ")
            Log.d("DatePickerFragment", "" + date)
            setOnDateOfBirth?.setDateOfBirth(date!!)
        }
    }

    fun setOnDateOfBirth(setOnDateOfBirth: SetOnDateOfBirth) {
        this.setOnDateOfBirth = setOnDateOfBirth
    }

    interface SetOnDateOfBirth {
        fun setDateOfBirth(date: StringBuilder?)
    }
}