

package io.renren.modules.security.service.impl;

import cn.hutool.cache.Cache;
import cn.hutool.cache.CacheUtil;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import io.renren.common.redis.RedisKeys;
import io.renren.common.redis.RedisUtils;
import io.renren.modules.security.service.CaptchaService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Verification code
 *
 * @author Mark sunlightcs@gmail.com
 */
@Service
public class CaptchaServiceImpl implements CaptchaService {
    @Resource
    private RedisUtils redisUtils;
    @Value("${renren.redis.open: false}")
    private boolean open;
    /**
     * Local Cache 5 minutes expired
     */
    Cache<String, String> localCache = CacheUtil.newLRUCache(1000, 1000 * 60 * 5);

    @Override
    public void create(HttpServletResponse response, String uuid) throws IOException {
        response.setContentType("image/gif");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        //Generate verification code
        SpecCaptcha captcha = new SpecCaptcha(150, 40);
        captcha.setLen(5);
        captcha.setCharType(Captcha.TYPE_DEFAULT);
        captcha.out(response.getOutputStream());

        //Save to cache
        setCache(uuid, captcha.text());
    }

    @Override
    public boolean validate(String uuid, String code) {
        //Get the verification code
        String captcha = getCache(uuid);

        //Successful test
        if (code.equalsIgnoreCase(captcha)) {
            return true;
        }

        return false;
    }

    private void setCache(String key, String value) {
        if (open) {
            key = RedisKeys.getCaptchaKey(key);
            redisUtils.set(key, value, 300);
        } else {
            localCache.put(key, value);
        }
    }

    private String getCache(String key) {
        if (open) {
            key = RedisKeys.getCaptchaKey(key);
            String captcha = (String) redisUtils.get(key);
            //Delete the verification code
            if (captcha != null) {
                redisUtils.delete(key);
            }

            return captcha;
        }

        String captcha = localCache.get(key);
        //Delete the verification code
        if (captcha != null) {
            localCache.remove(key);
        }
        return captcha;
    }
}
