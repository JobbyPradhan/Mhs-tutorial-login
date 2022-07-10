package com.mhs.mhs_tutorial_login.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.button.MaterialButton
import com.mhs.mhs_tutorial_login.R
import com.mhs.mhs_tutorial_login.card.ListActivity

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        findViewById<MaterialButton>(R.id.btn_register).setOnClickListener {
            val intent = Intent(this,ListActivity::class.java)
            startActivity(intent)
        }
    }
}