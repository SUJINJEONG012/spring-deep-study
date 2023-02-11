package study.advanced.trace;

// 로그의 상태정보
public class TraceStatus {
	
	private TraceId traceId; //로그아이디
	private Long startTimeMs; //로그시간 
	private String message; //로그내용
	
	public TraceStatus(TraceId traceId, Long startTimeMs, String message) {
		this.traceId = traceId;
		this.startTimeMs = startTimeMs; 
		this.message = message;
	}

	public TraceId getTraceId() {
		return traceId;
	}

	public Long getStartITmeMs() {
		return startTimeMs;
	}

	public String getMessage() {
		return message;
	}


}
