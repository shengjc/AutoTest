/**
 * 
 */
package sjc.mybatis.beans;

import java.io.Serializable;

/**
 * @author Administrator
 * mybatis�ķ�װ
 */
public class CaseStatisticsBean implements Serializable {
	private static final long serialVersionUID = 1L;	//�־û��汾��
	private String caseId = null;		//��������ID
	private String description = null;		//����
	private String suitId = null;		//������ID
	
	public CaseStatisticsBean() {
		super();
	}
	
	//������Ϣ��ֻ�־û�caseid��description
	public CaseStatisticsBean(String caseId, String description) {
		super();
		this.caseId = caseId;
		this.description = description;
	}
	//������Ϣ���־û�caseid��description��suitid
	public CaseStatisticsBean(String caseId, String description, String suitId) {
		super();
		this.caseId = caseId;
		this.description = description;
		this.suitId = suitId;
	}	

	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSuitId() {
		return suitId;
	}

	public void setSuitId(String suitId) {
		this.suitId = suitId;
	}

	//���������Ϣ
	@Override
	public String toString() {
		return "CaseStatisticsBean [description=" + description + ", suitId=" + suitId + "]";
	}
	
	
}
