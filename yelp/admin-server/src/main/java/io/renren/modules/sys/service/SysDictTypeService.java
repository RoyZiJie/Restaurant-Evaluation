

package io.renren.modules.sys.service;

import io.renren.common.page.PageData;
import io.renren.common.service.BaseService;
import io.renren.modules.sys.dto.SysDictTypeDTO;
import io.renren.modules.sys.entity.DictType;
import io.renren.modules.sys.entity.SysDictTypeEntity;

import java.util.List;
import java.util.Map;

/**
 * Data Dictionary
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface SysDictTypeService extends BaseService<SysDictTypeEntity> {

    PageData<SysDictTypeDTO> page(Map<String, Object> params);

    SysDictTypeDTO get(Long id);

    void save(SysDictTypeDTO dto);

    void update(SysDictTypeDTO dto);

    void delete(Long[] ids);

    /**
     * Get all dictionaries
     */
    List<DictType> getAllList();

}