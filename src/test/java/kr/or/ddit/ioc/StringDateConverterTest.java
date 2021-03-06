package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.ranger.model.RangerVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-context-conversion.xml")
public class StringDateConverterTest {
	
	private Logger logger = LoggerFactory.getLogger(StringDateConverterTest.class);
	
	@Resource(name="rangerVo")
	private RangerVO rangerVo;
	
	@Test
	public void testRangerVo() {
		/***Given***/
		
		/***When***/
		String userId = rangerVo.getUserId();
		Date birth = rangerVo.getBirth();
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		String birthStr = sdf.format(birth);
		logger.debug(birthStr);
		/***Then***/
		assertNotNull(rangerVo);
		assertEquals("brown", userId);
		assertEquals("08-08-2018",birthStr);
	}
}
