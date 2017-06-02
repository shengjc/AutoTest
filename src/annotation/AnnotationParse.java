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
 * ע�������
 */
public class AnnotationParse {
	public static void TestCaseParse() {
//		int suitId = 0,caseId = 0;
//		String description = null,className = null;
		TestCase testcase = null;	//TestCaseע��ʵ��
		Class<? extends Annotation> annotationClazz = TestCase.class;	//TestCaseע��Class
		Class<?> targetClazz = null;		//��ע��Ŀ����Class
		
		AnnotationProperty ap = AnnotationProperty.getInstance();	//��ȡAnnotationPropertyʵ��
		targetClazz = ap.getTargetClass();		//��ȡ��ע�����Class����targetClazz
		Method[] targetMethods = targetClazz.getDeclaredMethods();		//����ע��������з���
		
		if(targetClazz != null) {	//��鱻ע�����Ƿ�Ϊ��
			for(Method method : targetMethods) {	//������ע��������з���
				if(method.isAnnotationPresent(annotationClazz)) {	//��鷽�����Ƿ���TestCaseע��
					testcase = (TestCase) method.getAnnotation(annotationClazz);		//��ȡ�����ϵ�TestCaseע��
					//���ע���ϵ�caseId��description��SuitId
					System.out.println("��������ID��"+testcase.caseId()+"     "+"������"+testcase.description()+"     "+"����������������"+testcase.SuitId());
					insertCase(testcase.caseId(),testcase.description(),testcase.SuitId());
				}
			}
		}		
	}
	
	//��caseId��description��SuitId���뵽���ݿ����
	public static void insertCase(String caseId,String description,String SuitId) {
		//ʹ��ibatisģʽ�����ݿ⽻��
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
