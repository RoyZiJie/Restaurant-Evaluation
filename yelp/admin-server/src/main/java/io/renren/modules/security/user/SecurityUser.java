

package io.renren.modules.security.user;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * User
 *
 * @author Mark sunlightcs@gmail.com
 */
public class SecurityUser {

    public static Subject getSubject() {
        try {
            return SecurityUtils.getSubject();
        }catch (Exception e){
            return null;
        }
    }

    /**
     * Get user information
     */
    public static UserDetail getUser() {
        Subject subject = getSubject();
        if(subject == null){
            return new UserDetail();
        }

        UserDetail user = (UserDetail)subject.getPrincipal();
        if(user == null){
            return new UserDetail();
        }

        return user;
    }

    /**
     * Get user ID
     */
    public static Long getUserId() {
        return getUser().getId();
    }

    /**
     * Obtain department ID
     */
    public static Long getDeptId() {
        return getUser().getDeptId();
    }
}