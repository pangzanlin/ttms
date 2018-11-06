package cn.demo.ttms.attachement.dao;

import java.util.List;

import cn.demo.ttms.attachement.entity.Attachement;
import cn.demo.ttms.common.dao.BaseDao;

public interface AttachementDao 
   extends BaseDao<Attachement>{

	List<Attachement> findObjects();
	/**根据摘要信息判定文件是否已经存在*/
	int findObjectByDisgest(String fileDisgest);
	
	Attachement findObjectById(Integer id);
	
}
