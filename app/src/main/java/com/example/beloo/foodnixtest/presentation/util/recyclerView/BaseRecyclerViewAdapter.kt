package com.example.beloo.foodnixtest.presentation.util.recyclerView

import android.widget.AdapterView
import java.util.*

/**
 * Base adapter for recycler view
 */
abstract class BaseRecyclerViewAdapter<TData> : androidx.recyclerview.widget.RecyclerView.Adapter<BindViewHolder<TData>>() {

    private val data: MutableList<TData> = mutableListOf()

    val snapshot: List<TData>
        get() = ArrayList(data)

    override fun onBindViewHolder(holder: BindViewHolder<TData>, position: Int) {
        holder.bindItem(getItem(position))
    }

    override fun onViewRecycled(holder: BindViewHolder<TData>) {
        super.onViewRecycled(holder)
        holder.unbind()
    }

    override fun getItemCount(): Int = data.size

    @Throws(ArrayIndexOutOfBoundsException::class)
    fun getItem(position: Int): TData = data[position]

    fun add(item: TData): Boolean {
        val add = data.add(item)
        notifyItemInserted(getItemPosition(item))
        return add
    }

    fun remove(item: TData): Boolean {
        val remove = data.remove(item)
        notifyItemRemoved(getItemPosition(item))
        return remove
    }

    fun remove(position: Int): TData {
        val remove = data.removeAt(position)
        notifyItemRemoved(position)
        return remove
    }

    fun removeAll() {
        val count = data.count()
        data.clear()
        notifyItemRangeRemoved(0, count)
    }

    fun clear() {
        data.clear()
    }

    fun addAll(collection: List<TData>) {
        data.addAll(collection)
        notifyDataSetChanged()
    }

    final override fun getItemId(position: Int): Long = getItemId(getItem(position))

    final override fun getItemViewType(position: Int): Int = getItemViewType(getItem(position))

    abstract fun getItemViewType(item: TData): Int

    private fun getItemId(item: TData): Long = if (item == null) {
       AdapterView.INVALID_ROW_ID
   } else {
       Math.abs(item.hashCode().toLong())
   }

    fun getItemPosition(item: TData): Int = data.indexOf(item)

    fun insert(item: TData, position: Int) {
        data.add(position, item)
        notifyItemInserted(position)
    }

    fun insertAll(item: Collection<TData>, position: Int) {
        data.addAll(position, item)
    }
}
