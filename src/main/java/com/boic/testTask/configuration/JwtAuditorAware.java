package com.boic.testTask.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import java.util.Optional;

/**
 * Реализация AuditorAware для получения ID пользователя из JWT токена
 */
@Component
public class JwtAuditorAware implements AuditorAware<Long> {

    @Override
    public Optional<Long> getCurrentAuditor() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();

            // Если principal - это CustomUserDetails
            if (principal instanceof CustomUserDetails) {
                return Optional.of(((CustomUserDetails) principal).getId());
            }

            // Если principal - строка (username), можно попробовать найти ID
            if (principal instanceof String) {
                // Здесь должна быть логика поиска пользователя по username
                // return userRepository.findByUsername((String) principal).map(User::getId);
            }
        }

        return Optional.empty();
    }
}
