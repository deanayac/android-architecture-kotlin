package com.bootcamp.kotlin.register

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bootcamp.kotlin.R
import com.bootcamp.kotlin.util.AccountRepositoryImpl
import com.bootcamp.kotlin.util.showMessage
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : Fragment(), RegisterContract.View, View.OnClickListener {

    private var presenter: RegisterPresenter? = null
    private var listener: ActionListener? = null

    interface ActionListener {
        fun navigateToHome()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = RegisterPresenter(this, AccountRepositoryImpl(activity as AppCompatActivity))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        saveUserButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        if (nameUserEditText.text.isNullOrEmpty()) {
            context?.showMessage(getString(R.string.enter_user_name))
            return
        }

        presenter?.saveUserName(nameUserEditText.text.toString())
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