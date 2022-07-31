package whale.whale_Project.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import whale.whale_Project.Service.MemberService;
import whale.whale_Project.Service.ResultService;
import whale.whale_Project.domain.Address;
import whale.whale_Project.domain.Member;
import whale.whale_Project.domain.MemberStatus;
import whale.whale_Project.domain.Result;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional //** Annotation 꼭 삽입
class MemberRepositoryTest {

    @PersistenceContext EntityManager em;

    @Autowired MemberRepository memberRepository;
    @Autowired MemberService memberService;
    @Autowired ResultRepository resultRepository;
    @Autowired ResultService resultService;

    @Test
    //@Rollback(false)
    public void testMember() {

        Member member = new Member();
        member.setName("하윤");
        member.setAddress(new Address("서울시", "강남구", "일원1동"));
        member.setEmail("gkdbssla97@naver.com");

        em.persist(member);

        Long saveId = memberRepository.save(member);
        Member findMember = memberRepository.findOne(saveId);

        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getName()).isEqualTo(member.getName());
        assertThat(findMember).isEqualTo(member);

        Result result = new Result();
        result.setMember(member);
        // 결과창을 통해 어떻게 회원 여부를 판단하지? -> ResultService
        //MemberStatus memberStatus = new MemberStatus();
        result.setMemberStatus(result.getMemberStatus());
        result.setTestDate(LocalDateTime.now());
        //result.setMbti(); //Mbti랑 Whale 두 개 연관매핑
        //result.setWhale();

        em.persist(result);

        resultRepository.save(result);
    }

    //중복회원 예외
    @Test
    public void 중복회원에외() throws Exception {
        Member member1 = new Member();
        member1.setName("A");
        Member member2 = new Member();
        member2.setName("B");

        memberService.join(member1);
        memberService.join(member2);

        fail("예외가 발생해야한다.");
    }
}