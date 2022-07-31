package whale.whale_Project.Service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import whale.whale_Project.domain.Address;
import whale.whale_Project.domain.Member;
import whale.whale_Project.repository.MemberRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired MemberRepository memberRepository;
    @Autowired MemberService memberService;


    @Test
    @Rollback(false)
    public void testMember() {

        Member member = new Member();
        member.setName("하윤");
        member.setAddress(new Address("서울시", "강남구", "일원1동"));
        member.setEmail("gkdbssla97@naver.com");

        Long saveId = memberService.join(member);
        Member findMember = memberRepository.findOne(saveId);

        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getName()).isEqualTo(member.getName());
        assertThat(findMember).isEqualTo(member);
    }

    //중복회원 예외
    @Test//(expected = IllegalStateException.class)
    public void 중복회원예외() throws Exception {
        Member member1 = new Member();
        member1.setName("A");
        Member member2 = new Member();
        member2.setName("B");

        memberService.join(member1);
        memberService.join(member2);

        //fail("예외가 발생해야한다.");
    }
}