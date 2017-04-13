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
 * @author Administrator
 *
 */
public @interface TestCase {
	public String caseId() default "";
	public String SuitId() default "";
	public String description() default "";		
}
