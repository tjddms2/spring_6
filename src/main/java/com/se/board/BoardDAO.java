package com.se.board;

import java.util.List;

import com.se.util.Paper;

public interface BoardDAO {
	//팀장님~
	//List
	public List<BoardDTO> list(Paper pager) throws Exception;
	
	//select				매개변수 int num
	public BoardDTO select(int num) throws Exception;
	
	//insert=write
	public int insert(BoardDTO boardDTO) throws Exception;

	//update
	public int update(BoardDTO boardDTO)throws Exception;

	//delete
	public int delete(int num)throws Exception;
	
	//totalCount
	public int totalCount(Paper pager) throws Exception;
}
