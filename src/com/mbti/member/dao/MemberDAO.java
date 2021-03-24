package com.mbti.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mbti.member.vo.LoginVO;
import com.mbti.member.vo.MemberVO;
import com.mbti.util.db.DBInfo;
import com.mbti.util.db.MemberDBSQL;

public class MemberDAO {
	
	// 객체 선언
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	// 1. 로그인 처리를 위한 메서드
	
	public LoginVO login(LoginVO vo) throws Exception{
		LoginVO loginVO = null;
		
		try {
			// 드라이버 확인 + 연결객체
			con = DBInfo.getConnection();
			// sql + 실행객체
			pstmt = con.prepareStatement(MemberDBSQL.MEMBER_LOGIN);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPw());
			// 실행
			rs = pstmt.executeQuery();
			// 표시
			if(rs != null && rs.next()) {
				loginVO = new LoginVO();
				loginVO.setId(rs.getString("id"));
				loginVO.setName(rs.getString("name"));
				loginVO.setGradeNo(rs.getInt("gradeNo"));
				loginVO.setGradeName(rs.getString("gradeName"));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("로그인 DB 처리 중 오류");
		} finally {
			DBInfo.close(con, pstmt, rs);
			
		}
		return loginVO;
	}
	
	
	// 2. 회원가입
	public int write(MemberVO vo) throws Exception{
		int result = 0;
		try {
			// 드라이버 확인 + 연결객체
			con = DBInfo.getConnection();
			// sql + 실행객체
			pstmt = con.prepareStatement(MemberDBSQL.MEMBER_WRITE);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPw());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getGender());
			pstmt.setString(5, vo.getBirth());
			pstmt.setString(6, vo.getTel());
			pstmt.setString(7, vo.getEmail());
			// 실행
			result = pstmt.executeUpdate();
			System.out.println("회원가입 성공");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("회원가입 처리 중 DB 오류 발생");
		} finally {
			DBInfo.close(con, pstmt);
		}
		return result;
	}
	
	// 2-1. 아이디 중복 체크
	public String checkId(String id) throws Exception{
		String result = null;
		
		try {
			// 드라이버 확인 + 연결
			con = DBInfo.getConnection();
			// sql + 실행
			pstmt = con.prepareStatement(MemberDBSQL.MEMBER_CHECK_ID);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			// 표시
			if(rs != null && rs.next())
				result = rs.getString("id");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("MemberDAO Error - 아이디 중복 체크 DB 처리 중 오류");
		} finally {
			DBInfo.close(con, pstmt, rs);
		}
		System.out.println("MemberDAO.write().result : " + result);
		return result;
	}

	// 3. 회원 정보 보기
	public MemberVO view(String id) throws Exception{
		MemberVO vo = null;
		
		try {
			// 드라이버 확인 + 연결
			con = DBInfo.getConnection();
			// sql + 실행
			pstmt = con.prepareStatement(MemberDBSQL.MEMBER_VIEW);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			// 표시
			if(rs != null && rs.next()) {
				vo = new MemberVO();
				vo.setId(rs.getString("id"));
				vo.setName(rs.getString("name"));
				vo.setGender(rs.getString("gender"));
				vo.setBirth(rs.getString("birth"));
				vo.setTel(rs.getString("tel"));
				vo.setEmail(rs.getString("email"));
				vo.setStatus(rs.getString("status"));
				vo.setGradeNo(rs.getInt("gradeNo"));
				vo.setGradeName(rs.getString("gradeName"));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("회원 정보 보기 실행 중 DB 처리 오류 ");
		} finally {
			
			DBInfo.close(con, pstmt, rs);
		}
		System.out.println("MemberDAO.view().vo :" + vo);
		return vo;
	}
	
	// 4. 회원 리스트
	public List<MemberVO> list() throws Exception{
		List<MemberVO> list = null;
		
		try {
			// 드라이버 확인 + 연결
			con = DBInfo.getConnection();
			// sql + 실행
			pstmt = con.prepareStatement(MemberDBSQL.MEMBER_LIST);
			pstmt.setLong(1, 1); // 시작 번호
			pstmt.setLong(2, 10); // 끝 번호
			rs = pstmt.executeQuery();
			// 표시
			if(rs !=null) {
				while(rs.next()) {
					if(list == null) list = new ArrayList<>();
					MemberVO vo = new MemberVO();
					vo.setId(rs.getString("id"));
					vo.setName(rs.getString("name"));
					vo.setGender(rs.getString("gender"));
					vo.setBirth(rs.getString("birth"));
					vo.setTel(rs.getString("tel"));
					vo.setStatus(rs.getString("status"));
					vo.setGradeNo(rs.getInt("gradeNo"));
					vo.setGradeName(rs.getString("gradeName"));
					list.add(vo);
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("회원 리스트 실행 중 DB 처리 오류");
		} finally {
			DBInfo.close(con, pstmt, rs);
		}
		return list;
	}
	
	
	// 5. 회원 등급 수정
	public int gradeModify(MemberVO vo) throws Exception{
		int result = 0;
		
		try {
			// 드라이버 확인 + 연결
			con = DBInfo.getConnection();
			// sql + 실행
			pstmt = con.prepareStatement(MemberDBSQL.MEMBER_GRADE_MODIFY);
			pstmt.setInt(1, vo.getGradeNo());
			pstmt.setString(2, vo.getId());
			result = pstmt.executeUpdate();
			// 표시
			if(result ==1) {
				System.out.println("MemberDAO.gradeModify() - 회원등급 수정 완료....");
			} else {
				throw new Exception("조건에 맞는 데이터(id)가 존재하지 않습니다.");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("회원 등급 수정 DB 처리 중 오류");
		} finally {
			DBInfo.close(con, pstmt);
		}
		System.out.println("MemberDAO.gradeModify().result : " + result);
		return result;
	}
	
}
