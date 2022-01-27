package edukata.services;

import edukata.models.User;

import java.util.List;

public interface UserService {
     void addUser(User user);
     void updateUser(User user);
     User getUser(Long id);
     void deleteUser(Long id);
     List<User> getUsers();
}
