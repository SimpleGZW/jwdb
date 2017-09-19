package Aogu.Genes.Repository;

import Aogu.Genes.Domain.TblOrgUserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016/8/17.
 */
@Repository
public interface OrgUserRepository extends JpaRepository<TblOrgUserEntity,Integer>,JpaSpecificationExecutor<TblOrgUserEntity> {

    @Query("select o from TblOrgUserEntity o where o.valid='0' order by o.date desc")
    public List<TblOrgUserEntity> findAllIsValid();

    @Query(value = "select o from TblOrgUserEntity o where o.valid='0'")
    public List<TblOrgUserEntity> findAllIsVlidAndLimit10(Pageable pageable);

    @Query("select o from TblOrgUserEntity o where lower(o.userid)=lower(:userid) ")
    public TblOrgUserEntity findOneById(@Param("userid") int userid);

    @Query("select o from TblOrgUserEntity o where o.valid='0'")
    public List<TblOrgUserEntity> findOneLatest(Pageable pageable);

    @Query("select o from TblOrgUserEntity o where o.username=:username")
    public TblOrgUserEntity findOneByUsername(@Param("username") String username);
}
