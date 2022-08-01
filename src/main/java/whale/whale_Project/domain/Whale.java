package whale.whale_Project.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Getter @Setter
public class Whale {

    @Id @GeneratedValue
    @Column(name = "whale_id")
    private Long id;

    private int totalPoint;

    @OneToOne(mappedBy = "whale", fetch = FetchType.LAZY)
    private Result result;

    //고래 종류
    @Enumerated(EnumType.STRING)
    private MbtiMappingWithWhale mbtiWhaleMapping; //ENUM [16Types]
    private String whaleType;

    //==생성 메서드==//
    public static Whale createMbtiWithWhale(MbtiMappingWithWhale mbtiMappingWithWhale) {
        Whale whale = new Whale();
        // 아래 두개는 어떻게 매핑할건지?
        whale.setMbtiWhaleMapping(mbtiMappingWithWhale);
        whale.setWhaleType(mbtiMappingWithWhale.getWhaleType().name());
        return whale;
    }
    //==비즈니스 로직==//
    public HashMap<String, Integer> addPoint(Result result, WhaleCountList whaleCountList) {

        String mbtiType = result.getWhale().getMbtiWhaleMapping().name();
        Integer point = whaleCountList.whaleCountMap.get(mbtiType);
        whaleCountList.whaleCountMap.put(mbtiType, point + 1);

        return whaleCountList.whaleCountMap;
    }
}
