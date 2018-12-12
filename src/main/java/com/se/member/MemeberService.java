package com.se.member;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

@Service
public class MemeberService {
	@Inject
	private MemberDAO memberDAO;
	
	public int join(MemberDTO memberDTO)throws Exception{
		return memberDAO.join(memberDTO);
	}
	
	public MemberDTO idCheck(String id)throws Exception{
		return memberDAO.idCheck(id);
	}
	
	public MemberDTO login(MemberDTO memberDTO)throws Exception{
		return memberDAO.login(memberDTO);
	}
	public int update(MemberDTO memberDTO)throws Exception{
		return memberDAO.update(memberDTO);
	}
	public int delete(String id) throws Exception{
		return memberDAO.delete(id);
	}
}
