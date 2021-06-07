package org.tabdeal.utils

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import org.tabdeal.databinding.DialogUpdateBinding

class UpdateDialog : DialogFragment() {
    private lateinit var binding: DialogUpdateBinding
    private lateinit var listener: DialogListener
    private var isForce = false

    companion object {
        fun newInstance(force: Boolean) = UpdateDialog().apply {
            arguments = bundleOf("force" to force)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = parentFragment as DialogListener
        } catch (e : ClassCastException) {
            e.printStackTrace()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogUpdateBinding.inflate(layoutInflater, container, false)
        isForce = requireArguments().getBoolean("force")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (isForce) {
            binding.btnNo.visibility = View.GONE
            binding.btnYes.visibility = View.GONE
            binding.btnUpdate.visibility = View.VISIBLE
        }
        binding.btnNo.setOnClickListener {
            dismiss()
            listener.onCancel()
        }
        binding.btnYes.setOnClickListener {
            dismiss()
            listener.onConfirm()
        }
        binding.btnUpdate.setOnClickListener {
            dismiss()
            listener.onConfirm()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
        return dialog
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        listener.onDismiss()
    }
}