package com.example.revenue.navigation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.revenue.R
import com.example.revenue.base.BaseActivity
import com.example.revenue.data.models.RevenueModel
import com.example.revenue.favorites.FavoriteFragment
import com.example.revenue.revenue.RevenueFragment
import com.example.revenue.revenue_details.RevenueDetailsPresenter
import com.example.revenue.search_result.SearchResultActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class NavigationActivity : BaseActivity(),NavigationContract.View {

    var searchView:androidx.appcompat.widget.SearchView?=null
    var listR:ArrayList<RevenueModel>?=null
    var presenter: NavigationPresenter?=null

    override fun onFailureNav(message: String?) {
        Toast.makeText(applicationContext,message,Toast.LENGTH_LONG).show()
    }

    override fun onSuccessNav(list: MutableList<RevenueModel>) {
        listR?.addAll(list)
    }

    override fun showError(messageRes: Int) {
        TODO("Not yet implemented")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
        listR = ArrayList()
        presenter = NavigationPresenter(this)
        presenter?.recepts(applicationContext,"")

        val fragment = RevenueFragment()
        val favorites = FavoriteFragment()
        CurrentFragment(fragment)

        searchView = findViewById(R.id.act_navigation_search_bar)

        searchView?.setOnCloseListener(object : SearchView.OnCloseListener,
            androidx.appcompat.widget.SearchView.OnCloseListener {
            override fun onClose(): Boolean {
                presenter?.recepts(applicationContext,"")
                return false
            }

        })
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView!!.clearFocus()
                if (query != null) {
                    if(listR != null){
                        val intent = Intent(applicationContext, SearchResultActivity::class.java)
                        intent.putExtra("query",query)
                        startActivity(intent)

                    }else{
                        Toast.makeText(applicationContext,"Não foi encontrado",Toast.LENGTH_LONG).show()
                    }
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return false
            }
        })

        val bottom_navigation = findViewById<BottomNavigationView>(R.id.act_revenue_bottom_navigation)
        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.ic_home -> CurrentFragment(fragment)
                R.id.ic_favorite -> CurrentFragment(favorites)
            }
            true
        }



    }

    private fun CurrentFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.act_revenue_frame,fragment)
            commit()
        }
    }
}