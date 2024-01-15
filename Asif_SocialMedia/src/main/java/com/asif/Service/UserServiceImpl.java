package com.asif.Service;

import com.asif.Config.JwtProvider;
import com.asif.Entity.User;
import com.asif.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findUserById(Integer id) throws Exception {
        return userRepository.findById(id).orElseThrow(() -> new Exception("User not exist with userid " + id));
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User updateUser(User user, Integer id) throws Exception {
        return userRepository.findById(id)
                .map(oldUser -> {
                    if (user.getFirstName() != null) {
                        oldUser.setFirstName(user.getFirstName());
                    }
                    if (user.getLastName() != null) {
                        oldUser.setLastName(user.getLastName());
                    }
                    if (user.getPassword() != null) {
                        oldUser.setPassword(user.getPassword());
                    }
                    if (user.getEmail() != null) {
                        oldUser.setEmail(user.getEmail());
                    }
                    if(user.getGender()!= null) {
                        oldUser.setGender(user.getGender());
                    }
                    return userRepository.save(oldUser);
                })
                .orElseThrow(() -> new Exception("User not exist with userid " + id));

    }

    @Override
    public List<User> getallUsers() {
        return userRepository.findAll();
    }

    @Override
    public User followUser(Integer requestUserId, Integer userId2) throws Exception {
        User requestUser = findUserById(requestUserId);
        User user2 = findUserById(userId2);
        user2.getFollowers().add(requestUser.getId());
        requestUser.getFollowings().add(user2.getId());
        userRepository.save(requestUser);
        userRepository.save(user2);
        return requestUser;
    }

    @Override
    public User unfollowUser(Integer userId1, Integer userId2) {
        return null;
    }

    @Override
    public List<User> searchUser(String query) {
        return userRepository.searchUser(query);
    }

    @Override
    public User findUserByJwt(String jwt) {
        String email = JwtProvider.getEmailFromJwtToken(jwt);
        System.out.println("email is " + email);

        return userRepository.findByEmail(email);
    }
}
