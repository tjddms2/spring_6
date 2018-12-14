package com.se.s6;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.se.member.MemberDTO;
import com.se.member.MemeberService;
@Repository
@Controller(value="/member/**")
public class MemberController {
	@Inject
	private MemeberService memeberService;
	
	//중복확인
		@RequestMapping(value="idCheck")
		public String idcheck(String id,Model model)throws Exception{
			MemberDTO memberDTO = memeberService.idCheck(id);
			//int result=0  //사용 불가능
			//result =1		//사용 가능
			int result=0;
			if(memberDTO == null) {
				result=1;
			}
			model.addAttribute("result", result);
			return "common/result";
		}
	
	//가입
	@RequestMapping(value="join",method=RequestMethod.GET)
	public void join()throws Exception{	}
	@RequestMapping(value="join",method=RequestMethod.POST)
	public String join(MemberDTO memberDTO,RedirectAttributes rd)throws Exception{
		memberDTO.setGrade(9);
		int result = memeberService.join(memberDTO);
		String path="redirect:../";
		if(result<1) {
			path="redirect:./join";
			rd.addFlashAttribute("msg", "회원가입 실패");
		}
		return path;
	}
	
	//로그인폼
	@RequestMapping(value="login",method=RequestMethod.GET)
	public void login()throws Exception{}
	
	//로그인 처리
	@RequestMapping(value="login",method=RequestMethod.POST)
	public String login(MemberDTO memberDTO,HttpSession session,RedirectAttributes rd)throws Exception{
		memberDTO=memeberService.login(memberDTO);
		String path="";
		if(memberDTO !=null) {
			session.setAttribute("member", memberDTO);
			path="redirect:../";
		}else {
			path="redirect:./login";
			rd.addFlashAttribute("msg", "로그인 실패");
		}
		return path;
	}
	
	//수정 폼
	@RequestMapping(value="update", method=RequestMethod.GET)
	public void update()throws Exception{
	}
	//수정처리
	@RequestMapping(value="update", method=RequestMethod.POST)
	public String update(MemberDTO memberDTO, HttpSession session, RedirectAttributes rd)throws Exception{
		MemberDTO smMemberDTO= (MemberDTO)session.getAttribute("member");
		memberDTO.setId(smMemberDTO.getId());
		int result = memeberService.update(memberDTO);
		if(result>0) {
			memberDTO.setGrade(smMemberDTO.getGrade());
			session.setAttribute("member", memberDTO);
		}else {
			rd.addFlashAttribute("msg", "Update fail");
		}
		return "redirect : ./myPage";
	}
	
	//탈퇴처리
	public String delete(HttpSession session, RedirectAttributes rd)throws Exception{
		MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
		int result=memeberService.delete(memberDTO.getId());
		String message="delete Fail";
		if(result>0) {
			message="Delete success";
			session.invalidate();
		}
		rd.addFlashAttribute("msg", message);
		return "redirect:./";
	}
	//myPage
	@RequestMapping(value="myPage")
	public void myPage()throws Exception{}
	
	//logOut
	public String logOut(HttpSession session)throws Exception{
		session.invalidate();
		return "redirect:../";
	}
}
