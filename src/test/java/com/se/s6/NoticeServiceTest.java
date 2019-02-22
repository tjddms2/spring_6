package com.se.s6;

import static org.junit.Assert.*;

import java.util.Random;

import javax.inject.Inject;

import org.junit.Test;

import com.se.board.notice.NoticeDAO;
import com.se.board.notice.NoticeDTO;
import com.se.board.notice.NoticeTestService;
import com.se.file.FileDTO;

public class NoticeServiceTest extends AbstractTestCase {
	@Inject
	private  NoticeDAO noticeService;
	
	@Test
	public void test()throws Exception {
		assertNotNull(noticeService);
/*		NoticeDTO noticeDTO = new NoticeDTO();
		FileDTO fileDTO = new FileDTO();
		Random r= new Random();
		int num = r.nextInt(2);
		noticeDTO.setNum(41);
		noticeDTO.setTitle("T");
		noticeDTO.setWriter("T");
		noticeDTO.setContents("T");
		
		if(num==0) {
			throw new Exception();
		}
		
		fileDTO.setNum(40);
		fileDTO.setFname(null);
		fileDTO.setOname("T");
		fileDTO.setKind("n");
	
		
		noticeService.insert(noticeDTO, fileDTO);
	*/
	}
}
