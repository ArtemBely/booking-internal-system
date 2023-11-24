package com.example.bookingauth.services;

import com.example.bookingmodel.data.dto.CustomerDto;
import com.example.bookingmodel.data.entity.Customer;
import com.example.bookingmodel.data.entity.UserRole;
import com.example.bookingmodel.data.mapper.CustomerMapper;
import com.example.bookingmodel.repositories.CustomerRepository;
import com.example.bookingmodel.repositories.UserRoleRepository;
import com.example.bookingmodel.utilities.DefaultConstants;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class UserAuthService {

    private final CustomerMapper customerMapper;

    private final CustomerRepository customerRepository;

    private final EncryptionService encryptionService;

    private final JWTService jwtService;

    private final AuthenticationManager authenticationManager;

    private final UserRoleRepository userRoleRepository;


//    public CustomerDTO setAndSaveEncryptedPassword(CustomerDTO customerDTO) {
//        log.info("Encrypt password...");
//        String encryptedPassword = encryptionService.getEncodePass(customerDTO.getPassword());
//        customerDTO.setPassword(encryptedPassword);
//        return customerDTO;
//    }
//
//    public void createUser(CustomerDTO customerDTO) {
//        //setAndSaveEncryptedPassword(customerDTO);
//        CustomerEntity customerEntity = customerMapper.mapToEntity(setAndSaveEncryptedPassword(customerDTO));
//        log.info("start saving user into DB...");
//        customerRepository.save(customerEntity);
//        log.info("end saving user into DB.");
//    }


    /**
     * Check if bcrypt compare correctly (test method, remove in prod mode)
     *
     * @param customerDTO
     * @return
     */
    public boolean matchPassword(CustomerDto customerDTO) {
        String plainPassword = customerDTO.getPassword();
        Optional<Customer> customerEntity = customerRepository.findById(3);
        return encryptionService.checkPassword(plainPassword, customerEntity.get().getPassword());
    }

    public AuthenticationResponse register(CustomerDto request) {
        Customer customerEntity = customerMapper.mapToEntity(request);
        customerEntity.setPassword(encryptionService.getEncodePass(request.getPassword()));
        customerEntity.setLevelId(DefaultConstants.DEFAULT_USER_LEVEL);
        var savedUser = customerRepository.save(customerEntity);
        declareDefaultRoleForNewCustomer(savedUser);
        var jwtToken = jwtService.generateToken(savedUser);
        var refreshToken = jwtService.generateRefreshToken(savedUser);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public void declareDefaultRoleForNewCustomer(Customer customerEntity) {
        log.info("Saving default User role...");
        userRoleRepository.save(
                new UserRole(
                        customerEntity.getId(),
                        DefaultConstants.DEFAULT_USER_ROLE_ID));
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = customerRepository.findCustomerEntitiesByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
//        revokeAllUserTokens(user);
//        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
//                .accessToken(jwtToken)
//                .refreshToken(refreshToken)
                .build();
    }
}