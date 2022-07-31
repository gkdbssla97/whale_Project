package whale.whale_Project.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    //회원 Account / Pwd
    private String account;
    private String pwd;

    @NotEmpty
    private String name;

    @Embedded
    private Address address;

    private String email;

    @OneToMany(mappedBy = "member")
    private List<Result> results = new ArrayList<>();
}
