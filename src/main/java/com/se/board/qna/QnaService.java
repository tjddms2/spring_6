package com.se.board.qna;

import java.io.File;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.se.board.BoardDTO;
import com.se.board.BoardService;
import com.se.file.FileDAO;
import com.se.file.FileDTO;
import com.se.util.FileSaver;
import com.se.util.Paper;

@Service
public class QnaService implements BoardService {

	@Autowired
	private QnaDAO qnaDAO;
	@Inject
	private FileDAO fileDAO;
	
	//@Override
	public ModelAndView list(Paper pager) throws Exception {
		ModelAndView mv= new ModelAndView();
		int totalCount = qnaDAO.totalCount(pager);
		//row
		pager.makeRow();
		//pageing
		pager.makePage(totalCount);
		
		mv.addObject("list", qnaDAO.list(pager));
		mv.addObject(pager);
		mv.setViewName("board/boardList");
		return mv;
	}

	//@Override
	public ModelAndView select(int num) throws Exception {
	ModelAndView mv= new ModelAndView();
	//qna table
	BoardDTO boardDTO = qnaDAO.select(num);
	//files table
	if(boardDTO !=null) {
		mv.setViewName("board/boardSelect");
		mv.addObject("msg",	"글이 없습니다.");
	}
		mv.addObject("board", "qna");
		return mv;
	}

	//@Override
	public ModelAndView insert(BoardDTO boardDTO, List<MultipartFile> f1,HttpSession session) throws Exception {
		 //1. sequence num 가져오기
		int num = qnaDAO.getNum();
		
		//2. qna Table에 insert
		boardDTO.setNum(num);
		int result= qnaDAO.insert(boardDTO);
		//transaction 처리
		if(result<1) {
			throw new Exception();
		}
		 //3. HDD에 File Save 
		FileSaver fs = new FileSaver();
		String realPath= session.getServletContext().getRealPath("resources/qna");
		
		for(MultipartFile multipartFile:f1) {
			if(multipartFile.isEmpty()) {
				continue;
			}
			String fname= fs.savefile(realPath, multipartFile);
			
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
			mv.setViewName("redirect:./qnaList");
			mv.addObject("msg", "Write Success");
		
		return mv;
	}

	//@Override
	public ModelAndView update(BoardDTO boardDTO, List<MultipartFile> f1,HttpSession session) throws Exception {
		ModelAndView mv= new ModelAndView();
		int result =qnaDAO.update(boardDTO);
		if(result<1) {
			throw new Exception();
		}
		//HDD save
		FileSaver fs= new FileSaver();
		String realPath = session.getServletContext().getRealPath("resources/qna");
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
		String msg ="Update Success";
		
		mv.setViewName("redirect: ./qnaSelect?num="+boardDTO.getNum());
		mv.addObject("msg", msg);
		}
		return mv;
	}

	//@Override
	public ModelAndView delete(int num, HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView();
		//1. qna Delete
		int result = qnaDAO.delete(num);
		if(result<1) {
			throw new Exception();
		}
		//2. Files Delete 준비
		FileDTO fileDTO = new FileDTO();
		fileDTO.setNum(num);
		fileDTO.setKind("n");
		List<FileDTO> ar = fileDAO.list(fileDTO);
		//3. Files Delete 준비
		result = fileDAO.deleteAll(fileDTO);
		if(result<1) {
			throw new Exception();
		}
		 //4. HDD Delete 
		String realPath = session.getServletContext().getRealPath("resources/qna");
		for(FileDTO fileDTO2:ar) {
			File file = new File(realPath, fileDTO.getFname());
			file.delete();
		}
		mv.setViewName("redirect: ./qnaList");
		mv.addObject("msg", "delete 성공");
		return mv;
	}
					//자식데이터 부모의num데이터를 조회
	public int reply(QnaDTO qnaDTO) throws Exception {			
		//부모의 ref, step, depth //1.num이 부모의 글번호 ref,step을 가지고 온다.
		BoardDTO pDto = qnaDAO.select(qnaDTO.getNum());					//부모의데이터를 
		QnaDTO pQnaDTO= (QnaDTO)pDto;									//부모 형변환
		
		qnaDAO.replyUpdate(pQnaDTO);
		
		qnaDTO.setRef(pQnaDTO.getRef());								//부모의 ref랑 같고 step을 1씩증가시키는 코드->DB
		qnaDTO.setStep(pQnaDTO.getStep()+1);
		qnaDTO.setDepth(pQnaDTO.getDepth()+1);					//pQnaDTO-> qnaDTO->getDepth
		
		/*qnaDAO.reply(boardDTO);	*/									//2.문법적으로 ((QnaDTO)boardDTO)는 되지만, 매개변수로 controller로 되어있서서 안됨..
		return qnaDAO.reply(qnaDTO);									//

	}
}
