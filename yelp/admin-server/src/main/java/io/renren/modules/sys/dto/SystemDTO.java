

package io.renren.modules.sys.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * System data
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
@Data
@Schema(title = "System data")
public class SystemDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long sysTime;
    private String osName;
    private String osArch;
    private String osVersion;
    private String userLanguage;
    private String userDir;
    private Long totalPhysical;
    private Long freePhysical;
    private BigDecimal memoryRate;
    private Integer processors;
    private String jvmName;
    private String javaVersion;
    private String javaHome;
    private Long javaTotalMemory;
    private Long javaFreeMemory;
    private Long javaMaxMemory;
    private String userName;
    private BigDecimal systemCpuLoad;
    private String userTimezone;

}
