package com.se.s6;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.se.file.FileService;

@Controller(value="/file/**")
public class FileController {

	@Inject
	private FileService fileService;
	
	@RequestMapping(value="delete")
	public ModelAndView delete(int fnum)throws Exception{
		ModelAndView mv= fileService.delete(fnum);
		return mv;
	}
}
