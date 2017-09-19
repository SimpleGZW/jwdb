package Aogu.Genes.Service;
import Aogu.Genes.Domain.*;
import Aogu.Genes.Repository.OrgUserRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.List;

/**
 * Created by Administrator on 2016/8/17.
 */
@Transactional
@Service
public class OrgUserService {
    @Autowired
    private OrgUserRepository orgUserRepository;

    public void add(TblOrgUserEntity user){
        orgUserRepository.save(user);
    }

    public Page<TblOrgUserEntity> userList(PageRequest pageRequest) throws Exception{
        //特殊情况查询
        Specification<TblOrgUserEntity> userSpecification=new Specification< TblOrgUserEntity >() {
            @Override
            public Predicate toPredicate(Root<TblOrgUserEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList= Lists.newArrayList();

                Predicate predicate=null;

                //匹配属性和属性对应的值
                predicate=criteriaBuilder.equal(root.get("valid"),"0");//0代表有效，1代表无效
                //添加
                predicateList.add(criteriaBuilder.and(predicate));


                criteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
                return null;
            }
        };

        Sort sort=new Sort(Sort.Direction.fromString("desc"),"date");
        //起始，长度
        Pageable pageable=new PageRequest(pageRequest.getPageNumber(),pageRequest.getPageSize(),sort);

        Page<TblOrgUserEntity> users= null;
        try {
            users=orgUserRepository.findAll(userSpecification,pageable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users.getTotalElements()>0?users:null;
    }

//    public List<TblOrgUserEntity> userList(){
//        List<TblOrgUserEntity> users=orgUserRepository.findAllIsValid();
//        return users.size()>0?users:null;
//    }
//
    public TblOrgUserEntity findOneById(int userid){
        TblOrgUserEntity user=orgUserRepository.findOneById(userid);
        return user;
    }

    public void delete(TblOrgUserEntity user){
        orgUserRepository.save(user);
    }

    public TblOrgUserEntity findUserByUsername(String username){
        TblOrgUserEntity user=orgUserRepository.findOneByUsername(username);
        return user;
    }
}
