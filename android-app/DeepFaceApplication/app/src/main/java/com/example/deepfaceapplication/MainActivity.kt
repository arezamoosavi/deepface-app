package com.example.deepfaceapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, 0, 0
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_history -> {
                Toast.makeText(this, "History clicked", Toast.LENGTH_SHORT).show()
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}


//curl -X POST "https://deepface-app.herokuapp.com/analyze/" -H "accept: application/json" -H "Content-Type: multipart/form-data" -F "file=@profile-photo.jpeg;type=image/jpeg"


//{
//    "file_size": 73437,
//    "prediction": {
//    "emotion": {
//    "angry": 0.014951343146365207,
//    "disgust": 0.0026594704208199736,
//    "fear": 54.735111625777286,
//    "happy": 5.547018432972618,
//    "sad": 6.066571570447496,
//    "surprise": 0.04090486130600586,
//    "neutral": 33.59277741995127
//},
//    "dominant_emotion": "fear"
//}
//}