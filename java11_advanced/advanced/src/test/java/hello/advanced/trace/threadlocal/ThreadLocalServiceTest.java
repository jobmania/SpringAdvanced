package hello.advanced.trace.threadlocal;

import hello.advanced.trace.threadlocal.code.FieldService;
import hello.advanced.trace.threadlocal.code.ThreadLocalService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ThreadLocalServiceTest {
    private ThreadLocalService service = new ThreadLocalService();

    @Test
    void field(){
        log.info("main Start");
        Runnable userA = () ->{
            service.logic("userA");
        };

        Runnable userB = () -> {
            service.logic("userB");
        };

        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");
        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");

        threadA.start();
//        sleep(2000); // 동시성 문제 절대 안하지! -> 하지면 쓰레드 로컬해서 문제없지!
        sleep(100); // 짧게 하면 동시성 문제 발생하지~. 1번째 실행하는데는 1초 걸리지롱 -> 하지면 쓰레드 로컬해서 문제없지!
        threadB.start();

        sleep(3000);    /// 메인 쓰레드 종료 대기..

        log.info("main exit");
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
