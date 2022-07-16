package com.mhs.mhs_tutorial_login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.snackbar.Snackbar
import com.mhs.mhs_tutorial_login.card.ListActivity
import com.mhs.mhs_tutorial_login.databinding.ActivityMainWithMaterialBinding
import com.mhs.mhs_tutorial_login.register.RegisterActivity
import com.mhs.mhs_tutorial_login.util.AppSharedPreference

/*
*[SharedPreference]
*Android provides many ways of storing data of an application.
*  One of this way is called Shared Preferences.
* Shared Preferences allow you to save and retrieve data in the form of key,value pair.
* */
class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainWithMaterialBinding

    private lateinit var sharedPref : AppSharedPreference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainWithMaterialBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPref = AppSharedPreference(this)
        val isLogin = sharedPref.getValueBoolean("IsLogin",false)
        if (isLogin){
            val intent = Intent(this@LoginActivity,ListActivity::class.java)
            startActivity(intent)
            finish()
        }

       checkValidate()
        binding.btnLogin.setOnClickListener {
            val phoneNumber = binding.etPhNo.text.toString()
            val password = binding.etPassword.text.toString()
            if(phoneNumber.isNullOrEmpty()){
                binding.tilPhNo.error = "Enter Phone Number"
                binding.tilPhNo.isErrorEnabled=true
                return@setOnClickListener
            }
            if(password.isNullOrEmpty()){
                binding.tilPassword.error="Enter Password"
                binding.tilPassword.isErrorEnabled = true
                return@setOnClickListener
            }
            if(phoneNumber.length<8){
                binding.tilPhNo.error = "Enter Correct Phone Number"
                binding.tilPhNo.isErrorEnabled=true
                return@setOnClickListener
            }
            if(phoneNumber == "09795014119" && password == "abc123"){
                sharedPref.save("IsLogin",true)
                Toast.makeText(this@LoginActivity, "Welcome", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@LoginActivity,ListActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                Snackbar.make(binding.root,"Enter Correct Phone Number and Correct Password",Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

        }

        binding.tvSignUp.setOnClickListener {
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun checkValidate(){
        binding.etPhNo.doAfterTextChanged { s->
            if(!s.isNullOrEmpty()){
                if(s.length >8){
                    binding.tilPhNo.isErrorEnabled = false
                }
            }
        }
        binding.etPassword.doAfterTextChanged { s->
            if(!s.isNullOrEmpty()){
                binding.tilPassword.isErrorEnabled = false
            }
        }
    }
}