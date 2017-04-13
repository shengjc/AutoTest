/**
 * 
 */
package annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import bsh.This;

/**
 * @author Administrator
 *
 */
public class AnnotationParse {
	public static void TestCaseParse() {
		int suitId = 0,caseId = 0;
		String description = null,className = null;
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
				}
			}
		}		
	}
}
