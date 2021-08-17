package com.example.appjogos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.appjogos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()

        setSupportActionBar(binding.toolBar)
        setupActionBarWithNavController(navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if(item.itemId == R.id.jogo1Fragment)
            item.onNavDestinationSelected(navController) ||
                    super.onOptionsItemSelected(item)
        else if(item.itemId == R.id.jogo2Fragment){
            item.onNavDestinationSelected(navController) ||
                    super.onOptionsItemSelected(item)
        }else if(item.itemId == R.id.jogo3Fragment){
            item.onNavDestinationSelected(navController) ||
                    super.onOptionsItemSelected(item)
        }else if(item.itemId == R.id.welcomeFragment){
            item.onNavDestinationSelected(navController) ||
                    super.onOptionsItemSelected(item)
        }else
            false
    }

}
