package com.dojogrouppty.account;

import com.dojogrouppty.catalogs.SystemCodes;
import com.dojogrouppty.catalogs.SystemCodesRepository;
import com.dojogrouppty.common.ERROR_ACCOUNT;
import com.dojogrouppty.common.ParentControllerService;
import com.dojogrouppty.error.GenericBZKException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.ui.Model;

@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AccountService extends ParentControllerService implements UserDetailsService  {
	
	@Autowired
	private AccountRepository accountRepository;
        @Autowired
        MessageSource messageSource;
        @Autowired
        private SystemCodesRepository systemCodesRepository;
    private static final Logger logger
            = LoggerFactory.getLogger(AccountService.class);
//	@PostConstruct	
//	protected void initialize() {
//		save(new Account("user", "demo", "ROLE_USER"));
//		save(new Account("admin", "admin", "ROLE_ADMIN"));
//	}

	@Transactional
	public Account save(Account account) {
		//account.setPassword(account.getPassword());
		accountRepository.save(account);
		return account;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            logger.debug("Entrando en loadUserByUsername con username: "+username);
		Account account = accountRepository.findOneByEmail(username);                
		if(account == null || account.getDategivenLow()!=null) {
                    logger.debug("Account is NULL or  account.getDategivenLow()!=null");
			throw new UsernameNotFoundException("user not found");
		}
                
             logger.debug("Saliendo en loadUserByUsername..");   
		return createUser(account);
	}
	
	public void signin(Account account) {
            logger.debug("Entrando en signin..");
		SecurityContextHolder.getContext().setAuthentication(authenticate(account));
             logger.debug("Saliendo en signin..");    
	}
	
	private Authentication authenticate(Account account) {
             logger.debug("Entrando en authenticate..");
		return new UsernamePasswordAuthenticationToken(createUser(account), null, Collections.singleton(createAuthority(account)));		
	}
	
	private User createUser(Account account) {
            logger.debug("Entrando en createUser..");
		return new User(account.getEmail(), account.getPassword(), Collections.singleton(createAuthority(account)));
	}

	private GrantedAuthority createAuthority(Account account) { 
            logger.debug("Entrando en createAuthority..");
           
		return new SimpleGrantedAuthority(account.getRole());
	}
        public List<AccountDTO> getUserEnabled(){
         List<AccountDTO> listDto = new   ArrayList <AccountDTO>();
         List<Object []> objs = accountRepository.getUserEnable();
         for(Object [] row :objs){
             AccountDTO dto = new AccountDTO();
             dto.setId(Long.parseLong( row[0].toString()));
             dto.setEmail(row[1].toString());
             dto.setFirtname(row[2].toString());
             dto.setLasname(row[3].toString());
             dto.setRole(row[4].toString());
             logger.debug("dto.getEmail(): "+dto.getEmail()+" dto.getId():"+dto.getId());
             listDto.add(dto);
         }
         return listDto;
        }
   /**
    * Method add user
    * @param mod
    * @param accountForm 
    */     
   @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = {Exception.class,GenericBZKException.class})    
    public void addUser(Model mod,AccountForm accountForm){
        if(validMail(mod,accountForm)){
            if(validPassword(mod,accountForm.getPassword(),accountForm.getRepeatPassword())){
                insertUser(mod,accountForm);
            }
        }        
    }  
    /**
     * Method insert user
     * @param mod
     * @param accountForm
     * @return 
     */
    private boolean insertUser(Model mod,AccountForm accountForm){
        Account account = new Account();
        account.setEmail(accountForm.getEmail());
        account.setFirtName(accountForm.getFirtname());
        account.setLastName(accountForm.getLastname());
        BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
        String encPassword = pe.encode(accountForm.getPassword());
        account.setPassword(encPassword);
        account.setRole(accountForm.getRole());
        account.setDateRegistration(new Date());
        account.setSignatureName(accountForm.getFirtname().concat(accountForm.getLastname().substring(0,1)).toUpperCase());
        account = accountRepository.save(account);
        if(account.getId()==null){
            String message= messageSource.getMessage(ERROR_ACCOUNT.ERROR_INSERT_ACCOUNT.getValue(), null, Locale.getDefault());
             mod.addAttribute(GENERAL_MODAL_MESSAGE, message);
            return Boolean.FALSE;   
        }
        else{
           String message= messageSource.getMessage(ACCOUNT_OK, null, Locale.getDefault()).replace("%s", accountForm.getFirtname());
           mod.addAttribute(GENERAL_MODAL_MESSAGE, message);   
           accountRepository.flush();
        }
        return Boolean.TRUE;   
    }
    /**
     * Method valid mail
     * @param mod
     * @param accountForm
     * @return 
     */
    private boolean validMail(Model mod,AccountForm accountForm){
        Account account = accountRepository.findOneByEmail(accountForm.getEmail());  
        if(account!=null && account.getId()!=null && account.getDategivenLow()==null){
            String message= messageSource.getMessage(ERROR_ACCOUNT.ERROR_EMAIL.getValue(), null, Locale.getDefault());
             mod.addAttribute(GENERAL_MODAL_MESSAGE, message);
            return Boolean.FALSE;    
        }
     return Boolean.TRUE;    
    }
    /**
     *Validate the password with the following rules:
        8 characters length
        2 letters in Upper Case
        1 Special Character (! @ # $ & *)
        2 numerals (0-9)
        3 letters in Lower Case
     * @param mod
     * @param password
     * @return 
     */
    private boolean validPassword(Model mod,String password,String rPassword){
        Pattern p = Pattern.compile("^(?=.*[A-Z].*[A-Z])(?=.*[!@#$&*])(?=.*[0-9].*[0-9])(?=.*[a-z].*[a-z].*[a-z]).{8}$", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(password);
        boolean b = m.find();
        if (password == null || password.trim().isEmpty()) {
            String message = messageSource.getMessage(ERROR_ACCOUNT.ERROR_PASSWORD_SIZE.getValue(), null, Locale.getDefault());
            mod.addAttribute(GENERAL_MODAL_MESSAGE, message);
            return Boolean.FALSE; 
        }
        else if(password.length()<MIN_LENGTH_PASSWORD){
           String message= messageSource.getMessage(ERROR_ACCOUNT.ERROR_PASSWORD_SIZE.getValue(), null, Locale.getDefault());
           mod.addAttribute(GENERAL_MODAL_MESSAGE, message);
           return Boolean.FALSE;    
        }//lro@mail.bzk
        else if((rPassword == null || rPassword.trim().isEmpty()) || !password.equals(rPassword) ){
           String message= messageSource.getMessage(ERROR_ACCOUNT.ERROR_PASSWORD_CONFIRM.getValue(), null, Locale.getDefault());
           mod.addAttribute(GENERAL_MODAL_MESSAGE, message);
           return Boolean.FALSE; 
        }
        else if(!b){
            String message= messageSource.getMessage(ERROR_ACCOUNT.ERROR_PASSWORD_RULES.getValue(), null, Locale.getDefault());
           mod.addAttribute(GENERAL_MODAL_MESSAGE, message);
           return Boolean.FALSE;    
        }
        return Boolean.TRUE;  
    }
    
 
    
    /**
     * This our Password generating method
     * We have use static here, so that we not to
     * make any object for it 
     * @param len
     * @return 
     */         
    static String generatePassword(int len)
    {
         // A strong password has Cap_chars, Lower_chars,
        // numeric value and symbols. So we are using all of
        // them to generate our password
        String capitalChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String smallChars = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String symbols = "!@#$%^&*_=+-/.?<>)";
 
 
        String values = new StringBuilder(capitalChars).
                append(smallChars).
                append(numbers).append(symbols).toString();
 
        // Using random method
        Random rndm_method = new Random();
 
        char[] password = new char[len];
 
        for (int i = 0; i < len; i++)
        {
            // Use of charAt() method : to get character value
            // Use of nextInt() as it is scanning the value as int
            password[i] =
              values.charAt(rndm_method.nextInt(values.length()));
 
        }
        return new String(password);
    }
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = {Exception.class,GenericBZKException.class})        
     public void updateUsers(Model mod,AccountForm accountForm){
        List<SystemCodes> codSysAccion = systemCodesRepository.getSystemCodesByGroup(SYSTEM_SYSACCION);
        SystemCodes codDelete = null;
        SystemCodes codEdit = null;
        Integer count=0;
        for (SystemCodes cod : codSysAccion) {
            switch (cod.getCode()) {
                case CODE_DETELE:
                    codDelete = cod;
                    break;
                case CODE_EDIT:
                    codEdit = cod;
                    break;
            }
        }
         for(AccountDTO dto:accountForm.getListAccountDto()){
             logger.debug("ID: "+dto.getId()+" ACCION:"+dto.getAccion()+" ROLE:"+dto.getRole());
            if (dto.getAccion().equals(codDelete.getId().intValue())) {
                Account account = accountRepository.findOne(dto.getId());
                account.setDategivenLow(new Date());
                accountRepository.save(account);
                count++;
            }
            else if (dto.getAccion().equals(codEdit.getId().intValue())) {
                Account account = accountRepository.findOne(dto.getId());
                account.setRole(dto.getRole());
                accountRepository.save(account);
                count++;
            }
         }
      accountRepository.flush();   
      String message= messageSource.getMessage(ACCOUNT_OK_UPDATE, null, Locale.getDefault()).replace("%s", count.toString());
      mod.addAttribute(GENERAL_MODAL_MESSAGE, message);   
    }   
}
