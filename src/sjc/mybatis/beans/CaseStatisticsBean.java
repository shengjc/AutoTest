/**
 * 
 */
package sjc.mybatis.beans;

import java.io.Serializable;

/**
 * @author Administrator
 * mybatis的封装
 */
public class CaseStatisticsBean implements Serializable {
	private static final long serialVersionUID = 1L;	//持久化版本号
	private String caseId = null;		//测试用例ID
	private String description = null;		//描述
	private String suitId = null;		//用例集ID
	
	public CaseStatisticsBean() {
		super();
	}
	
	//用例信息，只持久化caseid和description
	public CaseStatisticsBean(String caseId, String description) {
		super();
		this.caseId = caseId;
		this.description = description;
	}
	//用例信息，持久化caseid和description、suitid
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

	//输出用例信息
	@Override
	public String toString() {
		return "CaseStatisticsBean [description=" + description + ", suitId=" + suitId + "]";
	}
	
	
}
