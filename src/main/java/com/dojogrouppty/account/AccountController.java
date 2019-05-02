package com.dojogrouppty.account;

import com.dojogrouppty.common.ParentControllerService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class AccountController  extends ParentControllerService {

    private final AccountRepository accountRepository;

    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @SuppressWarnings("deprecation")
	@GetMapping("account/current")
    @ResponseStatus(value = HttpStatus.OK)
    @Secured({"ROLE_USER", "ROLE_ADMIN","ROLE_SUPER_ADMIN"})
    public Account currentAccount(Principal principal) {
        Assert.notNull(principal);
        return accountRepository.findOneByEmail(principal.getName());
    }

    @GetMapping("account/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    @Secured({"ROLE_ADMIN","ROLE_SUPER_ADMIN"})
    public Account account(@PathVariable("id") Long id) {
        return accountRepository.findOne(id);
    }   

}
