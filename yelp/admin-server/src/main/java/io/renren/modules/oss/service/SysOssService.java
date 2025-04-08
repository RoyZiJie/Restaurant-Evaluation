

package io.renren.modules.oss.service;

import io.renren.common.page.PageData;
import io.renren.common.service.BaseService;
import io.renren.modules.oss.entity.SysOssEntity;

import java.util.Map;

/**
 * File upload
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface SysOssService extends BaseService<SysOssEntity> {

	PageData<SysOssEntity> page(Map<String, Object> params);
}
