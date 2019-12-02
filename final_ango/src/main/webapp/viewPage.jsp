
<%@page import="java.io.File"%>
<%@page import="java.io.IOException"%>
<%@page import="java.util.Enumeration"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%
	String saveFolder = "C:/jsp/final_ango/src/main/webapp/images";
	String encType = "EUC-KR";
	int maxSize = 5 * 1024 * 1024;

	try {

		MultipartRequest multi = null;
		multi = new MultipartRequest(request, saveFolder, maxSize, encType, new DefaultFileRenamePolicy()); //업로드
		Enumeration params = multi.getParameterNames();

		Enumeration files = multi.getFileNames();
		while (files.hasMoreElements()) {

			String name = (String) files.nextElement();
			String filename = multi.getFilesystemName(name);
			String original = multi.getOriginalFileName(name);
			String type = multi.getContentType(name);

			File f = multi.getFile(name);

			out.println("파라미터 이름 : " + name + "<br/>");
			out.println("실제 파일 이름 : " + original + "<br/>");
			out.println("저장된 파일 이름 : " + filename + "<br/>");
			out.println("파일 타입 : " + type + "<br/>");

			if (f != null) {

				out.println("크기 : " + f.length() + "바이트");
				out.println("<br>");
			}
		}

	} catch (IOException ioe) {

		System.out.println(ioe);
	} catch (Exception ex) {

		System.out.println(ex);
	}
%>

