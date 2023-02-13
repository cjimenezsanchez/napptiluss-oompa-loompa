package com.jime.listdetail.presentation.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jime.listdetail.domain.model.OompaLoompa
import com.jime.listdetail.presentation.databinding.OompaLoompaItemBinding

class OompaLoompaListAdapter(
    private val items: MutableList<OompaLoompa> = mutableListOf(),
    private val onItemClicked: (String) -> Unit
) : RecyclerView.Adapter<OompaLoompaListAdapter.ViewHolder>() {

    fun addItems(newItems: List<OompaLoompa>) {
        items.addAll(newItems)
        notifyItemRangeInserted(items.size, newItems.size)    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val item =
            OompaLoompaItemBinding
                .inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
        return ViewHolder(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    inner class ViewHolder(binding: OompaLoompaItemBinding) : RecyclerView.ViewHolder(binding.root) {

        private val name: TextView = binding.name
        private val profession: TextView = binding.profession
        private val email: TextView = binding.email
        private val profilePic: ImageView = binding.profilePic

        fun bind(oompaLoompa: OompaLoompa) {
            val fullName = oompaLoompa.name + oompaLoompa.lastName
            name.text = fullName
            email.text = oompaLoompa.email
            profession.text = oompaLoompa.profession.name


            itemView.setOnClickListener {
                onItemClicked(oompaLoompa.id)
            }
        }
    }
}
