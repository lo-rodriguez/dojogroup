/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dojogrouppty.catalogs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.Query;
import javax.transaction.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.domain.Sort;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Service;


import com.dojogrouppty.common.ParentControllerService;
import com.dojogrouppty.common.STATUS;

/**
 *
 * @author lrodriguezn
 */
@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CatalogsService  extends ParentControllerService {
        private static final Logger logger
            = LoggerFactory.getLogger(CatalogsService.class);
    @Autowired
    private SystemCodesRepository systemCodesRepository;
    private static Map <Long,String> allCodes; 
    @Autowired
    private LocalContainerEntityManagerFactoryBean entityManagerFact;
    @Autowired
    private MessageSource messageSource;
    private static List <SystemCodes> listCodes = new ArrayList<SystemCodes>(); 
    /**
     * list of cod by group
     * @param group
     * @return 
     */
    public List <SystemCodes> getSystemCodesByGroup(String group){
      logger.debug("Entrando al metodo getSystemCodesByGroup con el grupo:"+group);
       if(listCodes==null || listCodes.isEmpty()){
          
          listCodes =  systemCodesRepository.findAll(sortAscByDescription() );
       }
      return  listCodes.stream().filter(cod->group.equals(cod.getGroup()))
         .collect(Collectors.toList());
    }
    private  Sort sortAscByDescription(){
        return new Sort(Sort.Direction.ASC, "description") ;
        
    }
    /**
     * Getting all codes
     * @return Map <id,String>
     */
    public Map<Long,String> getAllCodes (){
      logger.debug("Entrando a getAllCodes..");
       if (allCodes==null || allCodes.isEmpty() ){
        logger.debug("No existe datos en allCodes..");      
        List <SystemCodes> list  =   systemCodesRepository.findAll(sortAscByDescription());
        logger.debug("Lis contiene:"+list.size()); 
        allCodes = new HashMap <Long,String> ();
        for(SystemCodes code:list){
           allCodes.put(code.getId(),code.getDescription());
        }
       }
       logger.debug("Saliendo de getAllCodes..");
       return allCodes;
    }
    /**
     * Get description code
     * @param id
     * @return 
     */
    public String getDescriptionCode(Long id){
      String decription="";
      logger.debug("getDescriptionCode ID=["+id+"]");
      SystemCodes cod=  systemCodesRepository.findOne(id);
      if(cod==null){
        logger.error("Error in getting cod..");
      }
      decription=cod.getDescription();
      logger.debug("decription=["+decription+"]");
      return decription;  
    }
    public String getDescriptionCode(short id){
        Short t = id;
        return getDescriptionCode(t.longValue());
    }
    @SuppressWarnings("unchecked")
    /**
     * Method that returns all types of products
     * @return Map <Integer,String> types of products
     */
    public Map <Integer,String> getTypesProduct(){
    	Map <Integer,String> map= new HashMap<>();
    	 Query query = entityManagerFact.getObject().createEntityManager().createNamedQuery(SQL_GET_TYPES_PRODUCTS);    	  
		List<Object[]> listObj = query.getResultList();
           for(Object[] objs:listObj){
        	   map.put(Integer.parseInt(objs[0].toString ()),objs[1].toString ());
           }
        return map;
    }
    
    public  Map <Short,String> getStatus(){
    	Map <Short,String> map= new HashMap<>();
    	map.put(STATUS.ACTIVE.getStatus(),  messageSource.getMessage(MESSAGE_ACTIVE, null, Locale.getDefault()));
    	map.put(STATUS.INACTIVE.getStatus(),  messageSource.getMessage(MESSAGE_INACTIVE, null, Locale.getDefault()));
    	return map;
    }
}
