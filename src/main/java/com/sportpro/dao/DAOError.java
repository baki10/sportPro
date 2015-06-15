package com.sportpro.dao;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOError extends RuntimeException {
    private String title;

    public String getTitle() {
        return title;
    }

    public DAOError(String title, String message) {
        super(message);
        this.title = title;
        Logger.getLogger(DAOError.class.getName()).log(Level.SEVERE, title +"\n"+ message);
    }
    public DAOError(String title, String message, Exception ex) {
        super(message, ex);
        this.title = title;
        Logger.getLogger(DAOError.class.getName()).log(Level.SEVERE, "DAOError из-за",ex);
    }

}
