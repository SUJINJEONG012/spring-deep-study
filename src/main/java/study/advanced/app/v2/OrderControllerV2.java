package study.advanced.app.v2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import study.advanced.trace.TraceStatus;
import study.advanced.trace.hellotrace.HelloTraceV2;

@RestController
@RequiredArgsConstructor
public class OrderControllerV2 {

	private final OrderServiceV2 orderService;
	private final HelloTraceV2 trace;
	
	@GetMapping("/v2/request") //요청이 오면 아래 메서드가 호출
	public String request(String itemId) {
		
		TraceStatus status = null;
		
		//예외가 되더라도 로그가 되게끔 try처리
		try {
	    status = trace.begin("OrderController.request()");
		orderService.orderItem(status.getTraceId(),itemId);
		trace.end(status);
		return "ok";
		
		}catch (Exception e) {	
			trace.exception(status, e);
			throw e; // 예외를꼭 다시 던져주어야 한다. 
		}
		
		
	}
	
	
}
