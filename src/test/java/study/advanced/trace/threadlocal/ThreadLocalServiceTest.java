package study.advanced.trace.threadlocal;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;
import study.advanced.trace.threadlocal.code.ThreadLocalService;

@Slf4j
public class ThreadLocalServiceTest {
	
	private ThreadLocalService ThreadLocalservice  = new ThreadLocalService();
	
	@Test
	void field() {
		log.info("main start");
		
		//쓰레드1 
		Runnable userA = () -> {
			ThreadLocalservice.logic("userA");
		};
		
		//쓰레드2
		Runnable userB = () -> {
			ThreadLocalservice.logic("userB");
		};
		
		
		Thread threadA = new Thread(userA);
		threadA.setName("thread-A"); // 쓰레드 이름설정 
		Thread threadB = new Thread(userB);
		threadB.setName("thread-B"); // 쓰레드 이름설정
		
		threadA.start(); // 쓰레드 A시작
		//sleep(2000); //2초동안 쉼, 동시성 문제 발생X
		sleep(100); //동시성 문제발생
		
		threadB.start(); //쓰레드 B시작
		sleep(2000); //메인 쓰레드 종료대기
		log.info("main exit");
		
		
	}

	private void sleep(int millis) {
		try {
			Thread.sleep(millis);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}	
	}
	
	
	
}
