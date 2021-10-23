package cz.ich.notinoapp.main.system

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import cz.ich.notinoapp.R
import cz.ich.notinoapp.databinding.DialogFragmentErrorBinding

/**
 * [DialogFragment] for all errors.
 */
class ErrorDialogFragment : DialogFragment(R.layout.dialog_fragment_error) {

    private val binding by viewBinding(DialogFragmentErrorBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnOk.setOnClickListener {
            dismiss()
        }
    }

    companion object {
        const val TAG = "NotinoErrorDialog"
    }
}
