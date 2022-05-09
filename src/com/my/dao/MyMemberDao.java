package com.my.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.my.vo.MyMemberVo;

public class MyMemberDao {
	private Connection conn;

	public MyMemberDao(Connection conn) {
		this.conn = conn;
	}
	
	private void close(PreparedStatement pstmt, ResultSet rs) {
		if ( pstmt != null ) try { pstmt.close(); } catch (Exception e) { }
		if ( rs != null ) try { rs.close(); } catch (Exception e) { }
	}
	
	public List<MyMemberVo> listMembers() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select * from t_member order by id";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			List<MyMemberVo> list = new ArrayList<>();
			while (rs.next()) {
				String id     = rs.getString("id");
				String pwd    = rs.getString("pwd");
				String sname   = rs.getString("name");
				String email  = rs.getString("email");
				Date joindate = rs.getDate("joindate");
				
				MyMemberVo Mvo = new MyMemberVo(id, pwd, sname, email, joindate);
				list.add(Mvo);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt, rs);
		}
		return null;
	} // listMembers()
	
	public boolean addMember(MyMemberVo vo) {
		PreparedStatement pstmt = null;
		try {
			String sql = "insert into t_member(id, pwd, name, email)"
					+ "		values(?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPwd());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getEmail());
			int count = pstmt.executeUpdate();
			if (count > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt, null);
		}
		return false;
	}
	
	public void delMember(String[] ids) {
		PreparedStatement pstmt = null;
		try {
			String sql = "delete from t_member"
					+ "	  where id in (";
			for (int i = 0; i < ids.length; i++) {
				sql += "?";
				if (i < ids.length - 1) {
					sql += ",";
				}
			}
			sql += ")";
			System.out.println("sql:" + sql);
			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < ids.length; i++) {
				pstmt.setString(i + 1, ids[i]);
			}
			pstmt.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt, null);
		}
	}
	
	public boolean delMember(String id) {
		PreparedStatement pstmt = null;
		try {
			String sql = "delete from t_member"
					+ "	  where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			int count = pstmt.executeUpdate();
			if (count > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt, null);
		}
		return false;
	} // delMember()
	
	public boolean isExisted(MyMemberVo myMemberVo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// count의 별칭으로 cnt설정
			String sql = "select count(*) cnt from t_member"
					+ "   where id = ? and pwd = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, myMemberVo.getId());
			pstmt.setString(2, myMemberVo.getPwd());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int cnt = rs.getInt("cnt");
				if (cnt > 0) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt, rs);
		}
		return false;
	}
	
	public boolean updateMember(MyMemberVo vo) {
		PreparedStatement pstmt = null;
		try {
			String sql = "update t_member set"
					+ "	  pwd = ?, name = ?, email = ?"
					+ "	  where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getPwd());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getEmail());
			pstmt.setString(4, vo.getId());
			int count = pstmt.executeUpdate();
			if (count > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt, null);
		}
		return false;
	}
	
	public MyMemberVo selectMemberById(String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select * from t_member"
					+ "	  where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date joindate = rs.getDate("joindate");
				int point = rs.getInt("point");
				MyMemberVo vo = new MyMemberVo(id, pwd, name, email, joindate, point);
				return vo;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt, rs);
		}
		return null;
	}
	
	public boolean isDupId(String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select count(*) cnt from t_member"
					+ "	  where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int count = rs.getInt("cnt");
				if (count > 0) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt, rs);
		}
		return false;
	}
	
	public boolean updatePoint(String id, int point) {
		PreparedStatement pstmt = null;
		
		try {
			String sql = "update t_member set"
					+ "			point = point + ?"
					+ "	  where id = ?";
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
			close(pstmt, null);
		}
		return false;
	}

}
