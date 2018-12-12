package com.se.board.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.se.board.BoardDAO;
import com.se.board.BoardDTO;
import com.se.util.DBConnector;
import com.se.util.Paper;

@Repository
public abstract class NoticeDAO implements BoardDAO {
	
	@Inject
	private SqlSession session;
	private String namespace="noticeMapper.";
	
	//@Override
	public List<BoardDTO> list(Paper pager, int startRow, int lastRow, String search , String kind) throws Exception {
		Map<String, Object> map =new HashMap<String, Object>();
		map.put("pager", pager);
		map.put("startRow", startRow);
		map.put("lastRow", lastRow);
		map.put("search", search);
		map.put("kind", kind);
				
		return  session.selectList(namespace+"selectList", map);
	}
	
	@Override
	public BoardDTO select(int num) throws Exception {
		//글 하나를 조회 : SqlSession을 가지고 와야됨.
		return session.selectOne(namespace+"selectOne", num);
	}
	
	@Override
	public int insert(BoardDTO boardDTO) throws Exception {
		return session.insert(namespace+"ins", boardDTO);
	}
	@Override
	public int update(BoardDTO boardDTO) throws Exception {
		return session.update(namespace+"udt", boardDTO);
	}
	
	@Override
	public int delete(int num) throws Exception {
		return session.delete(namespace+"del", num);
	}
	@Override
	public int totalCount(Paper pager) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
	
	
	
	/*
	@Override
	public int totalCount(Paper pager) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql="select count(num) from notice where "+pager.getKind()+" like ?";
		PreparedStatement st= con.prepareStatement(sql);
		
		st.setString(1, "%"+pager.getSearch()+"%");
		ResultSet rs= st.executeQuery();
		rs.next();     							//읽기
		int totalCount = rs.getInt(1);			//출력
		DBConnector.disConnect(rs, st, con);
		return totalCount;
	}
	

	@Override				//매개변수를 받아야됨
	public List<BoardDTO> list(Paper pager) throws Exception {
		List<BoardDTO> ar = new ArrayList<BoardDTO>();
		Connection con= DBConnector.getConnect();
		String sql="select * from"
				+ "(select rownum R, N.* from "
				+ "(select * from notice where "+pager.getKind()+" like ? order by num desc) N)"
				+ " where R between ? and ?";
				// 어디로 가지고 와서 사이에 ? 그리고 ?;
		PreparedStatement st= con.prepareStatement(sql);
		st.setString(1, "%"+pager.getSearch()+"%");
		st.setInt(2, pager.getStartRow());
		st.setInt(3, pager.getLastRow());
		ResultSet rs = st.executeQuery();
		
		while(rs.next()) {
			NoticeDTO noticeDTO = new NoticeDTO();
			noticeDTO.setNum(rs.getInt("num"));
			noticeDTO.setTitle(rs.getString("title"));
			noticeDTO.setWriter(rs.getString("writer"));
			noticeDTO.setContents(rs.getString("contents"));
			noticeDTO.setReg_date(rs.getDate("reg_date"));
			noticeDTO.setHit(rs.getInt("hit"));
			ar.add(noticeDTO);
		}
		DBConnector.disConnect(rs, st, con);
		return ar;
	}

	//notice select출력
	  public static void main(String[] args) {
		  NoticeDAO noticeDAO = new NoticeDAO();
		  BoardDTO boardDTO;
		try {
			boardDTO =noticeDAO.select(1);
			System.out.println(boardDTO.getTitle());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	  @Override
	   public BoardDTO select(int num) throws Exception {
	      Connection con = DBConnector.getConnect(); //이제는 mybatis에서 전송하기로 했음.
	      String sql = "select * from notice where num=?";
	      PreparedStatement st = con.prepareStatement(sql);
	      st.setInt(1, num);
	      ResultSet rs = st.executeQuery();
	      NoticeDTO noticeDTO = null;
	      if(rs.next()) {
	         noticeDTO = new NoticeDTO();
	         noticeDTO.setNum(rs.getInt("num"));
	         noticeDTO.setTitle(rs.getString("title"));
	         noticeDTO.setContents(rs.getString("contents"));
	         noticeDTO.setWriter(rs.getString("writer"));
	         noticeDTO.setReg_date(rs.getDate("reg_date"));
	         noticeDTO.setHit(rs.getInt("hit"));
	      }
	      
	      DBConnector.disConnect(rs, st, con);
	      return noticeDTO;
	      
	   }

	@Override
	public int insert(BoardDTO boardDTO) throws Exception {
		Connection con =DBConnector.getConnect();
		String sql="insert into notice values(notice_seq.nextval,?,?,?,sysdate,0)";
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setString(1, boardDTO.getTitle());
		st.setString(2, boardDTO.getContents());
		st.setString(3, boardDTO.getWriter());
		int result=st.executeUpdate();
		
		DBConnector.disConnect(st, con);		
		return result;
	}

	@Override
	public int update(BoardDTO boardDTO) throws Exception {
		Connection con= DBConnector.getConnect();
		String sql="update notice set title=?, contents=? where num=? ";
		PreparedStatement st= con.prepareStatement(sql);
		st.setString(1, boardDTO.getTitle());
		st.setInt(2, boardDTO.getNum());
		st.setString(3, boardDTO.getContents());
		int result= st.executeUpdate();
		
		DBConnector.disConnect(st, con);
		return result;
	}

	@Override
	public int delete(int num) throws Exception {
		Connection con =DBConnector.getConnect();
		String sql="delete notice where num=?";
		PreparedStatement st=con.prepareStatement(sql);
		st.setInt(1, num);
		
		int result=st.executeUpdate();
		DBConnector.disConnect(st, con);
		return result;
	}*/

}
