package com.benten.creaturesapp.views.allCreatures

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.benten.creaturesapp.R
import com.benten.creaturesapp.databinding.FragmentAllCreaturesBinding
import com.benten.creaturesapp.model.Creature
import com.benten.creaturesapp.model.CreatureAttributes
import com.benten.creaturesapp.views.addCreature.AddCreatureFragment
import com.benten.creaturesapp.views.allCreatures.adapters.AllCreaturesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AllCreaturesFragment : Fragment() {
    private var _binding: FragmentAllCreaturesBinding? = null
    private val binding get() = _binding!!
    private lateinit var creaturesAdapter: AllCreaturesAdapter

    private val viewModel by viewModels<AllCreaturesViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAllCreaturesBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        creaturesAdapter = AllCreaturesAdapter()
        binding.rvCreatures.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)


        binding.rvCreatures.adapter = creaturesAdapter

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    when (uiState) {
                        is AllCreaturesState.Error -> TODO()
                        is AllCreaturesState.Success -> creaturesAdapter.updateAll(uiState.data)
                    }

                }
            }
        }
        binding.etSearch.doAfterTextChanged {
            Log.d("Tkach13", it.toString())
            viewModel.onSearchRequested(it.toString())
        }
        binding.fabAddButton.setOnClickListener {
            viewModel.onPlusClicked()
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.addClikedSharedFlow.collect {
                    if (it) {
                        goToAddCreature()
                    }
                }
            }
        }
    }

    private fun goToAddCreature() {
        parentFragmentManager.beginTransaction().apply {
            replace(R.id.flContent, AddCreatureFragment())
            addToBackStack(AddCreatureFragment::class.java.name)
            commit()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}