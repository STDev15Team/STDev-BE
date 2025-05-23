package stdev.global.config.data;


import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import stdev.domain.other.domain.entity.Lucky;
import stdev.domain.other.domain.repository.LuckyRepository;

import java.time.LocalDate;

//@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final LuckyRepository luckyRepository;


    @PostConstruct
    public void initializeMembers() {
        // 데이터베이스 초기화를 위한 코드 작성
        Lucky lucky1 = Lucky.builder().luckyImage("").keyword("총운잡몽").comment("\"일상 속 즐거움을 찾아볼까요?\"\n" +
                "잡몽은 일상적인 경험이 뒤섞인 꿈으로, 특별한 감정적 반응이나 심리적 변화를 주지 않는 경우가 많아요. 이런 꿈은 보통 스트레스나 피로로 인해 발생합니다. 사용자가 평소 겪은 일이나 기억들이 비논리적으로 엮여 나타나는 경우가 많아요. 꿈의 내용이 일상적이라 특별한 상징이 없지만, 때로는 자신이 놓친 감정이나 상황을 무의식적으로 처리하는 기회를 제공할 수 있어요. 잡몽을 꾸는 것은 뇌가 감정적, 신경적 부담을 풀기 위한 자연스러운 반응으로 볼 수 있습니다.\n" +
                "현재의 운은 평범한 시기일 가능성이 커요. 잡몽을 꾸었다면 현재 자신에게 큰 변화가 없거나, 특별히 두드러진 일이 없는 상태일 가능성이 높습니다. 변화가 없다고 해서 지루한 것은 아니에요. 안정적인 상태에서 자기 관리를 잘 하는 시점입니다. 작은 일에 집중하고, 일상 속에서 즐거움을 찾으세요. 이런 때일수록 여유를 가지며, 내면의 평화를 찾는 것이 중요해요.\n").build();
        Lucky lucky2 = Lucky.builder().luckyImage("").keyword("총운허몽").comment("\"변화의 기운이 느껴지지 않나요?\"\n" +
                "허몽은 비현실적이고 상상적인 꿈으로, 현실에서 일어날 법하지 않은 상황들이 발생하는 꿈이에요. 이런 꿈은 뇌의 창의력과 상상력이 뚜렷하게 나타난 경우로, 무의식적으로 새로운 가능성이나 기회를 예고하는 경우가 많습니다. 허몽은 때로 뇌가 창의적인 방식으로 문제를 해결하거나, 자기 발전을 위한 무언가를 추구하는 과정을 나타낼 수 있어요.\n" +
                "허몽을 꾸었다면, 현재 운은 변화의 기운이 도는 시기입니다. 새로운 도전이나 창의적 아이디어가 필요한 순간일 수 있어요. 다가오는 기회는 평범하지 않거나 예상치 못한 형태로 다가올 가능성이 큽니다. 변화와 새로운 시작에 대해 두려워하지 말고, 적극적인 태도를 유지하는 것이 중요합니다.\n").build();
        Lucky lucky3 = Lucky.builder().luckyImage("").keyword("총운영몽").comment("\"자기 자신을 믿으세요!\"\n" +
                "영몽은 미래를 예고하는 듯한 직관적인 꿈으로, 때로는 예상치 못한 신호나 암시를 제공하는 경우가 많아요. 이 꿈은 뇌가 과거의 경험이나 현재의 상황을 바탕으로 미래를 예측하거나 중요한 정보를 직관적으로 전달하려는 경우입니다. 영몽은 신경과학적으로도 미래 예측 능력을 사용하는 뇌의 반응으로 해석할 수 있어요.\n" +
                "영몽을 꾼 만큼, 미래에 대한 직관적 예감이 강해질 수 있습니다. 예지몽의 힘을 활용하여 다가오는 변화나 기회를 빠르게 포착할 수 있을 것입니다. 다가오는 상황에서 자신의 직감을 믿고, 적극적으로 움직여 보세요. 중요한 결정을 내릴 때 직관을 따르는 것이 유리할 수 있습니다.\n").build();
        Lucky lucky4 = Lucky.builder().luckyImage("").keyword("총운정몽").comment("\"행운이 가득가득\"\n" +
                "정몽은 행운과 긍정적인 기회를 나타내는 꿈으로, 기회나 성공을 암시합니다. 길몽이라고도 불리며, 개인의 삶에서 긍정적 변화나 성공적인 결과를 예고하는 꿈이에요.\n" +
                "정몽을 꾸었다면, 미래에 대한 긍정적 에너지가 가득한 시기입니다. 큰 성공이나 행운이 다가올 가능성이 높아요. 이 시점을 기회로 삼아 적극적으로 도전하는 것이 중요합니다. 운이 따라오는 시기이니, 기회를 놓치지 마세요.\n").build();
        Lucky lucky5 = Lucky.builder().luckyImage("").keyword("총운심몽").comment("\"너무 부담갖지 않아도 돼요\"\n" +
                "심몽은 내면의 갈등이나 심리적 불안을 나타내는 꿈으로, 자신의 감정이나 심리적 문제를 무의식적으로 처리하는 과정에서 나타납니다. 심몽은 심리적 부담이나 불안을 해소하는데 중요한 역할을 합니다.\n" +
                "심몽을 꾸었다면, 내면적 갈등이나 불안이 클 수 있어요. 현재 상황에서의 심리적 부담을 해소하는 것이 중요합니다. 이 시기에는 자기 이해와 감정 관리에 집중하여 내면의 평화를 찾는 것이 필요합니다.\n").build();

        Lucky lucky6 = Lucky.builder().luckyImage("").keyword("애정운잡몽").comment("\"서로 배려하는 마음을 가져보아요\"\n" +
                "잡몽은 일상적인 경험이 뒤섞인 꿈으로, 특별한 감정적 반응이나 심리적 변화를 주지 않는 경우가 많아요. 이런 꿈은 보통 스트레스나 피로로 인해 발생합니다. 사용자가 평소 겪은 일이나 기억들이 비논리적으로 엮여 나타나는 경우가 많아요. 꿈의 내용이 일상적이라 특별한 상징이 없지만, 때로는 자신이 놓친 감정이나 상황을 무의식적으로 처리하는 기회를 제공할 수 있어요. 잡몽을 꾸는 것은 뇌가 감정적, 신경적 부담을 풀기 위한 자연스러운 반응으로 볼 수 있습니다.\n" +
                "애정운은 그리 특별하지 않지만, 큰 갈등이나 불화는 없어 보입니다. 다만, 작은 오해나 불편함이 있을 수 있어요. 연인이나 주변 사람들과의 관계에서 작은 마찰이 생길 수 있는데, 그럴 때 상대방의 입장을 이해하려는 노력이 필요합니다. 감정이 얽히지 않도록 서로 배려하는 마음이 중요해요.\n").build();
        Lucky lucky7 = Lucky.builder().luckyImage("").keyword("애정운허몽").comment("\"새로운 매력 발견이 기대되네요!\"\n" +
                "허몽은 비현실적이고 상상적인 꿈으로, 현실에서 일어날 법하지 않은 상황들이 발생하는 꿈이에요. 이런 꿈은 뇌의 창의력과 상상력이 뚜렷하게 나타난 경우로, 무의식적으로 새로운 가능성이나 기회를 예고하는 경우가 많습니다. 허몽은 때로 뇌가 창의적인 방식으로 문제를 해결하거나, 자기 발전을 위한 무언가를 추구하는 과정을 나타낼 수 있어요.\n" +
                "애정운에서는 새로운 사람과의 만남이나 인연이 생길 수 있어요. 특히 연애에 있어서 기존의 패턴을 벗어난 새로운 접근이 필요할 수 있습니다. 허몽에서 느낀 변화와 창의력을 애정 관계에 적용하면, 서로에게 새로운 매력을 발견할 수 있을 것입니다. 상대방과 다양한 방식으로 소통하며 관계를 발전시킬 수 있습니다.\n").build();
        Lucky lucky8 = Lucky.builder().luckyImage("").keyword("애정운영몽").comment("\"새로운 인연이 찾아오고 있어요!\"\n" +
                "영몽은 미래를 예고하는 듯한 직관적인 꿈으로, 때로는 예상치 못한 신호나 암시를 제공하는 경우가 많아요. 이 꿈은 뇌가 과거의 경험이나 현재의 상황을 바탕으로 미래를 예측하거나 중요한 정보를 직관적으로 전달하려는 경우입니다. 영몽은 신경과학적으로도 미래 예측 능력을 사용하는 뇌의 반응으로 해석할 수 있어요.\n" +
                "애정에서 예상치 못한 변화나 새로운 전환점이 나타날 가능성이 높습니다. 영몽은 사랑의 변화나 새로운 인연을 예고하는 경우가 많기 때문에, 감정적 변화가 올 수 있습니다. 직관적인 감정의 흐름을 따르며, 사랑을 다가갈 수 있는 시점입니다.\n").build();
        Lucky lucky9 = Lucky.builder().luckyImage("").keyword("애정운정몽").comment("\"사랑을 시작하기 좋은 시점이에요!\"\n" +
                "정몽은 행운과 긍정적인 기회를 나타내는 꿈으로, 기회나 성공을 암시합니다. 길몽이라고도 불리며, 개인의 삶에서 긍정적 변화나 성공적인 결과를 예고하는 꿈이에요.\n" +
                "애정운에서는 새로운 시작이나 긍정적인 변화가 있을 것입니다. 사랑에 있어 행운이 따르는 시기이니, 연애에서 행복한 변화나 뜻밖의 발전이 있을 수 있습니다. 사랑을 시작하는 좋은 시점이에요.\n").build();
        Lucky lucky10 = Lucky.builder().luckyImage("").keyword("애정운심몽").comment("\"우리 서로 충분한 이야기를 나누어 보아요\"\n" +
                "심몽은 내면의 갈등이나 심리적 불안을 나타내는 꿈으로, 자신의 감정이나 심리적 문제를 무의식적으로 처리하는 과정에서 나타납니다. 심몽은 심리적 부담이나 불안을 해소하는데 중요한 역할을 합니다.\n" +
                "애정에서는 갈등이나 오해가 발생할 수 있습니다. 상대방과의 관계에서 소통 부족이나 감정적 어려움이 있을 수 있으므로, 서로의 감정을 공유하고 이해하는 노력이 필요합니다.\n").build();

        Lucky lucky11 = Lucky.builder().luckyImage("").keyword("제물운잡몽").comment("\"티끌 모아 태산!\"\n" +
                "잡몽은 일상적인 경험이 뒤섞인 꿈으로, 특별한 감정적 반응이나 심리적 변화를 주지 않는 경우가 많아요. 이런 꿈은 보통 스트레스나 피로로 인해 발생합니다. 사용자가 평소 겪은 일이나 기억들이 비논리적으로 엮여 나타나는 경우가 많아요. 꿈의 내용이 일상적이라 특별한 상징이 없지만, 때로는 자신이 놓친 감정이나 상황을 무의식적으로 처리하는 기회를 제공할 수 있어요. 잡몽을 꾸는 것은 뇌가 감정적, 신경적 부담을 풀기 위한 자연스러운 반응으로 볼 수 있습니다.\n" +
                "재물운은 안정적이에요. 갑작스러운 금전적인 변화나 불안은 없으나, 지나치게 과도한 소비는 피하는 것이 좋아요. 재정적으로 큰 변화가 없는 시기라, 자신의 소비 패턴을 점검하고 저축을 하는 것이 좋습니다. 큰 지출이나 투자 계획은 잠시 보류하는 것이 현명할 수 있어요.\n").build();
        Lucky lucky12 = Lucky.builder().luckyImage("").keyword("제물운허몽").comment("\"매순간 신중해야 해요\"\n" +
                "허몽은 비현실적이고 상상적인 꿈으로, 현실에서 일어날 법하지 않은 상황들이 발생하는 꿈이에요. 이런 꿈은 뇌의 창의력과 상상력이 뚜렷하게 나타난 경우로, 무의식적으로 새로운 가능성이나 기회를 예고하는 경우가 많습니다. 허몽은 때로 뇌가 창의적인 방식으로 문제를 해결하거나, 자기 발전을 위한 무언가를 추구하는 과정을 나타낼 수 있어요.\n" +
                "재물운은 다소 불확실한 시기입니다. 허몽에서 느낀 상상력과 창의성을 바탕으로 새로운 재정적 기회가 생길 수 있지만, 불안정한 요소가 있기 때문에 신중함이 필요합니다. 투자나 큰 금전적 결정을 내리기 전에, 충분한 조사와 검토를 하는 것이 중요합니다.\n").build();
        Lucky lucky13 = Lucky.builder().luckyImage("").keyword("제물운영몽").comment("\"본인을 한 번 더 믿어봐요!\"\n" +
                "영몽은 미래를 예고하는 듯한 직관적인 꿈으로, 때로는 예상치 못한 신호나 암시를 제공하는 경우가 많아요. 이 꿈은 뇌가 과거의 경험이나 현재의 상황을 바탕으로 미래를 예측하거나 중요한 정보를 직관적으로 전달하려는 경우입니다. 영몽은 신경과학적으로도 미래 예측 능력을 사용하는 뇌의 반응으로 해석할 수 있어요.\n" +
                "재물운은 미래 예측과 관련된 부분이 강하게 나타납니다. 영몽에서 얻은 직감이나 예감이 재정적인 기회나 위험을 예고할 수 있습니다. 금전적인 결정을 내리기 전에, 본인의 직관을 믿고 빠르게 움직이는 것이 좋습니다.\n").build();
        Lucky lucky14 = Lucky.builder().luckyImage("").keyword("제물운정몽").comment("\"설마 나도 복권당첨?\"\n" +
                "정몽은 행운과 긍정적인 기회를 나타내는 꿈으로, 기회나 성공을 암시합니다. 길몽이라고도 불리며, 개인의 삶에서 긍정적 변화나 성공적인 결과를 예고하는 꿈이에요.\n" +
                "재물운은 매우 긍정적입니다. 예상치 못한 금전적 기회가 다가오거나, 재정적인 안정을 경험할 수 있습니다. 재정적으로도 행운이 따를 시기이니, 투자나 재정 계획에 있어서 긍정적인 변화를 기대할 수 있습니다.\n").build();
        Lucky lucky15 = Lucky.builder().luckyImage("").keyword("제물운심몽").comment("\"충동적인 과소비 금지!\"\n" +
                "심몽은 내면의 갈등이나 심리적 불안을 나타내는 꿈으로, 자신의 감정이나 심리적 문제를 무의식적으로 처리하는 과정에서 나타납니다. 심몽은 심리적 부담이나 불안을 해소하는데 중요한 역할을 합니다.\n" +
                "재정적으로 다소 불안정한 시기입니다. 과도한 소비나 충동적인 지출을 자제하는 것이 중요합니다. 차분한 계획을 세워 재정 관리를 해 나가는 것이 필요합니다.\n").build();

        Lucky lucky16 = Lucky.builder().luckyImage("").keyword("직장운잡몽").comment("\"우리 차근차근 나아가봐요\"\n" +
                "잡몽은 일상적인 경험이 뒤섞인 꿈으로, 특별한 감정적 반응이나 심리적 변화를 주지 않는 경우가 많아요. 이런 꿈은 보통 스트레스나 피로로 인해 발생합니다. 사용자가 평소 겪은 일이나 기억들이 비논리적으로 엮여 나타나는 경우가 많아요. 꿈의 내용이 일상적이라 특별한 상징이 없지만, 때로는 자신이 놓친 감정이나 상황을 무의식적으로 처리하는 기회를 제공할 수 있어요. 잡몽을 꾸는 것은 뇌가 감정적, 신경적 부담을 풀기 위한 자연스러운 반응으로 볼 수 있습니다.\n" +
                "직장에서의 분위기는 평온하지만 지루할 수 있어요. 자극적이지 않지만 안정적인 상태이기 때문에, 급격한 변화나 혁신은 기대하기 어려운 시기입니다. 자신에게 주어진 업무를 차근차근 처리하는 것이 중요해요. 일에서 소소한 성과를 쌓아 가며, 차후 더 큰 기회를 위해 준비하는 시간이 될 것입니다.").build();
        Lucky lucky17 = Lucky.builder().luckyImage("").keyword("직장운허몽").comment("\"기회를 쟁취하는 자!\"\n" +
                "허몽은 비현실적이고 상상적인 꿈으로, 현실에서 일어날 법하지 않은 상황들이 발생하는 꿈이에요. 이런 꿈은 뇌의 창의력과 상상력이 뚜렷하게 나타난 경우로, 무의식적으로 새로운 가능성이나 기회를 예고하는 경우가 많습니다. 허몽은 때로 뇌가 창의적인 방식으로 문제를 해결하거나, 자기 발전을 위한 무언가를 추구하는 과정을 나타낼 수 있어요.\n" +
                "직장에서의 혁신적 변화나 새로운 프로젝트에 대한 기회가 있을 수 있습니다. 허몽이 창의적이고 비현실적인 요소를 반영한 만큼, 직장에서의 역할이나 업무가 예상과 다르게 발전할 가능성도 존재합니다. 이런 변화를 받아들이고, 유연하게 적응하는 자세가 필요합니다.\n").build();
        Lucky lucky18 = Lucky.builder().luckyImage("").keyword("직장운영몽").comment("\"지금 다가오고 있는 기회를 놓치지 마세요!\"\n" +
                "영몽은 미래를 예고하는 듯한 직관적인 꿈으로, 때로는 예상치 못한 신호나 암시를 제공하는 경우가 많아요. 이 꿈은 뇌가 과거의 경험이나 현재의 상황을 바탕으로 미래를 예측하거나 중요한 정보를 직관적으로 전달하려는 경우입니다. 영몽은 신경과학적으로도 미래 예측 능력을 사용하는 뇌의 반응으로 해석할 수 있어요.\n" +
                "직장에서는 급격한 변화가 예고됩니다. 영몽은 다가오는 상황에 대한 직감적인 신호를 제공하므로, 직장 내에서 새로운 기회나 예상치 못한 상황에 대비해야 합니다. 직장에서의 상상치 못한 기회를 잘 포착하세요.\n").build();
        Lucky lucky19 = Lucky.builder().luckyImage("").keyword("직장운정몽").comment("\"열심히 노력한 사람에게 기회가 찾아오는 법이죠!\"\n" +
                "정몽은 행운과 긍정적인 기회를 나타내는 꿈으로, 기회나 성공을 암시합니다. 길몽이라고도 불리며, 개인의 삶에서 긍정적 변화나 성공적인 결과를 예고하는 꿈이에요.\n" +
                "직장에서 승진이나 긍정적인 변화가 있을 가능성이 큽니다. 직장 내에서의 기회나 상사의 인정이 따를 수 있는 시점이에요. 적극적으로 자신의 능력을 발휘하며 승진의 기회를 잡아 보세요.\n").build();
        Lucky lucky20 = Lucky.builder().luckyImage("").keyword("직장운심몽").comment("\"감정 컨트롤이 중요한 날이에요\"\n" +
                "심몽은 내면의 갈등이나 심리적 불안을 나타내는 꿈으로, 자신의 감정이나 심리적 문제를 무의식적으로 처리하는 과정에서 나타납니다. 심몽은 심리적 부담이나 불안을 해소하는데 중요한 역할을 합니다.\n" +
                "직장에서의 갈등이나 불안감이 있을 수 있어요. 현재 상황에서 직장 내 관계나 업무에 대해 심리적인 불편함이 있을 수 있으므로, 자신의 감정을 잘 관리하는 것이 중요합니다.\n").build();

        Lucky lucky21 = Lucky.builder().luckyImage("").keyword("학업시험운잡몽").comment("\"한 발, 한 발 꾸준히 나아가요\"\n" +
                "잡몽은 일상적인 경험이 뒤섞인 꿈으로, 특별한 감정적 반응이나 심리적 변화를 주지 않는 경우가 많아요. 이런 꿈은 보통 스트레스나 피로로 인해 발생합니다. 사용자가 평소 겪은 일이나 기억들이 비논리적으로 엮여 나타나는 경우가 많아요. 꿈의 내용이 일상적이라 특별한 상징이 없지만, 때로는 자신이 놓친 감정이나 상황을 무의식적으로 처리하는 기회를 제공할 수 있어요. 잡몽을 꾸는 것은 뇌가 감정적, 신경적 부담을 풀기 위한 자연스러운 반응으로 볼 수 있습니다.\n" +
                "학업이나 시험 준비에서 큰 변화는 없지만, 꾸준히 노력하는 것이 중요해요. 잡몽을 꾸었다면 그만큼 과도한 스트레스나 불안이 적고, 마음이 평온한 상태일 수 있어요. 이런 평온함 속에서 차분히 준비하는 것이 중요합니다. 성적이 기대 이상으로 나올 수 있는 가능성도 존재하니, 무리하지 않고 꾸준히 해 나가는 것이 좋습니다.").build();
        Lucky lucky22 = Lucky.builder().luckyImage("").keyword("학업시험운허몽").comment("\"새로운 시도가 큰 도움이 될 수 있어요!\"\n" +
                "허몽은 비현실적이고 상상적인 꿈으로, 현실에서 일어날 법하지 않은 상황들이 발생하는 꿈이에요. 이런 꿈은 뇌의 창의력과 상상력이 뚜렷하게 나타난 경우로, 무의식적으로 새로운 가능성이나 기회를 예고하는 경우가 많습니다. 허몽은 때로 뇌가 창의적인 방식으로 문제를 해결하거나, 자기 발전을 위한 무언가를 추구하는 과정을 나타낼 수 있어요.\n" +
                "학업에서 새로운 방식이나 창의적인 접근이 필요한 시기입니다. 기존의 공부 방법에서 벗어나 새로운 학습법을 시도해보는 것이 유익할 수 있어요. 독창적이고 창의적인 아이디어를 통해 시험 준비에 큰 도움이 될 것입니다. 과감히 시도해보세요!\n").build();
        Lucky lucky23 = Lucky.builder().luckyImage("").keyword("학업시험운영몽").comment("\"예상치 못한 기회가 나에게?\"\n" +
                "영몽은 미래를 예고하는 듯한 직관적인 꿈으로, 때로는 예상치 못한 신호나 암시를 제공하는 경우가 많아요. 이 꿈은 뇌가 과거의 경험이나 현재의 상황을 바탕으로 미래를 예측하거나 중요한 정보를 직관적으로 전달하려는 경우입니다. 영몽은 신경과학적으로도 미래 예측 능력을 사용하는 뇌의 반응으로 해석할 수 있어요.\n" +
                "학업에서 중요한 변화나 발전이 있을 수 있습니다. 시험을 준비하면서, 예상치 못한 팁이나 직관적인 힌트를 얻을 수 있을 것입니다. 직감을 활용하여 학업 목표를 달성하세요.\n").build();
        Lucky lucky24 = Lucky.builder().luckyImage("").keyword("학업시험운정몽").comment("\"10점 만점에 100점!\"\n" +
                "정몽은 행운과 긍정적인 기회를 나타내는 꿈으로, 기회나 성공을 암시합니다. 길몽이라고도 불리며, 개인의 삶에서 긍정적 변화나 성공적인 결과를 예고하는 꿈이에요.\n" +
                "학업에서도 좋은 결과를 예상할 수 있습니다. 공부나 시험에서 성공적인 결과가 따를 가능성이 큽니다. 집중하고 노력하면 원하는 성과를 얻을 수 있어요.\n").build();
        Lucky lucky25 = Lucky.builder().luckyImage("").keyword("학업시험운심몽").comment("\"마음의 짐을 하나씩 내려놓아요\"\n" +
                "심몽은 내면의 갈등이나 심리적 불안을 나타내는 꿈으로, 자신의 감정이나 심리적 문제를 무의식적으로 처리하는 과정에서 나타납니다. 심몽은 심리적 부담이나 불안을 해소하는데 중요한 역할을 합니다.\n" +
                "학업에서의 불안이나 스트레스가 꿈에 나타났을 수 있습니다. 집중력을 높이기 위한 마음의 준비와 정신적 회복이 필요합니다. 스트레스를 관리하며 준비하는 것이 중요합니다.\n").build();

        Lucky lucky26 = Lucky.builder().luckyImage("").keyword("건강운잡몽").comment("\"충분한 휴식은 필수!\"\n" +
                "잡몽은 일상적인 경험이 뒤섞인 꿈으로, 특별한 감정적 반응이나 심리적 변화를 주지 않는 경우가 많아요. 이런 꿈은 보통 스트레스나 피로로 인해 발생합니다. 사용자가 평소 겪은 일이나 기억들이 비논리적으로 엮여 나타나는 경우가 많아요. 꿈의 내용이 일상적이라 특별한 상징이 없지만, 때로는 자신이 놓친 감정이나 상황을 무의식적으로 처리하는 기회를 제공할 수 있어요. 잡몽을 꾸는 것은 뇌가 감정적, 신경적 부담을 풀기 위한 자연스러운 반응으로 볼 수 있습니다.\n" +
                "건강 상태는 좋은 편이지만, 과로나 스트레스가 쌓이지 않도록 신경 써야 해요. 잡몽은 정신적 피로가 반영된 경우가 많기 때문에, 충분한 휴식과 자아 회복이 필요합니다. 규칙적인 운동이나 명상을 통해 신체적, 정신적 회복을 도와주세요.").build();
        Lucky lucky27 = Lucky.builder().luckyImage("").keyword("건강운허몽").comment("\"충분한 휴식을 취해주세요\"\n" +
                "허몽은 비현실적이고 상상적인 꿈으로, 현실에서 일어날 법하지 않은 상황들이 발생하는 꿈이에요. 이런 꿈은 뇌의 창의력과 상상력이 뚜렷하게 나타난 경우로, 무의식적으로 새로운 가능성이나 기회를 예고하는 경우가 많습니다. 허몽은 때로 뇌가 창의적인 방식으로 문제를 해결하거나, 자기 발전을 위한 무언가를 추구하는 과정을 나타낼 수 있어요.\n" +
                "건강은 전반적으로 양호하나, 정신적 과중이나 스트레스가 누적될 수 있습니다. 허몽이 비현실적인 꿈이라 상상력이 활발하게 작용한 만큼, 정신적 회복을 위한 시간이 필요할 수 있습니다. 휴식과 자기 관리에 신경 써주세요.\n").build();
        Lucky lucky28 = Lucky.builder().luckyImage("").keyword("건강운영몽").comment("\"너무 신경 쓰지 않아도 돼요\"\n" +
                "영몽은 미래를 예고하는 듯한 직관적인 꿈으로, 때로는 예상치 못한 신호나 암시를 제공하는 경우가 많아요. 이 꿈은 뇌가 과거의 경험이나 현재의 상황을 바탕으로 미래를 예측하거나 중요한 정보를 직관적으로 전달하려는 경우입니다. 영몽은 신경과학적으로도 미래 예측 능력을 사용하는 뇌의 반응으로 해석할 수 있어요.\n" +
                "건강은 양호하지만, 신경 과민이 있을 수 있습니다. 스트레스나 불안감이 건강에 영향을 미칠 수 있으니, 영몽에서 나타나는 신호를 잘 해석하고 마음을 평온하게 유지하세요.\n").build();
        Lucky lucky29 = Lucky.builder().luckyImage("").keyword("건강운정몽").comment("\"당신이 오늘의 에너자이저?\"\n" +
                "정몽은 행운과 긍정적인 기회를 나타내는 꿈으로, 기회나 성공을 암시합니다. 길몽이라고도 불리며, 개인의 삶에서 긍정적 변화나 성공적인 결과를 예고하는 꿈이에요.\n" +
                "건강 상태는 매우 양호합니다. 에너지가 넘치는 시기이니, 운동이나 활동을 통해 더 건강한 상태를 유지할 수 있습니다.\n").build();
        Lucky lucky30 = Lucky.builder().luckyImage("").keyword("건강운심몽").comment("\"공기 좋은 곳에서 명상 어떠세요?\"\n" +
                "심몽은 내면의 갈등이나 심리적 불안을 나타내는 꿈으로, 자신의 감정이나 심리적 문제를 무의식적으로 처리하는 과정에서 나타납니다. 심몽은 심리적 부담이나 불안을 해소하는데 중요한 역할을 합니다.\n" +
                "정신적 스트레스가 신체 건강에 영향을 미칠 수 있습니다. 자기 회복과 스트레스 관리가 중요해요. 명상이나 휴식을 통해 몸과 마음의 균형을 맞추는 것이 필요합니다.\n").build();

        luckyRepository.save(lucky1);
        luckyRepository.save(lucky2);
        luckyRepository.save(lucky3);
        luckyRepository.save(lucky4);
        luckyRepository.save(lucky5);
        luckyRepository.save(lucky6);
        luckyRepository.save(lucky7);
        luckyRepository.save(lucky8);
        luckyRepository.save(lucky9);
        luckyRepository.save(lucky10);
        luckyRepository.save(lucky11);
        luckyRepository.save(lucky12);
        luckyRepository.save(lucky13);
        luckyRepository.save(lucky14);
        luckyRepository.save(lucky15);
        luckyRepository.save(lucky16);
        luckyRepository.save(lucky17);
        luckyRepository.save(lucky18);
        luckyRepository.save(lucky19);
        luckyRepository.save(lucky20);
        luckyRepository.save(lucky21);
        luckyRepository.save(lucky22);
        luckyRepository.save(lucky23);
        luckyRepository.save(lucky24);
        luckyRepository.save(lucky25);
        luckyRepository.save(lucky26);
        luckyRepository.save(lucky27);
        luckyRepository.save(lucky28);
        luckyRepository.save(lucky29);
        luckyRepository.save(lucky30);

    }

}