package com.se.s6;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.se.board.BoardDTO;
import com.se.board.notice.NoticeService;
import com.se.util.Paper;

@Controller
@RequestMapping("/notice/**")/*<-이렇게 써도 무방하다~ (value="/notice/**")*/
public class NoticeController {
	@Inject
	private NoticeService noticeService;
	
	//list	//파라미터가 안넘와서 세트로 안하는거고 null이 된 상태도
	@RequestMapping(value="noticeList")
	public ModelAndView list(Paper pager) throws Exception {

		ModelAndView mv = noticeService.list(pager);
		mv.addObject("board", "notice");
		return mv;
	}
	//select
	@RequestMapping(value="noticeSelect")
	public ModelAndView select(int num) throws Exception {
		ModelAndView mv = noticeService.select(num);
		return mv;
	}
	
	//write Form
	@RequestMapping(value="noticeWrite",method=RequestMethod.GET)
	public String noticeWrite(Model model) {
		model.addAttribute("board", "notice");
		return "/board/boardWrite";
	}	
	
	//write Process(글쓰기 처리,작성자,제목,내용)											
	@RequestMapping(value="noticeWrite", method=RequestMethod.POST)					
	public ModelAndView noticeWrite(BoardDTO boardDTO, HttpSession session, List<MultipartFile> f1, RedirectAttributes rd)throws Exception {								
		String realPath= session.getServletContext().getRealPath("resources/upload");
		System.out.println(realPath);
		
		ModelAndView mv= noticeService.insert(boardDTO, f1, session);
		return mv;									
		
	}
	
	//update form
	@RequestMapping(value="noticeUpdate",method=RequestMethod.GET)
	public ModelAndView noticeUpdate(int num)throws Exception {
		ModelAndView mv = noticeService.select(num);
		mv.setViewName("board/boardUpdate");
		return mv;
	}
	//Update Process
	@RequestMapping(value="noticeUpdate", method=RequestMethod.POST)
	public ModelAndView update(BoardDTO boardDTO, List<MultipartFile> f1, HttpSession session) throws Exception {
		 ModelAndView mv=noticeService.update(boardDTO,f1,session);
		 return mv;
	}
	
	//delete Process
	@RequestMapping(value="noticeDelete", method=RequestMethod.POST)
	public ModelAndView noticeDelete(int num, HttpSession session)throws Exception {
	ModelAndView mv = noticeService.delete(num, session);
	return mv;
	}
	
	/*@RequestMapping(value="noticeReply")
	public void noticeReply() {}

	@RequestMapping(value="noticeRely", method=RequestMethod.POST)
	public String noticeReply2() {
	System.out.println("board Reply");
	return "";
}*/
}
