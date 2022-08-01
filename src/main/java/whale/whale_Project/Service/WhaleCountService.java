package whale.whale_Project.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import whale.whale_Project.domain.*;
import whale.whale_Project.repository.ResultRepository;
import whale.whale_Project.repository.WhaleCountRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WhaleCountService {

    private final WhaleCountRepository whaleCountRepository;
    private final ResultRepository resultRepository;

    @Transactional
    public void updateCountList(Long resultId, WhaleCountList whaleCountList) {
        Result result  = resultRepository.findOne(resultId);
        result.getWhale().addPoint(result, whaleCountList);
    }
}
