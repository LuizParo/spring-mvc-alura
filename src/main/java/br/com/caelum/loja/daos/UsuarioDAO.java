package br.com.caelum.loja.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.caelum.loja.models.Usuario;

@Repository
public class UsuarioDAO implements UserDetailsService {
    
    @PersistenceContext
    private EntityManager manager;
    
    @Override
    public Usuario loadUserByUsername(String email) throws UsernameNotFoundException {
	List<Usuario> usuarios = manager.createQuery("select u from Usuario u where u.email = :email", Usuario.class)
		.setParameter("email", email)
		.getResultList();
	
	if(usuarios.isEmpty()) throw new UsernameNotFoundException("Usuário " + email + " não foi encontrado!");
	
	return usuarios.get(0);
    }
    
    public void save(Usuario usuario) {
        Usuario recoveredUser = this.manager.find(Usuario.class, usuario.getEmail());
        if(recoveredUser == null) {
            this.manager.persist(usuario);
        }
    }
}