package com.mhs.mhs_tutorial_login.card

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.core.view.MenuItemCompat
import androidx.core.widget.doAfterTextChanged
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
        binding.sampleEditText.doAfterTextChanged { s->
            Toast.makeText(this, "$s", Toast.LENGTH_SHORT).show()
        }

        binding.recList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@ListActivity)
            adapter = studentAdapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.item_menu,menu)
        val searchItem: MenuItem? = menu?.findItem(R.id.app_bar_search)
        val searchView : SearchView =searchItem?.actionView as SearchView
        searchView.queryHint = "Search something"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
               return true
            }

        })
        return super.onCreateOptionsMenu(menu)

    }
}