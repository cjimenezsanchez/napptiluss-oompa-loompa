package com.jime.listdetail.presentation.list.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.jime.listdetail.domain.model.Gender
import com.jime.listdetail.domain.model.OompaLoompa
import com.jime.listdetail.domain.model.Profession
import com.jime.listdetail.presentation.databinding.OompaLoompaItemBinding
import com.jime.listdetail.presentation.databinding.ProgressItemBinding
import com.jime.listdetail.presentation.util.getDrawableId

class OompaLoompaListAdapter(
    private val items: MutableList<OompaLoompa> = mutableListOf(),
    private val onItemClicked: (Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    init {
        addItems(listOf(emptyItem()))
    }

    companion object {
        private const val VIEW_TYPE_ITEM = 1
        private const val VIEW_TYPE_LOADING = 0
    }

    private var isLoading = true

    fun addItems(newItems: List<OompaLoompa>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    fun onLoadingItems() {
        isLoading = true
    }

    fun onFinishedLoadingItems() {
        isLoading = false
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_ITEM) {
            val binding =
                OompaLoompaItemBinding
                    .inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
            return ItemViewHolder(parent.context, binding)
        } else {
            val binding =
                ProgressItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            LoadingViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemViewHolder) {
            holder.bind(items[position])
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == items.size - 1 && isLoading)
            VIEW_TYPE_LOADING
        else
            VIEW_TYPE_ITEM
    }

    private fun emptyItem(): OompaLoompa {
        return OompaLoompa(-1, "", "", "", Profession.fromString(""), Gender.fromString(""), "")
    }

    inner class ItemViewHolder(private val context: Context, binding: OompaLoompaItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val name: TextView = binding.name
        private val profession: TextView = binding.profession
        private val professionIcon: ImageView = binding.professionIcon
        private val email: TextView = binding.email
        private val profilePic: ImageView = binding.profilePic

        fun bind(oompaLoompa: OompaLoompa) {
            val fullName = "${oompaLoompa.name} ${oompaLoompa.lastName}"
            name.text = fullName
            email.text = oompaLoompa.email
            val icon =
                AppCompatResources.getDrawable(context, oompaLoompa.profession.getDrawableId())
            professionIcon.setImageDrawable(icon)
            profession.text = oompaLoompa.profession.name
            profilePic.load(oompaLoompa.image)

            itemView.setOnClickListener {
                onItemClicked(oompaLoompa.id)
            }
        }
    }

    inner class LoadingViewHolder(binding: ProgressItemBinding) :
        RecyclerView.ViewHolder(binding.root)

}


