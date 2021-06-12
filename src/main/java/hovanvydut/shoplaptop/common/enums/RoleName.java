package hovanvydut.shoplaptop.common.enums;

/**
 * @author hovanvydut
 * Created on 5/27/21
 */

public enum RoleName {
    ADMIN("ROLE_Admin"),
    EDITOR("ROLE_Editor"),
    SALESPERSON("ROLE_Salesperson"),
    SHIPPER("ROLE_Shipper"),
    ASSISTANCE("ROLE_Assistance");

    private final String name;

    RoleName(String name) {
        this.name = name;
    }

    public String get() {
        return this.name;
    }

}
