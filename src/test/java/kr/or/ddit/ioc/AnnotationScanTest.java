package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.ranger.dao.IRangerDao;
import kr.or.ddit.ranger.service.IRangerService;


//설정 정보
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-context-annotation.xml")
public class AnnotationScanTest {

	private Logger logger = LoggerFactory.getLogger(AnnotationScanTest.class);
	
	//rangerDao 주입
	@Resource(name="rangerDaoImpl")
	private IRangerDao rangerDao;
	//rangerService 주입
	@Resource(name="rangerServiceImpl")
	private IRangerDao rangerService;
	
	//두 개의 스프링 빈이 정상적으로 생성 되었는지 테스트
	
	/**
	* Method : testRangerDao
	* 작성자 : PC04
	* 변경이력 :
	* Method 설명 : rangerDao 빈 컴포넌트 스캔 테스트
	*/
	@Test
	public void testRangerDao() {
		List<String> rangers = rangerDao.getRangers();
		for (String ranger : rangers) {
			logger.debug("ranger : {}",ranger);
		}
		assertNotNull(rangerDao);
		assertEquals(5, rangers.size());
	}
	
	
	/**
	* Method : testRangerService
	* 작성자 : PC04
	* 변경이력 :
	* Method 설명 : rangerService 빈 컴포넌트 스캔 테스트
	*/
	@Test
	public void testRangerService() {
		List<String> rangers = rangerService.getRangers();
		for (String ranger : rangers) {
			logger.debug("ranger : {}",ranger);
		}
		assertNotNull(rangerService);
		assertEquals(5, rangers.size());
	}

}
