package com.shivam.habittrakker

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.shivam.habittrakker.intro.IntroActivity

class MainActivity : AppCompatActivity() {
    var userFirstTime=true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadData()
if(userFirstTime)
{
    userFirstTime=false
    saveData()
intent= Intent(this,IntroActivity::class.java)
startActivity(intent)
    finish()
}
        setupActionBarWithNavController(findNavController(R.id.navHostFragment))

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.navHostFragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
fun saveData(){
    val sf=getSharedPreferences("sher_pref", Context.MODE_PRIVATE)
sf.edit().putBoolean("isFirst",userFirstTime).apply()

}

fun loadData()
{
    val sf=getSharedPreferences("sher_pref", Context.MODE_PRIVATE)
    userFirstTime=sf.getBoolean("isFirst",true)

}
}
