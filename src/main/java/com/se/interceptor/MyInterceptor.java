package com.se.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.se.board.BoardDTO;
import com.se.member.MemberDTO;

public class MyInterceptor extends HandlerInterceptorAdapter {
	
	//controller 진입 전
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		
		//return이 true면 controller로 이동
		//return이 false면 controller로 이동 X
		System.out.println(request.getPathInfo());
		HttpSession session=request.getSession();
		boolean check = false;
		if(session.getAttribute("member") != null) {			//회원
			check= true;
		}else {													//비회원
			response.sendRedirect("../member/login");
		}
		
		
		return check;
	}
	//controller를 빠져 나갈때, 완료 후
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
			/*1.String viewName =modelAndView.getViewName();
			Map<String, Object> map=modelAndView.getModel();
			BoardDTO noticeDTO=(BoardDTO)map.get("dto");
			System.out.println("Write :"+noticeDTO.getWriter());
		System.out.println("view Name: "+viewName);*/
		String m= request.getMethod();
		
		if(m.equals("GET")) {
			HttpSession session= request.getSession();
			MemberDTO memberDTO = (MemberDTO)session.getAttribute("Member");		//MemberDTO의 형변환을 해주는것
			Map<String , Object> map = modelAndView.getModel();
			BoardDTO boardDTO = (BoardDTO)map.get("dto");							//작성자가 맞나 안맞나 비교하는것
			
			if(memberDTO == null || !memberDTO.getId().equals(boardDTO.getWriter())){
				String board =(String)map.get("board");
				modelAndView.setViewName("redirect :./"+board+"noticeList"); 				//qnaList, BoardList
				
			}
		}
		request.getSession(); //작성자가 맞나 안맞나 ?
		super.postHandle(request, response, handler, modelAndView);
	}
}
