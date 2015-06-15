/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportpro.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author ilmir
 */
@Entity
@Table(name = "orders")
@NamedQueries({
    @NamedQuery(name = "UserOrder.findAll", query = "SELECT u FROM UserOrder u"),
    @NamedQuery(name = "UserOrder.findByOrderNo", query = "SELECT u FROM UserOrder u WHERE u.orderNo = :orderNo"),
    @NamedQuery(name = "UserOrder.findByOrderDate", query = "SELECT u FROM UserOrder u WHERE u.orderDate = :orderDate"),
    @NamedQuery(name = "UserOrder.findByCardId", query = "SELECT u FROM UserOrder u WHERE u.cardId = :cardId")})
public class UserOrder implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "order_no")
    private Long orderNo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "order_date")
    @Temporal(TemporalType.DATE)
    private Date orderDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "card_id")
    private int cardId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userOrder")
    private List<ItemOrdered> itemOrderedList;
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Address addressId;
    @JoinColumn(name = "username", referencedColumnName = "username")
    @ManyToOne(optional = false)
    private User username;

    public UserOrder() {
    }

    public UserOrder(Long orderNo) {
        this.orderNo = orderNo;
    }

    public UserOrder(Long orderNo, Date orderDate, int cardId) {
        this.orderNo = orderNo;
        this.orderDate = orderDate;
        this.cardId = cardId;
    }

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public List<ItemOrdered> getItemOrderedList() {
        return itemOrderedList;
    }

    public void setItemOrderedList(List<ItemOrdered> itemOrderedList) {
        this.itemOrderedList = itemOrderedList;
    }

    public Address getAddressId() {
        return addressId;
    }

    public void setAddressId(Address addressId) {
        this.addressId = addressId;
    }

    public User getUsername() {
        return username;
    }

    public void setUsername(User username) {
        this.username = username;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderNo != null ? orderNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserOrder)) {
            return false;
        }
        UserOrder other = (UserOrder) object;
        if ((this.orderNo == null && other.orderNo != null) || (this.orderNo != null && !this.orderNo.equals(other.orderNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sportpro.entity.UserOrder[ orderNo=" + orderNo + " ]";
    }
    
}
