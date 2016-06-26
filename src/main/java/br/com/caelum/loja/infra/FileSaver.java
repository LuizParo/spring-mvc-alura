package br.com.caelum.loja.infra;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileSaver {

    @Autowired
    HttpServletRequest request;

    public String gravar(String basePath, MultipartFile arquivo) {
	try {
	    String realPath = request.getServletContext().getRealPath("/" + basePath);
	    String path = realPath + "/" + arquivo.getOriginalFilename();
	    arquivo.transferTo(new File(path));
	    return basePath + "/" + arquivo.getOriginalFilename();
	} catch (IOException | IllegalStateException e) {
	    throw new RuntimeException(e.getMessage(), e);
	}
    }
}