package com.example.mypetproject.ui.subject

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mypetproject.R
import com.example.mypetproject.databinding.SubjectDetailsFragmentBinding
import com.example.mypetproject.model.LessonModel
import com.example.mypetproject.ui.adapters.SubjectDetailAdapter
import com.example.mypetproject.ui.dashboard.DashboardFragmentDirections
import com.example.mypetproject.utils.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class SubjectDetailsFragment : Fragment(R.layout.subject_details_fragment),
    ItemClickListener<LessonModel> {

    private val binding: SubjectDetailsFragmentBinding by viewBinding(SubjectDetailsFragmentBinding::bind)

    private val viewModel: SubjectDetailsViewModel by activityViewModels()

    private val detailsArgument: SubjectDetailsFragmentArgs by navArgs()

    private val subjectDetailAdapter: SubjectDetailAdapter by lazy {
        SubjectDetailAdapter(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()

    }

    private fun setupViews() {
        setUpToolbar(binding.toolbar) {
            setDisplayHomeAsUpEnabled(true)
        }

        binding.toolbar.title = detailsArgument.subject.name

        binding.topicsRecycler.apply {
            adapter = subjectDetailAdapter
            layoutManager = LinearLayoutManager(context)
        }

        if (detailsArgument.subject.chapters.isNotEmpty()) {
            subjectDetailAdapter.submitList(detailsArgument.subject.chapters)
        }
    }

    override fun onItemClick(model: LessonModel) {

        navController.navigate(
            SubjectDetailsFragmentDirections.actionSubjectDetailsFragmentToLessonVideoFragment(
                model, detailsArgument.subject.name
            )
        )
    }
}