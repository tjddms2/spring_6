package com.se.s6;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.se.board.BoardDTO;
import com.se.board.qna.QnaDTO;
import com.se.board.qna.QnaService;
import com.se.util.Paper;

@Controller
@RequestMapping(value="/qna/**")
public class QnaController {
	
	@Inject
	private QnaService qnaService;
	
		//list
		@RequestMapping(value="qnaList")
		public String List(Model model ,Paper pager)throws Exception {
			model.addAttribute("board", "qna");
			List<BoardDTO> ar = qnaService.list(pager);
			model.addAttribute("list", ar);
			model.addAttribute("pager", pager);
			return "board/boardList";
		}
		
		//select
		@RequestMapping(value="qnaSelect")
		public String Select(Model model, int num, RedirectAttributes rd)throws Exception{
			model.addAttribute("board", "qna");
			BoardDTO boardDTO = qnaService.select(num);
			
			String path="";
			if(boardDTO != null) {
				path = "board/boardSelect";
				model.addAttribute("dto", boardDTO);
			}else {
				path ="redirect:./qnaList";
				rd.addFlashAttribute("msg", "해당글이 존재 하지 않습니다.");
			}																			//1.절대경로 2.상대경로
			return path;
		}
		
		//write Form
		@RequestMapping(value="qnaWrite",method=RequestMethod.GET)
		public String Write(Model model) {
			model.addAttribute("board", "qna");
			return "board/boardWrite";
		}
		//write Process(글쓰기 처리,작성자,제목,내용)					
		@RequestMapping(value="qnaWrite", method=RequestMethod.POST)
		public String Write(BoardDTO boardDTO, RedirectAttributes rd)throws Exception {	
			/*System.out.println("write:"+boardDTO.getTitle());*/ 
			int result =qnaService.insert(boardDTO);												//실패했을때, 리다이넥트로 메세지 주기
			if(result<1) {
				rd.addFlashAttribute("msg", "Write Fail");
			}
			return "redirect:./qnaList";
		}
		
		//update form
		@RequestMapping(value="qnaUpdate",method=RequestMethod.GET)
		public String Update(Model model,int num)throws Exception {
			model.addAttribute("board", "qna");
		BoardDTO boardDTO =qnaService.select(num);
		model.addAttribute("dto",boardDTO);
			return "board/boardUpdate";
		}
		//Update Process
		@RequestMapping(value="qnaUpdate", method=RequestMethod.POST)
		public String Update(BoardDTO boardDTO,RedirectAttributes rd)throws Exception {
			/*System.out.println("update:"+boardDTO.getTitle());*/
			int result =qnaService.update(boardDTO);
			if(result<1) {
				rd.addFlashAttribute("msg", "Update Fail");
			}
			return "redirect:./qnaSelect?num="+boardDTO.getNum();
		}
		
		//delete Process
		@RequestMapping(value="qnaDelete", method=RequestMethod.POST)
		public String Delete(int num, RedirectAttributes rd)throws Exception {
		/*System.out.println("Delete:"+num);*/
			int result = qnaService.delete(num);							//파라미터 넘을 보내고
			if(result<1) {
				rd.addFlashAttribute("msg", "Delete Fail");
			}
		return "redirect:./qnaList";
		}
		
		
		//reply form
		@RequestMapping(value="qnaReply", method=RequestMethod.GET)
		public String reply(Model model,int num) {
		model.addAttribute("board", "qna");
		model.addAttribute("num", num);
		return "board/boardReply";
		 }
		   
		//reply process
		@RequestMapping(value="qnaReply",method=RequestMethod.POST)
		public String reply(QnaDTO qnaDTO)throws Exception {
		/*System.out.println("reply : " + boardDTO.getTitle());*/
			int result = qnaService.reply(qnaDTO);
			return "redirect:./qnaSelect?num="+qnaDTO.getNum();
		}
		/*뭐가 틀린지 보기//Reply form
		@RequestMapping(value="qnaRely", method=RequestMethod.GET)
		public String Reply(Model model) {
			model.addAttribute("board", "qna");
			return "board/boardReply";
		}
		//reply Process
		@RequestMapping(value="qnaReply",method=RequestMethod.POST)
		public void Reply(BoardDTO boardDTO) {
			System.out.println("reply:"+boardDTO.getTitle());
		}*/

}
