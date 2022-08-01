package whale.whale_Project.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class AllResultStatistics {

    @Id
    @GeneratedValue
    @Column(name = "allresultstatistics_id")
    private Long id;

    private String mbtiWhaleMapping;
    private String whaleType;

    @OneToMany(mappedBy = "allresultstatistics")
    private List<Result> result = new ArrayList<>();
}
