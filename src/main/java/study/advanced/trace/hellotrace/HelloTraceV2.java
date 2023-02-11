package study.advanced.trace.hellotrace;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import study.advanced.trace.TraceId;
import study.advanced.trace.TraceStatus;

@Slf4j
@Component
public class HelloTraceV2 {
	
	private static final String START_PREFIX = "-->";
	private static final String COMPLETE_PREFIX ="<--";
	private static final String EX_PREFIX = "<X-";
	

	public TraceStatus begin(String message) {
		TraceId traceId = new TraceId();
		Long startTimeMs = System.currentTimeMillis();
		log.info("[{}] {} {} ", traceId.getId(), addSpace(START_PREFIX, traceId.getLevel()), message);
		//로그출력
		return new TraceStatus(traceId, startTimeMs, message);
	};
	
	public void end(TraceStatus status) {
		complete(status, null);
	}
	
	//예외로 처리될때
	public void exception(TraceStatus status, Exception e) {
		complete(status, e);
	}; 
	
	
	private void complete(TraceStatus status, Exception e){
		Long stopTimeMs = System.currentTimeMillis();
		long resultTimeMs = stopTimeMs - status.getStartITmeMs();
		TraceId traceId = status.getTraceId();
		
		if(e == null) {
			log.info("[{}] {} {} time={}ms", traceId.getId(), addSpace(COMPLETE_PREFIX, traceId.getLevel()), status.getMessage(), resultTimeMs);
		}else {
			log.info("[{}] {}{} time={}ms ex={}", traceId.getId(), addSpace(EX_PREFIX, traceId.getLevel()), status.getMessage(), resultTimeMs, e.toString());
		}
	}
	
	
	private Object addSpace(String prefix, int level) {
		StringBuilder sb = new StringBuilder();
		
		for(int i =0; i <level; i++) {
			sb.append((i==level - 1) ? "|" + prefix : "|   ");
		}
		return sb.toString();
	}
	
}