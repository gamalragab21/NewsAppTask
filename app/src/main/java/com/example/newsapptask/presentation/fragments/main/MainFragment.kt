package com.example.newsapptask.presentation.fragments.main

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapptask.common.NewsIds
import com.example.newsapptask.common.TAG
import com.example.newsapptask.common.utils.BaseFragment
import com.example.newsapptask.common.utils.navigateSafely
import com.example.newsapptask.common.utils.snackbar
import com.example.newsapptask.common.utils.toArrayList
import com.example.newsapptask.databinding.FragmentMainBinding
import com.example.newsapptask.presentation.fragments.main.adapters.MainAdapter
import com.example.newsapptask.presentation.fragments.main.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>() {
    private val viewModel: MainViewModel by viewModels()

    @Inject
    lateinit var mainAdapter: MainAdapter
    override fun getViewBinding(): FragmentMainBinding = FragmentMainBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerViewMainNews()
        subscribeToObservables()
        lifecycleScope.launchWhenStarted {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.mainUiActions.send(MainUiEvent.LoadNews)
            }
        }
        actions()
    }

    private fun actions() {
        mainAdapter.setOnItemClickListener {
            val bundle = bundleOf("item" to it)
            findNavController().navigateSafely(
                NewsIds.action_mainFragment_to_detailsFragment,
                bundle
            )
        }
    }

    private fun setupRecyclerViewMainNews() = binding.newsRecyclerView.apply {
        itemAnimator = null
        isNestedScrollingEnabled = true
        layoutManager = LinearLayoutManager(requireContext())
        adapter = mainAdapter

    }

    override fun subscribeToObservables() {
        viewModel.newsUiState.observe(viewLifecycleOwner) {
            Log.i(TAG, "subscribeToObservables: $it")

            uiCommunicationListener.showProgressBar(it.loading)
            it.message?.let {
                snackbar(it)
            }
            mainAdapter.news = it.data.reversed()
        }

    }
}