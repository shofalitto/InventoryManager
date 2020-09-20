package openlegacy.com.InventoryManager.service;

import openlegacy.com.InventoryManager.model.Item;

import java.math.BigDecimal;
import java.util.List;

public interface ItemService {
    Item createItem(Item item);

    Item updateItem(Item item);

    Item withdrawalItemQuantity(Long id, BigDecimal quantity);

    Item depositItemQuantity(Long id, BigDecimal quantity);

    List<Item> getAllItems();

    Item getItemById(long ItemId);

    void deleteItem(long id);
}
