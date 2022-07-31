package whale.whale_Project.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import whale.whale_Project.domain.Mbti;
import whale.whale_Project.domain.Member;
import whale.whale_Project.domain.MemberStatus;
import whale.whale_Project.domain.Result;
import whale.whale_Project.repository.MemberRepository;
import whale.whale_Project.repository.ResultRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ResultService {

    private final MemberRepository memberRepository;
    private final ResultRepository resultRepository;
    private final Mbti mbti;
    /**
     * 고래테스트
     * @param
     */
//    @Transactional
//    public Long whaleTest(Long memberId) {
//        //엔티티 조회
//        Member member = memberRepository.findOne(memberId);
//
//        //MBTI 생성
//        Mbti resultMbti = Mbti.createMbtiWithWhale(mbti.getWhale(), mbti.getType());
//
//        //결과지 생성
//        Result result = Result.createResult(member, resultMbti);
//
//        //결과 저장
//        resultRepository.save(result);
//
//        return result.getId();
//    }
    /** 결과(고래종류별로?) 검색
    public Result findResults(ResultSearch resultSearch) {
        return resultRepository.findAll(resultSearch);
    }
    **/
}
