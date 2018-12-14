package com.se.board;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.se.util.Paper;

public interface BoardService {

	//list
	public ModelAndView list(Paper pager) throws Exception;
	
	//select
	public ModelAndView select(int num) throws Exception;
	
	//insert
	public ModelAndView insert(BoardDTO boardDTO, List<MultipartFile>f1, HttpSession session) throws Exception;
	
	//update
	public ModelAndView update(BoardDTO boardDTO,List<MultipartFile>f1, HttpSession session) throws Exception;
	
	//delete
	public ModelAndView delete(int num,HttpSession session) throws Exception;
}
