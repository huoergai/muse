package com.huoergai.muse.ui.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.huoergai.muse.base.BaseFragment
import com.huoergai.muse.databinding.FragmentTvBinding

/**
 * D&T: 2023-10-13 21:28
 * DES:
 */
class TvFragment : BaseFragment() {
    private var _binding: FragmentTvBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvBinding.inflate(layoutInflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}