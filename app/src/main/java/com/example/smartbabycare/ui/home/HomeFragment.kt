package com.example.smartbabycare.ui.home

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.smartbabycare.adapter.ChildAdapter
import com.example.smartbabycare.databinding.FragmentHomeBinding
import com.example.smartbabycare.model.Child
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import java.util.*

class HomeFragment : Fragment() {
    private lateinit var homeBinding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var adapter: ChildAdapter
    private lateinit var children: ArrayList<Child>
    private lateinit var childrenRef: DatabaseReference

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        homeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = homeBinding.root
        children = ArrayList()
        layoutManager = LinearLayoutManager(requireContext())
        homeBinding.childrenRecyclerView.layoutManager = layoutManager
        homeBinding.childrenRecyclerView.setHasFixedSize(true)
        val sharedPreferences: SharedPreferences = requireActivity().getSharedPreferences("Preferences", 0)
        val phoneNo : String? = sharedPreferences.getString("phone", null)
        childrenRef = FirebaseDatabase.getInstance().getReference("ChildRecords").child(phoneNo!!)
        childrenRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                children.clear()
                for (shot in snapshot.children) {
                    val retrievedChild = shot.getValue<Child>()

                    children.add(retrievedChild!!)
                }
                adapter = ChildAdapter(children, requireActivity())
                homeBinding.childrenRecyclerView.adapter = adapter
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d(TAG, "onCancelled: " + error.message)
            }
        })
        return root
    }

    companion object {
        private const val TAG = "HomeFragment"
    }
}