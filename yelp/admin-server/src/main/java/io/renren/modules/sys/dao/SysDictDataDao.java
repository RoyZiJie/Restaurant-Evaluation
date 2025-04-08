

package io.renren.modules.sys.dao;

import io.renren.common.dao.BaseDao;
import io.renren.modules.sys.entity.DictData;
import io.renren.modules.sys.entity.SysDictDataEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Dictionary data
 *
 * @author Mark sunlightcs@gmail.com
 */
@Mapper
public interface SysDictDataDao extends BaseDao<SysDictDataEntity> {

    /**
     * Dictionary data list
     */
    List<DictData> getDictDataList();
}
