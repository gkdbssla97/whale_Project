package whale.whale_Project.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public class WhaleCountList {
    @Id @GeneratedValue
    private Long Id;

    public HashMap<String, Integer> algorithmCount() {
        HashMap<String, Integer> whaleCountMap = new HashMap<String, Integer>();
        List<String> mbtiKey = Stream.of(MbtiMappingWithWhale.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        for (String mbtiType : mbtiKey) {
            whaleCountMap.put(mbtiType, 0);
        }
        //        List list4 = Stream.of(MbtiMappingWithWhale.values())
        //                .map(m -> m.getWhaleType())
        //                .collect(Collectors.toList());
        //        System.out.println(list4);
        return whaleCountMap;
    }

}
