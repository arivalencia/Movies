package com.ari.movies.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ari.movies.R
import com.ari.movies.databinding.FragmentMovieDetailBinding
import com.ari.movies.ui.model.MovieUi
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment: Fragment() {

    companion object {
        const val EXTRA_MOVIE = "EXTRA_MOVIE"
    }

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding: FragmentMovieDetailBinding get() = _binding!!

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


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}