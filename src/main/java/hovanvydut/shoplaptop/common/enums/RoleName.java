package hovanvydut.shoplaptop.common.enums;

/**
 * @author hovanvydut
 * Created on 5/27/21
 */

public enum RoleName {
    ADMIN("Admin"),
    EDITOR("Editor"),
    SALESPERSON("Salesperson"),
    SHIPPER("Shipper"),
    ASSISTANCE("Assistance");

    private final String name;

    RoleName(String name) {
        this.name = name;
    }

    public String get() {
        return this.name;
    }

}
