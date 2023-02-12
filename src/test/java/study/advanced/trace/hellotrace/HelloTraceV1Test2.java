package study.advanced.trace.hellotrace;

import org.junit.jupiter.api.Test;

import study.advanced.trace.TraceStatus;

public class HelloTraceV1Test2 {

	@Test
	void begin_end() {
		HelloTraceV2 trace = new HelloTraceV2();
		TraceStatus status1 = trace.begin("hello1");
		TraceStatus status2 = trace.beginSync(status1.getTraceId(), "hello2");
		trace.end(status2); 
		trace.end(status1);
	}
	
	@Test
	void begin_exception() {
		HelloTraceV2 trace = new HelloTraceV2();	
		TraceStatus status1 = trace.begin("hello1");
		TraceStatus status2 = trace.beginSync(status1.getTraceId(), "hello2");
		trace.exception(status2, new IllegalStateException());
		trace.exception(status1, new IllegalStateException());
	}
}
