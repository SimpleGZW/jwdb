package Aogu.Genes.Domain;

import javax.persistence.*;
import java.util.List;

/**
 * Created by wuxm on 2016/8/31.
 */
@Entity
@Table(name = "tbl_order_type", schema = "jwdb")
public class TblOrderTypeEntity {
    private String ordertype;
    private String typedescription;

    @Id
    @Column(name = "ordertype", nullable = false, length = 32)
    public String getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(String ordertype) {
        this.ordertype = ordertype;
    }

    @Basic
    @Column(name = "typedescription", nullable = true, length = 256)
    public String getTypedescription() {
        return typedescription;
    }

    public void setTypedescription(String typedescription) {
        this.typedescription = typedescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TblOrderTypeEntity that = (TblOrderTypeEntity) o;
        if (ordertype != null ? !ordertype.equals(that.ordertype) : that.ordertype != null) return false;
        if (typedescription != null ? !typedescription.equals(that.typedescription) : that.typedescription != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + (ordertype != null ? ordertype.hashCode() : 0);
        result = 31 * result + (typedescription != null ? typedescription.hashCode() : 0);
        return result;
    }

}
