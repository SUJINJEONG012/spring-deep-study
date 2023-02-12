package study.advanced.trace.threadlocal;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;
import study.advanced.trace.threadlocal.code.FieldService;

@Slf4j
public class FieldServiceTest {
	
	private FieldService fieldService  = new FieldService();
	
	@Test
	void field() {
		log.info("main start");
		
		Runnable userA = () -> {
			fieldService.logic("userA");
		};
		
		Runnable userB = () -> {
			fieldService.logic("userB");
		};
		
		Thread threadA = new Thread(userA);
		threadA.setName("thread-A");
		Thread threadB = new Thread(userB);
		threadB.setName("thread-B");	
		
	}
	
}
