package com.hg_studio.oneline

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.google.android.material.textfield.TextInputEditText

class SignUpActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val idEt: TextInputEditText = findViewById(R.id.signup_id_et)
        val pwEt:TextInputEditText = findViewById(R.id.signup_pw_et)
        val pwCheckEt:TextInputEditText = findViewById(R.id.signup_pwCheck_et)
        val nameEt:TextInputEditText = findViewById(R.id.signup_name_et)

        val signUpBtn:Button = findViewById(R.id.signup_signup_btn)

        signUpBtn.setOnClickListener{
            signUp(idEt.text.toString(), pwEt.text.toString(), pwCheckEt.text.toString(), nameEt.text.toString())

            finish()
        }
    }

    private fun signUp(id:String, pw:String, pwCheckEt:String, name:String){
        if(id.isEmpty() || pw.isEmpty() || pwCheckEt.isEmpty() || name.isEmpty()){
            Toast.makeText(this, "정보를 모두 입력해주세요.", Toast.LENGTH_SHORT).show()
            return
        }

        if(pw != pwCheckEt){
            Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
            return
        }

        //위에 코드를 통과했으면 DB에 유저정보를 저장함

        val userDB: UserDB = Room.databaseBuilder(this, UserDB::class.java, "user-db").allowMainThreadQueries().build()
        val user = User(id,pw,name)

        userDB.userDao().insertUser(user) //유저정보를 담고있는 엔티티를 insertUser메소드의 매개변수로 넘겨줌

        val users = userDB.userDao().getUsers()

        for(user in users){
            Log.d("user-db", "idx: ${user.idx}, userId: ${user.id}, userPw: ${user.pw}, userName: ${user.name}")
        }
    }

    override fun onRestart() {
        super.onRestart()

//        val nameEt: EditText = findViewById(R.id.name_name_et)
//
//        nameEt.setText("")
    }
}