package com.huoergai.muse.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.huoergai.muse.base.BaseFragment
import com.huoergai.muse.databinding.FragmentMovieBinding
import com.huoergai.muse.ui.MainViewModel
import com.huoergai.muse.ui.view.adapter.MovieRvAdapter
import kotlinx.coroutines.launch

/**
 * D&T: 2023-10-13 21:28
 * DES:
 */
class MovieFragment : BaseFragment() {
    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!

    private val mainVM: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val gridLayoutMgr = GridLayoutManager(context, 2)
        val rvAdapter = MovieRvAdapter().apply {
            setItemClickListener {
                val pos = gridLayoutMgr.getPosition(it)
                val movie = this.getData(pos)
                MovieDetailActivity.start(requireActivity(), it, movie)
            }
        }

        mainVM.loadMovies()

        binding.rvMovies.apply {
            layoutManager = gridLayoutMgr
            adapter = rvAdapter
        }

        lifecycleScope.launch {
            mainVM.movieList.collect {
                rvAdapter.addData(it)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}