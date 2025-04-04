

package io.renren.modules.log.service;


import io.renren.common.page.PageData;
import io.renren.common.service.BaseService;
import io.renren.modules.log.dto.SysLogErrorDTO;
import io.renren.modules.log.entity.SysLogErrorEntity;

import java.util.List;
import java.util.Map;

/**
 * Exception log
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
public interface SysLogErrorService extends BaseService<SysLogErrorEntity> {

    PageData<SysLogErrorDTO> page(Map<String, Object> params);

    List<SysLogErrorDTO> list(Map<String, Object> params);

    void save(SysLogErrorEntity entity);

}