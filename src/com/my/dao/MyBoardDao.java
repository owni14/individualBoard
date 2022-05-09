package com.my.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.GapContent;

import com.my.vo.MyBoardVo;
import com.my.vo.MyPagingDto;

public class MyBoardDao {
	private Connection conn;
	// 가져온 Connection값 저장
	public MyBoardDao(Connection conn) {
		this.conn = conn;
	}
	
	private void closeAll(PreparedStatement pstmt, ResultSet rs) {
		if (pstmt != null) try { pstmt.close(); } catch (Exception e) { }
		if (rs != null) try { rs.close(); } catch (Exception e) { }
	}
	
	public List<MyBoardVo> getList(MyPagingDto pagingDto) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String searchType = pagingDto.getSearchType();
		String keyword = pagingDto.getKeyword();
		try {
			String sql = "select * from" + 
					"    	(select rownum rnum, a.* from" + 
					"        	(select * from my_board";
				   sql += getQuery(searchType, keyword);
				   sql += "       	 order by g_no desc, re_level asc)a )" + 
					      "	  where rnum between ? and ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pagingDto.getPageStartRow());
			pstmt.setInt(2, pagingDto.getPageEndRow());
			rs = pstmt.executeQuery();
			List<MyBoardVo> list = new ArrayList<>();
			while (rs.next()) {
				int b_no = rs.getInt("b_no");
				int g_no = rs.getInt("g_no");
				String b_title = rs.getString("b_title");
				String b_content = rs.getString("b_content");
				String id = rs.getString("id");
				Date b_date = rs.getDate("b_date");
				int read_count = rs.getInt("read_count");
				int re_seq = rs.getInt("re_seq");
				int re_level = rs.getInt("re_level");
				MyBoardVo vo = new MyBoardVo(b_no, g_no, b_content, b_title, b_date, id, read_count, re_seq, re_level);
				list.add(vo);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(pstmt, rs);
		}
		return null;
	}
	
	public boolean insertArticle(MyBoardVo myBoardVo) {
		PreparedStatement pstmt = null;
		try {
			String sql = "insert into my_board (b_no, g_no, b_title, b_content, id)"
					+ "	  values (my_seq.nextval, my_seq.nextval, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, myBoardVo.getB_title());
			pstmt.setString(2, myBoardVo.getB_content());
			pstmt.setString(3, myBoardVo.getId());
			int count = pstmt.executeUpdate();
			if (count > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(pstmt, null);
		}
		return false;
	}
	
	public MyBoardVo getArticleByBno(int b_no) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from my_board"
					+ "	  where b_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b_no);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int g_no = rs.getInt("g_no");
				String b_title = rs.getString("b_title");
				String b_content = rs.getString("b_content");
				Date b_date = rs.getDate("b_date");
				String id = rs.getString("id");
				int read_count = rs.getInt("read_count");
				int re_seq = rs.getInt("re_seq");
				int re_level = rs.getInt("re_level");
				MyBoardVo myBoardVo = new MyBoardVo(b_no, g_no, b_title, b_content, b_date, id, read_count, re_seq, re_level);
				return myBoardVo;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(pstmt, rs);
		}
		return null;
	}
	
	public boolean deleteArticle(int b_no) {
		PreparedStatement pstmt = null;
		try {
			String sql = "delete from my_board"
					+ "   where b_no = " + b_no;
			pstmt = conn.prepareStatement(sql);
			int count = pstmt.executeUpdate();
			if (count > 0) {
				return true;
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(pstmt, null);
		}
		return false;
	}
	
	public boolean updateArticle(MyBoardVo myBoardVo) {
		PreparedStatement pstmt = null;
		try {
			String sql = "update my_board set"
					+ "   b_title = ?, b_content = ?"
					+ "	  where b_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, myBoardVo.getB_title());
			pstmt.setString(2, myBoardVo.getB_content());
			pstmt.setInt(3, myBoardVo.getB_no());
			int count = pstmt.executeUpdate();
			if (count > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(pstmt, null);
		}
		return false;
	}
	
	public void increaseCount(int b_no) {
		PreparedStatement pstmt = null;
		try {
			String sql = "update my_board set"
					+ "   read_count = read_count + 1"
					+ "   where b_no = " + b_no;
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(pstmt, null);
		}
	}
	
	public boolean updateContentAndInsertReply(MyBoardVo vo) {
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		try {
			String sql1 = "update my_board set"
					+ "	   re_seq = re_seq + 1"
					+ "	   where g_no = ? and re_seq > ?";
			String sql2 = "insert into my_board"
					+ "	   values (my_seq.nextval, ?, ?, ?, null, sysdate, ?, 0, ?, ?)";
			pstmt1 = conn.prepareStatement(sql1);
			pstmt2 = conn.prepareStatement(sql2);
			
			pstmt1.setInt(1, vo.getG_no());
			pstmt1.setInt(2, vo.getRe_seq());
			
			pstmt2.setInt(1, vo.getG_no());
			pstmt2.setString(2, vo.getB_title());
			pstmt2.setString(3, vo.getB_content());
			pstmt2.setString(4, vo.getId());
			pstmt2.setInt(5, vo.getRe_seq() + 1);
			pstmt2.setInt(6, vo.getRe_level() + 1);
			int count1 = pstmt1.executeUpdate();
			int count2 = pstmt2.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(pstmt1, null);
			closeAll(pstmt2, null);
		}
		return false;
	}
	
	public int getTotalCount(MyPagingDto pagingDto) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String searchType = pagingDto.getSearchType();
		String keyword = pagingDto.getKeyword();
		try {
			String sql = "select count(*) cnt from my_board";
			       sql += getQuery(searchType, keyword);
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("cnt");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(pstmt, rs);
		}
		return 0;
	}
	
	private String getQuery(String searchType, String keyword) {
		String sql = "";
		if (searchType != null && !(searchType == "") && keyword != null && !(keyword == "")) {
			switch (searchType) {
			case "tit":
				sql += " where b_title like '%" + keyword + "%'";
				break;
			case "cont":
				sql += " where b_content like '%" + keyword + "%'";
				break;
			case "wrt":
				sql += " where id like '%" + keyword + "%'";
				break;
			case "tit+cont":
				sql += " where b_title like '%" + keyword + "%'" + " or " + "b_content like '%" + keyword + "%'";
				break;
			case "tit+cont+wrt":
				sql += " where b_title like '%" + keyword + "%'" + " or " + "b_content like '%" + keyword + "%'" + " or " + "id like '%" + keyword + "%'";
				break;
			}
		}
		return sql;
	}
	
	public boolean updatePoint(String id, int point) {
		PreparedStatement pstmt = null;
		try {
			String sql = "update t_member set"
					+ "   point = point + ?"
					+ "   where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, point);
			pstmt.setString(2, id);
			int count = pstmt.executeUpdate();
			if (count > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(pstmt, null);
		}
		return false;
	}
}
