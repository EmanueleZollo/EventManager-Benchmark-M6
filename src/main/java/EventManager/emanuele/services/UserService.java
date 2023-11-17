package EventManager.emanuele.services;

import EventManager.emanuele.entities.Event;
import EventManager.emanuele.entities.Role;
import EventManager.emanuele.entities.User;
import EventManager.emanuele.exceptions.BadRequestException;
import EventManager.emanuele.exceptions.NotFoundException;
import EventManager.emanuele.payloads.NewUserDTO;
import EventManager.emanuele.repository.UsersRepository;
import io.jsonwebtoken.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UsersRepository usersRepository;

    public User save(NewUserDTO body) throws IOException {
        usersRepository.findByEmail(body.email()).ifPresent( user -> {
            throw new BadRequestException("L'email " + user.getEmail() + " è già utilizzata!");
        });
        User newUser = new User();
        newUser.setName(body.name());
        newUser.setSurname(body.surname());
        newUser.setNickname(body.nickname());
        newUser.setPassword(body.password());
        newUser.setEmail(body.email());
        newUser.setRole(Role.USER);
        return usersRepository.save(newUser);
    };

    public User findById(int id) throws NotFoundException{
        return usersRepository.findById(id).orElseThrow( ()  -> new NotFoundException(id));
    }

    public void findByIdAndDelete(int id) throws NotFoundException{
        User newUser = this.findById(id);
        usersRepository.delete(newUser);
    }

    public User findByIdAndUpdate(int id, User body) throws NotFoundException {
        User newUser = this.findById(id);
        newUser.setSurname(body.getSurname());
        newUser.setName(body.getName());
        newUser.setSurname(body.getSurname());
        newUser.setNickname(body.getNickname());
        newUser.setPassword(body.getPassword());
        newUser.setEmail(body.getEmail());
        return usersRepository.save(newUser);
    }

    public User findByEmail(String email){
        return usersRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Utente con email " + email + " non trovato!"));
    }


}
