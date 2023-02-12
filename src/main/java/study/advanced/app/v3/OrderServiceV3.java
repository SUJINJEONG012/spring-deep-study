package study.advanced.app.v3;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import study.advanced.trace.TraceStatus;
import study.advanced.trace.logtrace.LogTrace;

@Service
@RequiredArgsConstructor
public class OrderServiceV3 {

	private final OrderRepositoryV3 orderRepository;
	private final LogTrace trace;

	 public void orderItem(String itemId) {
		
		TraceStatus status = null;

		// 예외가 되더라도 로그가 되게끔 try처리
		try {
			status = trace.begin("OrderService.orderItem()");
			orderRepository.save(itemId);
			trace.end(status);
			

		} catch (Exception e) {
			trace.exception(status, e);
			throw e; 
		}
		
	}

}
