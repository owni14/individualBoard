package com.my.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.my.vo.MyCommentVo;

public class MyCommentDao {
	private Connection conn;
	
	public MyCommentDao(Connection conn) {
		this.conn = conn;
	}
	
	private void closeAll(PreparedStatement pstmt, ResultSet rs) {
		if ( pstmt != null ) try { pstmt.close(); } catch (Exception e) { }
		if ( rs != null )    try { rs.close(); }    catch (Exception e) { }
	}
	
	public boolean insertComment(MyCommentVo myCommentVo) {
		PreparedStatement pstmt = null;
		try {
			String sql = "insert into my_comment (c_id, c_content, id, b_no)"
					+ "   values (seq_mycomment.nextval, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, myCommentVo.getC_content());
			pstmt.setString(2, myCommentVo.getId());
			pstmt.setInt(3, myCommentVo.getB_no());
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
	
	public List<MyCommentVo> getCommentList(int b_no) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from my_comment"
					+ "   where b_no = ?"
					+ "   order by c_date desc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b_no);
			rs = pstmt.executeQuery();
			List<MyCommentVo> list = new ArrayList<>();
			while (rs.next()) {
				int c_id = rs.getInt("c_id");
				String c_content = rs.getString("c_content");
				String id = rs.getString("id");
				Date c_date = rs.getDate("c_date");
				MyCommentVo myCommentVo = new MyCommentVo(c_id, c_content, id, b_no, c_date);
				list.add(myCommentVo);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(pstmt, rs);
		}
		return null;
	}
}
