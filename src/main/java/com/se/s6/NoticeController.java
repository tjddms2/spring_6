package com.se.s6;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	@RequestMapping(value="noticeList")	//int curPage, String kind, String search ↓1.request.파라미터로 줘도 되고,2.Pager로 선언해도됨															//노티스리스트라는 방식을 쓰면 퍼블릭에서 실행한다
											   //주소
	public String noticeList(Model model,Paper pager)throws Exception {					//파라미터를 리스트로 보내서 모델이랑 같이 받을겁니까?
		/*Paper pager = new Paper();*/
		/*pager.setCurPage(curPage);
		pager.setKind(kind);
		pager.setSearch(search); //이코드 4개가 사라지는것 ->int curPage, String kind, String search을 없애고 Pager pager로 바뀌었다.*/
		
		System.out.println(pager);
		List<BoardDTO> ar=noticeService.list(pager);					
		model.addAttribute("board", "notice"); 															//키= board 밸류=notice 로 보내주기
		model.addAttribute("list", ar);  												//글 목록에서 처리하는것.
		model.addAttribute("pager", pager);
		/*System.out.println(pager.getCurPage());
		System.out.println(pager.getKind());
		System.out.println(pager.getSearch());*/
		return "board/boardList";
	}
	//select
	@RequestMapping(value="noticeSelect")
	public String noticeSelect(Model model, int num)throws Exception{
		BoardDTO boardDTO=noticeService.select(num);
		model.addAttribute("board", "notice");
		model.addAttribute(boardDTO); //boardDTO가 속성명
	return "/board/boardSelect";
	}
	
	//write Form
	@RequestMapping(value="noticeWrite",method=RequestMethod.GET)
	public String noticeWrite(Model model) {
		model.addAttribute("board", "notice");
		return "/board/boardWrite";
	}	
	//write Process(글쓰기 처리,작성자,제목,내용)											//똑같은 이름을 만드는건 오버로딩
	@RequestMapping(value="noticeWrite", method=RequestMethod.POST)					//위랑 아래랑 둘중에 하나는 오버라이딩
	public String noticeWrite(BoardDTO boardDTO,RedirectAttributes rd)throws Exception {									//list로 돌아가게끔 만들기
		System.out.println("notice");
		int result=noticeService.insert(boardDTO);
		/*result=0;*/
		if(result < 1) {
			rd.addFlashAttribute("msg","insert Fail");
		}
		return "redirect:./noticeList";									//notice->boardList->알림창
		/*System.out.println("write:"+boardDTO.getTitle()); */
	}
	
	//update form
	@RequestMapping(value="noticeUpdate",method=RequestMethod.GET)
	public String noticeUpdate(Model model) {
		model.addAttribute("board", "notice");
		return "board/boardUpdate";
	}
	//Update Process
	@RequestMapping(value="noticeUpdate", method=RequestMethod.POST)
	public void noticeUpdate(BoardDTO boardDTO)throws Exception{
		System.out.println("notice");
		int result=noticeService.update(boardDTO);
		System.out.println("update:"+boardDTO.getTitle());
	}
	
	//delete Process
	@RequestMapping(value="noticeDelete", method=RequestMethod.POST)
	public void noticeDelete(int num) {
	System.out.println("Delete:"+num);
	
	}
	
	/*@RequestMapping(value="noticeReply")
	public void noticeReply() {}

	@RequestMapping(value="noticeRely", method=RequestMethod.POST)
	public String noticeReply2() {
	System.out.println("board Reply");
	return "";
}*/
}
