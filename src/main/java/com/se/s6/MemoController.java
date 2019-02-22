package com.se.s6;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.se.board.memo.MemoDTO;
import com.se.board.memo.MemoService;
import com.se.util.Paper;

@Controller
@RequestMapping("/memo/**")
public class MemoController {
	@Inject
	private MemoService memoservice;
	
	@RequestMapping("memoList")
	public void memoList()throws Exception{}
		
	@RequestMapping(value="jtest2", produces="application/json;charset=UTF-8")
	@ResponseBody														//모든 메서드가 ResponseBody를 쓸경우 @RestController로 class위에 써놓기..
	public String jtest2()throws Exception{
		return "테스트2";
	}
	
	@RequestMapping("jtest1")
	@ResponseBody														//제이슨의 결과물을 보기 //번호 하나의 결과물을 console을 출력
	public Map<String , MemoDTO> jtest1()throws Exception{
		Map<String, MemoDTO>map =  new HashMap<String, MemoDTO>();
		MemoDTO memoDTO = new MemoDTO();
		memoDTO.setNum(1);
		map.put("f1", memoDTO);
		memoDTO = new MemoDTO();
		memoDTO.setNum(2); 	
		map.put("f2", memoDTO);
		return map;
	
	}
	
	/*@RequestMapping("list/{curPage}")
	@ResponseBody				//@path변수 자체가 경로입니다.
	public List<MemoDTO> list(@PathVariable int curPage)throws Exception{
		System.out.println("curPage:"+curPage);
		Paper pager = new Paper();
		return memoservice.list(pager);
		List<MemoDTO> ar=memoservice.list(pager);
		System.out.println(ar.get(0).getContents());
	}↓*/

	@RequestMapping("list/{board}")	//.do는 확장자 인식을 못한다.
	@ResponseBody				
	public List<MemoDTO> list(@PathVariable String board)throws Exception{
		System.out.println("board:"+board);
		
		Paper pager = new Paper();
		return memoservice.list(pager);
		/*List<MemoDTO> ar=memoservice.list(pager);
		System.out.println(ar.get(0).getContents());*/
	}
	
	
	@RequestMapping("select")
	@ResponseBody
	public MemoDTO select(int num)throws Exception{
		MemoDTO memoDTO = memoservice.select(num);
		//1.System.out.println(memoDTO.getContents());
		/*2.ModelAndView mv = new ModelAndView();
		String result="{\"";
		result = result+"writer\":\""+memoDTO.getWriter()+"\",";		//json의 형태->data로 바꿔주기
		result = result+"\"contents\":\""+memoDTO.getContents()+"\"}";
		mv.addObject("dto", memoDTO);
		mv.setViewName("common/memo"); */
		return memoDTO;
	}
	
	
}
