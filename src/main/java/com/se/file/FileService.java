package com.se.file;

import java.net.URLEncoder;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.se.util.FileSaver;

@Service
public class FileService {

	@Inject
	private FileDAO fileDAO;
	
	public ModelAndView delete(int fnum)throws Exception{
		ModelAndView mv = new ModelAndView();
		int result = fileDAO.delete(fnum);
		mv.setViewName("common/result");
		mv.addObject("result",	result);
		return mv;
	}
	public String se2(PhotoDTO photoDTO,HttpSession session)throws Exception {
		String realPath=session.getServletContext().getRealPath("resources/upload");
		String contextName= session.getServletContext().getContextPath();
		
		FileSaver fs = new FileSaver();
		String fname=fs.savefile(realPath, photoDTO.getFiledata());
		String result="&bNewLine=true&sFileName="+URLEncoder.encode(photoDTO.getFiledata().getOriginalFilename(), "UTF-8");
		result = result+"&sFileURL="+contextName+"/resources/upload/"+URLEncoder.encode(fname, "UTF-8");
		System.out.println("redirect:"+photoDTO.getCallback()+"?callback_func="+photoDTO.getCallback_func()+result);
		return "redirect:"+photoDTO.getCallback()+"?callback_func="+photoDTO.getCallback_func()+result;
	}
}
