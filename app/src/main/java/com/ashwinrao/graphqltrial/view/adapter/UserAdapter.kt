package com.ashwinrao.graphqltrial.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.apollographql.apollo.graphqltrial.fragment.UserFragment
import com.ashwinrao.graphqltrial.R
import com.ashwinrao.graphqltrial.databinding.ViewholderUserBinding
import com.ashwinrao.graphqltrial.databinding.ViewholderUserBindingImpl

class UserAdapter(private val context: Context) :
    ListAdapter<UserFragment, UserAdapter.UserViewHolder>(UserDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
        UserViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.viewholder_user,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.binding.user = getItem(position)
    }

    inner class UserViewHolder(val binding: ViewholderUserBinding) :
        RecyclerView.ViewHolder(binding.root)

}

class UserDiffUtil : DiffUtil.ItemCallback<UserFragment>() {

    override fun areItemsTheSame(oldItem: UserFragment, newItem: UserFragment): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: UserFragment, newItem: UserFragment): Boolean =
        oldItem.name() == newItem.name() && oldItem.bio() == newItem.bio()

}
