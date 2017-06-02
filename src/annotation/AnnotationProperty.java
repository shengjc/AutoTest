/**
 * 
 */
package annotation;

/**
 * @author shengjc
 * ���ڻ�ȡ��ע���������Ŀǰ����BasePage���еĹ��췽���У�������ҳ�涼��̳���BasePage��
 */
public class AnnotationProperty {
	
	private Class annotationClass = null;	//���ڴ洢��ȡ����Class
	private static AnnotationProperty instance = null;	//�洢ʵ��
	//ʹ��˫�ؼ��ĵ���ģʽ	
	public static AnnotationProperty getInstance() {
		if(instance == null) {	//��һ�ؼ��
			synchronized(AnnotationProperty.class) {	//�ڶ��ؼ��
				if(instance == null) {
					instance = new AnnotationProperty();
				}
			}
		}
		return instance;		
	}
	
	//�õ�Ŀ��Class
	public Class getTargetClass() {
		return annotationClass;
	}
	
	//ΪĿ��Class��ֵ
	public void setAnnotationClass(Class annotationClass) {
		this.annotationClass = annotationClass;
	}
	
	
}
