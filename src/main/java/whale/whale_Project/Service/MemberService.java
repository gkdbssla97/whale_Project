package whale.whale_Project.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import whale.whale_Project.domain.Address;
import whale.whale_Project.domain.Member;
import whale.whale_Project.domain.Result;
import whale.whale_Project.domain.Whale;
import whale.whale_Project.repository.MemberRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원가입
     */
    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member); //중복회원검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * 단건 조회
    * @param memberId
     * @return
     */
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    @Transactional
    public void updateMember(Long id, String account, String pwd, Address address, String email) {
        Member member = memberRepository.findOne(id); //영속성 상태
        member.setAccount(account);
        member.setPwd(pwd);
        member.setAddress(address);
        member.setEmail(email);

        //회원일 경우-> 테스트 결과지 회원상태로 수정 후 공유가능?
        //result.setMemberStatus(MemberStatus.MEMBER);
    }
}
