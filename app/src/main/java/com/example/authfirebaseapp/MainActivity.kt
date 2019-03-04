package com.example.authfirebaseapp

import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mAuth : FirebaseAuth
    private var mDialog : ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()
        mDialog = ProgressDialog(this)

        logView.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        regBtn.setOnClickListener {

            val email = regEmail.text.toString().trim()
            val password = regPass.text.toString().trim()
            daftarUser(email, password)
        }

    }
    private fun daftarUser(email : String, password : String){
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful){
                    Toast.makeText(this, "berhasil", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "gagal", Toast.LENGTH_LONG).show()
                }
            }
    }
}
