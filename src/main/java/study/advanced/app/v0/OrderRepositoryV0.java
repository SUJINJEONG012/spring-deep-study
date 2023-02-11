package study.advanced.app.v0;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV0 {

	
	public void save(String itemId) {
		
		//저장로직
		if(itemId.equals("ex")) {
			throw new IllegalStateException("예외발생");
		}
		//상품저장에 1초
		sleep(1000);
	}

	private void sleep(int millis) {
		try {
			Thread.sleep(millis);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
