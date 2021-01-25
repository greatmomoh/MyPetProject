package com.example.mypetproject.ui.dashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mypetproject.R
import com.example.mypetproject.databinding.DashboardFragmentBinding
import com.example.mypetproject.model.SubjectModel
import com.example.mypetproject.ui.adapters.SubjectsAdapter
import com.example.mypetproject.utils.ItemClickListener
import com.example.mypetproject.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class DashboardFragment : Fragment(R.layout.dashboard_fragment), ItemClickListener<SubjectModel> {

    private val binding: DashboardFragmentBinding by viewBinding(DashboardFragmentBinding::bind)

    private val dashboardViewModel: DashboardViewModel by activityViewModels()

    private val subjectsAdapter : SubjectsAdapter by lazy {
        SubjectsAdapter(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        listenForStateChanges()

    }

    private fun listenForStateChanges() {

    }

    private fun setupViews() {
        binding.coursesRecycler.apply {
            adapter = subjectsAdapter
            layoutManager = GridLayoutManager(context, 2)
        }
    }

    override fun onItemClick(model: SubjectModel) {

    }


}