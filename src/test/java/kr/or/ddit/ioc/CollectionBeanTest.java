package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-context-collection.xml")
public class CollectionBeanTest {

	private Logger logger = LoggerFactory.getLogger(CollectionBeanTest.class);
	
	@Resource(name="collectionBean")
	private CollectionBean collectionBean;
	
	/**
	* Method : testCollectionBean
	* 작성자 : PC04
	* 변경이력 :
	* Method 설명 : 스프링 설정 파일을 통해 컬렉션 객체를 생성하고, 주입받는다.
	* 				list, set, map, properties
	*/
	@Test
	public void testCollectionBean() {
		/***Given***/
		
		/***When***/
		List<String> list= collectionBean.getList();
		for (String str : list) {
			logger.debug("list : {}",str);
		}

		/***Then***/
		assertNotNull(collectionBean);
		assertEquals(3, list.size());
		ArrayList<String> keys=new ArrayList<String>();
		keys.add("brown");
		keys.add("sally");
		keys.add("cony");
		//assert 구문을 이요하여 속성이 정상적으로 주입되었는지 테스트 코드 작성
		Map<String, String> map = collectionBean.getMap();
		Properties properties = collectionBean.getProperties();
		Set<String> set = collectionBean.getSet();
		for (String str : set) {
			logger.debug("set : {}",str);
		}
		
		for (int i = 0; i < map.size(); i++) {
			logger.debug("map : {}",map.get(keys.get(i)));
		}
		for (int i = 0; i < properties.size(); i++) {
			logger.debug("properties : {}",properties.get(keys.get(i)));
		}
		assertEquals(3, map.size());
		assertEquals(3, set.size());
		assertEquals(3, properties.size());
		
	}

}
