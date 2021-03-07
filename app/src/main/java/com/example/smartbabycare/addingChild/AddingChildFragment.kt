package com.example.smartbabycare.addingChild

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.smartbabycare.R
import com.example.smartbabycare.databinding.FragmentAddingChildBinding
import com.example.smartbabycare.model.Child
import com.example.smartbabycare.viewModel.sharedViewModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*


class AddingChildFragment : Fragment() {
    private lateinit var addingChildBinding: FragmentAddingChildBinding
    private lateinit var instance : Calendar
    private lateinit var childModel: sharedViewModel
    private lateinit var reference: DatabaseReference

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        addingChildBinding = FragmentAddingChildBinding.inflate(inflater, container, false)
        val root: View = addingChildBinding.root
        childModel = ViewModelProvider(requireActivity()).get(sharedViewModel::class.java)

        childModel.sharedFab.visibility = View.INVISIBLE

//        childDb = FirebaseDatabase.getInstance()
        reference = Firebase.database.getReference("ChildRecords")
        addingChildBinding.registerChildBtn.setOnClickListener {
            val name : String = addingChildBinding.etName.text.toString().trim()
            val dob : String = addingChildBinding.etDOB.text.toString().trim()
            val  gender: String = addingChildBinding.etGender.text.toString().trim()
            val pob : String = addingChildBinding.etPOB.text.toString().trim()
//            TimeUtils.

            val calenderInstance = Calendar.getInstance()
            val year = calenderInstance.get(Calendar.YEAR)
            val month = calenderInstance.get(Calendar.MONTH)
            val day = calenderInstance.get(Calendar.DAY_OF_MONTH)

//            01234567890
//            03/77/2323
            var regYear: String = dob.substring(6, 10)
            var regMonth: String = dob.substring(3, 5)
            var regDate: String = dob.substring(0, 2)

            var ageYear: Int = year - (regYear.toInt())
            var ageMonth: Int = month - (regMonth.toInt())
            var ageDay: Int = day - (regDate.toInt())

            reference = Firebase.database.getReference("ChildRecords")
            var key : String? = reference.push().key
            var child: Child = Child(name, ageYear.toString(), gender)
            reference.child(key!!).setValue(child)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        childModel.sharedFab.visibility = View.VISIBLE
    }
}