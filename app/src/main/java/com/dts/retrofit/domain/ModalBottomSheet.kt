package com.dts.retrofit.domain

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.dts.retrofit.databinding.BottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ModalBottomSheet : BottomSheetDialogFragment() {

    companion object {
        const val TAG = "ModalSheet"
    }

    private lateinit var binding: BottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListener()
    }

    private fun setupListener() {
        binding.fabAddPage.setOnClickListener {
            val link = binding.etLink.text.toString()

            if (Patterns.WEB_URL.matcher(link).matches()) {
                onAddNewRss?.invoke(NewsFragment.create(link))
                dismiss()
            } else {
                Toast.makeText(requireContext(), "Link not valid!", Toast.LENGTH_SHORT).show()
            }
            binding.etLink.setText("")
        }
    }

    var onAddNewRss: ((NewsFragment) -> Unit)? = null
}
