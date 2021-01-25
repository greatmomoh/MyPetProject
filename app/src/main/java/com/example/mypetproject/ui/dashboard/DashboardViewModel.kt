package com.example.mypetproject.ui.dashboard

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.mypetproject.model.SubjectModel
import com.example.mypetproject.utils.LoadingState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


@ExperimentalCoroutinesApi
class DashboardViewModel @ViewModelInject constructor() : ViewModel() {

    private val _viewState = MutableStateFlow(ViewState.init())
    val viewState: StateFlow<ViewState> = _viewState


    init {
        fetchUserCourses()
    }

    private fun fetchUserCourses() {

    }


    companion object {

        data class ViewState(
            val loadState: LoadingState,
            val subjects: List<SubjectModel>,
            val error: Throwable?,
        ) {

            companion object {
                fun init(): ViewState {
                    return ViewState(LoadingState.Idle, emptyList(), null)
                }
            }
        }

    }
}