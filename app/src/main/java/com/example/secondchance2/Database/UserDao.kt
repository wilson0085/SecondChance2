package com.example.secondchance2.Database

import androidx.room.*

@Dao
interface UserDao {
    @Query("SELECT * FROM User WHERE UserID = :userId")
    fun getThisUser(userId: String): User

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User)
}

//
//
//@Dao
//interface StudentDao {
//    @Query("SELECT * FROM student_table")
//    fun getAll(): List<Student>
//
//    /* @Query("SELECT * FROM student_table WHERE uid IN (:userIds)")
//     fun loadAllByIds(userIds: IntArray): List<Student>*/
//
//    @Query("SELECT * FROM student_table WHERE roll_no LIKE :roll LIMIT 1")
//    suspend fun findByRoll(roll: Int): Student
//
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    suspend fun insert(student: Student)
//
//    @Delete
//    suspend fun delete(student: Student)
//
//    @Query("DELETE FROM student_table")
//    suspend fun deleteAll()
//}