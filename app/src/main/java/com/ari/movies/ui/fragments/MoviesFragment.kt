package com.ari.movies.ui.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.ari.movies.R
import com.ari.movies.databinding.FragmentMoviesBinding
import com.ari.movies.ui.adapters.MoviesAdapter
import com.ari.movies.ui.viewmodels.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : Fragment() {

    private var _binding: FragmentMoviesBinding? = null
    private val binding: FragmentMoviesBinding get() = _binding!!
    private lateinit var moviesAdapter: MoviesAdapter
    private val moviesViewModel: MoviesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.setTitle(R.string.app_name)

        init()
        setHasOptionsMenu(true)
        observeResults()

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_movies_filter, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) { // On click option from actionBar menu
            R.id.most_popular -> moviesViewModel.getPopularMovies()
            R.id.playing_now -> moviesViewModel.getPlayingNowMovies()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun init() {
        moviesAdapter = MoviesAdapter() { movie, _ ->
            // Bundle for send to MovieDetailFragment and show
            val bundle = bundleOf(MovieDetailFragment.EXTRA_MOVIE to movie)
            // Navigate
            Navigation
                .findNavController(binding.root)
                .navigate(R.id.action_moviesFragment_to_movieDetailFragment, bundle)
        }
        binding.rvMovies.adapter = moviesAdapter
    }

    private fun observeResults() {
        moviesViewModel.error.observe(viewLifecycleOwner) { error ->
            showToast(error)
        }

        moviesViewModel.movies.observe(viewLifecycleOwner) { movies ->
            moviesAdapter.setList(movies)
        }

        moviesViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressIndicator.visibility = if (isLoading) View.VISIBLE else View.INVISIBLE
            binding.rvMovies.visibility = if (isLoading) View.GONE else View.VISIBLE
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}