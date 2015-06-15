package com.sportpro.controller;

import com.sportpro.dao.CategoryDAO;
import com.sportpro.dao.SuperDAO;
import com.sportpro.entity.Category;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author ilmir
 */

@Controller
@Transactional
@RequestMapping("/categories")
public class CategoryController {
    
    private SuperDAO dao;

    @Autowired
    public void setDao(CategoryDAO dao) {
        this.dao = dao;
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public List<Category> categories() {
        return dao.getAll();
    }
}
