package com.example.chatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LOGIN : AppCompatActivity() {
    private lateinit var edtEMAIL: EditText
    private lateinit var edtPASSWORD: EditText
    private lateinit var btnLOGIN: Button
    private lateinit var btnREGISTER: Button

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()
        edtEMAIL = findViewById(R.id.edt_EMAIL)
        edtPASSWORD = findViewById(R.id.edt_PASSWORD)
        btnLOGIN = findViewById(R.id.btnLOGIN)
        btnREGISTER = findViewById(R.id.btnREGISTER)

        btnREGISTER.setOnClickListener {
            val intent = Intent(this, REGISTER::class.java)
            startActivity(intent)
            Toast.makeText(this@LOGIN, "CREATE ACCOUNT", Toast.LENGTH_SHORT).show()
        }


        btnLOGIN.setOnClickListener {
            val EMAIL = edtEMAIL.text.toString()
            val PASSWORD = edtPASSWORD.text.toString()

            login(EMAIL, PASSWORD)
        }


    }

    private fun login(EMAIL: String, PASSWORD: String) {

        mAuth.signInWithEmailAndPassword(EMAIL,PASSWORD)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent=Intent(this@LOGIN,MainActivity::class.java)
                    finish()
                    startActivity(intent)

                } else {
                    Toast.makeText(this@LOGIN,"user does not exist", Toast.LENGTH_SHORT).show()
                }
            }
    }
}


