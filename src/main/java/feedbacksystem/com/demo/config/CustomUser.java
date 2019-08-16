package feedbacksystem.com.demo.config;


import feedbacksystem.com.demo.model.utils.AccessType;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Data
public class CustomUser extends User {

    private Long userId;
    private Integer accessType;

    public CustomUser(String username, String password, boolean enabled, boolean accountNonExpired,
                      boolean credentialsNonExpired,
                      boolean accountNonLocked,
                      Collection<? extends GrantedAuthority> authorities, Long userID, Integer accessType) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.userId = userID;
        this.accessType = accessType;
    }

    public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities, Long userId,
                      Integer accessType) {
        this(username, password, true, true, true, true,
                authorities, userId, accessType);
    }


}
