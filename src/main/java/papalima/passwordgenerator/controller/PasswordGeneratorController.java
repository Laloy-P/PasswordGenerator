package papalima.passwordgenerator.controller;

import org.springframework.web.bind.annotation.*;
import papalima.passwordgenerator.model.PasswordRecord;
import papalima.passwordgenerator.services.PasswordGeneratorService;

@RestController
public class PasswordGeneratorController {
    PasswordGeneratorService generator = new PasswordGeneratorService();

    @GetMapping(path = "/generatePassword", produces="application/json")
    public PasswordRecord generateNewPassword() {
        System.out.println("called");
        return generator.generatePassword();
    }


    @GetMapping(path = "/generatePassword2")
    public PasswordRecord generateNewPassword (
            @RequestParam(name="passwordSize", required = false) int passwordSize,
            @RequestParam(name="specialChars", required = false) boolean specialChars,
            @RequestParam(name="upperCase", required = false) boolean upperCase,
            @RequestParam(name="withNumbers", required = false) boolean withNumbers
    ){
        return generator.generatePasswordWithParam(passwordSize, specialChars, upperCase, withNumbers);
    }

}
