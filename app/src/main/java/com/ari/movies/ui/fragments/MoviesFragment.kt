package com.ari.movies.ui.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
        moviesViewModel.onCreate()

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_movies_filter, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) { // On click option from actionBar menu
            R.id.most_popular -> moviesViewModel.changeToMostPopularMovies()
            R.id.playing_now -> moviesViewModel.changeToPlayingNowMovies()
        }
        moviesAdapter.clearList()
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
        val layoutManager = binding.rvMovies.layoutManager!!
        binding.rvMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount: Int = layoutManager.childCount
                val totalItemCount: Int = layoutManager.itemCount
                val firstVisibleItemPosition: Int =
                    (layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

                // Load more if we have reach the end to the recyclerView
                if (!moviesViewModel.isLoading.value!!) {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0
                        // && totalItemCount >= PAGE_SIZE
                        ) {
                        moviesViewModel.loadNextPage()
                    }
                }
            }
        })
    }

    private fun observeResults() {
        moviesViewModel.error.observe(viewLifecycleOwner) { error ->
            showToast(error)
        }

        moviesViewModel.movies.observe(viewLifecycleOwner) { movies ->
            if (moviesAdapter.itemCount == 0) {
                moviesAdapter.setList(movies)
            } else {
                moviesAdapter.addItems(movies.subList(moviesAdapter.itemCount, movies.size))
            }
        }

        moviesViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressIndicator.visibility = if (isLoading) View.VISIBLE else View.INVISIBLE
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