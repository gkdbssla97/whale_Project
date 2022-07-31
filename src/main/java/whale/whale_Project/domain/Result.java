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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member; //테스트 회원

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "whale_id")
    private Whale whale;

    private LocalDateTime testDate; //테스트시간

    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus; //회원상태 [MEMBER, NONMEMBER]

    //==연관관계 메서드==//
    public void setMember(Member member) {
        this.member = member;
        member.getResults().add(this);
    }
    public void addWhaleType(Whale whale) {
        this.whale = whale;
        whale.setResult(this);
    }

    //==생성 메서드==//
    public static Result createResult(Member member, Whale whale) {
        Result result = new Result();
        result.setMember(member);
        result.setWhale(whale);
        result.setTestDate(LocalDateTime.now());
        return result;
    }
}
