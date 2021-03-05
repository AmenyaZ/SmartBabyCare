package com.example.smartbabycare.AddingChild

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.smartbabycare.R
import com.example.smartbabycare.databinding.FragmentAddingChildBinding


class AddingChildFragment : Fragment() {
    private lateinit var addingChildBinding: FragmentAddingChildBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        addingChildBinding = FragmentAddingChildBinding.inflate(inflater, container, false)
        val root: View = addingChildBinding.root


        return root
    }

}