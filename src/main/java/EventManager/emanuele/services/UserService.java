package EventManager.emanuele.services;

import EventManager.emanuele.entities.Event;
import EventManager.emanuele.entities.User;
import EventManager.emanuele.exceptions.NotFoundException;
import EventManager.emanuele.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UsersRepository usersRepository;

    List<User> userList = new ArrayList<>();

    public User save(User body){
        this.userList.add(body);
        return body;
    };

    public List<User> getUserList() {
        return userList;
    }

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
        newUser.setEmail(body.getEmail());
        return usersRepository.save(newUser);
    }

    public User findByEmail(String email){
        return usersRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Utente con email " + email + " non trovato!"));
    }


}
