package BioAuth.api.dtos.userrole;

public interface UserRoleProjection {
    Long getUserId();
    String getFullName();
    String getEmail();
    String getImage();
    String getDigitalImage1();
    String getDigitalImage2();
    String getDigitalImage3();
    String getDigitalImage4();
    String getDigitalImage5();
    String getDigitalImage6();
    
    Long getRoleId();
    String getName();
    String getDescription();
    Long getLevelOrder();
}