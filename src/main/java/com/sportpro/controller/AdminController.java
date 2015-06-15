package com.sportpro.controller;

import com.sportpro.entity.Item;
import com.sportpro.entity.ItemPK;
import com.sportpro.service.ItemService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private ItemService service;

    @Autowired
    public void setService(ItemService service) {
        this.service = service;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String admin(Model model) {
        return "redirect:admin/boots";
    }
    
    @RequestMapping(value = "/boots", method = RequestMethod.GET)
    public String boots(Model model) {
        model.addAttribute("itemList", getListByCategory(1));
        model.addAttribute("title", "Бутсы");
        model.addAttribute("categoryId", 1);
        return "admin/adminitems";
    }

    @RequestMapping(value = "/wears", method = RequestMethod.GET)
    public String wears(Model model) {
        model.addAttribute("itemList", getListByCategory(3));
        model.addAttribute("title", "Одежда");
        model.addAttribute("categoryId", 3);
        return "admin/adminitems";
    }

    @RequestMapping(value = "/balls", method = RequestMethod.GET)
    public String balls(Model model) {
        model.addAttribute("itemList", getListByCategory(2));
        model.addAttribute("title", "Мячи");
        model.addAttribute("categoryId", 2);
        return "admin/adminitems";
    }

    @RequestMapping(value = "/defense", method = RequestMethod.GET)
    public String defense(Model model) {
        model.addAttribute("itemList", getListByCategory(4));
        model.addAttribute("title", "Защита");
        model.addAttribute("categoryId", 4);
        return "admin/adminitems";
    }

    private List<Item> getListByCategory(int i) {
        List<Item> listByCategory = new ArrayList<>();
        Set<Integer> itemNoSet = new HashSet<>();
        for (Item item : service.getListByCategory(i)) {
            if (itemNoSet.contains(item.getItemPK().getItemNo())) {
                continue;
            }
            listByCategory.add(item);
            itemNoSet.add(item.getItemPK().getItemNo());
        }
        return listByCategory;
    }

    @RequestMapping(value = "/edit_items", method = RequestMethod.GET)
    public String editItems(int itemNo, Model model) {

        List<Item> itemsByItemNo = service.getItemsByItemNo(itemNo);
        model.addAttribute("itemList", itemsByItemNo);
        return "admin/adminitem";
    }

    @RequestMapping(value = "/save_items_changes", method = RequestMethod.POST)
    public String saveItems(int itemNo, Model model,
            int unitPrice, String description, String name) {

        List<Item> itemsByItemNo = service.getItemsByItemNo(itemNo);
        for (Item item : itemsByItemNo) {
            item.setName(name);
            item.setDescription(description);
            item.setUnitPrice(unitPrice);
            service.update(item);
        }

        model.addAttribute("itemList", service.getItemsByItemNo(itemNo));
        return "admin/adminitem";
    }

    @RequestMapping(value = "/save_item_changes", method = RequestMethod.POST)
    public String saveItem(int itemNo, String color, String size, Model model, int count) {

        ItemPK itemPK = new ItemPK(itemNo, size, color);
        Item item = service.get(itemPK);
        item.setUnitsOnHand(count);
        service.update(item);
        return "redirect:edit_items?itemNo=" + itemNo;
    }

    @RequestMapping(value = "/delete_item", method = RequestMethod.POST)
    public String deleteItem(int itemNo, String color, String size, Model model) {

        ItemPK itemPK = new ItemPK(itemNo, size, color);
        Item item = service.get(itemPK);
        service.delete(item);
        return "redirect:edit_items?itemNo=" + itemNo;
    }

    @RequestMapping(value = "/createItem", method = RequestMethod.POST)
    public String create(Integer itemNo, Integer categoryId) {
        return "redirect:additem?categoryId=" + categoryId + "&&itemNo=" + itemNo;
    }

    @RequestMapping(value = "/additem", method = RequestMethod.GET)
    public String addItem(Integer itemNo, Integer categoryId, Model model) {
        if (itemNo != null) {
            List<Item> itemsByItemNo = service.getItemsByItemNo(itemNo);
            if (!itemsByItemNo.isEmpty()) {
                model.addAttribute("item", itemsByItemNo.get(0));
            }
        }
        model.addAttribute("categoryId", categoryId);
        return "admin/additem";
    }

    @RequestMapping(value = "/save_new_item", method = RequestMethod.POST)
    public String saveNewItem(String name, String description, String unitPrice, String color, String size,
            Model model, Integer quantity, String image, int categoryId, Integer itemNo) {

        if (name == null || description == null || unitPrice == null || color == null || size == null || quantity == null) {
            model.addAttribute("msg", "Ошибка добавления товара");
            model.addAttribute("details", "Не хватает данных для добавления нового товара! Заполните все поля");
            return "error";
        }

        if (itemNo == null) {
            itemNo = service.getAvailableItemNo();
        }
        ItemPK itemPK = new ItemPK(itemNo, size, color);
        Item item = new Item(itemPK);
        item.setCategoryId(service.getCategoryById(categoryId));
        item.setName(name);
        item.setDescription(description);
        item.setUnitsOnHand(quantity);
        item.setUnitPrice(Integer.parseInt(unitPrice));
        item.setImage(image);

        service.createItem(item);
        return "redirect:edit_items?itemNo=" + itemNo;
    }
}
