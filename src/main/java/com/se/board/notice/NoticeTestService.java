package com.se.board.notice;

import java.util.Random;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.se.file.FileDAO;
import com.se.file.FileDTO;

@Service
public class NoticeTestService {
	@Inject
	private NoticeDAO noticeDAO;
	@Inject
	private FileDAO fileDAO;
	
	@Transactional //지혼자 롤백이된다는말씀.
	public void insert(NoticeDTO noticeDTO, FileDTO fileDTO)throws Exception {
		noticeDAO.insert(noticeDTO); //0이 오면 롤백이 오지 않는다.
		/*Random r =new Random();
		int num = r.nextInt(2);
		if(num==0) {
			throw new Exception();
		}*/
		fileDAO.insert(fileDTO);
	}
	
}
