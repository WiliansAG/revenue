package com.example.revenue.revenue_details

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Typeface
import android.media.Image
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.revenue.R
import com.example.revenue.base.BaseActivity
import com.example.revenue.data.models.RevenueModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso


class RevenueDetailsActivity: BaseActivity(), RevenueDetailsContract.View {

    var rcv: RecyclerView?=null
    var rcvAdapter: RevenueDAdapter?=null
    var rcvMoreAdapter: RevenueMAdapter?=null
    var rcvMore: RecyclerView?=null
    var linearLayoutManager:LinearLayoutManager?=null
    var banner: ImageView?=null
    var recipeName: TextView?=null
    var presenter:RevenueDetailsPresenter?=null
    var share:ImageView?=null
    var favorite:ImageView?=null
    var list:RevenueModel?=null
    var back:ImageView?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_revenue_details)
        list = intent.getSerializableExtra("bundle") as RevenueModel
        var ingredients = list?.ingredients?.split(",")

        back = findViewById(R.id.act_revenue_detail_toolbar_back)
        banner = findViewById(R.id.act_revenue_details_image)
        recipeName = findViewById(R.id.act_revenue_details_recipe_name)
        share = findViewById(R.id.act_revenue_detail_share)
        favorite = findViewById(R.id.act_revenue_detail_favorite)
        presenter = RevenueDetailsPresenter(this)

        back?.setOnClickListener { onBackPressed() }
        Picasso.get()
            .load(list?.thumbnail)
            .placeholder(R.color.black)
            .fit()
            .into(banner)
        recipeName?.setTypeface(null,Typeface.BOLD)
        recipeName?.setText(list?.title)
        share?.setOnClickListener {
            val intent = Intent()
            intent.action=Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT,"Hey Check out this Great app:")
            intent.type="text/plain"
            startActivity(Intent.createChooser(intent,"Share To:"))
        }
        val pref = applicationContext.getSharedPreferences(
            "MyPref",
            Context.MODE_PRIVATE
        )

        favorite?.setOnClickListener {
            val ref = FirebaseDatabase.getInstance().getReference("recipes")
            val id = ref.push().key
            ref.child(id!!).setValue(list).addOnCompleteListener{
                Toast.makeText(applicationContext,"Saved",Toast.LENGTH_LONG).show()
            }
            //verifyFavorite()
        }


        rcv = findViewById(R.id.act_revenue_details_rcv_ingredients)
        rcvAdapter = RevenueDAdapter(this, ArrayList<String>())
        rcv?.adapter = rcvAdapter
        linearLayoutManager = LinearLayoutManager(applicationContext)
        rcv?.layoutManager = linearLayoutManager
        rcvAdapter?.update(ingredients!!)

        rcvMoreAdapter = RevenueMAdapter(ArrayList<RevenueModel>())
        rcvMore = findViewById(R.id.act_revenue_details_more_rcv)
        rcvMore?.layoutManager = GridLayoutManager(this, 2)
        rcvMore?.adapter = rcvMoreAdapter

        presenter?.recepts(this)
    }

    override fun onFailure(message: String?) {
        TODO("Not yet implemented")
    }

    override fun onSucess(list: MutableList<RevenueModel>) {
        rcvMoreAdapter?.update(list)
    }

    override fun showError(messageRes: Int) {
        TODO("Not yet implemented")
    }

    fun verifyFavorite(){
        val ref = FirebaseDatabase.getInstance().getReference("recipes")
        val id = ref.push().key

        ref.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(l in snapshot.children){
                        val itemDB = l.getValue(RevenueModel::class.java)
                        if(itemDB?.title == list?.title){
                            ref.child(id!!).setValue(null).addOnCompleteListener {
                                Toast.makeText(applicationContext,"REMOVIDO",Toast.LENGTH_LONG).show()
                            }
                        }
                    }
                }else{
                    ref.child(id!!).setValue(list).addOnCompleteListener{
                        Toast.makeText(applicationContext,"Saved",Toast.LENGTH_LONG).show()
                    }
                }
            }

        })
    }
}