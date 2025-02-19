package ru.vlyashuk.androidcoursestepik.shopping_list.presentation

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputLayout
import ru.vlyashuk.androidcoursestepik.MainApp
import ru.vlyashuk.androidcoursestepik.R
import ru.vlyashuk.androidcoursestepik.databinding.FragmentShopItemBinding
import ru.vlyashuk.androidcoursestepik.shopping_list.domain.ShopItem
import ru.vlyashuk.androidcoursestepik.shopping_list.presentation.viewmodels.ShopItemViewModel
import ru.vlyashuk.androidcoursestepik.shopping_list.presentation.viewmodels.ViewModelFactoryShoppingList
import javax.inject.Inject
import kotlin.concurrent.thread

class ShopItemFragment : Fragment() {

    private var _binding: FragmentShopItemBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ShopItemViewModel
    private lateinit var shopItemFinishedListener: OnShopItemEditingFinishedListener

    private var screenMode = MODE_UNKNOWN
    private var shopItemId = ShopItem.UNDEFINED_ID

    private lateinit var nameEditTextInputLayout: TextInputLayout
    private lateinit var countEditTextInputLayout: TextInputLayout
    private lateinit var nameEditText: EditText
    private lateinit var countEditText: EditText
    private lateinit var saveButton: Button

    @Inject
    lateinit var viewModelFactoryShoppingList: ViewModelFactoryShoppingList

    private val component by lazy {
        (requireActivity().application as MainApp).componentShoppingList
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
        if (context is OnShopItemEditingFinishedListener) {
            shopItemFinishedListener = context
        } else {
            throw RuntimeException("Activity must implement OnEditingFinishedListener")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseParams()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShopItemBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            ViewModelProvider(this, viewModelFactoryShoppingList)[ShopItemViewModel::class.java]
        requireActivity().findViewById<FloatingActionButton>(R.id.addShopItemButton).hide()
        initView()
        addTextChangeListeners()
        selectLaunchMode()
        observeViewModel()
    }

    private fun parseParams() {
        val args = requireArguments()
        if (!args.containsKey(SCREEN_MODE)) {
            throw RuntimeException("Param screen mode is absent")
        }
        val mode = args.getString(SCREEN_MODE)
        if (mode != MODE_EDIT && mode != MODE_ADD) {
            throw RuntimeException("Unknown screen mode $mode")
        }
        screenMode = mode
        if (screenMode == MODE_EDIT) {
            if (!args.containsKey(SHOP_ITEM_ID)) {
                throw RuntimeException("Param shop item id is absent")
            }
            shopItemId = args.getInt(SHOP_ITEM_ID, ShopItem.UNDEFINED_ID)
        }
    }

    private fun observeViewModel() {
        viewModel.errorInputName.observe(viewLifecycleOwner) {
            val message = if (it) {
                getString(R.string.error_input_name)
            } else {
                null
            }
            nameEditTextInputLayout.error = message
        }
        viewModel.errorInputCount.observe(viewLifecycleOwner) {
            val message = if (it) {
                getString(R.string.error_input_count)
            } else {
                null
            }
            countEditTextInputLayout.error = message
        }
        viewModel.closeScreen.observe(viewLifecycleOwner) {
            val isEditMode = screenMode == MODE_EDIT
            shopItemFinishedListener.shopItemEditingFinished(isEditMode)
        }
    }

    private fun addTextChangeListeners() {
        nameEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.resetErrorInputName()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
        countEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.resetErrorInputCount()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    private fun selectLaunchMode() {
        when (screenMode) {
            MODE_ADD -> launchAddMode()
            MODE_EDIT -> launchEditMode()
        }
    }

    private fun launchEditMode() {
        viewModel.getShopItem(shopItemId)
        viewModel.shopItem.observe(viewLifecycleOwner) {
            nameEditText.setText(it.name)
            countEditText.setText(it.count.toString())
        }
        saveButton.setOnClickListener {
            viewModel.editShopItem(nameEditText.text?.toString(), countEditText.text?.toString())
        }
    }

    private fun launchAddMode() {
        saveButton.setOnClickListener {
           // viewModel.addShopItem(nameEditText.text?.toString(), countEditText.text?.toString())
            thread {
                context?.contentResolver?.insert(
                    Uri.parse("content://ru.vlyashuk.androidcoursestepik.shopping_list/shop_items"),
                    ContentValues().apply {
                        put("id", 0)
                        put("name", nameEditText.text?.toString())
                        put("count", countEditText.text?.toString()?.toInt())
                        put("enabled", true)
                    }
                )
            }
        }
    }

    private fun initView() {
        nameEditTextInputLayout = binding.nameTextInputLayout
        countEditTextInputLayout = binding.countTextInputLayout
        nameEditText = binding.nameEditText
        countEditText = binding.countEditText
        saveButton = binding.saveButton
    }

    interface OnShopItemEditingFinishedListener {
        fun shopItemEditingFinished(isEditMode: Boolean)
    }

    companion object {
        private const val SCREEN_MODE = "extra_mode"
        private const val SHOP_ITEM_ID = "extra_shop_item_id"
        private const val MODE_ADD = "mode_add"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_UNKNOWN = ""

        fun newInstanceAddItem(): ShopItemFragment {
            return ShopItemFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, MODE_ADD)
                }
            }
        }

        fun newInstanceEditItem(shopItemId: Int): ShopItemFragment {
            return ShopItemFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, MODE_EDIT)
                    putInt(SHOP_ITEM_ID, shopItemId)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        requireActivity().findViewById<FloatingActionButton>(R.id.addShopItemButton).show()
        _binding = null
    }

}