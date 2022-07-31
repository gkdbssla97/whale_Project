package whale.whale_Project.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter @Setter
public class ResultSearch {

    private String memberName;
    private MbtiMappingWithWhale mbtiType;

}
