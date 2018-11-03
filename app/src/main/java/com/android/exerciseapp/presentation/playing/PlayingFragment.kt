package com.android.exerciseapp.presentation.playing

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.android.exerciseapp.R
import com.android.exerciseapp.presentation.playing.PlayingFragmentArgs.fromBundle
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.TrackGroupArray
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelection
import com.google.android.exoplayer2.trackselection.TrackSelectionArray
import com.google.android.exoplayer2.upstream.AssetDataSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DataSpec


/**
 * Created by JasonYang.
 */
class PlayingFragment : Fragment() {

    companion object {
        const val TAG = "PlayingFragment"
    }

    private lateinit var podcastName: TextView
    private lateinit var musicButton: ImageButton

    private lateinit var exoPlayer: SimpleExoPlayer
    private val eventListener = object : ExoPlayer.EventListener {

        override fun onTracksChanged(trackGroups: TrackGroupArray?, trackSelections: TrackSelectionArray?) {
            Log.i(TAG, "onTracksChanged")
        }

        override fun onLoadingChanged(isLoading: Boolean) {
            Log.i(TAG, "onLoadingChanged")
        }

        override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
            Log.i(
                TAG, "onPlayerStateChanged: playWhenReady = " + playWhenReady.toString()
                        + " playbackState = " + playbackState
            )
            when (playbackState) {
                ExoPlayer.STATE_ENDED -> {
                    Log.i(TAG, "Playback ended!")
                    setPlayPause(false)
                    exoPlayer.seekTo(0)
                }
                ExoPlayer.STATE_READY -> {
                }
                ExoPlayer.STATE_BUFFERING -> Log.i(TAG, "Playback buffering!")
                ExoPlayer.STATE_IDLE -> Log.i(TAG, "ExoPlayer idle!")
            }
        }

        override fun onPlayerError(error: ExoPlaybackException) {
            Log.i(TAG, "onPlaybackError: " + error.message)
        }
    }

    private var isPlaying = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_playing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val path = Uri.parse("asset:///${fromBundle(arguments).podcastPath}")
        prepareExoPlayerFromAssetResourceFile(path)
    }

    private fun prepareExoPlayerFromAssetResourceFile(path: Uri) {

        exoPlayer = ExoPlayerFactory.newSimpleInstance(
            context,
            DefaultTrackSelector(null as TrackSelection.Factory?),
            DefaultLoadControl()
        )
        exoPlayer.addListener(eventListener)

        val dataSpec = DataSpec(path)
        val assetDataSource = AssetDataSource(context)
        try {
            assetDataSource.open(dataSpec)
        } catch (e: AssetDataSource.AssetDataSourceException) {
            e.printStackTrace()
        }

        val factory = DataSource.Factory {
            assetDataSource
        }

        val audioSource = ExtractorMediaSource(
            assetDataSource.uri,
            factory, DefaultExtractorsFactory(), null, null
        )

        exoPlayer.prepare(audioSource)
        initMediaControls()
    }

    private fun initMediaControls() {
        initTextButton()
        initPlayButton()
    }

    private fun initTextButton() {
        podcastName = view?.findViewById(R.id.podcast_name) as TextView
        podcastName.text = fromBundle(arguments).podcastPath
    }

    private fun initPlayButton() {
        musicButton = view?.findViewById(R.id.music_button) as ImageButton
        musicButton.requestFocus()
        musicButton.setOnClickListener { this.setPlayPause(!isPlaying) }
    }

    private fun setPlayPause(play: Boolean) {
        isPlaying = play
        exoPlayer.playWhenReady = play
        if (!isPlaying) {
            musicButton.setImageResource(R.drawable.ic_play_arrow)
        } else {
            musicButton.setImageResource(R.drawable.ic_stop)
        }
    }

    override fun onResume() {
        super.onResume()
        exoPlayer.playWhenReady = isPlaying
    }

    override fun onStop() {
        exoPlayer.playWhenReady = false
        super.onStop()
    }

    override fun onDestroy() {
        exoPlayer.stop()
        exoPlayer.release()
        super.onDestroy()
    }

}
