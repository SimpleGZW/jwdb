package Aogu.Genes.Domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by Administrator on 2016/8/23.
 */
@Entity
@Table(name = "tbl_org_user", schema = "jwdb")
public class TblOrgUserEntity {
    private int userid;
    private String username;
    private String password;
    private String name;
    private String company;
    private String tel;
    private String address;
    private String agent;
    private String invoicetitle;
    private String valid;
    private Timestamp date;
    private String stand;
    private Collection<TblOrderDetailsEntity> orderDetailsesByUserid;
    private TblOrgLabEntity labByLabid;
    @Id
    @GeneratedValue
    @Column(name = "userid", nullable = false)
    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    @Basic
    @Column(name = "username", nullable = false, length = 64)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 64)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 64)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "company", nullable = false, length = 64)
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Basic
    @Column(name = "tel", nullable = false, length = 64)
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Basic
    @Column(name = "address", nullable = false, length = 64)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "agent", nullable = true, length = 64)
    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    @Basic
    @Column(name = "invoicetitle", nullable = true, length = 64)
    public String getInvoicetitle() {
        return invoicetitle;
    }

    public void setInvoicetitle(String invoicetitle) {
        this.invoicetitle = invoicetitle;
    }

    @Basic
    @Column(name = "valid", nullable = false, length = 4)
    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
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

        TblOrgUserEntity that = (TblOrgUserEntity) o;

        if (userid != that.userid) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (company != null ? !company.equals(that.company) : that.company != null) return false;
        if (tel != null ? !tel.equals(that.tel) : that.tel != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (agent != null ? !agent.equals(that.agent) : that.agent != null) return false;
        if (invoicetitle != null ? !invoicetitle.equals(that.invoicetitle) : that.invoicetitle != null) return false;
        if (valid != null ? !valid.equals(that.valid) : that.valid != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (stand != null ? !stand.equals(that.stand) : that.stand != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userid;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (company != null ? company.hashCode() : 0);
        result = 31 * result + (tel != null ? tel.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (agent != null ? agent.hashCode() : 0);
        result = 31 * result + (invoicetitle != null ? invoicetitle.hashCode() : 0);
        result = 31 * result + (valid != null ? valid.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (stand != null ? stand.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "optinfo")
    public Collection<TblOrderDetailsEntity> getOrderDetailsesByUserid() {
        return orderDetailsesByUserid;
    }

    public void setOrderDetailsesByUserid(Collection<TblOrderDetailsEntity> orderDetailsesByUserid) {
        this.orderDetailsesByUserid = orderDetailsesByUserid;
    }

    @ManyToOne
    @JoinColumn(name = "labid", referencedColumnName = "labid")
    public TblOrgLabEntity getLabByLabid() {
        return labByLabid;
    }

    public void setLabByLabid(TblOrgLabEntity labByLabid) {
        this.labByLabid = labByLabid;
    }
}
