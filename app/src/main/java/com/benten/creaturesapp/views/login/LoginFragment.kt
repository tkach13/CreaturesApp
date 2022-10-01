package com.benten.creaturesapp.views.login

import android.animation.Animator
import android.animation.ValueAnimator
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AccelerateInterpolator
import android.view.animation.BounceInterpolator
import android.view.animation.DecelerateInterpolator
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
            animateLogin()
        }

//        collectFlow {
//            viewModel.getAllCreaturesSharedFlow().collectLatest {
//                if (it) {
//                    goToAllCreatures()
//                }
//            }
//        }
    }

    private fun animateLogin() {
        val alphaAnimator = ValueAnimator.ofFloat(0f, 1f).apply {
            duration = 2000
            interpolator = BounceInterpolator()
        }
        val buttonWidth = binding.btnLogin.width
        val buttonHeight = binding.btnLogin.height

        binding.spinner.isVisible = true
        binding.spinner.playAnimation()

        alphaAnimator.addUpdateListener {
            val updatedValue = it.animatedValue as Float
            binding.btnLogin.alpha = 1 - updatedValue
            binding.btnLogin.updateLayoutParams {
                this.width = (buttonWidth * (1 - updatedValue)).toInt()
                this.height = (buttonHeight * (1 - updatedValue)).toInt()
            }
        }
        alphaAnimator.addListener(object :Animator.AnimatorListener {
            override fun onAnimationStart(p0: Animator?) {
            }

            override fun onAnimationEnd(p0: Animator?) {
                goToAllCreatures()
            }

            override fun onAnimationCancel(p0: Animator?) {
            }

            override fun onAnimationRepeat(p0: Animator?) {
            }


        })
        alphaAnimator.start()
    }


    private fun goToAllCreatures() {
        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToAllCreaturesFragment())
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}