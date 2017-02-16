package com.instanceKill.dto;
//����service��web������ݴ���
/**
 * ��¶��ɱ��ַDTO�����ݴ���㣩
 * @author Administrator
 *
 */
public class Exposer {

	//�Ƿ�����ɱ
	private boolean exposed;
	
	//һ�ּ��ܴ�ʩ
	private String md5;
	
	//��ɱ��ƷID
	private long instanceKillId;
	
	//ϵͳ��ǰʱ�䣨���룩
	private long now;
	
	//��ɱ����ʱ��
	private long start;
	
	//��ɱ����ʱ��
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
