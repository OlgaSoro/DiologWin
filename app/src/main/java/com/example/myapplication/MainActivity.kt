package com.example.myapplication

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.CustomDialogBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showAlertDialog()
        showDateDialog()
        showTimeDialog()
        customDialog()



    }

    private fun customDialog(){
        binding.customDialogbtn.setOnClickListener{
            val dialog=Dialog(this)
            val bindingDialog=CustomDialogBinding.inflate(dialog.layoutInflater)
            dialog.setContentView(bindingDialog.root)
            bindingDialog.nameField

            bindingDialog.namebtn.setOnClickListener{
                bindingDialog.title.text=bindingDialog.nameField.text.toString()
            }
            dialog.show()
            }
        }


    private fun showTimeDialog(){
        val calendar=Calendar.getInstance()
        val hour=calendar.get(Calendar.HOUR_OF_DAY)
        val minute=calendar.get(Calendar.MINUTE)

        val timePickerDialog= TimePickerDialog(this, { _, selectedHouer, selectedMinute->
            val time="${addZeroToData(selectedHouer.toString())}:${addZeroToData(selectedMinute.toString())}"
            binding.timePickDialogbtn.text=time
        }, hour, minute, true )

        binding.timePickDialogbtn.setOnClickListener{
            timePickerDialog.show()
        }
    }

    private fun showDateDialog(){
        val calendar=Calendar.getInstance()
        val year=calendar.get(Calendar.YEAR)
        val month=calendar.get(Calendar.MONTH)
        val day=calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog=DatePickerDialog(this, {_, selectedYear, selectedMonth, selectedDay->
            val date="${addZeroToData(selectedDay.toString())}.${addZeroToData((selectedMonth+1).toString())}.$selectedYear"
            Toast.makeText(this, date, Toast.LENGTH_SHORT).show()
            binding.dataPickDialogbtn.text=date
        }, year, month,day )

        binding.dataPickDialogbtn.setOnClickListener{
            datePickerDialog.show()
        }
    }

    private fun showAlertDialog(){
        val builder=AlertDialog.Builder(this)
        builder.setTitle("Подтвердите действие")
        builder.setMessage("Вы уверены, что хотите покинуть приложение")
        builder.setPositiveButton("Да"){ dialog,witch->
            Toast.makeText(this, "Вы выбрали Да", Toast.LENGTH_SHORT).show()
        }

        builder.setNegativeButton("Нет"){dialog,witch->
            Toast.makeText(this, "Вы выбрали Нет", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        val dialog:AlertDialog=builder.create()
        binding.alertDialogbtn.setOnClickListener{
            dialog.show()
        }
    }

    private fun addZeroToData(data:String):String{
        if (data.length>1){
            return data
        }else{
            return "0$data"
        }
    }
}