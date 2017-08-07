package com.df.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Administrator on 2017-08-06.
 */
@Entity
@Table(name = "acountflow", schema = "apisale_account", catalog = "")
public class AcountflowEntity {
    private int id;
    private Timestamp date;
    private long userid;
    private byte type;
    private int cv;
    private int pcoin;
    private int ncoin;
    private Long orderid;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "userid")
    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    @Basic
    @Column(name = "type")
    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    @Basic
    @Column(name = "cv")
    public int getCv() {
        return cv;
    }

    public void setCv(int cv) {
        this.cv = cv;
    }

    @Basic
    @Column(name = "pcoin")
    public int getPcoin() {
        return pcoin;
    }

    public void setPcoin(int pcoin) {
        this.pcoin = pcoin;
    }

    @Basic
    @Column(name = "ncoin")
    public int getNcoin() {
        return ncoin;
    }

    public void setNcoin(int ncoin) {
        this.ncoin = ncoin;
    }

    @Basic
    @Column(name = "orderid")
    public Long getOrderid() {
        return orderid;
    }

    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AcountflowEntity that = (AcountflowEntity) o;

        if (id != that.id) return false;
        if (userid != that.userid) return false;
        if (type != that.type) return false;
        if (cv != that.cv) return false;
        if (pcoin != that.pcoin) return false;
        if (ncoin != that.ncoin) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (orderid != null ? !orderid.equals(that.orderid) : that.orderid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (int) (userid ^ (userid >>> 32));
        result = 31 * result + (int) type;
        result = 31 * result + cv;
        result = 31 * result + pcoin;
        result = 31 * result + ncoin;
        result = 31 * result + (orderid != null ? orderid.hashCode() : 0);
        return result;
    }
}
