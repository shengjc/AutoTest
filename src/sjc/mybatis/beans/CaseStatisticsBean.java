/**
 * 
 */
package sjc.mybatis.beans;

import java.io.Serializable;

/**
 * @author Administrator
 *
 */
public class CaseStatisticsBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String caseId = null;
	private String description = null;
	private String suitId = null;
	
	public CaseStatisticsBean() {
		super();
	}

	public CaseStatisticsBean(String caseId, String description) {
		super();
		this.caseId = caseId;
		this.description = description;
	}

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

	@Override
	public String toString() {
		return "CaseStatisticsBean [description=" + description + ", suitId=" + suitId + "]";
	}
	
	
}
