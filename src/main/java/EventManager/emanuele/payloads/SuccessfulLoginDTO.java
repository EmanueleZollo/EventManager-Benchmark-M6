package EventManager.emanuele.payloads;

public class SuccessfulLoginDTO {
    public record UserLoginSuccessDTO(String accessToken) {
    }
}
