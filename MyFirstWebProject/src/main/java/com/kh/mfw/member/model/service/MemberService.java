package com.kh.mfw.member.model.service;

import com.kh.mfw.member.model.dao.MemberDAO;
import com.kh.mfw.member.model.dto.MemberDTO;

public class MemberService {
	
	public MemberDTO login(MemberDTO member) {
		
		// -> 유효성 검사하기(Validation)
		
		MemberDTO loginMember = new MemberDAO().login(member);
		
		return loginMember;
	}
	
	public int signUp(MemberDTO member) {
		
		int result = new MemberDAO().checkId(member.getMemberId());
		
		if(result > 0) {
			return result;
		}
		
		new MemberDAO().signUp(member);
		return result;
	}
}
