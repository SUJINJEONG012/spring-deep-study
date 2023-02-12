package study.advanced.app.v2;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import study.advanced.trace.TraceId;
import study.advanced.trace.TraceStatus;
import study.advanced.trace.hellotrace.HelloTraceV2;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV2 {

	private final HelloTraceV2 trace;
	
	
	public void save(TraceId traceId, String itemId) {
		
		TraceStatus status = null;
		
		try {
			status = trace.beginSync(traceId, "OrderRepository.save()");
			//저장로직
			if(itemId.equals("ex")) {
				throw new IllegalStateException("예외발생");
			}
			//상품저장에 1초
			sleep(1000);
			trace.end(status);
			
		}catch(Exception e) {
			trace.exception(status, e);
			throw e;
		}
	
		
	}

	private void sleep(int millis) {
		try {
			Thread.sleep(millis);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
