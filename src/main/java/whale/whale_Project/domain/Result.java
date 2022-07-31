package whale.whale_Project.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "results")
@Getter @Setter
public class Result {

    @Id @GeneratedValue
    @Column(name = "result_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; //테스트 회원

    @OneToOne(fetch = FetchType.LAZY) //(cascade = CascadeType.ALL)
    @JoinColumn(name = "mbti_id")
    private Mbti mbti;

    private LocalDateTime testDate; //테스트시간

    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus; //회원상태 [MEMBER, NONMEMBER]

    //==연관관계 메서드==//
    public void setMember(Member member) {
        this.member = member;
        member.getResults().add(this);
    }
    public void addMbtiType(Mbti mbti) {
        this.mbti = mbti;
        mbti.setResult(this);
    }

    //==생성 메서드==//
    public static Result createResult(Member member, Mbti mbti) {
        Result result = new Result();
        result.setMember(member);
        result.setMbti(mbti);
        result.setTestDate(LocalDateTime.now());
        return result;
    }
}
