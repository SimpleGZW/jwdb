package Aogu.Genes.Domain;

import javax.persistence.*;
import java.util.List;

/**
 * Created by wuxm on 2016/8/31.
 */
@Entity
@Table(name = "tbl_order_status", schema = "jwdb")
public class TblOrderStatusEntity {
    private String orderstatus;
    private String statusdescription;

    @Id
    @Column(name = "orderstatus", nullable = false, length = 32)
    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }

    @Basic
    @Column(name = "statusdescription", nullable = true, length = 256)
    public String getStatusdescription() {
        return statusdescription;
    }

    public void setStatusdescription(String statusdescription) {
        this.statusdescription = statusdescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TblOrderStatusEntity that = (TblOrderStatusEntity) o;
        if (orderstatus != null ? !orderstatus.equals(that.orderstatus) : that.orderstatus != null) return false;
        if (statusdescription != null ? !statusdescription.equals(that.statusdescription) : that.statusdescription != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + (orderstatus != null ? orderstatus.hashCode() : 0);
        result = 31 * result + (statusdescription != null ? statusdescription.hashCode() : 0);
        return result;
    }

}
