package com.sportpro.service;

import com.sportpro.dao.CategoryDAO;
import com.sportpro.dao.ItemDAO;
import com.sportpro.entity.Category;
import com.sportpro.entity.Item;
import com.sportpro.entity.ItemPK;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ilmir
 */
@Service(value = "itemService")
@Transactional
public class ItemServiceImpl implements ItemService {

    private ItemDAO itemDAO;
    private CategoryDAO categoryDAO;

    @Autowired
    public void setItemDAO(ItemDAO itemDAO) {
        this.itemDAO = itemDAO;
    }

    @Autowired
    public void setCategoryDAO(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @Override
    public void createItem(Item t) {
        itemDAO.create(t);
    }

    @Override
    public Item get(ItemPK id) {
        return itemDAO.get(id);
    }

    @Override
    public void update(Item t) {
        itemDAO.update(t);
    }

    @Override
    public void delete(Item t) {
        itemDAO.delete(t);
    }

    @Override
    public List<Item> getAll() {
        return itemDAO.getAll();
    }

    @Override
    public List<Item> getListByCategory(int i) {
        List<Item> listByCategory = itemDAO.getListByCategory(i);
        return categorizedItems(listByCategory);
    }

    @Override
    public List<Item> getItemsByItemNoAndColor(int itemNo, String color) {
        List<Item> listByItemNo = itemDAO.getListByItemNo(itemNo);
        List<Item> returnList = new ArrayList<>();
        for (Item i : listByItemNo) {
            if (i.getItemPK().getColor().equals(color)) {
                returnList.add(i);
            }
        }
        Collections.sort(returnList);
        return returnList;
    }

    @Override
    public List<Item> searchList(String text) {
        List<Item> allItems = itemDAO.getAll();
        List<Item> searchItems = new ArrayList<>();
        for (Item item : allItems) {
            String name = item.getName();
            if (name.toLowerCase().contains(text.toLowerCase())) {
                searchItems.add(item);
            }
        }
        return categorizedItems(searchItems);
    }

    private List<Item> categorizedItems(List<Item> listByCategory) {
        List<Item> returnItems = new ArrayList<>();
        Set<String> colorAndItemNoSet = new LinkedHashSet<>();
        if (!listByCategory.isEmpty()) {
            for (Item item : listByCategory) {
                String color = item.getItemPK().getColor();
                Integer itemNo = item.getItemPK().getItemNo();
                if (!colorAndItemNoSet.contains(color + itemNo)) {
                    returnItems.add(item);
                    colorAndItemNoSet.add(color + itemNo);
                }
            }
        }
        return returnItems;
    }

    @Override
    @Transactional(noRollbackFor = IllegalArgumentException.class)
    public synchronized Item placeItemsToCart(ItemPK itemPK, int count) throws IllegalArgumentException {
        //subtract count from itemquantity of item table
        Item item = get(itemPK);
        int units = item.getUnitsOnHand();
        units -= count;
        if (units < 0) {
            throw new IllegalArgumentException();
        }
        
        item.setUnitsOnHand(units);
        update(item);

        return item;
    }

    @Override
    public Item placeItemsBackFromCart(ItemPK itemPK, int count) {
        Item item = get(itemPK);
        if (count > 0) {
            item.setUnitsOnHand(item.getUnitsOnHand() + count);
            update(item);
        }
        return item;
    }

    @Override
    public List<String> getAvailableColorsByItemNo(int itemNo) {
        List<String> colors = new ArrayList<>();
        List<Item> listByItemNo = itemDAO.getListByItemNo(itemNo);
        for (Item item : listByItemNo) {
            if(colors.contains(item.getItemPK().getColor())){
                continue;
            }
            colors.add(item.getItemPK().getColor());
        }
        return colors;
    }

    @Override
    public List<Item> getItemsByItemNo(int itemNo) {
        return itemDAO.getListByItemNo(itemNo);
    }

    @Override
    public int getAvailableItemNo() {
        int returnItemNo = 1;
        Set itemNoSet = new HashSet();
        List<Item> all = itemDAO.getAll();
        for (Item item : all) {
            itemNoSet.add(item.getItemPK().getItemNo());
        }
        while (itemNoSet.contains(returnItemNo)) {
            returnItemNo++;
        }
        return returnItemNo;
    }

    @Override
    public Category getCategoryById(int categoryId) {
        List<Category> all = categoryDAO.getAll();
        for (Category category : all) {
            if(category.getId().equals(categoryId)){
                return category;
            }
        }
        return null;
    }
}
