package whale.whale_Project.Dto;

import lombok.Data;
import whale.whale_Project.domain.Address;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
public class CreateMemberDto {

    @NotEmpty(message = "회원 이름은 필수입니다.")
    private String name;

    private String email;
    private Address address;

    @NotEmpty(message = "아이디는 필수입니다.")
    private String account;

    @NotEmpty(message = "비밀번호는 필수입니다.")
    private String pwd;

    private Long creatorId;
    private LocalDateTime createdDateTime;
    //private String updateId;
    //private String updateDateTime;
    //private List<MemberFileDto> fileList;


    public CreateMemberDto(Long creatorId, String name, String email,
                           Address address, String account, String pwd,
                           LocalDateTime createdDateTime) {
        this.creatorId = creatorId;
        this.name = name;
        this.email = email;
        this.address = address;
        this.account = account;
        this.pwd = pwd;
        this.createdDateTime = createdDateTime;
    }
}
