package Aogu.Genes.Service;
import Aogu.Genes.Domain.*;
import Aogu.Genes.Repository.LabRepository;
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
 * Created by michael on 2016/8/17 0017.
 */
@Transactional
@Service
public class LabService {
    @Autowired
    private LabRepository labRepository;
    private OrgUserRepository userRepository;

    public TblOrgLabEntity findLabByName(String labName) {
        return labRepository.findLabByName(labName);
    }

    public TblOrgLabEntity findLabById(int labId) {
        return labRepository.findLabById(labId);
    }
    public Page<TblOrgLabEntity> findLabEntitysByPage(PageRequest pageRequest) {
        Specification<TblOrgLabEntity> newsSpecification=new Specification<TblOrgLabEntity>() {
            @Override
            public Predicate toPredicate(Root<TblOrgLabEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList= Lists.newArrayList();

                Predicate predicate=null;

                //匹配属性和属性对应的值
                predicate=criteriaBuilder.equal(root.get("isvalid"),"0");
                //添加
                predicateList.add(criteriaBuilder.and(predicate));

                criteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
                return null;
            }
        };

        Sort sort=new Sort(Sort.Direction.fromString("desc"),"labname");
        //起始，长度
        //Pageable pageable=new PageRequest(pageRequest.getPageNumber(),pageRequest.getPageSize(),sort);
        Pageable pageable=new PageRequest(pageRequest.getPageNumber(),pageRequest.getPageSize(),sort);
        Page<TblOrgLabEntity> TblOrgLabEntitys= null;
        try {
            TblOrgLabEntitys=labRepository.findAll(newsSpecification,pageable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return TblOrgLabEntitys.getTotalElements()>0?TblOrgLabEntitys:null;
    }
    public List<TblOrgLabEntity> findLabEntitys() {
        return labRepository.findAllLabEntitys();
    }



    public void add(TblOrgLabEntity labEntity)
    {
        labRepository.save(labEntity);
    }

    public void delete(TblOrgLabEntity labEntity)
    {
        labRepository.delete(labEntity);
    }

    public TblOrgLabEntity findOneById(int labid){
        TblOrgLabEntity lab=labRepository.findOneById(labid);
        return lab;
    }

    public TblOrgLabEntity findOneByLabname(String labname){
        TblOrgLabEntity lab=labRepository.findOneByLabname(labname);
        return lab;
    }

}
