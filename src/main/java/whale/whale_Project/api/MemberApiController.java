package whale.whale_Project.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import whale.whale_Project.Dto.MemberDto;
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
        List<MemberDto> collect = findMembers.stream()
                .map(m -> new MemberDto(m.getName(), m.getEmail(), m.getAddress(),
                        m.getAccount() ,m.getPwd(), m.getId(), m.getLocalDateTime()))
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
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid MemberDto memberDto) {

        Member member = createMember(memberDto);

        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    private Member createMember(MemberDto memberDto) {
        Member member = new Member();
        member.setName(memberDto.getName());
        member.setEmail(memberDto.getEmail());
        member.setAddress(memberDto.getAddress());
        member.setAccount(memberDto.getAccount());
        member.setPwd(memberDto.getPwd());
        member.setId(memberDto.getCreatorId());
        member.setLocalDateTime(memberDto.getCreatedDateTime());

        return member;
    }

    @PutMapping("/api/v2/members/{id}")
    public UpdateMemberResponse updateMemberV2(@PathVariable("id") Long id,
                                               @RequestBody @Valid MemberDto memberDto) {
        memberService.updateMember(id, memberDto.getName(), memberDto.getAccount(),
                memberDto.getPwd(), memberDto.getAddress(), memberDto.getEmail());
        Member findMember = memberService.findOne(id);
        return new UpdateMemberResponse(findMember.getId(), findMember.getName(), findMember.getEmail(),
                findMember.getAddress(), findMember.getAccount(), findMember.getPwd(),
                findMember.getLocalDateTime());
    }

    @Data
    @AllArgsConstructor
    static class UpdateMemberResponse {
        private Long updateId;
        private String name;
        private String email;
        private Address address;
        private String account;
        private String pwd;
        private LocalDateTime createdDateTime;
    }

    @Data
    static class CreateMemberResponse {
        private Long id;

        public CreateMemberResponse(Long id) {
            this.id = id;
        }
    }

}

