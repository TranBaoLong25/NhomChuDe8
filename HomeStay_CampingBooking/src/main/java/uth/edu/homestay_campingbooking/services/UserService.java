package uth.edu.homestay_campingbooking.services;
import org.springframework.beans.factory.annotation.Autowired;
import uth.edu.homestay_campingbooking.exception.AppException;
import uth.edu.homestay_campingbooking.exception.ErrorCode;
import uth.edu.homestay_campingbooking.models.User;
import org.springframework.stereotype.Service;
import uth.edu.homestay_campingbooking.repositories.IUserRepository;

import java.util.List;

@Service
public class UserService implements IUserService {
    private IUserRepository userRepository;
    @Autowired
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public void save(User user) {
        for (User u : userRepository.findAll()) {
            if (u.getUsername().equals(user.getUsername())) {
                throw new AppException(ErrorCode.USER_EXISTED);
            }
        }
        userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void update(Long id, User user) {
        userRepository.update(id, user);
    }

}
