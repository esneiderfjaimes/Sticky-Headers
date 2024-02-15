package co.nei.stickyheader

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.nei.stickyheader.databinding.ItemHeaderBinding
import co.nei.stickyheader.databinding.ItemListBinding

class ListRecyclerAdapter(
    private val itemsList: List<ItemModel>,
    private val onHeaderItemClick: (Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    StickyHeaderItemDecorator.StickyHeaderInterface {

    companion object {
        const val TYPE_HEADER = 1
        const val TYPE_CONTENT = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            TYPE_HEADER -> HeaderViewHolder(
                ItemHeaderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            else -> ItemBodyViewHolder(
                ItemListBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> holder.bind(position)
            is ItemBodyViewHolder -> holder.bind(position)
        }
    }

    override fun getItemCount(): Int = itemsList.size

    override fun getItemViewType(position: Int) =
        if (itemsList[position].isHeader) TYPE_HEADER else TYPE_CONTENT

    inner class ItemBodyViewHolder(private val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val text = "Content body at position $position"
            binding.content.text = text
            binding.root.setOnClickListener { onHeaderItemClick(position) }
        }
    }

    inner class HeaderViewHolder(private val binding: ItemHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val text = "Header $position"
            binding.content.text = text
        }
    }

    override fun isHeader(itemPosition: Int) = itemsList[itemPosition].isHeader

}