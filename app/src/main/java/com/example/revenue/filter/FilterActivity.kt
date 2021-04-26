package com.example.revenue.filter

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.revenue.R
import com.example.revenue.search_result.SearchResultActivity
import java.lang.StringBuilder

class FilterActivity : AppCompatActivity() {

    var rcv:RecyclerView?=null
    var linearLayout:LinearLayoutManager?=null
    var rcvAdapter:FilterAdapter?=null
    var filterList:ArrayList<String>?=null
     var selectedList:StringBuilder?=null
    var confirmFilter:ImageView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)

        confirmFilter = findViewById(R.id.act_filter_toolbar_confirm)
        filterList = ArrayList()
        selectedList = StringBuilder()
        rcv = findViewById(R.id.act_filter_rcv)
        linearLayout = LinearLayoutManager(this)
        rcvAdapter = FilterAdapter(FilterActivity(),ArrayList<String>())

        filterList?.add("onions")
        filterList?.add("garlic")
        filterList?.add("hot sauce")
        filterList?.add("anchovies")

        rcv?.layoutManager = linearLayout
        rcv?.adapter = rcvAdapter
        rcvAdapter?.update(filterList!!)
        setClick()
    }

    fun setClick(){
        confirmFilter?.setOnClickListener {
            var intent = Intent(this,SearchResultActivity::class.java)
            var teste = selectedList

            intent.putExtra("filterResult",selectedList.toString())
            startActivity(intent)
        }
    }

     fun foo(item:String){
        selectedList?.append(item+",")
    }
}