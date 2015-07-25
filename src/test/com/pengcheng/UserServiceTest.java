package test.com.pengcheng;

import org.junit.Test;

import com.pengcheng.model.User;
import com.pengcheng.service.UserService;
import com.pengcheng.spring.BeanFactory;
import com.pengcheng.spring.ClassPathXmlAplicationContext;

public class UserServiceTest {

	@Test
	public void test() throws Exception {
		BeanFactory fy = new ClassPathXmlAplicationContext();
		
		UserService service = (UserService) fy.getBean("userService");
		User user = new User();
		service.addUser(user);
	}
}
