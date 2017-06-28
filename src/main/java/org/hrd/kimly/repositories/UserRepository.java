package org.hrd.kimly.repositories;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.hrd.kimly.model.User;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository {

	@Select("SELECT " 
			+ " id," 
			+ " username,"
			+ " gender," 
			+ " email,"
			+ " phonenumber,"
			+ " status,"
			+ " user_hash"
			+ " from users")
	public List<User> getUser(); 
	
	
	
	/**
	 * Save user to database
	 * @param user
	 * @return
	 */
	@Insert("INSERT INTO users ("
			+ "	username, "
			+ "	gender, "
			+ "	email, "
			+ " phonenumber,"
			+ " status,"
			+ " user_hash,"
			+ " created_date"
			+ "	) VALUES ("
			+ "	#{user.username},"
			+ "	#{user.gender},"
			+ "	#{user.email},"
			+ "	#{user.phonenumber},"
			+ "	#{user.status},"
			+ "	#{user.user_hash}, #{user.created_date})")
	@SelectKey(
			statement="SELECT last_value FROM users_id_seq",
			keyProperty="user.id", keyColumn="last_value",
			before=false,
			resultType=int.class
	)
	public boolean save(@Param("user") User user);
	
	@Delete("DELETE FROM users WHERE user_hash=#{user_hash}")
	public boolean delete(@Param("user_hash") String userHash);
	
	@Update("UPDATE users SET"
			+ " username=#{user.username},"
			+ " email=#{user.email},"
			+ " gender=#{user.gender},"
			+ " phonenumber=#{user.phonenumber},"
			+ " status=#{user.status}"
			+ " WHERE user_hash=#{user.user_hash}")
	public boolean updateUser(@Param("user") User user);
	
	@Select("SELECT "
			+ " username,"
			+ " email,"
			+ " gender,"
			+ " phonenumber,"
			+ " status"
			+ " FROM users"
			+ " WHERE user_hash=#{user_hash}")
	public User getUser1(String user_hash);
	
	@Insert("<script>"
			+ "	INSERT INTO users ("
			+ "id,"
			+ "	username, "
			+ "	gender, "
			+ "	email, "
			+ " phonenumber,"
			+ " status,"
			+ "	user_hash,"
			+ " created_date"
			+ "	) VALUES ("
			+ " <foreach collection='users' item='user' separator=','>("
			+ "	#{users.id},"
			+ "	#{users.username},"
			+ "	#{users.gender},"
			+ "	#{users.email},"
			+ "	#{users.phonenumber},"
			+ "	#{users.status},"
			+ "	#{users.user_hash},"
			+ "	#{users.created_date})"
			+ "</foreach>"
			+ "</script>")
	public boolean saveBatch(@Param("users") List<User> users);
}
