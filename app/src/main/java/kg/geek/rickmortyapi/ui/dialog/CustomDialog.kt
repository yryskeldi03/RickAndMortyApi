package kg.geek.rickmortyapi.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
}