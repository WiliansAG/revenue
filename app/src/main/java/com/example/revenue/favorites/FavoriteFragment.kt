package com.example.revenue.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.revenue.R
import com.example.revenue.base.BaseFragment
import com.example.revenue.data.models.RevenueModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class FavoriteFragment : BaseFragment() {

    var rcv:RecyclerView?=null
    var rcvAdapter:FavoriteAdapter?=null
    var list:ArrayList<RevenueModel>?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list = ArrayList()
        rcv = view.findViewById(R.id.frg_favorite_rcv)
        rcvAdapter = FavoriteAdapter(ArrayList<RevenueModel>())
        rcv?.layoutManager = GridLayoutManager(context, 2)
        rcv?.adapter = rcvAdapter
        getFavorites()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun showError(messageRes: Int) {
        TODO("Not yet implemented")
    }

    fun getFavorites(){
        val ref = FirebaseDatabase.getInstance().getReference("recipes")
        val id = ref.push().key

        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(l in snapshot.children){
                        val itemDB = l.getValue(RevenueModel::class.java)
                        list?.add(itemDB!!)
                    }
                    rcvAdapter?.update(list!!)
                }
            }

        })
    }

}