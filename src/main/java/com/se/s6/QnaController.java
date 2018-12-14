package com.se.s6;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.se.board.BoardDTO;
import com.se.board.qna.QnaDTO;
import com.se.board.qna.QnaService;
import com.se.util.Paper;

@Controller
@RequestMapping("/qna/**")
public class QnaController {
	@Inject
	private QnaService qnaService;
	
	//list
	@RequestMapping(value="qnaList")
	public ModelAndView list(Model model, Paper pager)throws Exception{
		ModelAndView mv= qnaService.list(pager);
		mv.addObject("board", "qna");
		return mv;
	}
	
	//select
	@RequestMapping(value="qnaSelect")
	public ModelAndView select(Model model,int num,RedirectAttributes rd)throws Exception{
		ModelAndView mv = qnaService.select(num);
		return mv;
	}
	//writeForm
	@RequestMapping(value="qnaWrite", method=RequestMethod.GET)
	public String write(Model model) {
		model.addAttribute("board", "qna");
		return "board/boardWrite";
	}
	//Write Process
	@RequestMapping(value="qnaWrite", method=RequestMethod.POST)
	public ModelAndView Write(BoardDTO boardDTO, List<MultipartFile> f1, HttpSession session)throws Exception{
		String realPath= session.getServletContext().getRealPath("resources/upload");
		System.out.println(realPath);
		
		ModelAndView mv =qnaService.insert(boardDTO, f1, session);
		
		return mv;
	}
	
	//update Form
	@RequestMapping(value="qnaUpdate", method=RequestMethod.GET)
	public ModelAndView Update(Model model, int num)throws Exception{
		ModelAndView mv = qnaService.select(num);
		mv.setViewName("board/boardUpdate");
		return mv;
	}
	//Update Process
	public ModelAndView Update(BoardDTO boardDTO, List<MultipartFile> f1, HttpSession session)throws Exception{
		ModelAndView mv=qnaService.update(boardDTO, f1, session);
		return mv;
	}
	//delete Process
	@RequestMapping(value="qnaDelete", method=RequestMethod.POST)
	public ModelAndView delete(int num, HttpSession session)throws Exception{
		ModelAndView mv=qnaService.delete(num, session);
		return mv;
	}
	//reply form
	@RequestMapping(value="qnaRely", method=RequestMethod.GET)
	public String reply(Model model, int num) {
		model.addAttribute("board",	"qna");
		model.addAttribute("num", num);
		return "board/boardRely";
	}
	//reply Process
	@RequestMapping(value="qnareply", method=RequestMethod.POST)
	public String reply(QnaDTO qnaDTO)throws Exception{
		int result = qnaService.reply(qnaDTO);
		return "redirect:./qnaSelect?num="+qnaDTO.getNum();
	}
}
