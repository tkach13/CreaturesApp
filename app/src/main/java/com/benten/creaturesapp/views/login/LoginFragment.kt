package com.benten.creaturesapp.views.login

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.BounceInterpolator
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.airbnb.lottie.LottieDrawable
import com.benten.creaturesapp.R
import com.benten.creaturesapp.databinding.FragmentLoginBinding
import com.benten.creaturesapp.extensions.collectFlow
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogin.setOnClickListener {
            viewModel.onLoginIntent()
        }
        collectFlow {
            viewModel.getAllCreaturesSharedFlow().collectLatest {
                if (it) {
                    goToAllCreatures()
                }
            }
        }
    }



    private fun goToAllCreatures() {
        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToAllCreaturesFragment())
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}