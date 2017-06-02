/**
 * 
 */
package sjc.mybatis.mapper;

import java.util.List;

import sjc.mybatis.beans.CaseStatisticsBean;

/**
 * @author Administrator
 * mybatis的映射map
 */
public interface CaseMapper {
	/*插入数据
	@param casebean
	@retrun
	@throws Exception*/
	public int insertCase(CaseStatisticsBean casebean) throws Exception;	
	
	/*更新数据
	@param casebean,id
	@retrun
	@throws Exception*/
	public int updateCase(CaseStatisticsBean casebean,String id) throws Exception;
	
	/*删除数据
	@param id
	@retrun
	@throws Exception*/
	public int deleteCase(String id) throws Exception;
	
	/*插入数据
	@param id
	@retrun
	@throws Exception*/
	public CaseStatisticsBean selectCaseById(String id) throws Exception;
	
	/*插入数据	
	@retrun
	@throws Exception*/
	public List<CaseStatisticsBean> selectAllCase() throws Exception;
}
