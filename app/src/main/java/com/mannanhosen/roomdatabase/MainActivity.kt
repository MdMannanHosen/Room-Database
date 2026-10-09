package com.mannanhosen.roomdatabase

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.LogWriter
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.room.Room
import kotlinx.coroutines.launch
import java.util.Date

class MainActivity : AppCompatActivity() {

    lateinit var database: ContactDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = ContactDatabase.getDatabase(this)



        lifecycleScope.launch {
            database.contactDao().insertContact(Contact(0, "Mannan", "1234567890", Date()))
        }



    }

    fun getData(view: View) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                database.contactDao().getContacts().collect {
                    Log.d("Rahat:", it.toString())
                }
            }

        }

    }
}