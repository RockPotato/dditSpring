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
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-context-fconversion.xml")
public class StringDateFConverterTest {
	
	private Logger logger = LoggerFactory.getLogger(StringDateFConverterTest.class);
	
	@Resource(name="rangerVo")
	private RangerVO rangerVo;
	
	@Test
	public void testRangerVo() {
		/***Given***/
		
		/***When***/
		String userId = rangerVo.getUserId();
		Date birth = rangerVo.getBirth();
		Date reg_dt = rangerVo.getReg_dt();
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		String birthStr = sdf.format(birth);
		
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		String regDtStr = sdf2.format(reg_dt);
		
		logger.debug(birthStr);
		/***Then***/
		assertNotNull(rangerVo);
		assertEquals("brown", userId);
		assertEquals("08-08-2018",birthStr);
		assertEquals("2019-02-27",regDtStr);
	}
}
