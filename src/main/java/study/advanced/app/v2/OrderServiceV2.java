package study.advanced.app.v2;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import study.advanced.trace.TraceId;
import study.advanced.trace.TraceStatus;
import study.advanced.trace.hellotrace.HelloTraceV2;

@Service
@RequiredArgsConstructor
public class OrderServiceV2 {

	private final OrderRepositoryV2 orderRepository;
	private final HelloTraceV2 trace;

	 public void orderItem(TraceId traceId, String itemId) {
		
		TraceStatus status = null;

		// 예외가 되더라도 로그가 되게끔 try처리
		try {
			status = trace.beginSync( traceId, "OrderService.orderItem()");
			orderRepository.save(traceId, itemId);
			trace.end(status);
			

		} catch (Exception e) {
			trace.exception(status, e);
			throw e; 
		}
		
	}

}
