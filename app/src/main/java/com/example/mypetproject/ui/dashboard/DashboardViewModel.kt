package com.example.mypetproject.ui.dashboard

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypetproject.data.repositories.SubjectsRepository
import com.example.mypetproject.model.SubjectModel
import com.example.mypetproject.utils.LoadingState
import com.example.mypetproject.utils.updateValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*


@ExperimentalCoroutinesApi
class DashboardViewModel @ViewModelInject constructor(
    private val subjectsRepository: SubjectsRepository
) : ViewModel() {

    private val _viewState = MutableStateFlow(ViewState.init())
    val viewState: StateFlow<ViewState> = _viewState


    init {
        fetchUserCourses()
    }

    private fun fetchUserCourses() {
        subjectsRepository.streamUserSubjects().onStart {
            _viewState.updateValue {
                copy(loadState = LoadingState.Working, error = null)
            }
        }.onEach {
            _viewState.updateValue {
                copy(loadState = LoadingState.Success, subjects = it,  error = null)
            }
        }.catch {
            it.printStackTrace()
            _viewState.updateValue {
                copy(loadState = LoadingState.Error, error = it)
            }
        }.launchIn(viewModelScope)
    }

    fun fetchCourses(){
        fetchUserCourses()
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