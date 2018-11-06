package cn.demo.ttms.team.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.demo.ttms.common.exception.SaveRuntimeException;
import cn.demo.ttms.common.web.PageObject;
import cn.demo.ttms.project.dao.ProjectDao;
import cn.demo.ttms.team.dao.TeamDao;
import cn.demo.ttms.team.entity.Team;
import cn.demo.ttms.team.service.TeamService;

@Service
public class TeamServiceImpl implements TeamService {

	@Resource
	private TeamDao teamDao;
	
	@Resource
	private ProjectDao projectDao;
	
	public Map<String, Object> 
	findPageObjects(String projectName,
			Integer valid, 
			Integer pageCurrent) {
		PageObject pageObject=new PageObject();
		//pageObject.setPageSize(3); 不写默认是2,我们在对象中定义的
		pageObject.setPageCurrent(pageCurrent);
		//根据startIndex及参数获得当前页数据
		List<Map<String,Object>> list=
		teamDao.findPageObjects(projectName,
				valid,pageObject.getStartIndex(),
				pageObject.getPageSize());
		//获得总页数
		int rowCount=
		teamDao.getRowCount(projectName, valid);
		System.out.println(rowCount);//5
		//设置记录数
		pageObject.setRowCount(rowCount);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("list", list);
		map.put("pageObject", pageObject);
		return map;
	}
	/**保存团信息*/
	public void saveObject(Team team) {
		if(team==null)
		throw new SaveRuntimeException("保存的信息不能为空");
		int rows=teamDao.insertObject(team);
		if(rows==-1)
	    throw new SaveRuntimeException("保存失败");
	}
	public List<Map<String, Object>> findProjectIdAndNames() {
		return projectDao.findIdAndNames();
	}
	
}
