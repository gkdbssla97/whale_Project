package whale.whale_Project.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;
    private String account; //회원 ACCOUNT
    private String pwd; //회원 PWD

    @Embedded
    private Address address;

    private String email;
    private LocalDateTime localDateTime;

    @OneToMany(mappedBy = "member")
    private List<Result> results = new ArrayList<>();
}
