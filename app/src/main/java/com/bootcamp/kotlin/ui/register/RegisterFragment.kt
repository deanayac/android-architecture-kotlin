package com.bootcamp.kotlin.ui.register

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bootcamp.kotlin.R
import com.bootcamp.kotlin.databinding.FragmentRegisterBinding
import com.bootcamp.kotlin.databinding.ViewProgressBarBinding
import com.bootcamp.kotlin.ui.main.MainActivity
import com.bootcamp.kotlin.ui.movies.HomeFragment
import com.bootcamp.kotlin.util.showMessage
import com.bootcamp.kotlin.util.startActivity
import org.koin.android.scope.lifecycleScope
import org.koin.android.viewmodel.scope.viewModel
import java.util.*

class RegisterFragment : Fragment(), View.OnClickListener {

    private var listener: ActionListener? = null
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var loadingBinding: ViewProgressBarBinding
    private val viewModel: RegisterViewModel by lifecycleScope.viewModel(this)

    interface ActionListener {
        fun navigateToHome()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        loadingBinding = ViewProgressBarBinding.bind(binding.root)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.saveUserButton.setOnClickListener(this)
        viewModel.model.observe(this, Observer(::updateUi))
    }

    private fun updateUi(model: RegisterUiModel) {
        loadingBinding.progress.visibility =
            if (model is RegisterUiModel.Loading) View.VISIBLE else View.GONE

        when (model) {
            is RegisterUiModel.CheckUser -> {
                if (model.getPreferencesExists.isEmpty()) {
                    context?.showMessage(getString(R.string.message_not_found_user))
                    return
                }
                context?.startActivity<MainActivity>()
            }
        }
    }

    override fun onClick(v: View?) {
        if (binding.nameUserEditText.text.isNullOrEmpty()) {
            context?.showMessage(getString(R.string.enter_user_name))
            return
        }
        viewModel.saveUserName(binding.nameUserEditText.text.toString())
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ActionListener) {
            listener = context
        }
    }
}