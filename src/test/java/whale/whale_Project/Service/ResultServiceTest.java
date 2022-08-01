package whale.whale_Project.Service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import whale.whale_Project.domain.*;
import whale.whale_Project.repository.ResultRepository;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ResultServiceTest {

    @Autowired ResultService resultService;
    @Autowired ResultRepository resultRepository;
    @Autowired MemberService memberService;

    //@Test
    //@Rollback(false)
    public void testResult() {
        Member member = new Member();
        member.setName("하윤");
        member.setAddress(new Address("서울시","강남구","일원1동"));
        member.setEmail("gkdbssla97@naver.com");
        member.setAccount("gkdbssla97");
        member.setPwd("123456");

        Long memberId = memberService.join(member);

        Whale whale = Whale.createMbtiWithWhale(MbtiMappingWithWhale.ENFJ);

        Long testId = resultService.whaleTest(memberId, whale);
        // 결과창을 통해 어떻게 회원 여부를 판단하지? -> ResultService
        //MemberStatus memberStatus = new MemberStatus();

        Result result = resultRepository.findOne(testId);

        assertEquals("하윤", result.getMember().getName());
        assertEquals(WhaleType.돌고래, result.getWhale().getMbtiWhaleMapping().getWhaleType());
        assertEquals("ENFJ", result.getWhale().getMbtiWhaleMapping().name());
    }
}