package com.example.smartbabycare.addingChild

import android.app.DatePickerDialog
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.smartbabycare.SmartBaby
import com.example.smartbabycare.databinding.FragmentAddingChildBinding
import com.example.smartbabycare.model.Child
import com.example.smartbabycare.viewModel.sharedViewModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*


class AddingChildFragment : Fragment() {
    private lateinit var addingChildBinding: FragmentAddingChildBinding
    private lateinit var instance : Calendar
    private lateinit var childModel: sharedViewModel
    private lateinit var reference: DatabaseReference
    val myCalendar = Calendar.getInstance()
    var datePickerDialog: DatePickerDialog? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        addingChildBinding = FragmentAddingChildBinding.inflate(inflater, container, false)
        val root: View = addingChildBinding.root
        childModel = ViewModelProvider(requireActivity()).get(sharedViewModel::class.java)

        childModel.sharedFab.visibility = View.INVISIBLE






        val sharedPreferences: SharedPreferences = requireActivity().getSharedPreferences("Preferences", 0)
        val phoneNo : String? = sharedPreferences.getString("phone", null)
        reference = Firebase.database.getReference("ChildRecords").child(phoneNo!!)
        addingChildBinding.registerChildBtn.setOnClickListener {
            val name : String = addingChildBinding.etName.text.toString().trim()
            val dob : String = addingChildBinding.etDOB.text.toString().trim()
            val  gender: String = addingChildBinding.etGender.text.toString().trim()
            val pob : String = addingChildBinding.etPOB.text.toString().trim()

          /*  addingChildBinding.etDOB.setOnClickListener(View.OnClickListener {
                val year: Int = myCalendar.get(Calendar.YEAR)
                val month: Int = myCalendar.get(Calendar.MONTH)
                val day: Int = myCalendar.get(Calendar.DAY_OF_MONTH)
                datePickerDialog = DatePickerDialog(, { datePicker, i, i1, i2 -> datebcg.setText("$day/$month/$year") }, year, month, day)
                datePickerDialog.show()
            })*/

            addingChildBinding.etDOB.setOnClickListener {
                val c = Calendar.getInstance()
                val year = c.get(Calendar.YEAR)
                val month = c.get(Calendar.MONTH)
                val day = c.get(Calendar.DAY_OF_MONTH)
                val dpd = context?.let { it1 ->
                    DatePickerDialog(it1, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                        // Display Selected date in TextView
                        addingChildBinding.etDOB.setText("" + dayOfMonth + "/" + (month+1) + "/" + year)
                    }, year, month, day)
                }
                dpd?.show()

            }

            if(name.trim().isEmpty()){
                addingChildBinding.etName.error = "Name is Required"
           }
            else if(dob.trim().isEmpty()){
                addingChildBinding.etDOB.error = "Date of Birth is Required"
            }

            else if(gender.trim().isEmpty()){
                addingChildBinding.etGender.error = "Gander is Required"
            }

            else if(pob.trim().isEmpty()){
                addingChildBinding.etPOB.error = "Place Of Birth is Required"
            }
            else{
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

                if( ageYear > 5){
                    addingChildBinding.etDOB.error = "Child's Age is grater than 5 Years"
                }


                else {
//        childDb = FirebaseDatabase.getInstance()
                    /*val user = FirebaseAuth.getInstance().currentUser
                    val userid = user.uid*/
                    reference = Firebase.database.getReference("ChildRecords").child(phoneNo)
                    var key: String? = reference.push().key
                    var child: Child = Child(name, ageYear.toString(), gender, dob)
                    reference.child(key!!).setValue(child)
                    context?.startActivity(Intent(context, SmartBaby::class.java))


                }
           }




        }
        return root
    }

    /*private fun updateLabel(calenderInstance: Calendar, addingChildBinding: FragmentAddingChildBinding) {
        val myFormat: String = "dd-MMM-yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.UK)
        addingChildBinding.etDOB.setText(sdf.format(calenderInstance.time))

    }*/

    override fun onDestroyView() {
        super.onDestroyView()
        childModel.sharedFab.visibility = View.VISIBLE
    }
}