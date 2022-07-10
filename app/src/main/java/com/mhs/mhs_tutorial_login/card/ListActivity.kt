package com.mhs.mhs_tutorial_login.card

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mhs.mhs_tutorial_login.R
import com.mhs.mhs_tutorial_login.card.adapter.StudentAdapter
import com.mhs.mhs_tutorial_login.databinding.ActivityListBinding
import com.mhs.mhs_tutorial_login.util.TempData

class ListActivity : AppCompatActivity() {
    private lateinit var binding : ActivityListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val studentAdapter = StudentAdapter(TempData.studentList)
        binding.recList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@ListActivity)
            adapter = studentAdapter
        }
    }

}