package ru.vlyashuk.androidcoursestepik.shopping_list.presentation

import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import ru.vlyashuk.androidcoursestepik.MainApp
import ru.vlyashuk.androidcoursestepik.R
import ru.vlyashuk.androidcoursestepik.databinding.ActivityShoppingListBinding
import ru.vlyashuk.androidcoursestepik.shopping_list.domain.ShopItem
import ru.vlyashuk.androidcoursestepik.shopping_list.presentation.adapters.ShopListRecyclerViewAdapter
import ru.vlyashuk.androidcoursestepik.shopping_list.presentation.viewmodels.MainViewModel
import ru.vlyashuk.androidcoursestepik.shopping_list.presentation.viewmodels.ViewModelFactoryShoppingList
import javax.inject.Inject
import kotlin.concurrent.thread

class ShoppingListActivity : AppCompatActivity(),
    ShopItemFragment.OnShopItemEditingFinishedListener {

    private lateinit var binding: ActivityShoppingListBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var shopListAdapter: ShopListRecyclerViewAdapter

    @Inject
    lateinit var viewModelFactoryShoppingList: ViewModelFactoryShoppingList

    private val component by lazy {
        (application as MainApp).componentShoppingList
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setupRecyclerViewAdapter()
        mainViewModel =
            ViewModelProvider(this, viewModelFactoryShoppingList)[MainViewModel::class.java]
        mainViewModel.shopList.observe(this) {
            shopListAdapter.submitList(it)
        }

        binding.addShopItemButton.setOnClickListener {
            if (isOnePaneMode()) {
                launchFragment(ShopItemFragment.newInstanceAddItem(), R.id.shoppingList)
            } else {
                launchFragment(
                    ShopItemFragment.newInstanceAddItem(),
                    R.id.fragmentContainerViewLand
                )
            }
        }
        thread {
            val cursor = contentResolver.query(
                Uri.parse("content://ru.vlyashuk.androidcoursestepik.shopping_list/shop_items"),
                null,
                null,
                null,
                null,
                null
            )
            while (cursor?.moveToNext() == true) {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
                val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
                val count = cursor.getInt(cursor.getColumnIndexOrThrow("count"))
                val enabled = cursor.getInt(cursor.getColumnIndexOrThrow("enabled")) > 0
                val shopItem = ShopItem(
                    id = id,
                    name = name,
                    count = count,
                    enabled = enabled
                )
                Log.i("MainActivity", "$shopItem")
            }
            cursor?.close()
        }
    }

    private fun setupRecyclerViewAdapter() {
        val shopLisRecyclerView = binding.shoppingListRecyclerView
        shopListAdapter = ShopListRecyclerViewAdapter()
        shopLisRecyclerView.adapter = shopListAdapter
        shopLisRecyclerView.apply {
            recycledViewPool.setMaxRecycledViews(
                ShopListRecyclerViewAdapter.VIEW_TYPE_ENABLED,
                ShopListRecyclerViewAdapter.MAX_POOL_SIZE
            )
            recycledViewPool.setMaxRecycledViews(
                ShopListRecyclerViewAdapter.VIEW_TYPE_DISABLED,
                ShopListRecyclerViewAdapter.MAX_POOL_SIZE
            )
        }
        setupOnLongClickListener()
        setupOnClickListener()
        setupSwipeDeleteListener(shopLisRecyclerView)
    }

    private fun setupSwipeDeleteListener(shopListRecyclerView: RecyclerView) {
        val shopItemDeleteCallback = object :
            ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val shopItem = shopListAdapter.currentList[viewHolder.adapterPosition]
                //mainViewModel.deleteShopItem(shopItem)
                thread {
                    contentResolver.delete(
                        Uri.parse("content://ru.vlyashuk.androidcoursestepik.shopping_list/shop_items"),
                        null,
                        arrayOf(shopItem.id.toString())
                    )
                }
            }
        }
        val itemTouchHelper = ItemTouchHelper(shopItemDeleteCallback)
        itemTouchHelper.attachToRecyclerView(shopListRecyclerView)
    }

    private fun setupOnClickListener() {
        shopListAdapter.onShopItemClickListener = {
            if (isOnePaneMode()) {
                launchFragment(ShopItemFragment.newInstanceEditItem(it.id), R.id.shoppingList)
            } else {
                launchFragment(
                    ShopItemFragment.newInstanceEditItem(it.id),
                    R.id.fragmentContainerViewLand
                )
            }
        }
    }

    private fun setupOnLongClickListener() {
        shopListAdapter.onShopItemLongClickListener = {
            mainViewModel.changeEnabledState(it)
        }
    }

    private fun launchFragment(fragment: Fragment, container: Int) {
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .replace(container, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun isOnePaneMode(): Boolean {
        return resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
    }

    override fun shopItemEditingFinished(isEditMode: Boolean) {
        val message = if (isEditMode) {
            getString(R.string.edit_item_saved)
        } else {
            getString(R.string.add_new_item)
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        supportFragmentManager.popBackStack()
    }
}