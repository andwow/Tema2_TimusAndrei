package com.example.tema2_timusandrei

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadInitialFragment()
    }
    private fun loadInitialFragment(){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragment, UserFragment());
        fragmentTransaction.commit()
    }
    private fun loadAlbumFragment(userId: Int){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragment, AlbumFragment(userId));
        fragmentTransaction.commit()
    }
}