package com.example.mypetproject.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.example.mypetproject.R
import com.example.mypetproject.databinding.RecentlyWatchedRecyclerItemBinding
import com.example.mypetproject.model.LessonModel
import com.example.mypetproject.utils.ItemClickListener
import com.example.mypetproject.utils.recursivelyApplyClickListener

class RecentlyWatchedAdapter (private val itemClickListener: ItemClickListener<LessonModel>?):
    ListAdapter<LessonModel, RecentlyWatchedAdapter.RecentViewHolder>(DiffCallback()){

    class DiffCallback : DiffUtil.ItemCallback<LessonModel>() {
        override fun areItemsTheSame(oldItem: LessonModel, newItem: LessonModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: LessonModel, newItem: LessonModel): Boolean {
            return oldItem == newItem
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentViewHolder {
        val binding =
            RecentlyWatchedRecyclerItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

        return RecentViewHolder(binding, itemClickListener)
    }

    override fun onBindViewHolder(holder: RecentViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class RecentViewHolder(
        private val binding: RecentlyWatchedRecyclerItemBinding,
        private val actionClickListener: ItemClickListener<LessonModel>?
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(lessonModel: LessonModel) {
            binding.lessonName.text = lessonModel.name
            binding.lessonIcon.load(lessonModel.icon) {
                scale(Scale.FIT)
                placeholder(R.drawable.mask_group)
                error(R.drawable.mask_group)

            }

            binding.root.recursivelyApplyClickListener {
                actionClickListener?.onItemClick(lessonModel)
            }
        }
    }
}