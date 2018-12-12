package com.se.board.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.se.board.BoardDTO;
import com.se.board.BoardService;
import com.se.util.Paper;

public class QnaService implements BoardService {

	@Autowired
	private QnaDAO qnaDAO;
	
	@Override
	public List<BoardDTO> list(Paper pager) throws Exception {
		int totalCount = qnaDAO.totalCount(pager);
		//startrow
		pager.makeRow(); 				
		//pageing 총갯수
		pager.makePage(totalCount); 
		
		return qnaDAO.list(pager);
	}

	@Override
	public BoardDTO select(int num) throws Exception {
		// TODO Auto-generated method stub
		return qnaDAO.select(num);
	}

	@Override
	public int insert(BoardDTO boardDTO) throws Exception {
		// TODO Auto-generated method stub
		return qnaDAO.insert(boardDTO);
	}

	@Override
	public int update(BoardDTO boardDTO) throws Exception {
		// TODO Auto-generated method stub
		return qnaDAO.update(boardDTO);
	}

	@Override
	public int delete(int num) throws Exception {
		// TODO Auto-generated method stub
		return qnaDAO.delete(num);
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
