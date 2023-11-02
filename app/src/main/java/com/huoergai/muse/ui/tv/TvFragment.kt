package com.huoergai.muse.ui.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.huoergai.muse.R
import com.huoergai.muse.base.BaseFragment
import com.huoergai.muse.databinding.FragmentTvBinding
import com.huoergai.muse.ui.MainViewModel
import com.huoergai.muse.ui.view.adapter.TvRvAdapter
import kotlinx.coroutines.launch

/**
 * D&T: 2023-10-13 21:28
 * DES:
 */
class TvFragment : BaseFragment() {
    private var _binding: FragmentTvBinding? = null
    private val binding get() = _binding!!

    private val mainVM: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvBinding.inflate(layoutInflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutMgr = LinearLayoutManager(context)
        val tvAdapter = TvRvAdapter().apply {
            setItemClickListener {
                val pos = layoutMgr.getPosition(it)
                val tv = this.getData(pos)
                TvDetailActivity.start(requireActivity(), it, tv)
            }
        }

        lifecycleScope.launch {
            mainVM.tvList.collect {
                if (it.isEmpty()) {
                    binding.mtvTvLabel.apply {
                        visibility = View.VISIBLE
                        text = getString(R.string.empty)
                    }
                } else {
                    binding.mtvTvLabel.visibility = View.INVISIBLE
                    tvAdapter.addData(it)
                }
            }
        }

        binding.rv.apply {
            layoutManager = layoutMgr
            adapter = tvAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}