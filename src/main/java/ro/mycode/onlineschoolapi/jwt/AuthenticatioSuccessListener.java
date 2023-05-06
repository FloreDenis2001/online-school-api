package ro.mycode.onlineschoolapi.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ro.mycode.onlineschoolapi.services.LoginAttemptService;

@Component
public class AuthenticatioSuccessListener {
        private LoginAttemptService loginAttemptService;

        @Autowired
        public AuthenticatioSuccessListener(LoginAttemptService loginAttemptService){
            this.loginAttemptService=loginAttemptService;
        }



}
