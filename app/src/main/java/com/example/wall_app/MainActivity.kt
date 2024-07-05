package com.example.wall_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.wall_app.Fragments.DownloadFragment
import com.example.wall_app.Fragments.HomeFragment
import com.example.wall_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(binding.root)

        replaceFragment(HomeFragment())

        binding.icHome.setOnClickListener {
            replaceFragment(HomeFragment())
        }

        binding.icDownload.setOnClickListener {
            replaceFragment(DownloadFragment())
        }
    }

    fun replaceFragment(fragment:Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentReplace, fragment)
        transaction.commit()
    }
}