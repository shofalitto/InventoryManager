package openlegacy.com.InventoryManager.controller;

import openlegacy.com.InventoryManager.model.Item;
import openlegacy.com.InventoryManager.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/items")
    public ResponseEntity<List<Item>> getAllItems() {
        // .ok() = http response 200, .body() = contains body data
        return ResponseEntity.ok().body(itemService.getAllItems());
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable long id) {
        return ResponseEntity.ok().body(itemService.getItemById(id));
    }

    @PostMapping("/items")
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        return ResponseEntity.ok().body(this.itemService.createItem(item));
    }

    @PutMapping("/items/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable long id,@RequestBody Item item) {
        item.setId(id);
        return ResponseEntity.ok().body(this.itemService.updateItem(item));
    }

    @DeleteMapping("/items/{id}")
    public HttpStatus deleteItem(@PathVariable long id) {
        this.itemService.deleteItem(id);
        return HttpStatus.OK;

    }

    @PutMapping("/items/withdrawal/{id}/{amount}")
    public ResponseEntity<Item> withdrawalItemQuantity(@PathVariable long id, @PathVariable BigDecimal amount) {
        return ResponseEntity.ok().body(this.itemService.withdrawalItemQuantity(id,amount));
    }

    @PutMapping("/items/deposit/{id}/{amount}")
    public ResponseEntity<Item> depositItemQuantity(@PathVariable long id, @PathVariable BigDecimal amount) {
        return ResponseEntity.ok().body(this.itemService.depositItemQuantity(id, amount));
    }
}
