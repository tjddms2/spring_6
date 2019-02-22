package com.se.s6;

import javax.inject.Inject;

import org.junit.Test;

import com.se.board.BoardDTO;
import com.se.board.notice.NoticeDAO;
import com.se.file.FileDTO;

public class NoticeTest {

@Inject
	private NoticeDAO noticeDAO;
	
	@Test
	public void test()throws Exception{
		BoardDTO boardDTO = noticeDAO.select(29);
		System.out.println("===== start ======");
		System.out.println(boardDTO.getNum());
		System.out.println(boardDTO.getTitle());
		
		for(FileDTO fileDTO: boardDTO.getFiles()) {
			System.out.println(fileDTO.getFname());
		}
		System.out.println("===========");
	}
}
