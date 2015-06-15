package com.sportpro.util;

import com.sportpro.entity.ItemOrdered;
import com.sportpro.service.ItemService;
import java.util.List;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author ilmir
 */
public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("----------------------------------session start --------------------------------");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("----------------------------------session finish--------------------------------");
        Object obj = se.getSession().getAttribute("items");

        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(se.getSession().getServletContext());
        ItemService itemService = (ItemService) ctx.getBean("itemService");

        if (obj != null) {
            List<ItemOrdered> items = (List<ItemOrdered>) obj;
            if (!items.isEmpty()) {
                for (ItemOrdered io : items) {
                    itemService.placeItemsBackFromCart(io.getItem().getItemPK(), io.getQuantityOrdered());
                }
            }
        }
    }

}
