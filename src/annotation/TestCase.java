/**
 * 
 */
package annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(METHOD)
/**
 * @author shengjc
 * 创建注解TestCase,包括caseId-测试用例ID，SuitId-测试用例集ID，description-描述
 */
public @interface TestCase {
	public String caseId() default "";
	public String SuitId() default "";
	public String description() default "";		
}
