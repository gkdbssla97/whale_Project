package whale.whale_Project.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import whale.whale_Project.domain.Result;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ResultRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Result result) {
        if (result.getId() == null) {
            em.persist(result);
        } else {
            em.merge(result);
        }
    }

    public Result findOne(Long id) {
        return em.find(Result.class, id);
    }

    public List<Result> findAll() {
        return em.createQuery("select r from Result r", Result.class)
                .getResultList();
    }
}
