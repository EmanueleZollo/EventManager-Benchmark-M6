package EventManager.emanuele.exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(int id){
        super("ID " + id + "non trovato!!");
    }

    public NotFoundException(String message) {
        super(message);
    }
}
