package EventManager.emanuele.services;

import EventManager.emanuele.entities.User;
import EventManager.emanuele.exceptions.UnauthorizedException;
import EventManager.emanuele.payloads.UserLoginDTO;
import EventManager.emanuele.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthService {
    @Autowired
    private UserService usersService;

    @Autowired
    private JWTTools jwtTools;

    public String authenticateUser(UserLoginDTO body){
        User user = usersService.findByEmail(body.email());
        if(body.password().equals(user.getPassword())) {
            return jwtTools.createToken(user);
        } else {
            throw new UnauthorizedException("Credenziali non valide!");
        }
    }
}
