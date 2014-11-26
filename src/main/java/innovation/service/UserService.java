package innovation.service;

import java.util.List;

import innovation.model.User;

public interface UserService {
	 
	public int save(User user);
	
	public void delete(int id);
	
	public void update(User user);
	
	public User getById(int id);
	
	//public List<User> getUsers(String name);
	
	public List<User> getAll();
	
	public boolean ValidateName(String name);
	
<<<<<<< HEAD
	public boolean register(User user);
	
=======
>>>>>>> parent of 67e51a1... Revert "bug fix"
	public User login(User user);
}