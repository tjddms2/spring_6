package com.se.board.notice;

import java.io.File;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.se.board.BoardDTO;
import com.se.board.BoardService;
import com.se.file.FileDAO;
import com.se.file.FileDTO;
import com.se.util.FileSaver;
import com.se.util.Paper;
@Service
public class NoticeService{
	

	@Inject
	private NoticeDAO noticeDAO;
	@Inject
	private FileDAO fileDAO;

	public ModelAndView list(Paper pager)throws Exception{
		pager.makeRow();
		int totalCount = noticeDAO.totalCount(pager);
		pager.makePage(totalCount);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("board/boardList");
		mv.addObject("list", noticeDAO.list(pager));
		mv.addObject("pager", pager);
		return mv;
	}
	
	/*public List<BoardDTO> list(Paper pager) throws Exception {
	pager.makeRow();
	int totalCount = noticeDAO.totalCount(pager);
	pager.makePage(totalCount);
		return noticeDAO.list(pager);				//원래는 model 뷰를 만들고 리턴한다.
	}*/


	/*public BoardDTO select(int num) throws Exception {
		FileDTO fileDTO= new FileDTO();
		fileDTO.setNum(num);
		fileDTO.setKind("n");
		List<FileDTO> files=fileDAO.list(fileDTO);
		return noticeDAO.select(num);
	}*/
	
	public ModelAndView select(int num)throws Exception{
		ModelAndView mv= new ModelAndView();
		//1.notice table 
		BoardDTO boardDTO =noticeDAO.select(num);
		//2.Files table
		if(boardDTO != null) {
		FileDTO fileDTO = new FileDTO();
		fileDTO.setNum(num);
		fileDTO.setKind("n");
		List<FileDTO> ar=fileDAO.list(fileDTO);
		mv.setViewName("board/boardSelect");
		mv.addObject("dto",	boardDTO);
		mv.addObject("files", ar);
		}else {
			mv.setViewName("redirect:./noticeList");
			mv.addObject("msg", "글이없습니다.");
		}
		mv.addObject("board", "notice");
		return mv;
	}


	public ModelAndView insert(BoardDTO boardDTO, List<MultipartFile> f1, HttpSession session) throws Exception {
		 //1. sequence num 가져오기
	      int num = noticeDAO.getNum();
	      
	      //2. Notice Table에 insert
	      boardDTO.setNum(num);
	      int result = noticeDAO.insert(boardDTO);
	      
	      if(result<1) {
	         throw new Exception();
	      }
	      
	      //3. HDD에 File Save 
	      FileSaver fs = new FileSaver();
	      String realPath = session.getServletContext().getRealPath("resources/notice");
	      
	      for(MultipartFile multipartFile:f1) {
	         if(multipartFile.isEmpty()) {
	            continue;
	         }
	         String fname=fs.savefile(realPath, multipartFile);
	         //4. Files table insert
	         FileDTO fileDTO = new FileDTO();
	         fileDTO.setNum(num);
	         fileDTO.setFname(fname);
	         fileDTO.setOname(multipartFile.getOriginalFilename());
	         fileDTO.setKind("n");
	         result = fileDAO.insert(fileDTO);
	         //transaction 처리 
	         if(result<1) {
	            throw new Exception();
	         }
	      }
	         ModelAndView mv = new ModelAndView();
	         mv.setViewName("redirect:./noticeList");
	         mv.addObject("msg", "Write Success");
	
	         return mv;
	}


	public ModelAndView update(BoardDTO boardDTO,List<MultipartFile> f1, HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView();
		int result = noticeDAO.update(boardDTO);
		
		
		if(result<1) {
			throw new Exception();
		}
		//HDD save
		FileSaver fs= new FileSaver();
		String realPath =session.getServletContext().getRealPath("resources/notice");
		for(MultipartFile multipartFile:f1) {
			String fname=fs.savefile(realPath, multipartFile);
			FileDTO fileDTO = new FileDTO();
			fileDTO.setNum(boardDTO.getNum());
			fileDTO.setOname(multipartFile.getOriginalFilename());
			fileDTO.setFname(fname);
			fileDTO.setKind("n");
			result = fileDAO.insert(fileDTO);
			if(result<1) {
				throw new Exception();
			}
		}
		String msg="UPdate Success";
		mv.setViewName("redirect :./noticeSelect?num="+boardDTO.getNum());
		mv.addObject("msg", msg);
		return mv;
	}

	 public ModelAndView delete(int num, HttpSession session) throws Exception {
	      ModelAndView mv = new ModelAndView();
	      //1. notice Delete
	      int result = noticeDAO.delete(num);
	      if(result<1) {   //실패
	         throw new Exception();
	      }
	      //2. Files Delete 준비
	      FileDTO fileDTO = new FileDTO();
	      fileDTO.setNum(num);
	      fileDTO.setKind("n");
	      List<FileDTO> ar = fileDAO.list(fileDTO);
	      
	      //3. Files table Delete
	      result = fileDAO.deleteAll(fileDTO);
	      if(result<1) {
	         throw new Exception();
	      }
	      
	      //4. HDD Delete 
	      String realPath = session.getServletContext().getRealPath("resources/notice");
	      for(FileDTO fileDTO2:ar) {
	         File file = new File(realPath, fileDTO2.getFname());
	         file.delete();
	      }
	      mv.setViewName("redirect:./noticeList");
	      mv.addObject("msg", "Delete Success");
	      
	      return mv;
	   }


	


}
