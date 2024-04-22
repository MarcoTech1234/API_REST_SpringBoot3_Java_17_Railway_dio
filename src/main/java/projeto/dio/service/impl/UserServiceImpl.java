package projeto.dio.service.impl;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import projeto.dio.domain.model.User;
import projeto.dio.domain.repository.UserRepository;
import projeto.dio.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
            .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public User create(User userToCreate) {
        if (userRepository.existsByAccountNumber(userToCreate.getAccount().getNumber())) {
            throw new IllegalArgumentException("O numero da conta ja existe.");
        }
        return userRepository.save(userToCreate);
    }
}
