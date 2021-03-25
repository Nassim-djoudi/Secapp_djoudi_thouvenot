package dao;

import domain.User;

public class UserDao extends AbstractJpaDao<String, User> implements IUserDao {
	
	// M�thode permettant de trouver un utilisateur � partir de son ID
	@Override
	public User findOne(String id) {
		return this.entityManager.find(User.class, id);
	}
	
}
