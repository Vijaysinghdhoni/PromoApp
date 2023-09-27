package com.vijaydhoni.selfpromoapp

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var contactname: TextInputEditText
    private lateinit var contactnumber: TextInputEditText
    private lateinit var displayname: TextInputEditText
    private lateinit var juniorcheckbox: CheckBox
    private lateinit var itemspinner: Spinner
    private lateinit var immediatestart: CheckBox
    private lateinit var date: EditText
    private lateinit var calenderimage:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findIdofAllViews()
        settingCalender()
        val previewbutton: Button = findViewById(R.id.button_preview_mssg)
        previewbutton.setOnClickListener {
            onPreview()
        }
        val spinnerarray: Array<String> = arrayOf("Andriod Developer", "Andriod Engineer")
        val spinnerAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, spinnerarray)
        itemspinner.adapter = spinnerAdapter
    }

    private fun settingCalender() {

        calenderimage.setOnClickListener {
            val cal = Calendar.getInstance()
            val year=cal.get(Calendar.YEAR)
            val month=cal.get(Calendar.MONTH)
            val day=cal.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                this,
                { _, year, monthOfYear, dayOfMonth ->
                    val dat = (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                    date.setText(dat)
                },
                year,
                month,
                day
            )
            datePickerDialog.show()
        }
    }

    private fun findIdofAllViews() {
        contactname = findViewById(R.id.edit_text_contact_name)
        contactnumber = findViewById(R.id.edit_text_contact_number)
        displayname = findViewById(R.id.edit_text_contact_display_name)
        juniorcheckbox = findViewById(R.id.check_box_junior)
        itemspinner = findViewById(R.id.spiner_list)
        immediatestart = findViewById(R.id.check_box_immediate_start)
        date = findViewById(R.id.edit_text_from_date)
        calenderimage= findViewById(R.id.calender_id)
    }

    private fun onPreview() {
        val meesage = Message(
            contactname.text.toString(),
            contactnumber.text.toString(),
            displayname.text.toString(),
            juniorcheckbox.isChecked,
            itemspinner.selectedItem.toString(),
            immediatestart.isChecked,
            date.text.toString()
        )


        when {
            meesage.contactname.isEmpty() -> AlertDialog.Builder(this).setTitle("WARNING!")
                .setMessage("Please Provide Contact Name").show()
            meesage.contactnumber.isEmpty() -> AlertDialog.Builder(this).setTitle("WARNING!")
                .setMessage("Please Provide Contact Number").show()
            meesage.displayname.isEmpty() -> AlertDialog.Builder(this).setTitle("WARNING!")
                .setMessage("Please Provide Display Name").show()
            meesage.itemspinner.isEmpty() -> AlertDialog.Builder(this).setTitle("WARNING!")
                .setMessage("Please Provide Your Position ").show()
            meesage.date.isEmpty() -> AlertDialog.Builder(this).setTitle("WARNING!")
                .setMessage("Please Provide Date ").show()

            else -> {
                val movingtoPreviewActivityIntent = Intent(this, MessagePreviewActivity::class.java)
                movingtoPreviewActivityIntent.putExtra("Message", meesage)
                startActivity(movingtoPreviewActivityIntent)
            }
        }

    }
}