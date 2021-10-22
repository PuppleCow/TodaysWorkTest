package com.pupplecow.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.pupplecow.myapplication.R
import com.pupplecow.myapplication.databinding.ActivityManagerNavBinding
import com.pupplecow.myapplication.ui.manager.announcement.ManagerAnnouncementListFragment
import com.pupplecow.myapplication.ui.manager.home.ManagerHomeFragment
import com.pupplecow.myapplication.ui.manager.settings.ManagerSettingFragment
import com.pupplecow.myapplication.ui.shopping.ShoppingFragment
import kotlinx.android.synthetic.main.activity_manager_nav.*




@Suppress("DEPRECATION")
class ManagerNavActivity : AppCompatActivity(),
    BottomNavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityManagerNavBinding
    private lateinit var managerHomeFragment: ManagerHomeFragment
    private lateinit var managerAnnouncementListFragment: ManagerAnnouncementListFragment

    //private lateinit var settingCheckSafetyManualFragment: SettingCheckSafetyManualFragment
    private lateinit var managerSettingFragment: ManagerSettingFragment
    private lateinit var shoppingFragment: ShoppingFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manager_nav)
        binding = ActivityManagerNavBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.managerNavBottomVanBar.setOnNavigationItemSelectedListener(this)
        binding.managerNavBottomVanBar.selectedItemId = R.id.manager_navbar_home
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            R.id.manager_navbar_shopping -> {
                //supportFragmentManager?.popBackStack()
                shoppingFragment = ShoppingFragment.newInstance()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.manager_nav_frame, shoppingFragment).commit()


            }
            R.id.manager_navbar_home -> {
                //supportFragmentManager?.popBackStack()
                managerHomeFragment = ManagerHomeFragment.newInstance()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.manager_nav_frame, managerHomeFragment).commit()


            }
            R.id.manager_navbar_safety_manual -> {
                //supportFragmentManager?.popBackStack()
                //settingCheckSafetyManualFragment= SettingCheckSafetyManualFragment.newInstance()
                //supportFragmentManager.beginTransaction().replace(R.id.manager_nav_frame,settingCheckSafetyManualFragment).commit()


            }

            R.id.manager_navbar_announcement -> {
                //supportFragmentManager?.popBackStack()
                managerAnnouncementListFragment = ManagerAnnouncementListFragment.newInstance()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.manager_nav_frame, managerAnnouncementListFragment).commit()


            }
            R.id.manager_navbar_settings -> {
                //supportFragmentManager?.popBackStack()
                managerSettingFragment = ManagerSettingFragment.newInstance()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.manager_nav_frame, managerSettingFragment).commit()


            }
        }

        return true
    }
}