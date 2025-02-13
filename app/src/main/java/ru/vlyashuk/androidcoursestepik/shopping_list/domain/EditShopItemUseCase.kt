package ru.vlyashuk.androidcoursestepik.shopping_list.domain

class EditShopItemUseCase(private val shopListRepository: ShopListRepository) {

   suspend fun editShopItem(shopItem: ShopItem) {
        shopListRepository.editShopItem(shopItem)
    }
}