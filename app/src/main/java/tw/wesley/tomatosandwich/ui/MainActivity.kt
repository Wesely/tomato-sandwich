package tw.wesley.tomatosandwich.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import tw.wesley.tomatosandwich.R
import tw.wesley.tomatosandwich.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize the ActivityMainBinding instance
        binding = ActivityMainBinding.inflate(layoutInflater)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragmentContainer) as NavHostFragment?
        navController = navHostFragment?.navController ?: return

        setContentView(binding.root)
    }

    override fun onSupportNavigateUp(): Boolean {
        // Use navController
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
