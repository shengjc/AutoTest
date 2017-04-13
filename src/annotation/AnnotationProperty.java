/**
 * 
 */
package annotation;

/**
 * @author Administrator
 *
 */
public class AnnotationProperty {
	private Class annotationClass = null;
	private static AnnotationProperty instance = null;
		
	public static AnnotationProperty getInstance() {
		if(instance == null) {
			synchronized(AnnotationProperty.class) {
				if(instance == null) {
					instance = new AnnotationProperty();
				}
			}
		}
		return instance;		
	}
	
	public Class getTargetClass() {
		return annotationClass;
	}

	public void setAnnotationClass(Class annotationClass) {
		this.annotationClass = annotationClass;
	}
	
	
}
