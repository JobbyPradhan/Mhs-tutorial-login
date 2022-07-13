package com.mhs.mhs_tutorial_login.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import coil.load
import com.mhs.mhs_tutorial_login.R
import com.mhs.mhs_tutorial_login.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private var name :String =""
    private var desc:String = ""
    private var img :String =""
    private lateinit var binding:ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        img = intent.getStringExtra("img").toString()
        desc = intent.getStringExtra("desc").toString()
        name = intent.getStringExtra("name").toString()
        binding.tvDesc.text = desc
        binding.tvName.text = name
        binding.imgProfile.load(img)
    }
}