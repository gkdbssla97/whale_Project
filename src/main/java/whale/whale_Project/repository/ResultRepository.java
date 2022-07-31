package whale.whale_Project.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import whale.whale_Project.domain.MemberStatus;
import whale.whale_Project.domain.Result;
import whale.whale_Project.domain.ResultSearch;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ResultRepository {

    private final EntityManager em;

    public void save(Result result) {
        if (result.getId() == null) {
            //회원/비회원 구분 어떻게?
            result.setMemberStatus(MemberStatus.MEMBER);
            em.persist(result);
        } else {
            em.merge(result);
        }
    }

    public Result findOne(Long id) {
        return em.find(Result.class, id);
    }

    public List<Result> findAllByWhale(ResultSearch resultSearch) {
        //language = JPAQL
        String jpql = "select r From Result r join r.whale w";
        boolean isFirstCondition = true;

        //결과 고래 종류별 검색
        if (resultSearch.getWhaleType() != null) {
            if (isFirstCondition) {
                jpql += "where";
                isFirstCondition = false;
            } else {
                jpql += "and";
            }
            jpql += "r.type =: type";
        }
        /**
         * (현재 미구현 파트)
        //회원 이름별 검색
        if (StringUtils.hasText(resultSearch.getMemberName())) {
            if (isFirstCondition) {
                jpql += "where";
                isFirstCondition = false;
            } else {
                jpql += "and";
            }
            jpql += "w.type like =: type";
        }**/

        TypedQuery<Result> query = em.createQuery(jpql, Result.class)
                .setMaxResults(10);
        if (resultSearch.getWhaleType() != null) {
            query = query.setParameter("type", resultSearch.getWhaleType());
        }
        /**
        if (StringUtils.hasText(resultSearch.getMemberName())) {
            query = query.setParameter("name", resultSearch.getMemberName());
        }**/
        return query.getResultList();
    }
}