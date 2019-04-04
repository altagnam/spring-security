package br.mg.gnam.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.mg.gnam.security.modal.User;

@Repository
public interface UserRepository  extends JpaRepository<User, Long>  {
	
	/**
	 * Busca um usuário na base de dados pelo nome
	 * @param name
	 * @return
	 */
	public User findByName(String name);
	
	
	/**
	 * Retorna um usuário pelo login
	 * @param login
	 * @return
	 */
	public User findByLogin(String login);
	

}
