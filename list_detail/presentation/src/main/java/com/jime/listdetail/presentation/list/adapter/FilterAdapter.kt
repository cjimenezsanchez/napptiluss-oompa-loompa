package com.jime.listdetail.presentation.list.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jime.listdetail.domain.model.Profession
import com.jime.listdetail.presentation.R
import com.jime.listdetail.presentation.databinding.FilterItemBinding
import com.jime.listdetail.presentation.list.filter.ProfessionFilter
import com.jime.listdetail.presentation.util.getStringId
import com.jime.listdetail.presentation.util.sameAs

class FilterAdapter(
    private val onFilterItemClicked: (ProfessionFilter) -> Unit
) : RecyclerView.Adapter<FilterAdapter.ViewHolder>() {

    private var selectedFilter: ProfessionFilter = ProfessionFilter.None

    private val items: List<ProfessionFilter> = listOf(
        ProfessionFilter.None,
        ProfessionFilter.ByType(Profession.Developer),
        ProfessionFilter.ByType(Profession.Brewer),
        ProfessionFilter.ByType(Profession.Medic),
        ProfessionFilter.ByType(Profession.Metalworker),
        ProfessionFilter.ByType(Profession.Gemcutter)
    )

    fun updateSelectedFilter(newFilter: ProfessionFilter) {
        if (selectedFilter sameAs newFilter) {
            return
        }
        selectedFilter = newFilter
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = FilterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(parent.context, view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ViewHolder(private val context: Context, binding: FilterItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val name: TextView = binding.filterName
        private val marker: View = binding.marker
        private val container: View = binding.container

        fun bind(filterItem: ProfessionFilter) {
            val nameId = when (filterItem) {
                is ProfessionFilter.None -> {
                    R.string.all
                }
                is ProfessionFilter.ByType -> {
                    filterItem.type.getStringId()
                }
            }
            name.text = context.getString(nameId)
            container.setOnClickListener {
                onFilterItemClicked(filterItem)
            }

            if (filterItem sameAs selectedFilter) {
                marker.visibility = View.VISIBLE
            } else {
                marker.visibility = View.GONE
            }
        }
    }
}