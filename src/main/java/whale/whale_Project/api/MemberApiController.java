package whale.whale_Project.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import whale.whale_Project.Dto.CreateMemberDto;
import whale.whale_Project.Dto.UpdateMemberDto;
import whale.whale_Project.Service.MemberService;
import whale.whale_Project.domain.Address;
import whale.whale_Project.domain.Member;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    @GetMapping("/api/v2/members")
    public Result memberV2() {
        List<Member> findMembers = memberService.findMembers();
        List<CreateMemberDto> collect = findMembers.stream()
                .map(m -> new CreateMemberDto(m.getId(), m.getName(), m.getEmail(), m.getAddress(),
                        m.getAccount() ,m.getPwd(), m.getLocalDateTime()))
                .collect(Collectors.toList());
        return new Result(collect.size(), collect);
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private int count;
        private T data;
    }

    @PostMapping("/api/v2/members")
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberDto createMemberDto) {

        Member member = createMember(createMemberDto);

        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    private Member createMember(CreateMemberDto createMemberDto) {
        Member member = new Member();
        member.setId(createMemberDto.getCreatorId());
        member.setName(createMemberDto.getName());
        member.setEmail(createMemberDto.getEmail());
        member.setAddress(createMemberDto.getAddress());
        member.setAccount(createMemberDto.getAccount());
        member.setPwd(createMemberDto.getPwd());
        member.setLocalDateTime(createMemberDto.getCreatedDateTime());

        return member;
    }

    @PutMapping("/api/v2/members/{id}")
    public UpdateMemberResponse updateMemberV2(@PathVariable("id") Long id,
                                               @RequestBody @Valid UpdateMemberDto memberDto) {
        memberService.updateMember(id, memberDto.getAccount(),
                memberDto.getPwd(), memberDto.getAddress(), memberDto.getEmail());
        Member findMember = memberService.findOne(id);
        return new UpdateMemberResponse(findMember.getId(), findMember.getEmail(),
                findMember.getAddress(), findMember.getAccount(), findMember.getPwd(),
                findMember.getLocalDateTime());
    }

    @Data
    static class CreateMemberResponse {
        private Long createId;
        public CreateMemberResponse(Long id) {
            this.createId = id;
        }
    }

    @Data
    @AllArgsConstructor
    static class UpdateMemberResponse {
        private Long updateId;
        private String email;
        private Address address;
        private String account;
        private String pwd;
        private LocalDateTime updateDateTime;
    }
}

