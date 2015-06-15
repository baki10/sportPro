package com.sportpro.controller;

import com.sportpro.entity.Item;
import com.sportpro.entity.ItemOrdered;
import com.sportpro.entity.ItemPK;
import com.sportpro.service.ItemService;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ilmir
 */
@Controller
@Transactional
@RequestMapping("/items")
public class ItemController {

    private ItemService service;

    @Autowired
    public void setService(ItemService service) {
        this.service = service;
    }

    @RequestMapping(value = "/boots", method = RequestMethod.GET)
    public String boots(Model model) {
        model.addAttribute("itemList", service.getListByCategory(1));
        model.addAttribute("title", "Бутсы");
        return "items";
    }

    @RequestMapping(value = "/wears", method = RequestMethod.GET)
    public String wears(Model model) {
        model.addAttribute("itemList", service.getListByCategory(3));
        model.addAttribute("title", "Одежда");
        return "items";
    }

    @RequestMapping(value = "/balls", method = RequestMethod.GET)
    public String balls(Model model) {
        model.addAttribute("itemList", service.getListByCategory(2));
        model.addAttribute("title", "Мячи");
        return "items";
    }

    @RequestMapping(value = "/defense", method = RequestMethod.GET)
    public String defense(Model model) {
        model.addAttribute("itemList", service.getListByCategory(4));
        model.addAttribute("title", "Защита");
        return "items";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(Model model, @RequestParam("text") String text) {
        if (text.length() < 3) {
            model.addAttribute("msg", "Ошибка поиска");
            model.addAttribute("details", "Текст поиска должен быть больше 2-х букв");
            return "error";
        }

        List<Item> list = service.searchList(text);
        model.addAttribute("itemList", list);
        if (!list.isEmpty()) {
            model.addAttribute("listCount", "По запросу &#171;" + text + "&#187; найдено товаров:" + list.size());
        } else {
            model.addAttribute("listCount", "По запросу &#171;" + text + "&#187; ничего не найдено");
        }
        model.addAttribute("title", "Поиск");
        return "items";
    }

    @RequestMapping(value = "/{itemNo}", method = RequestMethod.GET)
    public String item(@PathVariable int itemNo, @RequestParam String color, String size,
            Model model, HttpSession session) {
        List<Item> itemList = service.getItemsByItemNoAndColor(itemNo, color);
        
        //definig item size attribute
        Set itemSize = new LinkedHashSet();
        for (Item i : itemList) {
            itemSize.add(i.getItemPK().getItemSize());
        }
        model.addAttribute("itemSize", itemSize.toArray());

        //defining item count
        for (Item i : itemList) {
            if (size == null) {
                if (color.equals(i.getItemPK().getColor())) {
                    model.addAttribute("availableItems", i.getUnitsOnHand());
                    break;
                }
            } else {
                if (size.equals(i.getItemPK().getItemSize())) {
                    model.addAttribute("availableItems", i.getUnitsOnHand());
                }
            }
        }

        model.addAttribute("itemColor", service.getAvailableColorsByItemNo(itemNo));
        model.addAttribute("itemList", itemList);
        return "item";
    }

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public String cart(Model model) {
        return "cart";
    }

    @RequestMapping(value = "/tocart", method = RequestMethod.GET)
    public String tocart(@RequestParam Integer itemNo, @RequestParam String color,
            @RequestParam String size, @RequestParam Integer count,
            HttpSession session, Model model) {

        try {
            Object obj = session.getAttribute("items");
            List<ItemOrdered> items = (obj == null) ? new ArrayList<>() : (List) obj;

            ItemPK itemPK = new ItemPK(itemNo, size, color);

            //если такой товар уже есть в корзине, вызываем обновление корзины
            ItemOrdered iOrdered = getOrderedItemFromList(itemPK, items);
            if (iOrdered != null) {
                return updateCart(itemNo, color, size, iOrdered.getQuantityOrdered() + count, session);
            }

            Item item = service.placeItemsToCart(itemPK, count);
            ItemOrdered itemOrdered = new ItemOrdered();
            itemOrdered.setItem(item);
            itemOrdered.setQuantityOrdered(count);
            items.add(itemOrdered);
            session.setAttribute("items", items);

            return "redirect:cart";
        } catch (IllegalArgumentException e) {
            model.addAttribute("msg", "Ошибка добавления в корзину");
            model.addAttribute("details", "Необходимого колличества товара нет в наличии");
            return "error";
        }
    }

    @RequestMapping(value = "/update_cart", method = RequestMethod.POST)
    public String updateItemInCart(@RequestParam Integer itemNo, @RequestParam String color,
            @RequestParam String size, @RequestParam Integer quantityOrdered,
            HttpSession session, Model model) {
        try {
            return updateCart(itemNo, color, size, quantityOrdered, session);
        } catch (IllegalArgumentException e) {
            return "redirect:cart";
        }
    }

    private String updateCart(@RequestParam Integer itemNo, @RequestParam String color,
            @RequestParam String size, @RequestParam Integer quantityOrdered,
            HttpSession session) {
        Object obj = session.getAttribute("items");
        if (obj != null) {
            List<ItemOrdered> items = (List<ItemOrdered>) obj;
            ItemOrdered io = getOrderedItemFromList(new ItemPK(itemNo, size, color), items);
            if (io != null) {
                int itemsInCart = io.getQuantityOrdered();
                quantityOrdered -= itemsInCart;
                if (quantityOrdered > 0) {
                    try {
                        Item updated = service.placeItemsToCart(io.getItem().getItemPK(), quantityOrdered);
                        io.setItem(updated);
                        io.setQuantityOrdered(io.getQuantityOrdered() + quantityOrdered);
                    } catch (IllegalArgumentException e) {
                        throw e;
                    }
                } else if (quantityOrdered < 0) {
                    Item updated = service.placeItemsBackFromCart(io.getItem().getItemPK(), quantityOrdered * (-1));
                    io.setQuantityOrdered(io.getQuantityOrdered() + quantityOrdered);
                    io.setItem(updated);
                }
            }
        }
        return "redirect:cart";
    }

    @RequestMapping(value = "/remove_cart", method = RequestMethod.POST)
    public String removeFromCart(@RequestParam Integer itemNo, @RequestParam String color,
            @RequestParam String size, HttpSession session) {

        Object obj = session.getAttribute("items");
        if (obj != null) {
            List<ItemOrdered> items = (List<ItemOrdered>) obj;
            ItemPK itemPK = new ItemPK(itemNo, size, color);

            for (int i = 0; i < items.size(); i++) {
                ItemOrdered io = items.get(i);
                if (io.getItem().getItemPK().equals(itemPK)) {
                    service.placeItemsBackFromCart(itemPK, io.getQuantityOrdered());
                    items.remove(i);
                    break;
                }
            }
            session.setAttribute("items", items);
        }
        return "redirect:cart";
    }

    @RequestMapping(value = "/buy_items", method = RequestMethod.POST)
    public String buyItem(Model model) {
        return "buy";
    }

    private ItemOrdered getOrderedItemFromList(ItemPK itemPK, List<ItemOrdered> items) {
        if (!items.isEmpty()) {
            for (ItemOrdered io : items) {
                if (io.getItem().getItemPK().equals(itemPK)) {
                    return io;
                }
            }
        }
        return null;
    }
}
