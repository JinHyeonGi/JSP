package board.model;

import java.sql.Timestamp;

public class BoardVO {

	// 게시글 정보
	private int num; 
	private String writer;
	private String email;
	private String subject;
	private String pass;
	private int readcount;
	
	private int ref; // 게시글 그룹
	private int step; // 그룹간 순위
	private int depth; // 들여쓰기 깊이
	
	private Timestamp regdate;
	private String content;
	private String ip;
	
	// Constructor
	public BoardVO() {
	}
	public BoardVO(int num, String writer, String email, String subject,
			String pass, int readcount, int ref, int step,
			int depth, Timestamp regdate, String content, String ip) {
		super();
		this.num = num;
		this.writer = writer;
		this.email = email;
		this.subject = subject;
		this.pass = pass;
		this.readcount = readcount;
		this.ref = ref;
		this.step = step;
		this.depth = depth;
		this.regdate = regdate;
		this.content = content;
		this.ip = ip;
	}
	
	public BoardVO(int num, String writer, String email, String subject, 
			String pass, int ref, int step, int depth,
			Timestamp regdate, String content, String ip) {
		super();
		this.num = num;
		this.writer = writer;
		this.email = email;
		this.subject = subject;
		this.pass = pass;
		this.ref = ref;
		this.step = step;
		this.depth = depth;
		this.regdate = regdate;
		this.content = content;
		this.ip = ip;
	}
	
	// Getter, Setter
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public int getReadcount() {
		return readcount;
	}

	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}

	public int getRef() {
		return ref;
	}

	public void setRef(int ref) {
		this.ref = ref;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public Timestamp getRegdate() {
		return regdate;
	}

	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
	public String toString() {
		return "BoardVO [num=" + num + ", writer=" + writer + ", email=" + email + ", subject=" + subject + ", pass="
				+ pass + ", readcount=" + readcount + ", ref=" + ref + ", step=" + step + ", depth=" + depth
				+ ", regdate=" + regdate + ", content=" + content + ", ip=" + ip + "]";
	}
	
}
