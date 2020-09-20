package openlegacy.com.InventoryManager.service;

import openlegacy.com.InventoryManager.exception.ResourceNotFoundException;
import openlegacy.com.InventoryManager.model.Item;
import openlegacy.com.InventoryManager.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public Item updateItem(Item item) {
        Optional<Item> itemDb = this.itemRepository.findById(item.getId());

        if (itemDb.isPresent()) {
            Item itemUpdate = itemDb.get();
            itemUpdate.setId(item.getId());
            itemUpdate.setName(item.getName());
            itemUpdate.setAmount(item.getAmount());
            itemUpdate.setInventoryCode(item.getInventoryCode());
            itemRepository.save(itemUpdate);
            return itemUpdate;
        }else {
            throw new ResourceNotFoundException("Item was not found with given ID: "+item.getId());
        }
    }

    @Override
    public Item withdrawalItemQuantity(Long id, BigDecimal quantity) {
        Optional<Item> itemDb = this.itemRepository.findById(id);

        if (itemDb.isPresent()) {
            Item itemUpdate = itemDb.get();
            itemUpdate.setAmount(itemUpdate.getAmount().subtract(quantity));
            itemRepository.save(itemUpdate);
            return itemUpdate;
        }else {
            throw new ResourceNotFoundException("Item was not found with given ID: "+id);
        }
    }

    @Override
    public Item depositItemQuantity(Long id, BigDecimal quantity) {
        Optional<Item> itemDb = this.itemRepository.findById(id);

        if (itemDb.isPresent()) {
            Item itemUpdate = itemDb.get();
            itemUpdate.setAmount(itemUpdate.getAmount().add(quantity));
            itemRepository.save(itemUpdate);
            return itemUpdate;
        }else {
            throw new ResourceNotFoundException("Item was not found with given ID: "+id);
        }
    }

    @Override
    public List<Item> getAllItems() {
        return this.itemRepository.findAll();
    }

    @Override
    public Item getItemById(long ItemId) {

        Optional<Item> itemDb = this.itemRepository.findById(ItemId);

        if (itemDb.isPresent()) {
            return itemDb.get();
        }else {
            throw new ResourceNotFoundException("Item was not found with given ID: "+ItemId);
        }
    }

    @Override
    public void deleteItem(long ItemId) {

        Optional<Item> itemDb = this.itemRepository.findById(ItemId);

        if (itemDb.isPresent()) {
            this.itemRepository.delete(itemDb.get());
        }else {
            throw new ResourceNotFoundException("Item was not found with given ID: "+ItemId);
        }
    }
}
