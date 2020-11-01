package kr.or.bit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.bit.dto.KoreaMember;
import kr.or.bit.utils.Singleton_Helper;

/*

1. db 연결
2. CRUD 함수 생성
  2.1 전체조회 : select id, email, content from memo;
  2.2 조건조회: select id, email, content from memo where id=?
  2.3 삽입: insert into memo(id, email, content) values(?,?,?); //name, age, gender, email
  2.4 수정: update memo set email=?, content=? where id=?;
  2.5 삭제: delete from memo where id=?;
  +알파
  Like 문자열 검색(이름 검색, 컨텐츠 내용 검색)

ID	VARCHAR2(50 BYTE)	No		1	
PWD	VARCHAR2(50 BYTE)	No		2	
NAME	VARCHAR2(50 BYTE)	No		3	
AGE	NUMBER	Yes		4	
GENDER	CHAR(4 BYTE)	Yes		5	
EMAIL	VARCHAR2(50 BYTE)	Yes		6	
IP	VARCHAR2(50 BYTE)	Yes		7	


*/
public class KoreaMemberDao {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private static final String SQL_SELECT_ALL = "SELECT ID, PWD, NAME, AGE, GENDER, EMAIL, IP "
												+ "FROM KOREAMEMBER";
	private static final String SQL_SELECT_MEMBERS_BY_NAME = "SELECT ID, PWD, NAME, AGE, GENDER, EMAIL, IP "
															+ "FROM KOREAMEMBER WHERE NAME LIKE ?";
	public static final String SQL_SELECT_MEMBER_BY_ID = "SELECT ID, PWD, NAME, AGE, GENDER, EMAIL, IP "
														+ " FROM KOREAMEMBER WHERE ID=?";
	private static final String SQL_INSERT_MEMBER = "INSERT INTO KOREAMEMBER(ID, PWD, NAME, AGE, GENDER, EMAIL, IP) "
													+ "VALUES(?,?,?,?,?,?,?)";
	private static final String SQL_UPDATE_MEMBER = "UPDATE KOREAMEMBER SET NAME=?, AGE=?, GENDER=?, EMAIL=? WHERE ID=?";
	private static final String SQL_DELETE_MEMBER = "DELETE FROM KOREAMEMBER WHERE ID=?";
	
	
	public KoreaMemberDao() {
		conn = Singleton_Helper.getConnection("oracle");
	}
	
	//전체조회
	public List<KoreaMember> getKoreaMemberList() {
		List<KoreaMember> memberlist =  new ArrayList<KoreaMember>();
		try {
			pstmt = conn.prepareStatement(SQL_SELECT_ALL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				KoreaMember km = new KoreaMember();
				km.setId(rs.getString("ID"));
				km.setPwd(rs.getString("PWD"));
				km.setName(rs.getString("NAME"));
				km.setAge(rs.getInt("AGE"));
				km.setGender(rs.getString("GENDER"));
				km.setEmail(rs.getString("EMAIL"));
				km.setIp(rs.getString("IP"));
				memberlist.add(km);
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			Singleton_Helper.close(rs);
			Singleton_Helper.close(pstmt);
		}
		return memberlist;
	}
	
	// LIKE 이름으로 멤버리스트 조회
	public List<KoreaMember> getKoreaMemberListByName(String name) {
		List<KoreaMember> memberlist =  new ArrayList<KoreaMember>();
		try {
			pstmt = conn.prepareStatement(SQL_SELECT_MEMBERS_BY_NAME);
			pstmt.setString(1, "%" + name + "%");
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				KoreaMember km = new KoreaMember();
				km.setId(rs.getString("ID"));
				km.setPwd(rs.getString("PWD"));
				km.setName(rs.getString("NAME"));
				km.setAge(rs.getInt("AGE"));
				km.setGender(rs.getString("GENDER"));
				km.setEmail(rs.getString("EMAIL"));
				km.setIp(rs.getString("IP"));
				memberlist.add(km);
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			Singleton_Helper.close(rs);
			Singleton_Helper.close(pstmt);
		}
		return memberlist;
	}
	
	// id로 멤버 조회
	public KoreaMember getKoreaMember(String _id) {
		KoreaMember member = null;
        try {
        	pstmt = conn.prepareStatement(SQL_SELECT_MEMBER_BY_ID);
        	pstmt.setString(1, _id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String id = rs.getString("ID");
                String pwd = rs.getString("PWD");
                String name = rs.getString("NAME");
                int age = rs.getInt("AGE");
                String gender = rs.getString("GENDER");
                String email = rs.getString("EMAIL");
                String ip = rs.getString("IP");
                member = new KoreaMember(id, pwd, name, age, gender, email, ip);
            }
        } catch (SQLException e) {
        	System.out.println(e.getMessage());
        } finally {
			Singleton_Helper.close(rs);
			Singleton_Helper.close(pstmt);
		}
        return member;
    }
	
	
	// 멤버추가
	public int insertKoreaMember(KoreaMember km) {
		int resultRow = 0;
		try {
			pstmt = conn.prepareStatement(SQL_INSERT_MEMBER);
			pstmt.setString(1, km.getId());
			pstmt.setString(2, km.getPwd());
			pstmt.setString(3, km.getName());
			pstmt.setInt(4, km.getAge());
			pstmt.setString(5, km.getGender());
			pstmt.setString(6, km.getEmail());
			pstmt.setString(7, km.getIp());
			
			resultRow = pstmt.executeUpdate();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			Singleton_Helper.close(pstmt);
		}
		
		return resultRow;
	}
	
	// 멤버수정
	public int updateKoreaMember(KoreaMember km) {
		int resultRow = 0;
		try {
			pstmt = conn.prepareStatement(SQL_UPDATE_MEMBER);
			pstmt.setString(1, km.getName());
			pstmt.setInt(2, km.getAge());
			pstmt.setString(3, km.getGender());
			pstmt.setString(4, km.getEmail());
			pstmt.setString(5, km.getId());
			System.out.println("in");
			resultRow = pstmt.executeUpdate();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			Singleton_Helper.close(pstmt);
		}
		return resultRow;
	}
	
	// 멤버삭제
	public int deleteKoreaMember(String id) {
		int resultRow = 0;
		try {
			pstmt = conn.prepareStatement(SQL_DELETE_MEMBER);
			pstmt.setString(1, id);
			
			resultRow = pstmt.executeUpdate();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			Singleton_Helper.close(pstmt);
		}
		return resultRow;
	}
		
}
