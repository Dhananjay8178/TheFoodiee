@file:Suppress("Annotator")

package com.example.thefoodie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.View
import android.widget.Button
import android.widget.EditText
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //finding the button
        val showButton = findViewById<Button>(R.id.button2)

        //finding the edit text
        val editText = findViewById<EditText>(R.id.RecipeInput)

        //setting on click listener
        showButton.setOnClickListener{

            //Getting the user input
            val Text = editText.text

            val intent = Intent(this,TheFoodiee::class.java)

            intent.putExtra("recipe",Text)
            startActivity(intent)


        }


    }


}