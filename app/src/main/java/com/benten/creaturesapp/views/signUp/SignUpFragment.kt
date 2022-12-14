package com.benten.creaturesapp.views.signUp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.benten.creaturesapp.databinding.FragmentSignUpBinding
import com.benten.creaturesapp.extensions.collectFlow
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<SignUpViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectFlow {
            viewModel.getSignUpEventFlow().collectLatest {
                if (it) {
                    findNavController().popBackStack()
                    Toast.makeText(requireContext(), "Successfully signed up", Toast.LENGTH_LONG)
                        .show()
                } else {
                    Toast.makeText(requireContext(), "Error while signing up", Toast.LENGTH_LONG)
                        .show()

                }
            }
        }
        binding.btnSignup.setOnClickListener {
            viewModel.onSignup(
                binding.etUserName.text.toString(),
                binding.etPassword.text.toString(),
                binding.etName.text.toString()
            )
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}