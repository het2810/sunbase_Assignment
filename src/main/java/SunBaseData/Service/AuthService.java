package SunBaseData.Service;
public interface AuthService {

    boolean authenticateUser(String loginId, String password);

    String generateBearerToken();

    boolean isValidBearerToken(String bearerToken);
}
