package whale.whale_Project.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import whale.whale_Project.domain.*;
import whale.whale_Project.repository.MemberRepository;
import whale.whale_Project.repository.ResultRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ResultService {

    private final MemberRepository memberRepository;
    private final ResultRepository resultRepository;
    /**
     * 고래테스트
     * @param
     */
    @Transactional
    public Long whaleTest(Long memberId, Whale whale) {
        //엔티티 조회
        Member member = memberRepository.findOne(memberId);

        //MBTI-Whale 생성
        Whale resultWhale = Whale.createMbtiWithWhale(whale.getMbtiWhaleMapping());

        //결과지 생성
        Result result = Result.createResult(member, resultWhale);

        //결과 저장
        resultRepository.save(result);

        return result.getId();
    }
    /** 결과(고래종류별로?) 검색
    public Result findResults(ResultSearch resultSearch) {
        return resultRepository.findAll(resultSearch);
    }
    **/
}
