package whale.whale_Project.repository;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class WhaleRepository {

    @PersistenceContext
    private EntityManager em;
}
