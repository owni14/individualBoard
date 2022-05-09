package com.my.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.my.vo.MyPointVo;

public class MyPointDao {
	private Connection conn;
	
	public MyPointDao(Connection conn) {
		this.conn = conn;
	}	
	
	private void closeAll(PreparedStatement pstmt, ResultSet rs) {
		if ( pstmt != null) try { pstmt.close(); } catch (Exception e) { }
		if ( rs != null)    try { rs.close(); }    catch (Exception e) { }
	}
	
	public int getPoint(int point_code) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			 String sql = "select point from my_point_code"
			 		+ "    where point_code = ?";
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setInt(1, point_code);
			 rs = pstmt.executeQuery();
			 if (rs.next()) {
				 int point = rs.getInt("point");
				 return point;
			 }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(pstmt, null);
		}
		return 0;
	}
	
	public boolean addPoint(MyPointVo myPointVo) {
		PreparedStatement pstmt = null;
		try {
			String sql = "insert into my_point (p_no, id, point, point_code, point_date)"
					+ "   values (seq_mypoint.nextval, ?, (select point from my_point_code"
					+ " 								   where point_code = ?), ?, sysdate)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, myPointVo.getId());
			pstmt.setInt(2, myPointVo.getPoint_code());
			pstmt.setInt(3, myPointVo.getPoint_code());
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
