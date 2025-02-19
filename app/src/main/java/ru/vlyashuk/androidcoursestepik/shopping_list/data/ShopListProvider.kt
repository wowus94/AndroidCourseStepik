package ru.vlyashuk.androidcoursestepik.shopping_list.data

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import ru.vlyashuk.androidcoursestepik.MainApp
import javax.inject.Inject

class ShopListProvider : ContentProvider() {

    private val component by lazy {
        (context as MainApp).componentShoppingList
    }

    @Inject
    lateinit var shopListDao: ShopListDao

    private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
        addURI(
            "ru.vlyashuk.androidcoursestepik.shopping_list",
            "shop_items",
            GET_SHOP_ITEMS_QUERY
        )
    }

    override fun onCreate(): Boolean {
        component.inject(this)
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        val code = uriMatcher.match(uri)
        return when (code) {
            GET_SHOP_ITEMS_QUERY -> {
                shopListDao.getShopListCursor()
            }
            else -> {
                return null
            }
        }
    }

    override fun getType(uri: Uri): String? {
        TODO("Not yet implemented")
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        TODO("Not yet implemented")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        TODO("Not yet implemented")
    }

    companion object {

        private const val GET_SHOP_ITEMS_QUERY = 100
    }
}