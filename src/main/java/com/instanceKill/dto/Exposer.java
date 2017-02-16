package com.instanceKill.dto;
//用于service与web层的数据传输
/**
 * 暴露秒杀地址DTO（数据传输层）
 * @author Administrator
 *
 */
public class Exposer {

	//是否开启秒杀
	private boolean exposed;
	
	//一种加密措施
	private String md5;
	
	//秒杀商品ID
	private long instanceKillId;
	
	//系统当前时间（毫秒）
	private long now;
	
	//秒杀开启时间
	private long start;
	
	//秒杀结束时间
	private long end;

	public Exposer(boolean exposed, String md5, long instanceKillId) {
		super();
		this.exposed = exposed;
		this.md5 = md5;
		this.instanceKillId = instanceKillId;
	}

	public Exposer(boolean exposed,long instanceKillId, long now, long start, long end) {
		super();
		this.exposed = exposed;
		this.instanceKillId=instanceKillId;
		this.now = now;
		this.start = start;
		this.end = end;
	}

	public Exposer(boolean exposed, long instanceKillId) {
		super();
		this.exposed = exposed;
		this.instanceKillId = instanceKillId;
	}

	public boolean isExposed() {
		return exposed;
	}

	public void setExposed(boolean exposed) {
		this.exposed = exposed;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public long getInstanceKillId() {
		return instanceKillId;
	}

	public void setInstanceKillId(long instanceKillId) {
		this.instanceKillId = instanceKillId;
	}

	public long getNow() {
		return now;
	}

	public void setNow(long now) {
		this.now = now;
	}

	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}

	public long getEnd() {
		return end;
	}

	public void setEnd(long end) {
		this.end = end;
	}

	@Override
	public String toString() {
		return "Exposer [exposed=" + exposed + ", md5=" + md5
				+ ", instanceKillId=" + instanceKillId + ", now=" + now
				+ ", start=" + start + ", end=" + end + "]";
	}
	
}
