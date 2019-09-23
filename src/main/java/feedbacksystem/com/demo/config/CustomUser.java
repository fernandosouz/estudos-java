package feedbacksystem.com.demo.config;


import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Data
public class CustomUser extends User {

    private Long userId;
    private Integer accessType;
    private Long companyId;

    public CustomUser(String username, String password, boolean enabled, boolean accountNonExpired,
                      boolean credentialsNonExpired,
                      boolean accountNonLocked,
                      Collection<? extends GrantedAuthority> authorities, Long userID, Integer accessType, Long companyId) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.userId = userID;
        this.accessType = accessType;
        this.companyId = companyId;
    }

    public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities, Long userId,
                      Integer accessType, Long companyId) {
        this(username, password, true, true, true, true,
                authorities, userId, accessType, companyId);
    }


}
