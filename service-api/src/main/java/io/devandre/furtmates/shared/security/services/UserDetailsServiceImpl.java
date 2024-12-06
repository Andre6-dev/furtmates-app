package io.devandre.furtmates.shared.security.services;

import io.devandre.furtmates.users.control.repository.RoleRepository;
import io.devandre.furtmates.users.control.repository.UserRepository;
import io.devandre.furtmates.users.entity.Role;
import io.devandre.furtmates.users.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserDetailsServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(username);

        Role role = roleRepository.findRoleById(Long.valueOf(user.getRoleId()));

        return new UserDetailsImpl(user, role);
    }
}
