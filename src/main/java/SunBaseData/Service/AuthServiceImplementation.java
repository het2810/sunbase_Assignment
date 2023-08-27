package SunBaseData.Service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class AuthServiceImplementation implements AuthService {

	private final Map<String, String> userCredentials = new HashMap<>();
	private final Map<String, String> tokenCache = new HashMap<>();

	public AuthServiceImplementation() {
		userCredentials.put("test@sunbasedata.com", "Test@123");
	}

	@Override
	public boolean authenticateUser(String loginId, String password) {
		String storedPassword = userCredentials.get(loginId);
		return storedPassword != null && storedPassword.equals(password);

	}

	@Override
	public String generateBearerToken() {
		String token = "BearerToken_" + System.currentTimeMillis();
        tokenCache.put(token, "authenticatedUser"); // Cache the token for validation
        return token;
	}

	@Override
	public boolean isValidBearerToken(String bearerToken) {
		// TODO Auto-generated method stub
		 return tokenCache.containsKey(bearerToken);
	}

}
