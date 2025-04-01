

package io.renren.modules.log.service;

import io.renren.common.page.PageData;
import io.renren.common.service.BaseService;
import io.renren.modules.log.dto.SysLogOperationDTO;
import io.renren.modules.log.entity.SysLogOperationEntity;

import java.util.List;
import java.util.Map;

/**
 * Operation log
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
public interface SysLogOperationService extends BaseService<SysLogOperationEntity> {

    PageData<SysLogOperationDTO> page(Map<String, Object> params);

    List<SysLogOperationDTO> list(Map<String, Object> params);

    void save(SysLogOperationEntity entity);
}