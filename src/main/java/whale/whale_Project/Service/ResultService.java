package whale.whale_Project.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import whale.whale_Project.domain.*;
import whale.whale_Project.repository.MemberRepository;
import whale.whale_Project.repository.ResultRepository;

import java.util.List;

import static whale.whale_Project.domain.Result.createResult;

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

        //<MBTI-Whale> 생성
        Whale resultWhale = Whale.createMbtiWithWhale(whale.getMbtiWhaleMapping());

        //결과지 생성
        Result result = createResult(member, resultWhale);

        //결과 저장
        resultRepository.save(result);

        return result.getId();
    }
    //결과(고래종류별로?) 검색
    public List<Result> findResults(ResultSearch resultSearch) {
        return resultRepository.findAllByWhale(resultSearch);
    }

    @Transactional
    public void updateResult(Long resultId, Whale whale) {
        Result result = resultRepository.findOne(resultId);
        result.setWhale(whale);

        //회원일 경우-> 테스트 결과지 회원상태로 수정 후 공유가능?
        //result.setMemberStatus(MemberStatus.MEMBER);
    }
}
