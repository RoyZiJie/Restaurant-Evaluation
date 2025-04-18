

package io.renren.common.service;

import io.renren.common.page.PageData;

import java.util.List;
import java.util.Map;

/**
 * CRUD basic service interface
 */
public interface CrudService<T, D> extends BaseService<T> {

    PageData<D> page(Map<String, Object> params);

    List<D> list(Map<String, Object> params);

    D get(Long id);

    void save(D dto);

    void update(D dto);

    void delete(Long[] ids);

}