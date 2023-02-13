package study.advanced.trace.threadlocal.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadLocalService {
	
	private ThreadLocal<String> nameStore = new ThreadLocal(); //<T> 타입지정, 난 String
	
	public String logic(String name) {
		log.info("저장 name={} -> nameStore={}", name, nameStore);
		nameStore = name;
		sleep(1000); //1초 쉬고
		log.info("조회 nameStore={}", nameStore);
		return nameStore;
	}

	private void sleep(int millis) {
		try {
			Thread.sleep(millis);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
