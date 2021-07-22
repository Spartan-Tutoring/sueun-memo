package com.hg_studio.oneline

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UserTable")
data class User(
    val id: String,
    val pw: String,
    val name: String
    //필요하면 더 추가해주면 됨
) {
    @PrimaryKey(autoGenerate = true) var idx: Int =0  //따로 필드값으로 넣어줌
}

//autoGenerate = true -> 프라이머리키 즉 키의 값을 자동적으로 값을 증가시킴 !
//INSERT를 진행하면 알아서 키의 값을 하나 증가시키게 됨