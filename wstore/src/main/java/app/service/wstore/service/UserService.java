package app.service.wstore.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import app.service.wstore.dto.RegisterDto;
import app.service.wstore.dto.UserDto;
import app.service.wstore.entity.CustomUserDetails;
import app.service.wstore.entity.Role;
import app.service.wstore.entity.User;
import app.service.wstore.repository.RoleRepository;
import app.service.wstore.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createCustomer(RegisterDto registerDto) {
        registerDto.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        Role setRole = roleRepository.findByName("CUSTOMER");

        User user = modelMapper.map(registerDto, User.class);
        user.addRole(setRole);

        return userRepository.saveAndFlush(user);
    }

    protected User createAdmin(String email, String password, String phone) {
        User adminUser = new User(email, passwordEncoder.encode(password), phone);
        Role setRole = roleRepository.findByName("ADMIN");

        adminUser.addRole(setRole);
        return userRepository.saveAndFlush(adminUser);
    }

    public UserDto showUser(int id) {
        User user = userRepository.findById(id);

        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(email);
        }
        return new CustomUserDetails(user);
    }

    @Transactional
    public UserDetails loadUserById(int id) {
        User user = userRepository.findById(id);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with" + id);
        }

        return new CustomUserDetails(user);
    }

    @Transactional
    public void generateUsersRoles() {
        Role roleAdmin = new Role("ADMIN");
        Role roleCustomer = new Role("CUSTOMER");
        Role roleStaff = new Role("STAFF");

        roleRepository.save(roleAdmin);
        roleRepository.save(roleCustomer);
        roleRepository.save(roleStaff);
        roleRepository.flush();

        User admin = createAdmin("admin@admin.com", "1", "000000000");
        userRepository.save(admin);
        userRepository.flush();
    }

}
