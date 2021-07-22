package com.hg_studio.oneline

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class SignInActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

//        val idEtLayout:TextInputLayout = findViewById(R.id.signin_id_et_layout)
//        idEtLayout.error = "회원가이 존이재하지 않습니"
        val idEt:TextInputEditText = findViewById(R.id.signin_id_et)
        val pwEt:TextInputEditText = findViewById(R.id.signin_pw_et)

        val signUpBtn:Button = findViewById(R.id.signin_signup_btn)
        val signInBtn:Button = findViewById(R.id.signin_signin_btn)

        signUpBtn.setOnClickListener{
            startSignUpActivity()
        }

        signInBtn.setOnClickListener {
            signIn(idEt.text.toString(), pwEt.text.toString())
        }
    }

    override fun onRestart() {
        super.onRestart()
//        val nameEt: EditText = findViewById(R.id.name_name_et)
//
//        nameEt.setText("")
    }

    private fun signIn(id: String, pw: String){
        if(id.isEmpty() || pw.isEmpty()){
            Toast.makeText(this, "정보를 모두 입력해주세요.", Toast.LENGTH_SHORT).show()
            return
        }

        //위에 코드를 통과했으면 DB에 유저정보를 저장함

        val userDB: UserDB = Room.databaseBuilder(this, UserDB::class.java, "user-db").allowMainThreadQueries().build()
        val user = userDB.userDao().getUser(id, pw) //유저정보를 담고있는 엔티티를 insertUser메소드의 매개변수로 넘겨줌

        user?.let {
            val spf: SharedPreferences = getPreferences(MODE_PRIVATE)
            val editor = spf.edit()

            editor.putInt("token", user.idx)

            startMainActivity(user.name)
            finish()
            return
        }

        Toast.makeText(this, "로그인에 실패했습니다.", Toast.LENGTH_SHORT).show()
    }

    private fun startMainActivity(name: String){
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("user-name", name)
        startActivity(intent)
    }
    private fun startSignUpActivity(){
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }
}