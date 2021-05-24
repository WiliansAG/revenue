package com.example.revenue

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.core.widget.doOnTextChanged
import com.example.revenue.data.models.RevenueModel
import com.example.revenue.data.models.UserModel
import com.example.revenue.navigation.NavigationActivity
import com.example.revenue.search_result.SearchResultActivity
import com.example.revenue.utils.PreferencesManeger
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.facebook.FacebookException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class MainActivity : AppCompatActivity() {

    private var email:TextInputEditText?=null
    private var password:TextInputEditText?=null
    private var loggin:MaterialButton?=null
    private var pref:PreferencesManeger?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        email = findViewById(R.id.activity_main_edt_email)
        password = findViewById(R.id.activity_main_edt_password)
        loggin = findViewById(R.id.acvitity_main_btn_loggin)
        pref = PreferencesManeger(applicationContext)


        if(pref?.setUser != ""){
            val intent = Intent(this, NavigationActivity::class.java)
            startActivity(intent)
        }

        loggin?.setOnClickListener{

            val ref = FirebaseDatabase.getInstance().getReference("login")
            val emailText = email?.text
            val passwordText = password?.text

            ref.child("user").get().addOnSuccessListener {
                var dataDB = it.getValue(UserModel::class.java)
                if(emailText?.toString() == dataDB?.email && passwordText?.toString() == dataDB?.password.toString()){
                    pref?.setUser = emailText.toString()
                    val intent = Intent(this, NavigationActivity::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(applicationContext,"LOGIN INVÁLIDO",Toast.LENGTH_LONG).show()
                }
            }
        }


    }


}
