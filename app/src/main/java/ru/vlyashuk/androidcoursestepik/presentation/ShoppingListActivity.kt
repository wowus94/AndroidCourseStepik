package ru.vlyashuk.androidcoursestepik.presentation

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputLayout
import ru.vlyashuk.androidcoursestepik.databinding.ActivityShoppingListBinding
import ru.vlyashuk.androidcoursestepik.presentation.adapters.ShopListRecyclerViewAdapter
import ru.vlyashuk.androidcoursestepik.presentation.viewmodels.MainViewModel

class ShoppingListActivity : AppCompatActivity() {

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
            val intent = ShopItemActivity.newIntentAddMode(this)
            startActivity(intent)
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
            val intent = ShopItemActivity.newIntentEditMode(this, it.id)
            startActivity(intent)
        }
    }

    private fun setupOnLongClickListener() {
        shopListAdapter.onShopItemLongClickListener = {
            mainViewModel.changeEnabledState(it)
        }
    }

}