package com.example.mypetproject.ui.dashboard

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypetproject.data.repositories.LessonsRepository
import com.example.mypetproject.data.repositories.SubjectsRepository
import com.example.mypetproject.model.LessonModel
import com.example.mypetproject.model.SubjectModel
import com.example.mypetproject.utils.LoadingState
import com.example.mypetproject.utils.updateValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*


@ExperimentalCoroutinesApi
class DashboardViewModel @ViewModelInject constructor(
    private val subjectsRepository: SubjectsRepository,
    private val lessonsRepository: LessonsRepository
) : ViewModel() {

    private val _viewState = MutableStateFlow(ViewState.init())
    val viewState: StateFlow<ViewState> = _viewState


    init {
        fetchUserCourses()
        fetchSavedContent()
    }

    fun fetchCourses(){
        fetchSavedContent()
    }

    private fun fetchSavedContent() {
        lessonsRepository.streamSavedLessons().onStart {
            _viewState.updateValue {
                copy(loadState = LoadingState.Working, error = null)
            }
        }.onEach {
            _viewState.updateValue {
                copy(loadState = LoadingState.Success, lessons = it,  error = null)
            }
        }.catch {
            it.printStackTrace()

            _viewState.updateValue {
                copy(loadState = LoadingState.Error, error = it)
            }
        }.launchIn(viewModelScope)
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



    companion object {

        data class ViewState(
            val loadState: LoadingState,
            val subjects: List<SubjectModel>,
            val lessons: List<LessonModel>,
            val error: Throwable?,
        ) {

            companion object {
                fun init(): ViewState {
                    return ViewState(LoadingState.Idle, emptyList(), emptyList(),null)
                }
            }
        }

    }
}