

package io.renren.common.exception;

import cn.hutool.core.map.MapUtil;
import io.renren.common.utils.HttpContextUtils;
import io.renren.common.utils.IpUtils;
import io.renren.common.utils.JsonUtils;
import io.renren.common.utils.Result;
import io.renren.modules.log.entity.SysLogErrorEntity;
import io.renren.modules.log.service.SysLogErrorService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;


/**
 *Exception handler
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
@Slf4j
@RestControllerAdvice
@AllArgsConstructor
public class RenExceptionHandler {
    private final SysLogErrorService sysLogErrorService;

    /**
     * Handle custom exceptions
     */
    @ExceptionHandler(RenException.class)
    public Result handleRenException(RenException ex) {
        Result result = new Result();
        result.error(ex.getCode(), ex.getMsg());

        return result;
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public Result handleDuplicateKeyException(DuplicateKeyException ex) {
        Result result = new Result();
        result.error(ErrorCode.DB_RECORD_EXISTS);

        return result;
    }

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception ex) {
        log.error(ex.getMessage(), ex);

        saveLog(ex);

        return new Result().error();
    }

    /**
     * save exception log
     */
    private void saveLog(Exception ex) {
        SysLogErrorEntity log = new SysLogErrorEntity();

        //Request relevant information
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        log.setIp(IpUtils.getIpAddr(request));
        log.setUserAgent(request.getHeader(HttpHeaders.USER_AGENT));
        log.setRequestUri(request.getRequestURI());
        log.setRequestMethod(request.getMethod());
        Map<String, String> params = HttpContextUtils.getParameterMap(request);
        if (MapUtil.isNotEmpty(params)) {
            log.setRequestParams(JsonUtils.toJsonString(params));
        }

        //Exception information
        log.setErrorInfo(ExceptionUtils.getErrorStackTrace(ex));

        //save
        sysLogErrorService.save(log);
    }
}
