/**
 * 
 */
package annotation;

/**
 * @author shengjc
 * 用于获取被注解的类名，目前放在BasePage类中的构造方法中，其他子页面都会继承自BasePage类
 */
public class AnnotationProperty {
	
	private Class annotationClass = null;	//用于存储获取到的Class
	private static AnnotationProperty instance = null;	//存储实例
	//使用双重检查的单例模式	
	public static AnnotationProperty getInstance() {
		if(instance == null) {	//第一重检查
			synchronized(AnnotationProperty.class) {	//第二重检查
				if(instance == null) {
					instance = new AnnotationProperty();
				}
			}
		}
		return instance;		
	}
	
	//得到目标Class
	public Class getTargetClass() {
		return annotationClass;
	}
	
	//为目标Class赋值
	public void setAnnotationClass(Class annotationClass) {
		this.annotationClass = annotationClass;
	}
	
	
}
