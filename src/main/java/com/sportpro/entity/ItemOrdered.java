/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportpro.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author ilmir
 */
@Entity
@Table(name = "item_ordered")
@NamedQueries({
    @NamedQuery(name = "ItemOrdered.findAll", query = "SELECT i FROM ItemOrdered i"),
    @NamedQuery(name = "ItemOrdered.findByOrderNo", query = "SELECT i FROM ItemOrdered i WHERE i.itemOrderedPK.orderNo = :orderNo"),
    @NamedQuery(name = "ItemOrdered.findByItemNo", query = "SELECT i FROM ItemOrdered i WHERE i.itemOrderedPK.itemNo = :itemNo"),
    @NamedQuery(name = "ItemOrdered.findByItemSize", query = "SELECT i FROM ItemOrdered i WHERE i.itemOrderedPK.itemSize = :itemSize"),
    @NamedQuery(name = "ItemOrdered.findByColor", query = "SELECT i FROM ItemOrdered i WHERE i.itemOrderedPK.color = :color"),
    @NamedQuery(name = "ItemOrdered.findByQuantityOrdered", query = "SELECT i FROM ItemOrdered i WHERE i.quantityOrdered = :quantityOrdered")})
public class ItemOrdered implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ItemOrderedPK itemOrderedPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantity_ordered")
    private int quantityOrdered;
    @JoinColumns({
        @JoinColumn(name = "item_no", referencedColumnName = "item_no", insertable = false, updatable = false),
        @JoinColumn(name = "item_size", referencedColumnName = "item_size", insertable = false, updatable = false),
        @JoinColumn(name = "color", referencedColumnName = "color", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Item item;
    @JoinColumn(name = "order_no", referencedColumnName = "order_no", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private UserOrder userOrder;

    public ItemOrdered() {
    }

    public ItemOrdered(ItemOrderedPK itemOrderedPK) {
        this.itemOrderedPK = itemOrderedPK;
    }

    public ItemOrdered(ItemOrderedPK itemOrderedPK, int quantityOrdered) {
        this.itemOrderedPK = itemOrderedPK;
        this.quantityOrdered = quantityOrdered;
    }

    public ItemOrdered(long orderNo, int itemNo, String itemSize, String color) {
        this.itemOrderedPK = new ItemOrderedPK(orderNo, itemNo, itemSize, color);
    }

    public ItemOrderedPK getItemOrderedPK() {
        return itemOrderedPK;
    }

    public void setItemOrderedPK(ItemOrderedPK itemOrderedPK) {
        this.itemOrderedPK = itemOrderedPK;
    }

    public int getQuantityOrdered() {
        return quantityOrdered;
    }

    public void setQuantityOrdered(int quantityOrdered) {
        this.quantityOrdered = quantityOrdered;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public UserOrder getUserOrder() {
        return userOrder;
    }

    public void setUserOrder(UserOrder userOrder) {
        this.userOrder = userOrder;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemOrderedPK != null ? itemOrderedPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemOrdered)) {
            return false;
        }
        ItemOrdered other = (ItemOrdered) object;
        if ((this.itemOrderedPK == null && other.itemOrderedPK != null) || (this.itemOrderedPK != null && !this.itemOrderedPK.equals(other.itemOrderedPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sportpro.entity.ItemOrdered[ itemOrderedPK=" + itemOrderedPK + " ]";
    }
    
}
