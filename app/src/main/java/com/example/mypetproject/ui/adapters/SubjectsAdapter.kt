package com.example.mypetproject.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.example.mypetproject.R
import com.example.mypetproject.databinding.SubjectRecyclerItemBinding
import com.example.mypetproject.model.SubjectModel
import com.example.mypetproject.utils.ItemClickListener
import com.example.mypetproject.utils.recursivelyApplyClickListener

class SubjectsAdapter(private val itemClickListener: ItemClickListener<SubjectModel>?) :
    ListAdapter<SubjectModel, SubjectsAdapter.SubjectsItemViewHolder>(DiffCallback()) {

    class DiffCallback : DiffUtil.ItemCallback<SubjectModel>() {
        override fun areItemsTheSame(oldItem: SubjectModel, newItem: SubjectModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: SubjectModel, newItem: SubjectModel): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectsItemViewHolder {
        val binding =
            SubjectRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return SubjectsItemViewHolder(binding, itemClickListener)
    }

    override fun onBindViewHolder(holder: SubjectsItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class SubjectsItemViewHolder(
        private val binding: SubjectRecyclerItemBinding,
        private val actionClickListener: ItemClickListener<SubjectModel>?
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(subjectModel: SubjectModel) {
            binding.subjectName.text = subjectModel.name
            binding.subjectImage.load(subjectModel.icon) {
                scale(Scale.FIT)
                placeholder(R.drawable.mask_group)
                error(R.drawable.mask_group)
            }

            binding.root.recursivelyApplyClickListener {
                actionClickListener?.onItemClick(subjectModel)
            }
        }
    }

}
