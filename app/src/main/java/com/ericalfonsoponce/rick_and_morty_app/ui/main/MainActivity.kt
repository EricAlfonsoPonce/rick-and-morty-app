package com.ericalfonsoponce.rick_and_morty_app.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ericalfonsoponce.rick_and_morty_app.R
import com.ericalfonsoponce.rick_and_morty_app.databinding.ActivityMainBinding
import com.ericalfonsoponce.rick_and_morty_app.helpers.Constants
import com.ericalfonsoponce.rick_and_morty_app.helpers.Constants.LOAD_VISIBLE_CHARACTER
import com.ericalfonsoponce.rick_and_morty_app.helpers.extensions.showDoubleDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: CharactersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater, null, false)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        setContentView(binding.root)

        viewModel.getCharacters()

        initAdapter()
        initListeners()
        initObservers()
    }

    private fun initAdapter() {
        adapter = CharactersAdapter(
            onCharacterClick = {

            }
        )
        binding.recyclerCharacter.adapter = adapter
    }

    private fun initListeners() {
        binding.swipeRefresh.apply {
            setOnRefreshListener {
                isRefreshing = false
                viewModel.refreshData()
            }
        }

        binding.recyclerCharacter.addOnScrollListener(
            object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    (binding.recyclerCharacter.layoutManager as? GridLayoutManager)?.let {
                        if (it.findLastVisibleItemPosition() >= adapter.itemCount - LOAD_VISIBLE_CHARACTER && viewModel.hastNext() && !viewModel.uiState.value.isLoading) {
                            viewModel.getCharacters()
                        }
                    }
                }
            }
        )
    }

    private fun initObservers() {
        lifecycleScope.launch {
            viewModel.uiState
                .map { it.isLoading }
                .distinctUntilChanged()
                .collect {

                }
        }

        lifecycleScope.launch {
            viewModel.uiState
                .map { it.listCharacters }
                .distinctUntilChanged()
                .collect {
                    adapter.submitList(it)
                    adapter.notifyItemRangeChanged(0, adapter.itemCount)
                }
        }

        lifecycleScope.launch {
            viewModel.uiState
                .map { it.errorResourceId }
                .distinctUntilChanged()
                .collect { error ->
                    error?.let {
                        if (error != 0) {
                            showDoubleDialog(
                                mapOf(
                                    Constants.DIALOG_TITLE to getString(R.string.error_title),
                                    Constants.DIALOG_DESCRIPTION to getString(error)
                                )
                            ) {
                                viewModel.clearError()
                            }
                        }
                    }
                }
        }
    }
}