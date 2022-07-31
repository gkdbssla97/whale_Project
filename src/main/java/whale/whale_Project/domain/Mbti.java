package whale.whale_Project.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Mbti {

    @Id @GeneratedValue
    @Column(name = "mbti_id")
    private Long id;

    @OneToOne(mappedBy = "mbti", fetch = FetchType.LAZY)
    private Result result;

    @OneToOne(fetch = FetchType.LAZY) //(cascade = CascadeType.ALL)
    @JoinColumn(name = "animal_id")
    private Whale whale;

    @Enumerated(EnumType.STRING)
    private MbtiType type; //ENUM [16Types]

    //==생성 메서드==//
    public static Mbti createMbtiWithWhale(Whale whale, MbtiType mbtiType) {
        Mbti mbti = new Mbti();
        // 아래 두개는 어떻게 매핑할건지?
        mbti.setType(mbtiType);
        mbti.setWhale(whale);

        return mbti;
    }
}
