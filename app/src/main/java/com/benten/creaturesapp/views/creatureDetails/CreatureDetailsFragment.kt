package com.benten.creaturesapp.views.creatureDetails

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.benten.creaturesapp.R
import com.benten.creaturesapp.databinding.FragmentCreatureDetailsBinding

class CreatureDetailsFragment : Fragment() {

    private var _binding: FragmentCreatureDetailsBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(requireContext())
            .inflateTransition(R.transition.shared_image)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreatureDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        CreatureDetailsFragmentArgs.fromBundle(requireArguments()).creatureImage.also {
            binding.imageVieeCreature.setImageResource(it)
        }
    }
    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}