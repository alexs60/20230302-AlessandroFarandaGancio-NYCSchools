package com.alessandrofarandagancio.nycschools.school.view
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alessandrofarandagancio.nycschools.R
import com.alessandrofarandagancio.nycschools.school.model.SchoolSimple
import com.alessandrofarandagancio.nycschools.utils.Utils

class SchoolItemAdapter(private val onClick: (SchoolSimple) -> Unit) :
    ListAdapter<SchoolSimple, SchoolItemAdapter.SchoolViewHolder>(SchoolDiffCallback) {

    class SchoolViewHolder(view: View, val onClick: (SchoolSimple) -> Unit) :
        RecyclerView.ViewHolder(view) {
        private val textViewName: TextView = view.findViewById(R.id.schoolItemName)
        private val textViewAddress: TextView = view.findViewById(R.id.schoolItemAddress)
        private var currentSchool: SchoolSimple? = null

        init {
            view.setOnClickListener {
                currentSchool?.let {
                    onClick(it)
                }
            }
        }

        fun bind(school: SchoolSimple) {
            currentSchool = school
            textViewName.text = school.school_name
            textViewAddress.text = Utils.removeBrachetsFromLocationString(school.location)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): SchoolViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.school_item, viewGroup, false)

        return SchoolViewHolder(view, onClick)
    }

    override fun onBindViewHolder(viewHolder: SchoolViewHolder, position: Int) {
        val school = getItem(position)
        viewHolder.bind(school)
    }
}

object SchoolDiffCallback : DiffUtil.ItemCallback<SchoolSimple>() {
    override fun areItemsTheSame(oldItem: SchoolSimple, newItem: SchoolSimple): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: SchoolSimple, newItem: SchoolSimple): Boolean {
        return oldItem.dbn == newItem.dbn
    }
}
