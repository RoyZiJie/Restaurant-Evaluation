

package io.renren.modules.sys.enums;

/**
 * Super Administrator Enumeration
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
public enum SuperAdminEnum {
    YES(1),
    NO(0);

    private int value;

    SuperAdminEnum(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }
}