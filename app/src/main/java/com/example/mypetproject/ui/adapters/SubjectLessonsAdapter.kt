package com.example.mypetproject.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.example.mypetproject.R
import com.example.mypetproject.databinding.SubjectLessonRecyclerItemBinding
import com.example.mypetproject.model.LessonModel
import com.example.mypetproject.utils.ItemClickListener
import com.example.mypetproject.utils.recursivelyApplyClickListener

class SubjectLessonsAdapter(private val itemClickListener: ItemClickListener<LessonModel>?) :
    ListAdapter<LessonModel, SubjectLessonsAdapter.SubjectLessonsViewHolder>(DiffCallback()) {


    class DiffCallback : DiffUtil.ItemCallback<LessonModel>() {
        override fun areItemsTheSame(oldItem: LessonModel, newItem: LessonModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: LessonModel, newItem: LessonModel): Boolean {
            return oldItem == newItem
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectLessonsViewHolder {
        val binding =
            SubjectLessonRecyclerItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

        return SubjectLessonsViewHolder(binding, itemClickListener)
    }

    override fun onBindViewHolder(holder: SubjectLessonsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class SubjectLessonsViewHolder(
        private val binding: SubjectLessonRecyclerItemBinding,
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