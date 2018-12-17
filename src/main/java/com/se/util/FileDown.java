package com.se.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import com.se.file.FileDTO;

public class FileDown extends AbstractView {
	//FileDown이 빨간줄이 되는이유는 추상메서드가 실행되는것.
	
	public FileDown() {
		//setContentType("application/download;charset=UTF-8");
	}
	
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		FileDTO fileDTO = (FileDTO)model.get("file");			//fname, oname을 꺼낼려고 함.
		String realPath= request.getSession().getServletContext().getRealPath("resources/notice");
		File file = new File(realPath, fileDTO.getFname());
		
		//한글 처리
		/*response.setCharacterEncoding("UTF-8");*/
		response.setCharacterEncoding(getContentType()); 
		
		//파일의 크기
		response.setContentLength((int)file.length());
		
		//oname을 UTF-8로 처리					//올릴때의 파일 이름
		String fileName = URLEncoder.encode(fileDTO.getOname(),"UTF-8");
		
		response.setHeader("Content-Disposition", "attachment: filename=\""+fileName+"\"");
		response.setHeader("Content-Transfer-Encoding", "binary");
		
		OutputStream os= response.getOutputStream();
		FileInputStream fi = new FileInputStream(file);
		
		FileCopyUtils.copy(fi, os);
		
		
	}
	
	
}
