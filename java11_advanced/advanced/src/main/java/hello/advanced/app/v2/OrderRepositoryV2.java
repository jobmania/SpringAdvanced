package hello.advanced.app.v2;

import hello.advanced.trace.HelloTrace.HelloTraceV2;
import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class OrderRepositoryV2 {
    private final HelloTraceV2 trace;

    public void save(TraceId traceId, String itemId){


        TraceStatus status = null;
        try{
            status = trace.beginSync(traceId,"OrderRepository.request()");



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
