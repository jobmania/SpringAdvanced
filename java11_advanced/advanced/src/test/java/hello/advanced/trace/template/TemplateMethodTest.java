package hello.advanced.trace.template;

import hello.advanced.trace.template.code.AbstractTemplate;
import hello.advanced.trace.template.code.SubClassLogic1;
import hello.advanced.trace.template.code.SubClassLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateMethodTest {
    @Test
    void templateMethodV0() {
        logic1();
        logic2();
    }


    /**
     * 템플릿 메서드 패턴 적용 ㅋ
     * */
    @Test
    void templateMethodV1(){
        AbstractTemplate template1 = new SubClassLogic1();
        template1.execute();

        AbstractTemplate template2 = new SubClassLogic2();
        template2.execute();
    }

    /**
     * 익명클래스 - 구체클래스 만들지 않고 사용 !
     * */
    @Test
    void templateMethodV2(){
        AbstractTemplate template1 = new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("비즈니스 로직 실행 11 !!!");
            }
        };
        log.info("익명클래스 이름={}",template1.getClass());
        template1.execute();

        AbstractTemplate template2 = new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("비즈니스 로직 실행 22 !!!");
            }
        };
        log.info("익명클래스 이름={}",template2.getClass());

        template2.execute();

    }

    /**
     * 미적용
     * */
    private void logic1() {
        long startTime = System.currentTimeMillis();
        // 비즈지스 로직 실행
        log.info("비즈니스 로직 1 실행");

        // 비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}",resultTime);
    }

    private void logic2() {
        long startTime = System.currentTimeMillis();
        // 비즈지스 로직 실행
        log.info("비즈니스 로직 2 실행");

        // 비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}",resultTime);
    }
}
