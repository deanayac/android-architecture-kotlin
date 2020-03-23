package com.bootcamp.kotlin.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.bootcamp.kotlin.R
import com.bootcamp.kotlin.base.showToast


class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)

        activity?.showToast("Hola", Toast.LENGTH_SHORT)
    }

    companion object{
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}
