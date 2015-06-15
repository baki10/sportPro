/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportpro.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ilmir
 */
@Embeddable
public class ItemPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "item_no")
    private int itemNo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "item_size")
    private String itemSize;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "color")
    private String color;

    public ItemPK() {
    }

    public ItemPK(int itemNo, String itemSize, String color) {
        this.itemNo = itemNo;
        this.itemSize = itemSize;
        this.color = color;
    }

    public int getItemNo() {
        return itemNo;
    }

    public void setItemNo(int itemNo) {
        this.itemNo = itemNo;
    }

    public String getItemSize() {
        return itemSize;
    }

    public void setItemSize(String itemSize) {
        this.itemSize = itemSize;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) itemNo;
        hash += (itemSize != null ? itemSize.hashCode() : 0);
        hash += (color != null ? color.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemPK)) {
            return false;
        }
        ItemPK other = (ItemPK) object;
        if (this.itemNo != other.itemNo) {
            return false;
        }
        if ((this.itemSize == null && other.itemSize != null) || (this.itemSize != null && !this.itemSize.equals(other.itemSize))) {
            return false;
        }
        if ((this.color == null && other.color != null) || (this.color != null && !this.color.equals(other.color))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sportpro.entity.ItemPK[ itemNo=" + itemNo + ", itemSize=" + itemSize + ", color=" + color + " ]";
    }
    
}
