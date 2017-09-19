package Aogu.Genes.Domain;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Administrator on 2016/8/23.
 */
@Entity
@Table(name = "tbl_org_lab", schema = "jwdb")
public class TblOrgLabEntity {
    private int labid;
    private String labname;
    private String labaddress;
    private String labphone;
    private String labinvoicetitle;
    private String isvalid;
    private String stand;
    private Collection<TblOrgUserEntity> usersByLabid;
    private Integer orderNum;

    @Transient
    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    @Id
    @GeneratedValue
    @Column(name = "labid", nullable = false)
    public int getLabid() {
        return labid;
    }

    public void setLabid(int labid) {
        this.labid = labid;
    }

    @Basic
    @Column(name = "labname", nullable = false, length = 64)
    public String getLabname() {
        return labname;
    }

    public void setLabname(String labname) {
        this.labname = labname;
    }

    @Basic
    @Column(name = "labaddress", nullable = false, length = 64)
    public String getLabaddress() {
        return labaddress;
    }

    public void setLabaddress(String labaddress) {
        this.labaddress = labaddress;
    }

    @Basic
    @Column(name = "labphone", nullable = false, length = 64)
    public String getLabphone() {
        return labphone;
    }

    public void setLabphone(String labphone) {
        this.labphone = labphone;
    }

    @Basic
    @Column(name = "labinvoicetitle", nullable = true, length = 64)
    public String getLabinvoicetitle() {
        return labinvoicetitle;
    }

    public void setLabinvoicetitle(String labinvoicetitle) {
        this.labinvoicetitle = labinvoicetitle;
    }

    @Basic
    @Column(name = "isvalid", nullable = false, length = 4)
    public String getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(String isvalid) {
        this.isvalid = isvalid;
    }

    @Basic
    @Column(name = "stand", nullable = true, length = 1024)
    public String getStand() {
        return stand;
    }

    public void setStand(String stand) {
        this.stand = stand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TblOrgLabEntity that = (TblOrgLabEntity) o;

        if (labid != that.labid) return false;
        if (labname != null ? !labname.equals(that.labname) : that.labname != null) return false;
        if (labaddress != null ? !labaddress.equals(that.labaddress) : that.labaddress != null) return false;
        if (labphone != null ? !labphone.equals(that.labphone) : that.labphone != null) return false;
        if (labinvoicetitle != null ? !labinvoicetitle.equals(that.labinvoicetitle) : that.labinvoicetitle != null)
            return false;
        if (isvalid != null ? !isvalid.equals(that.isvalid) : that.isvalid != null) return false;
        if (stand != null ? !stand.equals(that.stand) : that.stand != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = labid;
        result = 31 * result + (labname != null ? labname.hashCode() : 0);
        result = 31 * result + (labaddress != null ? labaddress.hashCode() : 0);
        result = 31 * result + (labphone != null ? labphone.hashCode() : 0);
        result = 31 * result + (labinvoicetitle != null ? labinvoicetitle.hashCode() : 0);
        result = 31 * result + (isvalid != null ? isvalid.hashCode() : 0);
        result = 31 * result + (stand != null ? stand.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "labByLabid")
    public Collection<TblOrgUserEntity> getUsersByLabid() {
        return usersByLabid;
    }

    public void setUsersByLabid(Collection<TblOrgUserEntity> usersByLabid) {
        this.usersByLabid = usersByLabid;
    }
}
