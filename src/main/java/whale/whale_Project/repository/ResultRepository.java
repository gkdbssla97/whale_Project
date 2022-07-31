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

    public List<Result> findAll(ResultSearch resultSearch) {
        //language = JPAQL
        String jpql = "select r From Result r join r.member m";
        boolean isFirstCondition = true;

        //결과 MBTI 종류 검색
        if (resultSearch.getMbtiType() != null) {
            if (isFirstCondition) {
                jpql += "where";
                isFirstCondition = false;
            } else {
                jpql += "and";
            }
            jpql += "r.type =: type";
        }
        //회원 이름 검색
        if (StringUtils.hasText(resultSearch.getMemberName())) {
            if (isFirstCondition) {
                jpql += "where";
                isFirstCondition = false;
            } else {
                jpql += "and";
            }
            jpql += "m.name like =: name";
        }

        TypedQuery<Result> query = em.createQuery(jpql, Result.class)
                .setMaxResults(10);
        if (resultSearch.getMbtiType() != null) {
            query = query.setParameter("type", resultSearch.getMbtiType());
        }
        if (StringUtils.hasText(resultSearch.getMemberName())) {
            query = query.setParameter("name", resultSearch.getMemberName());
        }
        return query.getResultList();
    }
}