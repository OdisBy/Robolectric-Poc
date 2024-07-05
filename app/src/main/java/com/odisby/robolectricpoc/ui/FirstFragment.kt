package com.odisby.robolectricpoc.ui

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.transition.Visibility
import com.odisby.robolectricpoc.R
import com.odisby.robolectricpoc.databinding.FragmentFirstBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FirstFragment : Fragment() {

    companion object {
        fun newInstance() = FirstFragment()
    }

    private var _binding: FragmentFirstBinding? = null

    private val binding get() = _binding!!


    private val viewModel: FirstViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.state.collect { state ->
                    bindUiState(state)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            tv1.text = "Ruliam"

            bt1.setOnClickListener {
                tv1.text = "Botao clicado!"
            }

            bt2.setOnClickListener {
                tv1.isVisible = false
            }

            bt3.setOnClickListener {
                viewModel.logicalOne()
            }

            btNextScreen.setOnClickListener {
                view.findNavController().navigate(R.id.action_firstFragment_to_secondFragment)
            }
        }
    }

    private fun bindUiState(uiState: FirstViewState) {
        binding.tvPopup.isVisible = uiState.shouldDisplaySecretTextView
    }
}