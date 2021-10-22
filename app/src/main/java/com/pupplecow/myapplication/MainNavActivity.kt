package com.pupplecow.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.pupplecow.myapplication.databinding.ActivityMainNavBinding
import com.pupplecow.myapplication.ui.worker.announcement.AnnouncementListFragment
import com.pupplecow.myapplication.ui.worker.home.HomeFragment
import com.pupplecow.myapplication.ui.worker.settings.SettingFragment
import com.pupplecow.myapplication.ui.shopping.ShoppingFragment
import kotlinx.android.synthetic.main.activity_main_nav.*


@Suppress("DEPRECATION")
class MainNavActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener{
    private lateinit var binding: ActivityMainNavBinding

    private lateinit var homeFragment: HomeFragment
    //private lateinit var settingCheckSafetyManualFragment: SettingCheckSafetyManualFragment
    private lateinit var announcementListFragment: AnnouncementListFragment
    private lateinit var settingsFragment: SettingFragment
    private lateinit var shoppingFragment: ShoppingFragment

    private val auth= Firebase.auth
    private val database=Firebase.database

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_nav)
        binding=ActivityMainNavBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainNavBottomVanBar.setOnNavigationItemSelectedListener(this)
        binding.mainNavBottomVanBar.selectedItemId=R.id.navbar_home

        getFcmToken()
    }

    private fun getFcmToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("FCM", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            // Log and toast
            val msg = token.toString()
            Log.d("FCM", msg)
            Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()

            //토큰은 한기기에 하나(일대일 대응)
            //리얼타임데이터베이스의 루트 -> 유저s->uid -> fcm token에 msg를 setvalue
            database.reference
                .child("users")
                .child(auth.uid!!)
                .child("fcmToken")
                .setValue(
                    msg
                )
        })
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.navbar_shopping->{
                //supportFragmentManager?.popBackStack()
                shoppingFragment= ShoppingFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.main_nav_frame,shoppingFragment).commit()
            }
            R.id.navbar_home->{
                //supportFragmentManager?.popBackStack()
                homeFragment= HomeFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.main_nav_frame,homeFragment).commit()
            }
            R.id.navbar_safty_manual->{
                //supportFragmentManager?.popBackStack()
               // settingCheckSafetyManualFragment= SettingCheckSafetyManualFragment.newInstance()
               // supportFragmentManager.beginTransaction().replace(R.id.main_nav_frame,settingCheckSafetyManualFragment).addToBackStack(null).commit()

            }

            R.id.navbar_announcement->{
                //supportFragmentManager?.popBackStack()
                announcementListFragment= AnnouncementListFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.main_nav_frame,announcementListFragment).commit()


            }
            R.id.navbar_settings->{
                //supportFragmentManager?.popBackStack()
                settingsFragment= SettingFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.main_nav_frame,settingsFragment).commit()


            }
        }

        return true
    }
}