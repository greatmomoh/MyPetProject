package com.example.mypetproject.ui.videoplayer

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.mypetproject.R
import com.example.mypetproject.databinding.LessonVideoFragmentBinding
import com.example.mypetproject.ui.videoplayer.videoplayerview.VideoPlayerState
import com.example.mypetproject.utils.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.exoplayer_controller_layout.view.*
import kotlinx.android.synthetic.main.lesson_video_fragment.view.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


@ExperimentalCoroutinesApi
@AndroidEntryPoint
class LessonVideoFragment : Fragment(R.layout.lesson_video_fragment) {

    private val binding: LessonVideoFragmentBinding by viewBinding(LessonVideoFragmentBinding::bind)

    private val lessonArgument: LessonVideoFragmentArgs by navArgs()

    private val viewModel: LessonVideoViewModel by activityViewModels()

    private var playerState: VideoPlayerState = VideoPlayerState()

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(PLAYER_STATE_KEY, playerState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkScreenOrientation()
        setupVideo()

        savedInstanceState?.getParcelable<VideoPlayerState>(PLAYER_STATE_KEY)?.let { state ->
            playerState = state
        }

        binding.videoPlayer.video_player.cross_im.setOnClickListener {
            navController.navigateUp()
        }

        viewModel.saveLesson(lessonArgument.lesson)
        listenForStateChanges()

    }

    private fun listenForStateChanges() {
        viewModel.viewState.onEach {
            handleViewStateChanges(it)
        }.launchIn(viewLifecycleScope)
    }

    private fun handleViewStateChanges(viewState: LessonVideoViewModel.Companion.ViewState) {
        when (viewState.loadState) {
            LoadingState.Idle -> {

            }
            LoadingState.Working -> {
            }
            LoadingState.Error -> {
                viewState.error?.message?.let {
                    showSnackBar("Couldn't save video to list, because $it")
                }
            }
            LoadingState.Success -> {
                showSnackBar("Video saved successfully!")
            }
        }
    }

    private fun checkScreenOrientation() {
        val orientation: Int = resources.configuration.orientation
        binding.videoPlayer.isFullScreen = orientation == Configuration.ORIENTATION_LANDSCAPE
//        binding.toolbar.visible = orientation != Configuration.ORIENTATION_LANDSCAPE
    }

    private fun setupVideo() {
        playerState = playerState.checkAndSet(lessonArgument.lesson.media_url)
        binding.videoPlayer.play(this, playerState)
        binding.lessonTitle.text = lessonArgument.lesson.name
        binding.chapterTitle.text = lessonArgument.name
    }


    companion object {
        const val PLAYER_STATE_KEY: String = "player_state"
    }
}