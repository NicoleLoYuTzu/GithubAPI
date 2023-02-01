package com.example.dcardhomework.vvm.homesearch

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dcardhomework.R
import com.example.dcardhomework.data.Repo
import com.example.dcardhomework.databinding.ItemsBindingBinding

class HomeSearchAdapter() : ListAdapter<Repo, HomeSearchAdapter.ItemHolder>(DiffCallback) {

    class ItemHolder(var binding: ItemsBindingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("UseCompatLoadingForDrawables")
        var navController: NavController? = null

        fun bind(repo: Repo) {
            binding.textView.text = repo.name
            binding.cons.setOnClickListener {
                navController = Navigation.findNavController(itemView)
                navController!!.navigate(R.id.detailFragment)
            }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Repo?>() {
        override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean {
            return oldItem == newItem
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            ItemsBindingBinding.inflate(
                LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(getItem(position))
    }
}