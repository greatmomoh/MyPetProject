package com.example.mypetproject.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mypetproject.databinding.SubjectDetailRecyclerItemBinding
import com.example.mypetproject.model.ChapterModel
import com.example.mypetproject.model.LessonModel
import com.example.mypetproject.utils.ItemClickListener
import com.example.mypetproject.utils.SpacingItemDecoration
import com.example.mypetproject.utils.dpToPx
import com.example.mypetproject.utils.removeAllDecorations

class SubjectDetailAdapter(private val itemClickListener: ItemClickListener<LessonModel>?) :
    ListAdapter<ChapterModel, SubjectDetailAdapter.SubjectDetailViewHolder>(DiffCallback()) {

    private val viewPool = RecyclerView.RecycledViewPool()


    class DiffCallback : DiffUtil.ItemCallback<ChapterModel>() {
        override fun areItemsTheSame(oldItem: ChapterModel, newItem: ChapterModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ChapterModel, newItem: ChapterModel): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectDetailViewHolder {
        val binding =
            SubjectDetailRecyclerItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return SubjectDetailViewHolder(binding, itemClickListener)

    }

    override fun onBindViewHolder(holder: SubjectDetailViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    inner class SubjectDetailViewHolder(
        private val binding: SubjectDetailRecyclerItemBinding,
        private val actionClickListener: ItemClickListener<LessonModel>?
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(chapterModel: ChapterModel) {
            binding.chapterName.text = chapterModel.name
            val childLayoutManager =
                LinearLayoutManager(binding.lessonsRecycler.context, RecyclerView.HORIZONTAL, false)
            childLayoutManager.initialPrefetchItemCount = 4
            val childAdapter = SubjectLessonsAdapter(actionClickListener)
            binding.lessonsRecycler.apply {
                removeAllDecorations()
                layoutManager = childLayoutManager
                adapter = childAdapter
                isNestedScrollingEnabled = false
                setRecycledViewPool(viewPool)
                addItemDecoration(
                    SpacingItemDecoration(
                        context.dpToPx(16), context.dpToPx(16),
                        false, isVertical = true
                    )
                )
            }
            childAdapter.submitList(chapterModel.lessons)
        }
    }
}