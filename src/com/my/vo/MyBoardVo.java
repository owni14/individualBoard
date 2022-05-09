package com.my.vo;

import java.sql.Date;

public class MyBoardVo {
	int b_no;
	int g_no;
	String b_title;
	String b_content;
	String b_file_name;
	Date b_date;
	String id;
	int read_count;
	int re_seq;
	int re_level;
	
	
	public MyBoardVo() {
		super();
	}
	
	// 글내용 수정시 사용할 메서드
	public MyBoardVo(int b_no, String b_title, String b_content) {
		super();
		this.b_no = b_no;
		this.b_title = b_title;
		this.b_content = b_content;
	}

	// 글쓰기 입력시 담을 메서드
	public MyBoardVo(String b_title, String b_content, String id) {
		super();
		this.b_title = b_title;
		this.b_content = b_content;
		this.id = id;
	}
	
	
	// 답글의 내용을 담을 메서드
	public MyBoardVo(int g_no, String b_title, String b_content, String id, int re_seq, int re_level) {
		super();
		this.g_no = g_no;
		this.b_title = b_title;
		this.b_content = b_content;
		this.id = id;
		this.re_seq = re_seq;
		this.re_level = re_level;
	}

	// 글내용안에 있는 데이터를 가져오기 위해 사용하는 메서드
	public MyBoardVo(int b_no, int g_no, String b_title, String b_content, Date b_date, String id, int read_count, int re_seq, int re_level) {
		super();
		this.b_no = b_no;
		this.g_no = g_no;
		this.b_title = b_title;
		this.b_content = b_content;
		this.b_date = b_date;
		this.id = id;
		this.read_count = read_count;
		this.re_seq = re_seq;
		this.re_level = re_level;
	}
	
	

	// 글목록 출력할때 담을 메서드
	public MyBoardVo(int b_no, int g_no, String b_title, String b_content, String b_file_name, Date b_date, String id,
			int read_count, int re_seq, int re_level) {
		super();
		this.b_no = b_no;
		this.g_no = g_no;
		this.b_title = b_title;
		this.b_content = b_content;
		this.b_file_name = b_file_name;
		this.b_date = b_date;
		this.id = id;
		this.read_count = read_count;
		this.re_seq = re_seq;
		this.re_level = re_level;
	}
	
	public MyBoardVo(int b_no, String b_title, Date b_date, String id, int read_count) {
		super();
		this.b_no = b_no;
		this.b_title = b_title;
		this.b_date = b_date;
		this.id = id;
		this.read_count = read_count;
	}

	public int getB_no() {
		return b_no;
	}

	public void setB_no(int b_no) {
		this.b_no = b_no;
	}

	public int getG_no() {
		return g_no;
	}

	public void setG_no(int g_no) {
		this.g_no = g_no;
	}

	public String getB_title() {
		return b_title;
	}

	public void setB_title(String b_title) {
		this.b_title = b_title;
	}

	public String getB_content() {
		return b_content;
	}

	public void setB_content(String b_content) {
		this.b_content = b_content;
	}

	public String getB_file_name() {
		return b_file_name;
	}

	public void setB_file_name(String b_file_name) {
		this.b_file_name = b_file_name;
	}

	public Date getB_date() {
		return b_date;
	}

	public void setB_date(Date b_date) {
		this.b_date = b_date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getRead_count() {
		return read_count;
	}

	public void setRead_count(int read_count) {
		this.read_count = read_count;
	}

	public int getRe_seq() {
		return re_seq;
	}

	public void setRe_seq(int re_seq) {
		this.re_seq = re_seq;
	}

	public int getRe_level() {
		return re_level;
	}

	public void setRe_level(int re_level) {
		this.re_level = re_level;
	}

	@Override
	public String toString() {
		return "MyBoardVo [b_no=" + b_no + ", g_no=" + g_no + ", b_title=" + b_title + ", b_content=" + b_content
				+ ", b_file_name=" + b_file_name + ", b_date=" + b_date + ", id=" + id + ", read_count=" + read_count
				+ ", re_seq=" + re_seq + ", re_level=" + re_level + "]";
	}
	
}
