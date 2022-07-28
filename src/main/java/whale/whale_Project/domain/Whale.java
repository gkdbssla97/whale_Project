package whale.whale_Project.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashMap;

@Entity
@Getter @Setter
@DiscriminatorColumn(name = "dtype")
public class Whale {

    @Id
    @GeneratedValue
    @Column(name = "whale_id")
    private Long id;

    private String name;
    //고래
    @Enumerated(EnumType.STRING)
    private WhaleType whaleType;

    @OneToOne(mappedBy = "whale", fetch = FetchType.LAZY)
    private Result result;
}
