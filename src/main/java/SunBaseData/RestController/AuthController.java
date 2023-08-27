package SunBaseData.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import SunBaseData.Request.AuthRequest;
import SunBaseData.Service.AuthService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // Authenticate user and return a bearer token
    @PostMapping("/authenticate")
    public ResponseEntity<Map<String, String>> authenticateUser(@RequestBody AuthRequest authRequest) {
        String loginId = authRequest.getLoginId();
        String password = authRequest.getPassword();

        // Authenticate the user (You should implement this logic)
        boolean isAuthenticated = authService.authenticateUser(loginId, password);

        if (isAuthenticated) {
            // Generate a bearer token (You should implement this logic)
            String bearerToken = authService.generateBearerToken();

            // Return the token in the response
            Map<String, String> response = new HashMap<>();
            response.put("token", bearerToken);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
