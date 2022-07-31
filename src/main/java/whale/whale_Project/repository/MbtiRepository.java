package whale.whale_Project.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@RequiredArgsConstructor
public class MbtiRepository {

    @PersistenceContext
    private EntityManager em;



}
