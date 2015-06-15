/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportpro.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ilmir
 */
@Entity
@Table(name = "item")
@NamedQueries({
    @NamedQuery(name = "Item.findAll", query = "SELECT i FROM Item i"),
    @NamedQuery(name = "Item.findByItemNo", query = "SELECT i FROM Item i WHERE i.itemPK.itemNo = :itemNo"),
    @NamedQuery(name = "Item.findByItemSize", query = "SELECT i FROM Item i WHERE i.itemPK.itemSize = :itemSize"),
    @NamedQuery(name = "Item.findByColor", query = "SELECT i FROM Item i WHERE i.itemPK.color = :color"),
    @NamedQuery(name = "Item.findByName", query = "SELECT i FROM Item i WHERE i.name = :name"),
    @NamedQuery(name = "Item.findByDescription", query = "SELECT i FROM Item i WHERE i.description = :description"),
    @NamedQuery(name = "Item.findByUnitsOnHand", query = "SELECT i FROM Item i WHERE i.unitsOnHand = :unitsOnHand"),
    @NamedQuery(name = "Item.findByUnitPrice", query = "SELECT i FROM Item i WHERE i.unitPrice = :unitPrice"),
    @NamedQuery(name = "Item.findByImage", query = "SELECT i FROM Item i WHERE i.image = :image"),
    @NamedQuery(name = "Item.findByCategory", query = "SELECT i FROM Item i WHERE i.categoryId.id = :categoryId ORDER BY i.unitPrice")})
public class Item implements Serializable, Comparable<Item> {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ItemPK itemPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "units_on_hand")
    private int unitsOnHand;
    @Basic(optional = false)
    @NotNull
    @Column(name = "unit_price")
    private int unitPrice;
    @Size(max = 255)
    @Column(name = "image")
    private String image;
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Category categoryId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "item")
    private List<ItemOrdered> itemOrderedList;

    public Item() {
    }

    public Item(ItemPK itemPK) {
        this.itemPK = itemPK;
    }

    public Item(ItemPK itemPK, String name, String description, int unitsOnHand, int unitPrice) {
        this.itemPK = itemPK;
        this.name = name;
        this.description = description;
        this.unitsOnHand = unitsOnHand;
        this.unitPrice = unitPrice;
    }

    public Item(int itemNo, String itemSize, String color) {
        this.itemPK = new ItemPK(itemNo, itemSize, color);
    }

    public ItemPK getItemPK() {
        return itemPK;
    }

    public void setItemPK(ItemPK itemPK) {
        this.itemPK = itemPK;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUnitsOnHand() {
        return unitsOnHand;
    }

    public void setUnitsOnHand(int unitsOnHand) {
        this.unitsOnHand = unitsOnHand;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Category getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Category categoryId) {
        this.categoryId = categoryId;
    }

    public List<ItemOrdered> getItemOrderedList() {
        return itemOrderedList;
    }

    public void setItemOrderedList(List<ItemOrdered> itemOrderedList) {
        this.itemOrderedList = itemOrderedList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemPK != null ? itemPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Item)) {
            return false;
        }
        Item other = (Item) object;
        if ((this.itemPK == null && other.itemPK != null) || (this.itemPK != null && !this.itemPK.equals(other.itemPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sportpro.entity.Item[ itemPK=" + itemPK + " ]";
    }

    @Override
    public int compareTo(Item other) {
        if (this.equals(other)) {
            return 0;
        } else if (this.itemPK.getColor().equals(other.getItemPK().getColor())) {
            if (Integer.parseInt(this.itemPK.getItemSize()) < Integer.parseInt(other.itemPK.getItemSize())) {
                return -1;
            } else {
                return 1;
            }
        } else {
            return -1;
        }
    }
}
