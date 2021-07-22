package com.hg_studio.oneline

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao //이렇게 어노테이션을 달면 얘는 다오로 쓰임
interface UserDao {
    @Query("SELECT * FROM UserTable")
    fun getUsers(): List<User>

    //idx, name은 내가 필요한 칼럼을 의미함
    //where 뒤에 내용은 아이디랑 패스워드만 뽑아온다는 것임
    @Query("SELECT idx, id, pw, name FROM UserTable WHERE id = :id AND pw = :pw")
    fun getUser(id: String, pw:String): User? //정보를 빼올 땐 그 데이터타입을 알아야함

    @Insert //데이터를 넣는 녀석
    fun insertUser(user: User)

    @Query("DELETE FROM UserTable") //데이터를 삭제하는 녀석
    fun deleteUser()
}