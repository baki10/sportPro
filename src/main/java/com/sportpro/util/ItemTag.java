/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportpro.util;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class ItemTag extends SimpleTagSupport {

    private String img;
    private int price;

    public void setImg(String img) {
        this.img = img;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        out.println("<div class=\"col-sm-4 col-lg-4 col-md-4\"><div class=\"thumbnail\">");
        out.println("<img src=\"" + img + "\" >");
        out.println("<div style=\"text-align: right; color: red;\">");
        out.println("<h4>" + price + "<span style=\"font-size: medium\" class=\"glyphicon glyphicon-rub\"></span></h4>");
        out.println("</div>");
        out.print("<h4>");
        getJspBody().invoke(out);
        out.println("</h4>");
        out.println("</div></div>");
    }
}
