package ru.vlyashuk.androidcoursestepik.shopping_list.domain

class GetShopItemUseCase(private val shopListRepository: ShopListRepository) {

   suspend fun getShopItem(shopItemId: Int): ShopItem {
        return shopListRepository.getShopItem(shopItemId)
    }
}