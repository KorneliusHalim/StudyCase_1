package dao;

import java.util.List;
import java.util.Optional;

import model.User;

public interface UserDao extends Dao<User,Long>{

	Optional<User> findByUserName(String username);

}
