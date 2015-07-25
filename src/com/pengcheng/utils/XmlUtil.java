package com.pengcheng.utils;


import java.io.IOException;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class XmlUtil {

	public static void parseXml(String filePath) {
		
		SAXBuilder sb = new SAXBuilder();
		try {
			Document doc = sb.build(XmlUtil.class.getClassLoader().getResourceAsStream(filePath));
			Element root = doc.getRootElement();
			List<Element> nodes = root.getChildren("disk");
			for (Element node : nodes) {
				String name = node.getAttributeValue("name");
				String capacity = node.getChildText("capacity");
				String directories = node.getChildText("directories");
				String files = node.getChildText("files");
				System.out.println("盘符:"+name);
				System.out.println("\t容量:"+capacity);
				System.out.println("\t目录数:"+directories);
				System.out.println("\t文件数:"+files);
				System.out.println();
			}
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
