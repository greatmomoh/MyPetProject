package com.example.mypetproject.ui.videoplayer

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypetproject.data.repositories.LessonsRepository
import com.example.mypetproject.model.LessonModel
import com.example.mypetproject.model.SubjectModel
import com.example.mypetproject.ui.dashboard.DashboardViewModel
import com.example.mypetproject.utils.LoadingState
import com.example.mypetproject.utils.updateValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject


@ExperimentalCoroutinesApi
class LessonVideoViewModel @ViewModelInject constructor(private val lessonsRepository: LessonsRepository) :
    ViewModel() {

    private val _viewState = MutableStateFlow(ViewState.init())
    val viewState: StateFlow<ViewState> = _viewState

    fun saveLesson(model: LessonModel) {
        lessonsRepository.saveLesson(model).onStart {
            _viewState.updateValue {
                copy(loadState = LoadingState.Working, error = null)
            }
        }.onEach {
            _viewState.updateValue {
                copy(loadState = LoadingState.Success, lesson = model, error = null)
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
            val lesson: LessonModel?,
            val error: Throwable?,
        ) {

            companion object {
                fun init(): ViewState {
                    return ViewState(LoadingState.Idle, null, null)
                }
            }
        }

    }
}