package whale.whale_Project.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Mbti {

    @Id @GeneratedValue
    @Column(name = "mbti_id")
    private Long id;

    @OneToOne(mappedBy = "mbti", fetch = FetchType.LAZY)
    private Result result;

    @Enumerated(EnumType.STRING)
    private MbtiStatus status; //ENUM [16Types]
}
