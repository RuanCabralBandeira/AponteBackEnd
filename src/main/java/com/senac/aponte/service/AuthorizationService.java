package com.senac.aponte.service;

import com.senac.aponte.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class AuthorizationService implements UserDetailsService {

    private final UserRepository userRepository;

    public AuthorizationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Este método é chamado pelo Spring Security durante o processo de autenticação (login).
     * @param username O e-mail que o usuário forneceu na tela de login.
     * @return Um objeto UserDetails (a nossa entidade User) que o Spring Security pode usar.
     * @throws UsernameNotFoundException Se o usuário não for encontrado no banco de dados.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // O Spring Security usa o termo "username" de forma genérica.
        // Nós o usamos para procurar o usuário pelo e-mail.
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
    }
}

