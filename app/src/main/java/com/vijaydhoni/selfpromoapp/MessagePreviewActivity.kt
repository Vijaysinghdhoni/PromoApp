package com.vijaydhoni.selfpromoapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MessagePreviewActivity : AppCompatActivity() {
    private lateinit var message: Message
    private lateinit var messagePreview: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message_preview)
        var messagetext: TextView = findViewById(R.id.text_view_message)
        messagedisplay(messagetext)
        onclickbutton()
    }

    private fun messagedisplay(messagetext: TextView) {
        message = intent.getSerializableExtra("Message") as Message
        messagePreview = """
                Hi ${message.contactname},
                
                my name is ${message.displayname} and i am ${message.jobdiscription()}.
                
                I have a portfolio of apps to demonstrate my technical skills that i can show on request.
                
                I am able to start a new position ${message.startpositon()}.
                
                Please get in touch if you have any suitable roles for me.
                
                Thanks and best regards.
            """.trimIndent()

        messagetext.text = messagePreview
    }

    private fun onclickbutton() {
        var button: Button = findViewById(R.id.button_mssg_preview)
        button.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("smsto:${message.contactnumber}")
                putExtra("sms_body", messagePreview)
            }
            startActivity(intent)
        }
    }

}