package study.advanced.app.v0;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OrderControllerV0 {

	private final OrderServiceV0 orderServiceV0;
	
	@GetMapping("/v0/request") //요청이 오면 아래 메서드가 호출
	public String request(String itemId) {
		orderServiceV0.orderItem(itemId);
		return "ok"; //문자반환
	}
	
	
}
