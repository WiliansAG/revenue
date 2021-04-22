package com.example.revenue

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.NonNull
import com.example.revenue.navigation.NavigationActivity
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.facebook.FacebookException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.*


class MainActivity : AppCompatActivity() {


    var callbackManager:CallbackManager?=null
    var mFireBaseAuth:FirebaseAuth?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val GoogleLogin = findViewById<Button>(R.id.activity_main_btn_google)

        GoogleLogin.setOnClickListener {
            val intent = Intent(this, NavigationActivity::class.java)
            startActivity(intent)
        }

        callbackManager = CallbackManager.Factory.create()
        val loginFacebook = findViewById<LoginButton>(R.id.activity_main_btn_facebook)


        loginFacebook.setOnClickListener {
            loginFacebook.setReadPermissions(listOf("email"))
            loginFacebook.registerCallback(callbackManager,
                object : FacebookCallback<LoginResult> {

                    override fun onSuccess(loginResult: LoginResult) {
                        Log.d("FACEBOOK","SUCCESS" + loginResult)
                        Toast.makeText(applicationContext,"ENTROU",Toast.LENGTH_LONG).show()
                        handleFacebookToken(loginResult.accessToken)
                    }

                    override fun onCancel() {
                        Toast.makeText(applicationContext,"CANCELCOU",Toast.LENGTH_LONG).show()
                    }

                    override fun onError(exception: FacebookException) {
                        Log.d("ERRROUUUUUUUU","TA DANDO PAU" + exception)
                        Toast.makeText(applicationContext,"ERRO",Toast.LENGTH_LONG).show()
                    }
                })
        }
    }


    private fun handleFacebookToken(token:AccessToken){
        Log.d("FACEBOOK","HANDLETOKEN" + token)

        var credential:AuthCredential = FacebookAuthProvider.getCredential(token.token)
        mFireBaseAuth?.signInWithCredential(credential)?.addOnCompleteListener(this,
            object : OnCompleteListener<AuthResult>{
                override fun onComplete(@NonNull p0: Task<AuthResult>) {
                    if(p0.isSuccessful){
                        Log.d("FACEBOOK","SIGN IN SUCCESSUFUL")
                        val user: FirebaseUser? = mFireBaseAuth!!.currentUser
                        val text = findViewById<TextView>(R.id.activity_main_text)
                        text.setText(user?.email)
                        Toast.makeText(applicationContext,"LOGADO",Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(applicationContext,"ERRO",Toast.LENGTH_LONG)
                    }
                }
            })
    }

}
