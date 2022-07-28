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
    @JoinColumn(name = "animal_id")
    private Whale whale;

    private LocalDateTime testDate;

    @OneToOne(fetch = FetchType.LAZY) //(cascade = CascadeType.ALL)
    private Mbti mbti;

}
