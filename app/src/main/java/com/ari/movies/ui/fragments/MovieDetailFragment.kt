package com.ari.movies.ui.fragments

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ari.movies.R
import com.ari.movies.databinding.FragmentMovieDetailBinding
import com.ari.movies.ui.model.MovieUi
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.TrackGroupArray
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelectionArray
import com.google.android.exoplayer2.trackselection.TrackSelector
import com.google.android.exoplayer2.upstream.BandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.upstream.RawResourceDataSource
import com.google.android.exoplayer2.util.Util
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment: Fragment() {

    companion object {
        const val EXTRA_MOVIE = "EXTRA_MOVIE"
    }

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding: FragmentMovieDetailBinding get() = _binding!!

    private var simpleExoPlayer: SimpleExoPlayer? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.setTitle(R.string.detail)

        val movie = arguments?.getParcelable<MovieUi>(EXTRA_MOVIE)

        movie?.let {
            binding.movieDetail = it
        }

        playVideo("https://html5demos.com/assets/dizzy.mp4")

    }

    private fun playVideo(videoUrl: String) {
        val loadControl: LoadControl = DefaultLoadControl()
        val bandwidthMeter: BandwidthMeter = DefaultBandwidthMeter()
        val trackSelector: TrackSelector = DefaultTrackSelector(
            AdaptiveTrackSelection.Factory(bandwidthMeter)
        )

        simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(requireContext(), trackSelector, loadControl)
        val dataSourceFactory = DefaultDataSourceFactory(
            requireContext(), Util.getUserAgent(requireContext(), getString(R.string.app_name))
        )
        val uri = Uri.parse(videoUrl)
        val firstSource = ExtractorMediaSource
            .Factory(dataSourceFactory)
            .createMediaSource(uri)

        binding.playerView.player = simpleExoPlayer
        binding.playerView.keepScreenOn = true
        binding.playerView.controllerHideOnTouch = true
        simpleExoPlayer!!.playWhenReady = true
        simpleExoPlayer!!.prepare(firstSource)
    }

    override fun onPause() {
        super.onPause()
        simpleExoPlayer!!.playWhenReady = false
        simpleExoPlayer!!.playbackState
    }

    override fun onResume() {
        super.onResume()
        if (simpleExoPlayer != null) {
            simpleExoPlayer!!.playWhenReady = true
            simpleExoPlayer!!.playbackState
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}