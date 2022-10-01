package com.benten.creaturesapp.views.allCreatures

import android.graphics.drawable.Animatable2
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.util.Pair as UtilPair
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.benten.creaturesapp.databinding.FragmentAllCreaturesBinding

import com.benten.creaturesapp.views.addCreature.data.AddCreatureDataModel
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


        postponeEnterTransition()

        binding.rvCreatures.apply {
            binding.rvCreatures.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = creaturesAdapter

        }


        val animatable2 = binding.ivSettings.drawable as? Animatable2

        animatable2?.start()

        binding.upperSort.setOnClickListener {
            if (!binding.upperSort.isActived) {
                binding.upperSort.setActive(true)
            }
            binding.downSort.setActive(false)

        }
        binding.downSort.setOnClickListener {
            if (!binding.downSort.isActived) {
                binding.downSort.setActive(true)
            }
            binding.upperSort.setActive(false)
        }
        creaturesAdapter.setOnClickListener {
            findNavController().navigate(
                AllCreaturesFragmentDirections.actionAllCreaturesFragmentToAddCreatureFragment(
                    it
                ),
            )
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    when (uiState) {
                        is AllCreaturesState.Error -> TODO()
                        is AllCreaturesState.Success -> {
                            creaturesAdapter.updateAll(uiState.data)
                            binding.rvCreatures.viewTreeObserver.addOnPreDrawListener {
                                startPostponedEnterTransition()
                                true
                            }

                        }

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
                        goToAddCreature(null)
                    }
                }
            }
        }
    }

    private fun goToAddCreature(addCreatureDataModel: AddCreatureDataModel?) {
        findNavController().navigate(
            AllCreaturesFragmentDirections.actionAllCreaturesFragmentToAddCreatureFragment(
                addCreatureDataModel
            )
        )
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}