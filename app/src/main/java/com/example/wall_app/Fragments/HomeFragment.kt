package com.example.wall_app.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wall_app.Adapter.AbstractAdapter
import com.example.wall_app.Model.AbstractModel
import com.example.wall_app.databinding.FragmentHomeBinding
import com.google.firebase.firestore.FirebaseFirestore

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    lateinit var db:FirebaseFirestore


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentHomeBinding.inflate(layoutInflater, container, false)
        db = FirebaseFirestore.getInstance()


        db.collection("Abstract").addSnapshotListener { value, error ->
            val listAbstract= arrayListOf<AbstractModel>()
            val data=value?.toObjects(AbstractModel::class.java)
            listAbstract.addAll(data!!)

            binding.rcvAbstract.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            binding.rcvAbstract.adapter=AbstractAdapter(requireContext(),listAbstract)
        }

        db.collection("Nature").addSnapshotListener { value, error ->
            val listnatural= arrayListOf<AbstractModel>()
            val data=value?.toObjects(AbstractModel::class.java)
            listnatural.addAll(data!!)

            binding.rcvNature.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            binding.rcvNature.adapter=AbstractAdapter(requireContext(),listnatural)
        }
        db.collection("Arts").addSnapshotListener { value, error ->
            val listArt= arrayListOf<AbstractModel>()
            val data=value?.toObjects(AbstractModel::class.java)
            listArt.addAll(data!!)

            binding.rcvArt.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            binding.rcvArt.adapter=AbstractAdapter(requireContext(),listArt)
        }




        return binding.root

    }


}