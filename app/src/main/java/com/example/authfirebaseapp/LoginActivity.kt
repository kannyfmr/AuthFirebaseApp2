package com.example.authfirebaseapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private var mAUth : FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAUth = FirebaseAuth.getInstance()



        logBtn.setOnClickListener {
            val email = logEmail.text.toString().trim()
            val password = logPass.text.toString().trim()
            loginUser(email, password)
        }
        regView.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loginUser(email : String, password : String) {
        mAUth!!.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful){
                    Toast.makeText(this, "Login berhasil", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, DashboardActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Login gagal", Toast.LENGTH_LONG).show()

                }
            }
    }
}
