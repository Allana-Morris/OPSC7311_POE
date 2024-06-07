package com.example.opsc7311poe

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.opsc7311poe.databinding.DialogIconPickerBinding


class IconPickerDialogFragment(private val icons: List<Int>, private val listener: OnIconSelectedListener) : DialogFragment(), IconAdapter.OnIconClickListener {

    interface OnIconSelectedListener {
        fun onIconSelected(iconResId: Int)
    }

    private var _binding: DialogIconPickerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DialogIconPickerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.iconRecyclerView.layoutManager = GridLayoutManager(context, 4) // 4 columns
        binding.iconRecyclerView.adapter = IconAdapter(icons, this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onIconClick(iconResId: Int) {
        listener.onIconSelected(iconResId)
        dismiss()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setOnShowListener {
            val width = (resources.displayMetrics.widthPixels * 0.85).toInt() // 85% of screen width
            val height = (resources.displayMetrics.heightPixels * 0.5).toInt() // 70% of screen height
            dialog.window?.setLayout(width, height)
        }
        return dialog
    }

    companion object {
        fun newInstance(icons: List<Int>, listener: OnIconSelectedListener): IconPickerDialogFragment {
            return IconPickerDialogFragment(icons, listener)
        }
    }
}

