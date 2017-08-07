package com.df.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Administrator on 2017-08-06.
 */
@Entity
@Table(name = "order", schema = "apisale_account", catalog = "")
public class OrderEntity {
    private long oid;
    private Timestamp date;
    private byte ostatus;
    private int sid;
    private long uid;
    private long price;
    private String back;

    @Id
    @Column(name = "oid")
    public long getOid() {
        return oid;
    }

    public void setOid(long oid) {
        this.oid = oid;
    }

    @Basic
    @Column(name = "date")
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Basic
    @Column(name = "ostatus")
    public byte getOstatus() {
        return ostatus;
    }

    public void setOstatus(byte ostatus) {
        this.ostatus = ostatus;
    }

    @Basic
    @Column(name = "sid")
    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    @Basic
    @Column(name = "uid")
    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "price")
    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    @Basic
    @Column(name = "back")
    public String getBack() {
        return back;
    }

    public void setBack(String back) {
        this.back = back;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderEntity that = (OrderEntity) o;

        if (oid != that.oid) return false;
        if (ostatus != that.ostatus) return false;
        if (sid != that.sid) return false;
        if (uid != that.uid) return false;
        if (price != that.price) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (back != null ? !back.equals(that.back) : that.back != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (oid ^ (oid >>> 32));
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (int) ostatus;
        result = 31 * result + sid;
        result = 31 * result + (int) (uid ^ (uid >>> 32));
        result = 31 * result + (int) (price ^ (price >>> 32));
        result = 31 * result + (back != null ? back.hashCode() : 0);
        return result;
    }
}
