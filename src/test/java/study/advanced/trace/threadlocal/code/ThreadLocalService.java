package study.advanced.trace.threadlocal.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadLocalService {
	
	private ThreadLocal<String> nameStore = new ThreadLocal(); //<T> 타입지정, 난 String
	
	public String logic(String name) {
		log.info("저장 name={} -> nameStore={}", name, nameStore.get()); //조회할때 get()
		nameStore.set(name); //set사용하면 쓰레드로컬을 사용할 수 있다. 
		sleep(1000); //1초 쉬고
		log.info("조회 nameStore={}", nameStore.get()); //조회할때 get으로 꺼냄
		return nameStore.get(); // return(꺼낼때) get()으로 꺼내면된다. 
	}

	private void sleep(int millis) {
		try {
			Thread.sleep(millis);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
