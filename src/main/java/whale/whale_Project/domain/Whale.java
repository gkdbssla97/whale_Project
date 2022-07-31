package whale.whale_Project.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashMap;

@Entity
@Getter @Setter
@DiscriminatorColumn(name = "dtype")
public abstract class Whale {

    @Id @GeneratedValue
    @Column(name = "whale_id")
    private Long id;

    private String name;
    private int totalPoint;

    //고래 종류
    @Enumerated(EnumType.STRING)
    private WhaleType whaleType;

    @OneToOne(mappedBy = "whale", fetch = FetchType.LAZY)
    private Mbti mbti;

    //==비즈니스 로직==//
    public void addPoint(int point) {
        this.totalPoint += point;
    }

}
