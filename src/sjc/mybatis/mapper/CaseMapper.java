/**
 * 
 */
package sjc.mybatis.mapper;

import java.util.List;

import sjc.mybatis.beans.CaseStatisticsBean;

/**
 * @author Administrator
 * mybatis��ӳ��map
 */
public interface CaseMapper {
	/*��������
	@param casebean
	@retrun
	@throws Exception*/
	public int insertCase(CaseStatisticsBean casebean) throws Exception;	
	
	/*��������
	@param casebean,id
	@retrun
	@throws Exception*/
	public int updateCase(CaseStatisticsBean casebean,String id) throws Exception;
	
	/*ɾ������
	@param id
	@retrun
	@throws Exception*/
	public int deleteCase(String id) throws Exception;
	
	/*��������
	@param id
	@retrun
	@throws Exception*/
	public CaseStatisticsBean selectCaseById(String id) throws Exception;
	
	/*��������	
	@retrun
	@throws Exception*/
	public List<CaseStatisticsBean> selectAllCase() throws Exception;
}
