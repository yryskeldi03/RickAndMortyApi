package kg.geek.rickmortyapi.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.DialogFragment
import kg.geek.rickmortyapi.R
import kg.geek.rickmortyapi.databinding.FragmentCustomDialogBinding
import kg.geek.rickmortyapi.extensions.showToast

class CustomDialogFragment : DialogFragment() {
    private lateinit var binding: FragmentCustomDialogBinding
    private lateinit var listener: CustomDialogResultListener
    private var genderResult: String? = null
    private var statusResult: String? = null

    fun setListener(listener: CustomDialogResultListener) {
        this.listener = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCustomDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnApply.setOnClickListener {
            val selectedStatusId = binding.rgStatus.checkedRadioButtonId
            val status = binding.root.findViewById<RadioButton>(selectedStatusId)
            if (status != null){
                statusResult = status.text.toString()
            }

            val selectedGenderId = binding.rgGender.checkedRadioButtonId
            val gender = binding.root.findViewById<RadioButton>(selectedGenderId)
            if (gender != null){
                genderResult = gender.text.toString()
            }

            if (statusResult != null && genderResult != null) {
                listener.statusAndGender(statusResult, genderResult)
            } else if (genderResult != null) {
                listener.gender(genderResult)
            } else if (statusResult != null) {
                listener.status(statusResult)
            } else {
                requireContext().showToast(getString(R.string.choose))
            }
            dismiss()
            binding.rgGender.clearCheck()
            binding.rgStatus.clearCheck()
        }

        binding.btnCancel.setOnClickListener {
            dismiss()
        }
    }

}

interface CustomDialogResultListener {
    fun status(status: String?)
    fun gender(gender: String?)
    fun statusAndGender(status: String?, gender: String?)
}