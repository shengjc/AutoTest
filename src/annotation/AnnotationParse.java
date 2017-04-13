/**
 * 
 */
package annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.apache.ibatis.session.SqlSession;

import sjc.mybatis.beans.CaseStatisticsBean;
import sjc.mybatis.mapper.CaseMapper;
import sjc.tools.DBTools;

/**
 * @author Administrator
 *
 */
public class AnnotationParse {
	public static void TestCaseParse() {
//		int suitId = 0,caseId = 0;
//		String description = null,className = null;
		TestCase testcase = null;
		Class<? extends Annotation> annotationClazz = TestCase.class;
		Class<?> targetClazz = null;		
		
		AnnotationProperty ap = AnnotationProperty.getInstance();
		targetClazz = ap.getTargetClass();
		Method[] targetMethods = targetClazz.getDeclaredMethods();
		
		if(targetClazz != null) {
			for(Method method : targetMethods) {
				if(method.isAnnotationPresent(annotationClazz)) {
					testcase = (TestCase) method.getAnnotation(annotationClazz);
					System.out.println("≤‚ ‘”√¿˝ID£∫"+testcase.caseId()+"     "+"√Ë ˆ£∫"+testcase.description()+"     "+"À˘ Ù≤‚ ‘”√¿˝ºØ£∫"+testcase.SuitId());
					insertCase(testcase.caseId(),testcase.description(),testcase.SuitId());
				}
			}
		}		
	}
	
	public static void insertCase(String caseId,String description,String SuitId) {
		SqlSession session = DBTools.getSession();
		CaseMapper mapper = session.getMapper(CaseMapper.class);
		CaseStatisticsBean casebean = new CaseStatisticsBean(caseId,description,SuitId);
		try {
			mapper.insertCase(casebean);
			session.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.rollback();
		}
		
	}
}
