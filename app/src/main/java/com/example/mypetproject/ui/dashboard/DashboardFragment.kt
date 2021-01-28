package com.example.mypetproject.ui.dashboard

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mypetproject.R
import com.example.mypetproject.databinding.DashboardFragmentBinding
import com.example.mypetproject.model.SubjectModel
import com.example.mypetproject.ui.adapters.SubjectsAdapter
import com.example.mypetproject.utils.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class DashboardFragment : Fragment(R.layout.dashboard_fragment), ItemClickListener<SubjectModel> {

    private val binding: DashboardFragmentBinding by viewBinding(DashboardFragmentBinding::bind)

    private val dashboardViewModel: DashboardViewModel by activityViewModels()

    private val subjectsAdapter: SubjectsAdapter by lazy {
        SubjectsAdapter(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        listenForStateChanges()

    }

    private fun listenForStateChanges() {
        dashboardViewModel.viewState.onEach {
            handleViewStateChanges(it)
        }.launchIn(viewLifecycleScope)
    }

    private fun handleViewStateChanges(viewState: DashboardViewModel.Companion.ViewState) {
        when (viewState.loadState) {
            LoadingState.Idle -> {

            }
            LoadingState.Working -> {
                binding.loadingProgress.isVisible = true
            }
            LoadingState.Error -> {
                binding.loadingProgress.isVisible = false
                viewState.error?.message?.let {
                    showSnackBar(it)
                }
//                if (viewState.subjects.isNotEmpty()) {
//                    subjectsAdapter.submitList(viewState.subjects)
//                }

            }
            LoadingState.Success -> {

                binding.loadingProgress.isVisible = false
                if (viewState.subjects.isNotEmpty()) {
                    subjectsAdapter.submitList(viewState.subjects)
                }

                if (viewState.lessons.isNotEmpty()){
                    showSnackBar(viewState.lessons.toString())
                }

            }
        }
    }

    private fun setupViews() {
        binding.studentName.setOnClickListener {
            dashboardViewModel.fetchCourses()
        }

        binding.coursesRecycler.apply {
            adapter = subjectsAdapter
            layoutManager = GridLayoutManager(context, 2)
        }
    }

    override fun onItemClick(model: SubjectModel) {
        navController.navigate(
            DashboardFragmentDirections.actionDashboardFragmentToSubjectDetailsFragment(
                model
            )
        )
    }


}