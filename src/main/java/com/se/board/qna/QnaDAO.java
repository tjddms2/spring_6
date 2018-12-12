package com.se.board.qna;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.se.board.BoardDAO;
import com.se.board.BoardDTO;
import com.se.util.DBConnector;
import com.se.util.Paper;

@Repository //QnaDAO를 너가 만드세요라는 뜻인데 spring 이 여기의 위치를 읽고 
public class QnaDAO implements BoardDAO {
	
	@Inject
	private SqlSession sqlsession;
	private final String NAMESPACE="qnaMapper.";
	//바뀌지 않는 
	

	@Override
	public int totalCount(Paper pager) throws Exception {
		return sqlsession.selectOne(NAMESPACE+"totalCount", pager);
	}
	
	@Override
	public List<BoardDTO> list(Paper pager) throws Exception {
		return sqlsession.selectList(NAMESPACE+"list", pager);
	}

	@Override
	public BoardDTO select(int num) throws Exception {
		return sqlsession.selectOne(NAMESPACE+"select", num);
	}

	@Override	//원본글작성
	public int insert(BoardDTO boardDTO) throws Exception {
		return sqlsession.insert(NAMESPACE+"insert", boardDTO);
	}

	@Override
	public int update(BoardDTO boardDTO) throws Exception {
		return sqlsession.update(NAMESPACE+"update", boardDTO);
	}

	@Override
	public int delete(int num) throws Exception {
		return sqlsession.delete(NAMESPACE+"delete", num);
	}

	//답글작성
	public int reply(QnaDTO qnaDTO) throws Exception{
		return sqlsession.insert(NAMESPACE+"reply", qnaDTO);
	}
	public int replyUpdate(QnaDTO qnaDTO) throws Exception{
		return sqlsession.update(NAMESPACE+"replyUpdate", qnaDTO);
	}
	
	
	
	/*@Override
	public int totalCount(Paper pager) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public List<BoardDTO> list(Paper pager) throws Exception {
		List<BoardDTO> ar = new ArrayList<BoardDTO>();
		Connection con = DBConnector.getConnect();
		String sql="select * from "
				+ "(select rownum R, Q.* from "
				+ "(select * from qna where "+pager.getKind()+" like ? order by ref desc, step asc) Q) "
				+"where R between ? and ?";
		PreparedStatement st =con.prepareStatement(sql);
		st.setString(1, "%"+pager.getSearch()+"%");
		st.setInt(2, pager.getStartRow());
		st.setInt(3, pager.getLastRow());
		ResultSet rs = st.executeQuery();
		
		while(rs.next()) {
			QnaDTO qnaDTO = new QnaDTO();
			qnaDTO.setNum(rs.getInt("num"));
			qnaDTO.setTitle(rs.getString("title"));
			qnaDTO.setWriter(rs.getString("writer"));
			qnaDTO.setReg_date(rs.getDate("reg_date"));
			qnaDTO.setHit(rs.getInt("hit"));
			qnaDTO.setRef(rs.getInt("ref"));
			qnaDTO.setStep(rs.getInt("step"));
			qnaDTO.setDepth(rs.getInt("depth"));
			ar.add(qnaDTO);
		}
		DBConnector.disConnect(rs, st, con);
		return ar;
	}
	
	@Override
	public BoardDTO select(int num) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql="select * from qna where num=?";
		PreparedStatement st=con.prepareStatement(sql);
		st.setInt(1, num);
		ResultSet rs= st.executeQuery();
		QnaDTO qnaDTO = null;
		if(rs.next()) {
			qnaDTO = new QnaDTO();
			qnaDTO.setNum(rs.getInt("num"));
			qnaDTO.setTitle(rs.getString("title"));
			qnaDTO.setContents(rs.getString("contents"));
			qnaDTO.setReg_date(rs.getDate("reg_date"));
			qnaDTO.setHit(rs.getInt("hit"));
			qnaDTO.setRef(rs.getInt("ref"));
			qnaDTO.setStep(rs.getInt("step"));
			qnaDTO.setDepth(rs.getInt("depth"));
			qnaDTO.setWriter(rs.getString("writer"));
		}
		DBConnector.disConnect(rs, st, con);
		return qnaDTO;
	}
	
	@Override	//원본글 작성할때,
	public int insert(BoardDTO boardDTO) throws Exception {
		Connection con = DBConnector.getConnect();							//현재번호를 가지고 오자
		String sql="insert into qna values(qna_seq.nextval,?,?,?,sysdate,0,qna_seq.currval,0,0)";
		PreparedStatement st= con.prepareStatement(sql);
		
		//물음표 세개를 찾아주기
		st.setString(1, boardDTO.getTitle());
		st.setString(2, boardDTO.getContents());
		st.setString(3, boardDTO.getWriter());
		
		int result=st.executeUpdate();
		DBConnector.disConnect(st, con); 
		return result;
	}		
	///boardDTO는 ref가 없음 -답글작성
	public int reply(QnaDTO qnaDTO) throws Exception{
			Connection con = DBConnector.getConnect();					//ref를 서비스에 가지고 와야한다 그리고 step,depth 다 물음표
			String sql="insert into qna values(qna_seq.nextval,?,?,?,sysdate,0,?,?,?)";
			PreparedStatement st =con.prepareStatement(sql);
			
			st.setString(1, qnaDTO.getTitle());
			st.setString(2, qnaDTO.getWriter());
			st.setString(3, qnaDTO.getContents());
			st.setInt(4, qnaDTO.getRef());
			st.setInt(5, qnaDTO.getStep());
			st.setInt(6, qnaDTO.getDepth());
			
			int result= st.executeUpdate();
			
			DBConnector.disConnect(st, con);
			return result;
	}
	
	@Override
	public int update(BoardDTO boardDTO) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql="update qna set title=?, contents=? where num=? ";
		PreparedStatement st= con.prepareStatement(sql);
		
		st.setString(1, boardDTO.getTitle());
		st.setInt(2, boardDTO.getNum());
		st.setString(3, boardDTO.getContents());
		int result=st.executeUpdate();
		
		DBConnector.disConnect(st, con);
		return result;
	}
	
	@Override
	public int delete(int num) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql="delete qna where num=?";
		PreparedStatement st= con.prepareStatement(sql);
		
		st.setInt(1, num);
		int result =st.executeUpdate();
		
		DBConnector.disConnect(st, con);
		return result;
	}
	*/
}
