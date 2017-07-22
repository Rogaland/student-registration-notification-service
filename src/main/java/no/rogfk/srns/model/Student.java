package no.rogfk.srns.model;

import lombok.Data;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.DnAttribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import javax.naming.Name;

@Data
@Entry(objectClasses = {"inetOrgPerson", "organizationalPerson", "person", "top"})
public final class Student {

    @Id
    private Name dn;

    @Attribute(name = "cn")
    private String cn;

    @Attribute(name = "logindisabled")
    boolean loginDisabled;

    @Attribute(name = "fullname")
    private String fullname;

    @Attribute(name = "sbasaktiveringtillatt")
    private String allowActivation;

    @Attribute(name = "mobile")
    private String mobile;

    @Attribute(name = "mail")
    private String mail;

    @Attribute(name = "l")
    private String school;

    @Attribute(name = "sbasaktivstatus")
    private String activeStatus;

    @Attribute(name = "brfkActivationNotification")
    private String activationNotification;

    public String getDn() {
        return dn.toString();
    }
}
