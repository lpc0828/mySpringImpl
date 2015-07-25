package com.pengcheng.spring;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

public class ClassPathXmlAplicationContext implements BeanFactory {

	private Map<String, Object> beans = new HashMap<String, Object>();
	
	public ClassPathXmlAplicationContext() throws Exception{
		
		SAXBuilder sb = new SAXBuilder();
		Document doc = sb.build(ClassPathXmlAplicationContext.class.getClassLoader().getResourceAsStream("beans.xml"));
		Element root = doc.getRootElement();
		List<Element> nodes = root.getChildren("bean");
		for (Element node : nodes) {
			String id = node.getAttributeValue("id");
			String clazz = node.getAttributeValue("class");
			System.out.println(id + " : " + clazz);
			Object o = Class.forName(clazz).newInstance();
			beans.put(id, o);
			
			List<Element> propetiesparents = node.getChildren("properties");
			if (propetiesparents == null || propetiesparents.size() == 0) {
				continue;
			}
			List<Element> properties = propetiesparents.get(0).getChildren("property");
			for (Element property: properties) {
				String name = property.getAttributeValue("name");
				String bean = property.getAttributeValue("bean");
				System.out.println(name + " : " + bean);
				
				Field[] fields = o.getClass().getDeclaredFields();
				System.out.println(fields.length);
				for (int j=0; j< fields.length; j++) {
					Field field = fields[j];
					if (field.getName().equals(name)) {
						boolean bAccessFlag = field.isAccessible();
						if (!bAccessFlag) {
							field.setAccessible(true);
						}
						field.set(o, beans.get(bean));
						if (!bAccessFlag) {
							field.setAccessible(false);
						}
					}
				}
			}
		}
	}

	@Override
	public Object getBean(String beanID) {
		return beans.get(beanID);
	}
}
