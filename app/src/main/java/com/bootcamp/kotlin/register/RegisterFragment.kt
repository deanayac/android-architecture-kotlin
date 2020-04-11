package com.bootcamp.kotlin.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.bootcamp.kotlin.R
import com.bootcamp.kotlin.base.Constants
import com.bootcamp.kotlin.main.MainActivity
import com.bootcamp.kotlin.util.LocalRepositoryImpl
import com.bootcamp.kotlin.util.startActivity
import com.bootcamp.kotlin.util.showMessage
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : Fragment(), RegisterContract.View {

    private var presenter: RegisterPresenter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = RegisterPresenter(LocalRepositoryImpl(activity as AppCompatActivity))
        presenter?.initView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        saveUserButton.setOnClickListener {
            if (validateInput("${nameUserEditText.text}")) {
                    activity?.startActivity<MainActivity> {}.let {
                    presenter?.saveUserName("${nameUserEditText.text}")
                }
            } else {
                activity?.showMessage(Constants.validateNameUser)
            }
        }
    }

    override fun validateInput(userName: String): Boolean {
        return userName != ""
    }
}