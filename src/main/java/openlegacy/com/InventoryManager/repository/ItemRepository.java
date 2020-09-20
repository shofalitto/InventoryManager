package openlegacy.com.InventoryManager.repository;

import openlegacy.com.InventoryManager.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository <Item,Long> {
}
