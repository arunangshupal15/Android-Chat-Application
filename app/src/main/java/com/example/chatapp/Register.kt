package com.example.chatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlin.jvm.internal.Ref

class REGISTER : AppCompatActivity() {
    private lateinit var edtName: EditText
    private lateinit var edtEMAIL: EditText
    private lateinit var edtPASSWORD: EditText

    private lateinit var btnREGISTER: Button
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        supportActionBar?.hide()


        mAuth = FirebaseAuth.getInstance()
        edtName = findViewById(R.id.edt_NAME)
        edtEMAIL = findViewById(R.id.edt_EMAIL)
        edtPASSWORD = findViewById(R.id.edt_PASSWORD)
        btnREGISTER = findViewById(R.id.btnREGISTER)

        btnREGISTER.setOnClickListener {

            val Name = edtName.text.toString()
            val EMAIL = edtEMAIL.text.toString()
            val PASSWORD = edtPASSWORD.text.toString()

            register(Name,EMAIL,PASSWORD)
        }
    }

    private fun register( Name:String,EMAIL:String,PASSWORD:String ){
        mAuth.createUserWithEmailAndPassword(EMAIL, PASSWORD)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    addUserToDatabase( Name,EMAIL, mAuth.currentUser?.uid!!)
                    val intent = Intent(this@REGISTER, LOGIN::class.java)
                    startActivity(intent)
                    Toast.makeText(this@REGISTER, "REGISTRATION SUCCESSFUL", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(this@REGISTER, "error occurred", Toast.LENGTH_SHORT).show()

                }
            }

    }
        private fun addUserToDatabase(Name: String, EMAIL: String, uid: String) {
            mDbRef=FirebaseDatabase.getInstance().reference
            mDbRef.child("user").child(uid).setValue(User(Name,EMAIL,uid))
    }
}