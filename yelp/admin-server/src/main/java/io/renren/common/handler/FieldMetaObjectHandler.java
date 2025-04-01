

package io.renren.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import io.renren.modules.security.user.SecurityUser;
import io.renren.modules.security.user.UserDetail;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Public field, auto-filling value
 *
 * @author Mark sunlightcs@gmail.com
 */
@Component
public class FieldMetaObjectHandler implements MetaObjectHandler {
    private final static String CREATE_DATE = "createDate";
    private final static String CREATOR = "creator";
    private final static String UPDATE_DATE = "updateDate";
    private final static String UPDATER = "updater";
    private final static String DEPT_ID = "deptId";

    @Override
    public void insertFill(MetaObject metaObject) {
        UserDetail user = SecurityUser.getUser();
        Date date = new Date();

        //Creator
        strictInsertFill(metaObject, CREATOR, Long.class, user.getId());
        //Creation time
        strictInsertFill(metaObject, CREATE_DATE, Date.class, date);

        //Creator所属部门
        strictInsertFill(metaObject, DEPT_ID, Long.class, user.getDeptId());

        //Updater
        strictInsertFill(metaObject, UPDATER, Long.class, user.getId());
        //Update time
        strictInsertFill(metaObject, UPDATE_DATE, Date.class, date);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //Updater
        strictUpdateFill(metaObject, UPDATER, Long.class, SecurityUser.getUserId());
        //Update time
        strictUpdateFill(metaObject, UPDATE_DATE, Date.class, new Date());
    }
}