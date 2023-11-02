package com.huoergai.muse.ui.people

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.huoergai.muse.base.BaseFragment
import com.huoergai.muse.databinding.FragmentPeopleBinding
import com.huoergai.muse.ui.MainViewModel
import com.huoergai.muse.ui.view.adapter.PersonRvAdapter
import kotlinx.coroutines.launch

/**
 * D&T: 2023-10-13 21:28
 * DES:
 */
class PeopleFragment : BaseFragment() {
    private var _binding: FragmentPeopleBinding? = null
    private val binding get() = _binding!!

    private val mainVM by activityViewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPeopleBinding.inflate(layoutInflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutMgr = GridLayoutManager(requireContext(), 2)
        val peopleAdapter = PersonRvAdapter().apply {
            setItemClickListener {
                val pos = layoutMgr.getPosition(it)
                val person = this.getData(pos)
                PersonDetailActivity.start(requireActivity(), it, person)
            }
        }

        lifecycleScope.launch {
            mainVM.persons.collect {
                peopleAdapter.setData(it)
            }
        }

        binding.rv.apply {
            layoutManager = layoutMgr
            adapter = peopleAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}