package com.kh.mfw.member.model.service;

import org.apache.ibatis.session.SqlSession;

import static com.kh.mfw.common.Template.getSqlSession;
import com.kh.mfw.member.model.dao.MemberDAO;
import com.kh.mfw.member.model.dto.MemberDTO;

public class MemberService {
	
	public MemberDTO login(MemberDTO member) {
		
		// JDBCUtil 클래스로부터 static Method로 구현해놓은
		// getConnection 메서드를 호출하여 Connection 객체를 반환받았음
		SqlSession sqlSession = getSqlSession();	// Connection 객체를 대신함
		
		// 유효성 검증 => 패스(원래 해야됨)
		MemberDTO loginMember = new MemberDAO().login(sqlSession, member);
		
		sqlSession.close();
	
		return loginMember;
	}
	
	public int signUp(MemberDTO member) {
		
		// 3차 유효성검사(java)
		// 4차 데이터 무결성 검증(DMBS)
		
		SqlSession sqlSession = getSqlSession();
		
		// boolean result = new MemberDAO().checkId(sqlSession, member.getMemberId());
		
		if(new MemberDAO().checkId(sqlSession, member.getMemberId())) {
			sqlSession.close();
			return 0;
		}
		
		int result = new MemberDAO().signUp(sqlSession, member);
		
		sqlSession.commit();
		
		sqlSession.close();
		
		return result;
	}
	
}
