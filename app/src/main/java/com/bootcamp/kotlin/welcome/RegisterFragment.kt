package com.bootcamp.kotlin.welcome

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bootcamp.kotlin.R
import com.bootcamp.kotlin.base.Constants
import com.bootcamp.kotlin.util.put
import com.bootcamp.kotlin.util.showMessage
import kotlinx.android.synthetic.main.fragment_register.view.*
import kotlinx.android.synthetic.main.fragment_register.view.nameUserEditText

class RegisterFragment : Fragment() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context?.let {
            sharedPreferences = it.getSharedPreferences(Constants.PREF_NAME,Constants.PRIVATE_MODE)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.saveUserButton.setOnClickListener {
            if(validateInput(view)){
                val intent = Intent(activity,MainActivity::class.java)
                startActivity(intent).let {
                    sharedPreferences.put(Constants.USER_NAME,view.nameUserEditText.text.toString())
                }
            }else{
                activity?.showMessage(Constants.validateNameUser)
            }

        }
    }

    private fun validateInput(view: View):Boolean{
        return view.nameUserEditText.text!=null && view.nameUserEditText.text.toString()!=""
    }
}
