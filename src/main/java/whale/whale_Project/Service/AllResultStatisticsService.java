package whale.whale_Project.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import whale.whale_Project.domain.AllResultStatistics;
import whale.whale_Project.domain.WhaleCountList;
import whale.whale_Project.repository.WhaleCountRepository;

import java.security.Key;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AllResultStatisticsService {

    private WhaleCountRepository whaleCountRepository;

    public void whatTheResult(WhaleCountList whaleCountList) {
        HashMap<java.lang.String, java.lang.Integer> map = whaleCountList.algorithmCount();
        String max = Collections.max(map.keySet());
        AllResultStatistics allResultStatistics = new AllResultStatistics();
        allResultStatistics.setMbtiWhaleMapping(max);
    }
}
