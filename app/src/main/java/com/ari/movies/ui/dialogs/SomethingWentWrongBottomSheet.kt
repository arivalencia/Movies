package com.ari.movies.ui.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ari.movies.databinding.DialogSomethingWentWrongBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SomethingWentWrongBottomSheet(
    private val onDismiss: () -> Unit
): BottomSheetDialogFragment() {

    private var _binding: DialogSomethingWentWrongBinding? = null
    private val binding: DialogSomethingWentWrongBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogSomethingWentWrongBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnOk.setOnClickListener {
            dismiss()
            onDismiss()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}