package dev.lab.helpers;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

public class ProcessFileupload {
	
	private static final String diretorio = "/uploads";
	
	public static boolean fileupload(HashMap<String, String> params, HttpServletRequest request, ServletContext context) throws Exception {
		boolean notFile = true;
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		
		if (isMultipart) {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			
			try {
				List<FileItem> items = upload.parseRequest(request);
				Iterator<FileItem> iterator = items.iterator();
				while (iterator.hasNext()) {
					FileItem item = iterator.next();
					if (!item.isFormField() && item.getSize() > 0) {
						notFile = false;
						params.put("image", HashUploadFileName.getImageName(item.getName())  + "." + FilenameUtils.getExtension(item.getName()));
						File path = new File(context.getRealPath("/") + diretorio);
						File uploadFile = new File(path + "/" + params.get("image"));
						item.write(uploadFile);
					}
					else {
						params.put(item.getFieldName(), item.getString("UTF-8"));
					}
				}
			}
			catch (FileUploadException  e) {
				throw new Exception(e);
			}
			catch (Exception e) {
				throw new Exception(e);
			}
		}
		return notFile;
	}

}
