package com.se.s6;

import java.io.File;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.Iterator;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.se.file.FileDTO;
import com.se.file.FileService;
import com.se.file.PhotoDTO;
import com.se.util.FileSaver;

@Controller
@RequestMapping(value="/file/**")
public class FileController {

	@Inject
	private FileService fileService;
	
	@RequestMapping("fileDown")
	public ModelAndView fileDown(FileDTO fileDTO)throws Exception{				//파일의 저장된 이름을 꺼내오기
		ModelAndView mv = new ModelAndView();
		mv.addObject("file", fileDTO);
		mv.setViewName("fileDown");												//fileDown과 fileViewName이 맞아야 됨
		return mv;
		
		
	}
	
	@RequestMapping(value="delete")
	public ModelAndView delete(int fnum)throws Exception{
		ModelAndView mv= fileService.delete(fnum);
		return mv;
	}
	
	/*@RequestMapping(value="photoUpload",method=RequestMethod.POST)
	public void se2(MultipartHttpServletRequest request) {//String String
		System.out.println("se22222222222222");
		Enumeration<Object> en=request.getParameterNames();
		while(en.hasMoreElements()) {										//다음요소가 있으면 진실 없으면 거짓
		String name= (String)en.nextElement();
			System.out.println(name); 										//눈으로 확인하고 싶다면 출력
		}
		Iterator<String> names=request.getFileNames();
		while(names.hasNext()) {
			String n=names.next();
			System.out.println(n);
		}
	}*/
		
	@RequestMapping(value="photoUpload",method=RequestMethod.POST)
	public String se2(PhotoDTO photoDTO, HttpSession session)throws Exception{
		String result= fileService.se2(photoDTO, session);
		
		return result;
	}
}
