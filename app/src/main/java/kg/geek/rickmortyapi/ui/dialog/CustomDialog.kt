package kg.geek.rickmortyapi.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.DialogFragment
import kg.geek.rickmortyapi.databinding.FragmentCustomDialogBinding

class CustomDialogFragment: DialogFragment() {
    private lateinit var binding: FragmentCustomDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCustomDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnApply.setOnClickListener {
            val selectedStatusId = binding.rgStatus.checkedRadioButtonId
            val status = binding.root.findViewById<RadioButton>(selectedStatusId)
        }

        binding.btnCancel.setOnClickListener {
            dismiss()
        }
    }
}