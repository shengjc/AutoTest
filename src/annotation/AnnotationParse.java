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
 * @author shengjc
 * 注解解析类
 */
public class AnnotationParse {
	public static void TestCaseParse() {
//		int suitId = 0,caseId = 0;
//		String description = null,className = null;
		TestCase testcase = null;	//TestCase注解实例
		Class<? extends Annotation> annotationClazz = TestCase.class;	//TestCase注解Class
		Class<?> targetClazz = null;		//被注解目标类Class
		
		AnnotationProperty ap = AnnotationProperty.getInstance();	//获取AnnotationProperty实例
		targetClazz = ap.getTargetClass();		//获取被注解类的Class存入targetClazz
		Method[] targetMethods = targetClazz.getDeclaredMethods();		//将被注解类的所有方法
		
		if(targetClazz != null) {	//检查被注解类是否为空
			for(Method method : targetMethods) {	//遍历被注解类的所有方法
				if(method.isAnnotationPresent(annotationClazz)) {	//检查方法上是否有TestCase注解
					testcase = (TestCase) method.getAnnotation(annotationClazz);		//获取方法上的TestCase注解
					//输出注解上的caseId，description，SuitId
					System.out.println("测试用例ID："+testcase.caseId()+"     "+"描述："+testcase.description()+"     "+"所属测试用例集："+testcase.SuitId());
					insertCase(testcase.caseId(),testcase.description(),testcase.SuitId());
				}
			}
		}		
	}
	
	//将caseId，description，SuitId插入到数据库表中
	public static void insertCase(String caseId,String description,String SuitId) {
		//使用ibatis模式与数据库交互
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
