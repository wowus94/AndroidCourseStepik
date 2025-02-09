package ru.vlyashuk.androidcoursestepik.presentation

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import ru.vlyashuk.androidcoursestepik.R
import ru.vlyashuk.androidcoursestepik.databinding.ActivityShoppingListBinding
import ru.vlyashuk.androidcoursestepik.presentation.adapters.ShopListRecyclerViewAdapter
import ru.vlyashuk.androidcoursestepik.presentation.viewmodels.MainViewModel

class ShoppingListActivity : AppCompatActivity(),
    ShopItemFragment.OnShopItemEditingFinishedListener {

    private lateinit var binding: ActivityShoppingListBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var shopListAdapter: ShopListRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setupRecyclerViewAdapter()
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
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
                mainViewModel.deleteShopItem(shopItem)
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