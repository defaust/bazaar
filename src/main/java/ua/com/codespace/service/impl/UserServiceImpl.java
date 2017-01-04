package ua.com.codespace.service.impl;


import ua.com.codespace.exception.UserNotFoundException;
import ua.com.codespace.model.User;
import ua.com.codespace.repository.UserRepository;
import ua.com.codespace.service.PhotoService;
import ua.com.codespace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PhotoService photoService;

    @Override
    public List<User> getUserFriends(Long id) {
        User user = userRepository.findOne(id);
        if (user == null) {
            throw new UserNotFoundException(id);
        } else {
            return user.getFriends().stream()
                    .sorted(comparing(User::getUsername))
                    .collect(toList());
        }
    }

    @Override
    public User save(User user, MultipartFile image) {
        String photoUrl = photoService.saveUserPhoto(user.getUsername(), image);
        user.setPhotoUrl(photoUrl);
        return userRepository.save(user);
    }
}
