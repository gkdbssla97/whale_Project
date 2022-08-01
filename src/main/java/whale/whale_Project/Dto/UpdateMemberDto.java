package whale.whale_Project.Dto;

import lombok.Data;
import whale.whale_Project.domain.Address;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
public class UpdateMemberDto {

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


    public UpdateMemberDto(String email, Address address,
                           String account, String pwd, Long creatorId,
                           LocalDateTime createdDateTime) {
        this.email = email;
        this.address = address;
        this.account = account;
        this.pwd = pwd;
        this.creatorId = creatorId;
        this.createdDateTime = createdDateTime;
    }
}
