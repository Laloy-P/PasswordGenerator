package papalima.passwordgenerator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import papalima.passwordgenerator.model.PasswordRecord;
import papalima.passwordgenerator.services.PasswordGeneratorService;

@RestController
public class PasswordGeneratorController {
    PasswordGeneratorService generator = new PasswordGeneratorService();

    @GetMapping(path = "/generatePassword", produces = "application/json")
    public PasswordRecord generateNewPassword(
            @RequestParam(name = "passwordSize", required = false, defaultValue = "8") Integer passwordSize,
            @RequestParam(name = "specialChars", required = false, defaultValue = "true") Boolean specialChars,
            @RequestParam(name = "upperCase", required = false, defaultValue = "true") Boolean upperCase,
            @RequestParam(name = "withNumbers", required = false, defaultValue = "true") Boolean withNumbers
    ) {
        return generator.generatePasswordWithParam(passwordSize, specialChars, upperCase, withNumbers);
    }

}
