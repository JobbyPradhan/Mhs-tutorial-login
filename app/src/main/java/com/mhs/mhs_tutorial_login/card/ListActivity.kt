package com.mhs.mhs_tutorial_login.card

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.core.view.MenuItemCompat
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mhs.mhs_tutorial_login.ItemClickInterface
import com.mhs.mhs_tutorial_login.R
import com.mhs.mhs_tutorial_login.card.adapter.StudentAdapter
import com.mhs.mhs_tutorial_login.databinding.ActivityListBinding
import com.mhs.mhs_tutorial_login.detail.DetailActivity
import com.mhs.mhs_tutorial_login.model.Student
import com.mhs.mhs_tutorial_login.util.TempData

class ListActivity : AppCompatActivity(),ItemClickInterface {
    private lateinit var binding : ActivityListBinding

    private lateinit var studentAdapter: StudentAdapter

    private var studentData : Student =Student()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id : Int = studentData.id ?: 0
        studentAdapter = StudentAdapter(TempData.studentList,this)
        binding.sampleEditText.doAfterTextChanged { 
            studentAdapter.filter.filter(it.toString()) //M
        }

        binding.recList.also {
            it.setHasFixedSize(true)
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = studentAdapter
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

    override fun onItemClick(position: Int) {
        val student = TempData.studentList[position]
        Log.i("GETDATA", "bind: $student")
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("name",student.name)
        intent.putExtra("desc",student.desc)
        intent.putExtra("img",student.profile)
        startActivity(intent)
    }
}