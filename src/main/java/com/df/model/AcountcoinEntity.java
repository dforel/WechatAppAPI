package com.df.model;

import javax.persistence.*;

/**
 * Created by Administrator on 2017-08-06.
 */
@Entity
@Table(name = "acountcoin", schema = "apisale_account", catalog = "")
public class AcountcoinEntity {
    private long userid;
    private int acoin;

    @Id
    @Column(name = "userid")
    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    @Basic
    @Column(name = "acoin")
    public int getAcoin() {
        return acoin;
    }

    public void setAcoin(int acoin) {
        this.acoin = acoin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AcountcoinEntity that = (AcountcoinEntity) o;

        if (userid != that.userid) return false;
        if (acoin != that.acoin) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (userid ^ (userid >>> 32));
        result = 31 * result + acoin;
        return result;
    }
}
