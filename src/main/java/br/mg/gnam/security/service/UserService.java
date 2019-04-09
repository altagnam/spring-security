package br.mg.gnam.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.mg.gnam.security.modal.User;
import br.mg.gnam.security.repository.UserRepository;

/**
 * <p>Serviço responsável por manipular as informações dos usuários cadastrados no sistema.</p> 
 * @author rafael.altagnam
 * @since 06/02/2019
 * @version 1.0
 */
@Service
public class UserService {

	/**
	 * ROLE padrão para todos os usuários
	 */
	private static final String ROLE_USER = "USER";
	
	/**
	 * Repositorio de dados do usuário
	 */
	@Autowired
	private UserRepository userRepository;

	/**
	 * Bean responsável pela criptografia da senha
	 */
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	

	/**
	 * Salva um usuário na base de dados
	 * 
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	public User save(User user) throws Exception {
		user.validate();		
		if (porLogin(user.getLogin()) != null) {
			throw new Exception("Login já cadastrado. Informe um login diferente.");
		}
		
		if (porNome(user.getName()) != null) {
			throw new Exception("Nome já cadastrado. Informe um nome diferente.");
		}
		
		user.setRole(ROLE_USER);
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}
	
	/**
	 * Remove um usuário  da base
	 * @param user
	 */
	public void delete(User user) {
		userRepository.delete(user);
	}

	/**
	 * Retorna a lista de usuarios cadastrados 
	 * @return
	 */
	public List<User> todos() {
		return userRepository.findAll();
	}

	/**
	 * Retorna uma usuário de acordo com o nome
	 * 
	 * @param name
	 * @return
	 */
	public User porNome(String name) {
		return userRepository.findByName(name);
	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	public User porLogin(String login) {
		User user = userRepository.findByLogin(login);
		return user;
	}

}
