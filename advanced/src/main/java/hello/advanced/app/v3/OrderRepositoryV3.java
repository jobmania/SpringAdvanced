package hello.advanced.app.v3;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class OrderRepositoryV3 {
    private final LogTrace trace;

    public void save( String itemId){
        TraceStatus status = null;
        try{
            status = trace.begin("OrderRepository.request()");
            // 저장 로직
            if(itemId.equals("ex")){
                throw new IllegalStateException("예외 발생!");
            }
            sleep(1000);
            trace.end(status);
        }catch (Exception e){
            trace.exception(status, e);
            throw e; // 예외를 던져줘야합니당.~
        }
    }
    private void sleep(int mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
