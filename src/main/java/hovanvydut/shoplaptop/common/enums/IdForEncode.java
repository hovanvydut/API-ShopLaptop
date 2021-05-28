package hovanvydut.shoplaptop.common.enums;

/**
 * @author hovanvydut
 * Created on 5/28/21
 */

public enum IdForEncode {
    BCRYPT("bcrypt"),
    NOOP("noop"),
    PBKDF2("pbkdf2"),
    SCRYPT("scrypt"),
    SHA256("sha256");

    private String id;

    IdForEncode(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }
}
