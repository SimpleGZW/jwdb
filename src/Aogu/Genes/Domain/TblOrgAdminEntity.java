package Aogu.Genes.Domain;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/8/23.
 */
@Entity
@Table(name = "tbl_org_admin", schema = "jwdb")
public class TblOrgAdminEntity {
    private int adminid;
    private String adminname;
    private String password;
    private String name;
    private String tel;
    private String valid;
    private String stand;

    @Id
    @GeneratedValue
    @Column(name = "adminid", nullable = false)
    public int getAdminid() {
        return adminid;
    }

    public void setAdminid(int adminid) {
        this.adminid = adminid;
    }

    @Basic
    @Column(name = "adminname", nullable = false, length = 64)
    public String getAdminname() {
        return adminname;
    }

    public void setAdminname(String adminname) {
        this.adminname = adminname;
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
    @Column(name = "name", nullable = true, length = 64)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    @Column(name = "valid", nullable = true, length = 4)
    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
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

        TblOrgAdminEntity that = (TblOrgAdminEntity) o;

        if (adminid != that.adminid) return false;
        if (adminname != null ? !adminname.equals(that.adminname) : that.adminname != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (tel != null ? !tel.equals(that.tel) : that.tel != null) return false;
        if (valid != null ? !valid.equals(that.valid) : that.valid != null) return false;
        if (stand != null ? !stand.equals(that.stand) : that.stand != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = adminid;
        result = 31 * result + (adminname != null ? adminname.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (tel != null ? tel.hashCode() : 0);
        result = 31 * result + (valid != null ? valid.hashCode() : 0);
        result = 31 * result + (stand != null ? stand.hashCode() : 0);
        return result;
    }
}
