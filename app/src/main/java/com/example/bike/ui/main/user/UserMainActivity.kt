package com.example.bike.ui.main.user

import android.app.Notification
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.bike.R
import com.example.bike.databinding.ActivityUserMainBinding
import com.example.bike.ui.main.user.Fragment.HomeFragment
import com.example.bike.ui.main.user.Fragment.NotificationFragment
import com.example.bike.ui.main.user.Fragment.ProfileFragment
import com.example.bike.ui.main.user.Fragment.SettingFragment
import com.qamar.curvedbottomnaviagtion.CurvedBottomNavigation

class UserMainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
       binding = ActivityUserMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.bottomNavigation.add(
            CurvedBottomNavigation.Model(1,"Home",R.drawable.ic_bike))
        binding.bottomNavigation.add(
            CurvedBottomNavigation.Model(2,"Notification",R.drawable.ic_bike))
        binding.bottomNavigation.add(
            CurvedBottomNavigation.Model(3,"Setting",R.drawable.ic_bike))
        binding.bottomNavigation.add(
            CurvedBottomNavigation.Model(4,"Profile",R.drawable.ic_bike))

        binding.bottomNavigation.setOnClickMenuListener {
            when(it.id)
            {
                1->{
                    replaceFragment(HomeFragment())
                }
                2->{
                    replaceFragment(NotificationFragment())
                }
                3->{
                    replaceFragment(SettingFragment())
                }
                4->{
                    replaceFragment(ProfileFragment())
                }
                else->{

                }
            }
        }
        replaceFragment(HomeFragment())
        binding.bottomNavigation.show(1)
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment,fragment)
            .commit()
    }
}