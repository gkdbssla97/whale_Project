package whale.whale_Project;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import whale.whale_Project.Service.AllResultStatisticsService;
import whale.whale_Project.Service.MemberService;
import whale.whale_Project.Service.ResultService;
import whale.whale_Project.domain.*;
import whale.whale_Project.repository.WhaleCountRepository;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
        initService.dbInit2();
        initService.dbInit3();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final ResultService resultService;
        private final MemberService memberService;
        private final WhaleCountRepository whaleCountRepository;
        private final AllResultStatisticsService allResultStatisticsService;

        public void dbInit1() {
            Member member = createMember("하윤", "hy97@sju.ac.kr", "서울시",
                    "서초구", "양재동", "gkdbssla97", "123456", LocalDateTime.now());
            Long memberId = memberService.join(member);

            Whale whale1 = Whale.createMbtiWithWhale(MbtiMappingWithWhale.ENFJ);
            Long resultId1 = resultService.whaleTest(memberId, whale1);

            Whale whale2 = Whale.createMbtiWithWhale(MbtiMappingWithWhale.ENTJ);
            Long resultId2 = resultService.whaleTest(memberId, whale2);
        }

        public void dbInit2() {
            Member member = createMember("윤광오", "swager253@fdsaf.comr", "인천시",
                    "부평구", "문화1로", "dbsrhkddh96", "654321", LocalDateTime.now());
            Long memberId = memberService.join(member);

            Whale whale1 = Whale.createMbtiWithWhale(MbtiMappingWithWhale.ESFJ);
            Long resultId1 = resultService.whaleTest(memberId, whale1);

            Whale whale2 = Whale.createMbtiWithWhale(MbtiMappingWithWhale.ESTJ);
            Long resultId2 = resultService.whaleTest(memberId, whale2);
        }

        private Member createMember(String name, String email, String city, String street,
                                    String zipcode, String account, String pwd,
                                    LocalDateTime createdDateTime) {
            Member member = new Member();
            member.setName(name);
            member.setEmail(email);
            member.setAddress(new Address(city, street, zipcode));
            member.setAccount(account);
            member.setPwd(pwd);
            member.setLocalDateTime(createdDateTime);
            return member;
        }

        public void dbInit3() {
            WhaleCountList whaleCountList = new WhaleCountList();
            whaleCountList.algorithmCount();
            whaleCountRepository.save(whaleCountList);
            allResultStatisticsService.whatTheResult(whaleCountList);
        }
    }
}

