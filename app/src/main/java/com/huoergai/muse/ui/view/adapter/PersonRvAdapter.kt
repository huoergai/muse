package com.huoergai.muse.ui.view.adapter

import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.view.ViewGroup
import com.huoergai.muse.R
import com.huoergai.muse.base.BaseRecyclerViewAdapter
import com.huoergai.muse.model.network.Person
import com.huoergai.muse.ui.view.viewholder.PersonViewHolder

/**
 * D&T: 2023-10-27 17:11
 * DES:
 */
class PersonRvAdapter : BaseRecyclerViewAdapter<PersonViewHolder>() {
    private val people = mutableListOf<Person>()
    private var itemClickListener: OnClickListener? = null

    fun setData(list: List<Person>) {
        people.clear()
        people.addAll(list)
        notifyDataSetChanged()
    }

    fun addData(list: List<Person>) {
        val start = people.size
        people.addAll(start, list)
        notifyItemRangeInserted(start, list.size)
    }

    fun getData(position: Int): Person {
        return people[position]
    }

    fun setItemClickListener(listener: OnClickListener) {
        this.itemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_person, parent, false)
        view.setOnClickListener(itemClickListener)
        return PersonViewHolder(view)
    }

    override fun getItemCount(): Int = people.size

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.bind(people[position])
    }
}