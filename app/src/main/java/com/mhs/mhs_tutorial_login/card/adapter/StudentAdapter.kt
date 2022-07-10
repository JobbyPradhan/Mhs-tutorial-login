package com.mhs.mhs_tutorial_login.card.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.mhs.mhs_tutorial_login.R
import com.mhs.mhs_tutorial_login.databinding.ItemListBinding
import com.mhs.mhs_tutorial_login.model.Student

class StudentAdapter(
    private val studentList : ArrayList<Student>
):RecyclerView.Adapter<StudentAdapter.StudentHolder>() {

    inner class StudentHolder(private val binding: ItemListBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(student: Student) {
            binding.tvName.text=student.name
            binding.tvDesc.text = student.desc

            binding.imgItem.load(student.profile) {
                crossfade(true)
                placeholder(R.drawable.reg)
                transformations(CircleCropTransformation())
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentHolder {
        return StudentHolder(
            ItemListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: StudentHolder, position: Int) {
        holder.bind(studentList[position])
    }

    override fun getItemCount(): Int {
        return studentList.size
    }
}