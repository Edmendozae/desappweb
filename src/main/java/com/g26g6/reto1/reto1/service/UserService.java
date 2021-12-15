package com.g26g6.reto1.reto1.service;

import com.g26g6.reto1.reto1.model.User;
import com.g26g6.reto1.reto1.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ED MENDOZA
 */
@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    public List<User> getAll(){
        return userRepository.getAll();
    }
    
    public Optional<User> getUser(int id){
        return userRepository.getUser(id);
    }
    
     public User registrar(User user){
        if (user.getId() == null){
            if (existeEmail(user.getEmail()) == false){
                return userRepository.save(user);
            } else {
                return user;
            }
        } else {
            return user;
        }
     }
     
     public boolean existeEmail(String email){
         return userRepository.existeEmail(email);
     }
     public User autenticarUsuario(String email, String password){
         Optional<User> usuario = userRepository.autenticarUsuario(email, password);
         
         if(usuario.isEmpty()){
             return new User(email, password, "NO DEFINIDO");
         } else {
             return usuario.get();
         }
     }
}