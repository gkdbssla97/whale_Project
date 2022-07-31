package whale.whale_Project.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MbtiMappingWithWhale {

    ISTJ(WhaleType.WHITE_WHALE), ISFJ(WhaleType.WHALE1),
    INFJ(WhaleType.WHALE2), INTJ(WhaleType.WHALE3),
    ISTP(WhaleType.WHALE4), ISFP(WhaleType.WHALE5),
    INFP(WhaleType.WHALE6), INTP(WhaleType.WHALE7),
    ESTP(WhaleType.WHALE8), ESFP(WhaleType.WHALE9),
    ENFP(WhaleType.WHALE10), ENTP(WhaleType.WHALE11),
    ESTJ(WhaleType.WHALE12), ESFJ(WhaleType.WHALE13),
    ENFJ(WhaleType.돌고래), ENTJ(WhaleType.혹등고래);

    private final WhaleType whaleType;
}