package com.se.file;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

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
}
