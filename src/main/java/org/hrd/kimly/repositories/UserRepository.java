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
			+ " user_hash"
			+ "	) VALUES ("
			+ "	#{user.username},"
			+ "	#{user.gender},"
			+ "	#{user.email},"
			+ "	#{user.phonenumber},"
			+ "	#{user.status},"
			+ "	#{user.user_hash})")
	@SelectKey(
			statement="SELECT last_value FROM users_id_seq",
			keyProperty="user.id", keyColumn="last_value",
			before=false,
			resultType=int.class
	)
	public boolean save(@Param("user") User user);
	
	@Delete("DELETE FROM users WHERE user_hash=#{user_hash}")
	public boolean delete(@Param("user_hash") String userHash);
	
	
	
	
	@Update("UPDATE users SET "
			+ "username=#{user.username},"
			+ "email=#{user.email},"
			+ "password=#{user.password},"
			+ "gender=#{user.gender}"
			+ " WHERE user_hash=#{user.userHash}")
	public boolean update(@Param("user") User user);
	
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
