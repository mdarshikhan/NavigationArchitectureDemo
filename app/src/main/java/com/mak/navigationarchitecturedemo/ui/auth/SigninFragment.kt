package com.mak.navigationarchitecturedemo.ui.auth

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.mak.navigationarchitecturedemo.R
import com.mak.navigationarchitecturedemo.databinding.FragmentSinginBinding

class SigninFragment : Fragment() {

    private var _binding: FragmentSinginBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSinginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListener()
    }

    private fun setListener() {
        _binding?.btnSignin?.setOnClickListener {
            findNavController().navigate(R.id.action_signinFragment_to_storageFragment)
            onSigninFinished()
        }

        _binding?.btnSignup?.setOnClickListener {
            findNavController().navigate(R.id.action_signinFragment_to_signupFragment)
        }

    }

    private fun onSigninFinished() {
        val sharedPref = requireActivity().getSharedPreferences("onSignin", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished", true)
        editor.apply()
    }

}