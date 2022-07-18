package com.mhs.mhs_tutorial_login.card.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.mhs.mhs_tutorial_login.ItemClickInterface
import com.mhs.mhs_tutorial_login.R
import com.mhs.mhs_tutorial_login.databinding.ItemListBinding
import com.mhs.mhs_tutorial_login.model.Student
import java.util.*
import kotlin.collections.ArrayList

class StudentAdapter(
    private val studentList : ArrayList<Student>,
    private val listener: ItemClickInterface
):RecyclerView.Adapter<StudentAdapter.StudentHolder>(),Filterable {

    private var studentSearchList = ArrayList<Student>(studentList)//8
    inner class StudentHolder(private val binding: ItemListBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(student: Student) {
            binding.tvName.text=student.name
            binding.tvDesc.text = student.desc

            binding.imgItem.load(student.profile) {
                crossfade(true)
                placeholder(R.drawable.reg)
                transformations(CircleCropTransformation())
            }
            binding.root.setOnClickListener {
                Log.i("ADAPTER", "bind: $adapterPosition")
                listener.onItemClick(adapterPosition)
               /* val intent = Intent(itemView.context,DetailActivity::class.java)
                intent.putExtra("name",student.name)
                intent.putExtra("desc",student.desc)
                intent.putExtra("img",student.profile)
                itemView.context.startActivity(intent)*/
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

    override fun getFilter(): Filter {
        return exampleFilter
    }
    private val exampleFilter: Filter = object : Filter() {
        //M
        override fun performFiltering(constraint: CharSequence): FilterResults {
            val filteredList: MutableList<Student> = ArrayList()
            if (constraint.isEmpty()) {
                filteredList.addAll(studentSearchList)
            } else {
                val filterPattern =
                    constraint.toString().lowercase(Locale.getDefault()).trim { it <= ' ' }
                //Mg
                for (item in studentSearchList) {
                    if (item.name.lowercase().contains(filterPattern) ||
                            item.id.toString().contains(filterPattern)) {
                        filteredList.add(item)
                        //2
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        @SuppressLint("NotifyDataSetChanged")
        override fun publishResults(constraint: CharSequence, results: FilterResults) {
            studentList.clear()
            studentList.addAll(results.values as Collection<Student>)//2
            notifyDataSetChanged()
        }
    }

}