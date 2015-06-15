package com.sportpro.service;

import com.sportpro.entity.Category;
import com.sportpro.entity.Item;
import com.sportpro.entity.ItemPK;
import java.util.List;

/**
 *
 * @author ilmir
 */
public interface ItemService {

    void createItem(Item t);

    void delete(Item t);

    Item get(ItemPK id);

    List<Item> getAll();

    void update(Item t);

    List<Item> getListByCategory(int i);

    List<Item> getItemsByItemNoAndColor(int itemNo,String color);

    List<Item> getItemsByItemNo(int itemNo);
    
    List<Item> searchList(String text);

    Item placeItemsToCart(ItemPK itemPK, int count) throws IllegalArgumentException;

    Item placeItemsBackFromCart(ItemPK itemPK, int count);

    List<String> getAvailableColorsByItemNo(int itemNo);

    int getAvailableItemNo();
    
    Category getCategoryById(int categoryId);
    
}
