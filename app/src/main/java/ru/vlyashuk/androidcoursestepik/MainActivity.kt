package ru.vlyashuk.androidcoursestepik

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ru.vlyashuk.androidcoursestepik.broadcast_receiver_app.MainBroadcastReceiverActivity
import ru.vlyashuk.androidcoursestepik.content_provider_app.MainContentProviderActivity
import ru.vlyashuk.androidcoursestepik.coroutine_app.MainCoroutineActivity
import ru.vlyashuk.androidcoursestepik.crypto_app.presentation.CoinPriceListActivity
import ru.vlyashuk.androidcoursestepik.databinding.ActivityMainBinding
import ru.vlyashuk.androidcoursestepik.services_test_app.ServicesTestMainActivity
import ru.vlyashuk.androidcoursestepik.shopping_list.presentation.ShoppingListActivity
import ru.vlyashuk.androidcoursestepik.the_number.presentation.MainNumberActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        enableEdgeToEdge()
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.apply {
            shoppingList.setOnClickListener {
                val navigate = Intent(this@MainActivity, ShoppingListActivity::class.java)
                startActivity(navigate)
            }

            theNumber.setOnClickListener {
                val navigate = Intent(this@MainActivity, MainNumberActivity::class.java)
                startActivity(navigate)
            }

            coroutineApp.setOnClickListener {
                val navigate = Intent(this@MainActivity, MainCoroutineActivity::class.java)
                startActivity(navigate)
            }

            servicesTestApp.setOnClickListener {
                val navigate = Intent(this@MainActivity, ServicesTestMainActivity::class.java)
                startActivity(navigate)
            }

            cryptoApp.setOnClickListener {
                val navigate = Intent(this@MainActivity, CoinPriceListActivity::class.java)
                startActivity(navigate)
            }

            broadcastReceiverApp.setOnClickListener {
                val navigate = Intent(this@MainActivity, MainBroadcastReceiverActivity::class.java)
                startActivity(navigate)
            }

            contentProviderApp.setOnClickListener {
                val navigate = Intent(this@MainActivity, MainContentProviderActivity::class.java)
                startActivity(navigate)
            }
        }
    }
}