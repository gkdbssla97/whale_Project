package whale.whale_Project.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import whale.whale_Project.domain.Member;
import whale.whale_Project.domain.WhaleCountList;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class WhaleCountRepository {

    private final EntityManager em;
    public Long save(WhaleCountList whaleCountList) {
        em.persist(whaleCountList);
        return whaleCountList.getId();
    }
}
