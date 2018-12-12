package com.se.board;

import java.util.List;

import com.se.util.Paper;

public interface BoardService {

	//list
	public List<BoardDTO> list(Paper pager) throws Exception;
	
	//select
	public BoardDTO select(int num) throws Exception;
	
	//insert
	public int insert(BoardDTO boardDTO) throws Exception;
	
	//update
	public int update(BoardDTO boardDTO) throws Exception;
	
	//delete
	public int delete(int num) throws Exception;
}
