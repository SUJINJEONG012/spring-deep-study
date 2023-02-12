package study.advanced.app.v3;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import study.advanced.trace.TraceStatus;
import study.advanced.trace.logtrace.LogTrace;

@RestController
@RequiredArgsConstructor
public class OrderControllerV3 {

	private final OrderServiceV3 orderService;
	private final LogTrace trace;
	
	@GetMapping("/v3/request") //요청이 오면 아래 메서드가 호출
	public String request(String itemId) {
		
		TraceStatus status = null;
		
		//예외가 되더라도 로그가 되게끔 try처리
		try {
	    status = trace.begin("OrderController.request()");
		orderService.orderItem(itemId);
		trace.end(status);
		return "ok";
		
		}catch (Exception e) {	
			trace.exception(status, e);
			throw e; // 예외를꼭 다시 던져주어야 한다. 
		}
		
		
	}
	
	
}
