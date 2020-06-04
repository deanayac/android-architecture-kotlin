package com.bootcamp.kotlin.ui.register

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bootcamp.kotlin.R
import com.bootcamp.kotlin.databinding.FragmentRegisterBinding

import com.bootcamp.kotlin.data.source.PreferenceDataSource

import com.bootcamp.kotlin.util.showMessage

class RegisterFragment : Fragment(), RegisterContract.View, View.OnClickListener {

    private var presenter: RegisterPresenter? = null
    private var listener: ActionListener? = null
    private lateinit var binding: FragmentRegisterBinding

    interface ActionListener {
        fun navigateToHome()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*presenter = RegisterPresenter(this,
            PreferenceDataSource(activity as Application)
        )*/
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.saveUserButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        if (binding.nameUserEditText.text.isNullOrEmpty()) {
            context?.showMessage(getString(R.string.enter_user_name))
            return
        }

        presenter?.saveUserName(binding.nameUserEditText.text.toString())
    }

    override fun showMessage(message: String) {
        context?.showMessage(message)
    }

    override fun navigateToHome() {
        listener?.navigateToHome()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ActionListener) {
            listener = context as ActionListener
        }
    }
}