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

import java.util.List;

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
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.getUserByEmail(email);

        List<Role> roles = roleRepository.findRolesByUserId(user.getId());

        return new UserDetailsImpl(user, roles);
    }
}
