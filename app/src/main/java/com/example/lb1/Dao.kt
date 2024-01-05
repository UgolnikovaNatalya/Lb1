package com.example.lb1

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.DeleteTable
import androidx.room.Insert
import androidx.room.Query
import com.example.lb1.StudTab
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Insert
    fun insertStudent(studTab: StudTab)

    @Query("SELECT * FROM students ORDER BY age")
//    fun getAllItem(): Flow<List<StudTab>>
    fun getAllItem() : List<StudTab>

    @Query("DELETE FROM students")
    fun clearTable()

}