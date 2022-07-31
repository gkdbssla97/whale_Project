package whale.whale_Project.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashMap;

@Entity
@Getter @Setter
@DiscriminatorColumn(name = "dtype")
public class Whale {

    @Id @GeneratedValue
    @Column(name = "whale_id")
    private Long id;

    private String name;
    private int totalPoint;

    @OneToOne(mappedBy = "whale", fetch = FetchType.LAZY)
    private Result result;

    //고래 종류
    @Enumerated(EnumType.STRING)
    private WhaleType whaleType; //ENUM [16Types]

    @Enumerated(EnumType.STRING)
    private MbtiType mbtiType; //ENUM [16Types]

    //==생성 메서드==//
    public static Whale createMbtiWithWhale(WhaleType whaleType, MbtiType mbtiType) {
        Whale whale = new Whale();
        // 아래 두개는 어떻게 매핑할건지?
        whale.setWhaleType(whaleType);
        whale.setMbtiType(mbtiType);

        return whale;
    }
    //==비즈니스 로직==//
    public void addPoint(int point) {
        this.totalPoint += point;
    }

}
