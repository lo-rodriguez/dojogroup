/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dojogrouppty.products;

import com.dojogrouppty.catalogs.SystemCodes;
import com.dojogrouppty.catalogs.SystemCodesRepository;
import com.dojogrouppty.common.DateUtils;
import com.dojogrouppty.common.ERROR_PRODUCTS;
import com.dojogrouppty.common.ParentControllerService;
import com.dojogrouppty.error.GenericBZKException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author lrodriguezn
 */
@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ProductsService extends ParentControllerService {

    @Autowired
    private ProductsRepository productsRepository;
    @Autowired
    private SystemCodesRepository systemCodesRepository;
    @Autowired
    MessageSource messageSource;
    private static final Logger logger
            = LoggerFactory.getLogger(ProductsService.class);
    
    private  Map<Integer, String> sortByValue(Map<Integer, String> unsortMap) {

        // 1. Convert Map to List of Map
        List<Map.Entry<Integer, String>> list
                = new LinkedList<Map.Entry<Integer, String>>(unsortMap.entrySet());

        // 2. Sort list with Collections.sort(), provide a custom Comparator
        //    Try switch the o1 o2 position for a different order
        Collections.sort(list, (Map.Entry<Integer, String> o1, Map.Entry<Integer, String> o2) -> (o1.getValue()).compareTo(o2.getValue()));

        // 3. Loop the sorted list and put it into a new insertion order Map LinkedHashMap
        Map<Integer, String> sortedMap = new LinkedHashMap<Integer, String>();
        for (Map.Entry<Integer, String> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }
    /**
     * Method return current product account
     * @return int current product account
     */
     public int currentProductAccount(){
      logger.debug("Into method currentProductAccount..");
      return  productsRepository.currentProductAccount();
     }
     /**
      * Method get the active products 
      * @return Map active products
      */
       public Map<Integer,String>   getActiveProducts(){
    	   Map <Integer,String> products = new  HashMap<Integer, String>(); 
       logger.debug("Into method getProductActive..");
       try {        
        List <Object[]> listProducts = productsRepository.getProductsEnable();  
        logger.debug("ListProducts.size():%d",listProducts.size());
        
        for(Object[] objs:listProducts){
            products.put((Integer)objs[0],(String)objs[1]);
        }
         products = sortByValue(products);       
        
       }
       catch(Exception e){
        logger.error("The following error originated:"+e.getMessage());        
       }
       return products;  
     }
    /**
     * Method return product by id
     * @param id
     * @return Product
     */   
    public Products getProduct(Long id){
        return productsRepository.findOne(id);
    }   
    /**
     * Method for get the products for the edition
     * @return 
     */
    public List<ProductsDTO> getProducts(){
        List<ProductsDTO> listDto = new ArrayList<ProductsDTO>();
        List <Object[]> listProducts =productsRepository.getProductsEnableForEdit();
        logger.debug("ListProducts.size():"+listProducts.size());
        for(Object[] arr:listProducts){
            ProductsDTO dto = new ProductsDTO();
            dto.setIdProduct(arr[0].toString());
            dto.setDescription(arr[1].toString());
            dto.setDateRegistration(arr[2].toString());
            dto.setPrice(new BigDecimal(arr[3].toString()));
            dto.setTax(new BigDecimal(arr[4].toString()));
            listDto.add(dto);
        }
        return listDto;
    }
    /**
     * Method for update the products
     * @param form
     * @param userRegister
     * @return Map <String,Object>
     * @throws java.text.ParseException 
     * @throws CloneNotSupportedException 
     * @throws GenericBZKException 
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = {Exception.class,GenericBZKException.class})
    public Map <String,Object>  getAddProducts(ProductsForm form,String userRegister) throws ParseException, CloneNotSupportedException, GenericBZKException{
        Map <String,Object> map = new HashMap<>();
        Map <Integer,String> err = new HashMap<>();
        SystemCodes cod =  buildSystemCode(form,err);
        if(err.containsKey(0) ){
            map.put(ERROR_FORM_PRODUCT, err.get(0));
          return map;   
        }
        else if(cod==null || cod.getId()==null){
            map.put(ERROR_FORM_PRODUCT, messageSource.getMessage(ERROR_PRODUCTS.ERROR_ID_PRODUCT.getValue(), null, Locale.getDefault()));
            return map;
        }
        else{
            if(form.getTax()<0F){
              map.put(ERROR_FORM_PRODUCT, messageSource.getMessage(ERROR_PRODUCTS.ERROR_TAX.getValue(), null, Locale.getDefault()).replace("%d", form.getTax().toString()));
              return map;
            }
            else if(form.getUnitPrice()<=0){
                map.put(ERROR_FORM_PRODUCT, messageSource.getMessage(ERROR_PRODUCTS.ERROR_UNIT_PRICE.getValue(), null, Locale.getDefault()));
              return map;
            }
            else{
            Products prod = new Products();
            prod.setDateRegistration(new Date());
            prod.setExpirationDate(DateUtils.date2999());
            prod.setTax(form.getTax());
            prod.setUnitPrice(form.getUnitPrice());
            prod.setUserRegister(userRegister);
            prod.setType(cod.getId().shortValue());                
            prod = productsRepository.save(prod);
            logger.debug("prod.getIdProduct():"+prod.getIdProduct());
            if(prod!=null && prod.getIdProduct()>0){
              logger.debug("prod!=null && prod.getIdProduct()>0:"+prod.getIdProduct());
              form.setListDto(getProducts());
              map.put(GENERAL_MODAL_MESSAGE,messageSource.getMessage(FINAL_MESSAGE_ADD_PRODUCT, null, Locale.getDefault()));
            }
           }
        }
        return map;
    }
    /**
     * Method that builds a new product code
     * @param form
     * @param err
     * @return new cod
     * @throws CloneNotSupportedException 
     * @throws GenericBZKException 
     */
    private SystemCodes buildSystemCode(ProductsForm form,Map <Integer,String>err) throws CloneNotSupportedException, GenericBZKException{
    	SystemCodes newCod=null;
        if(form.getDescription()==null || form.getDescription().equals("")){
            logger.debug("form.getDescription()==null");
            err.put(0, messageSource.getMessage(ERROR_PRODUCTS.ERROR_DESCRIPTION.getValue(), null, Locale.getDefault()));
        }else{
        SystemCodes cod  = systemCodesRepository.findOne(form.getTypeProduct().longValue());
			if (cod != null) {
				newCod = cod.clone();
				newCod.setCode(systemCodesRepository.getSecuenceProductBySubGroup(cod.getSubGrupDescription()));
				newCod.setDescription(form.getDescription());
				newCod = systemCodesRepository.save(newCod);
			}
			else {
				  throw new GenericBZKException("Code not found:"+form.getTypeProduct().toString());
			}
        }
        return newCod;
    }
     /**
     * Method for update the products
     * @param form
     * @param userRegister
     * @return 
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = {Exception.class,GenericBZKException.class})
    public Map <Integer,String> getUpdateProducts(ProductsForm form,String userRegister){
         Map <Integer,String> err = new HashMap<Integer, String>();
        try {
            List<ProductsDTO> listDto = form.getListDto();
           err = update(listDto,userRegister);
           form.setListDto(getProducts());
            return err;
        } catch (GenericBZKException|ParseException ex) {
            logger.error("The following error originated:"+ex.getMessage()); 
            err.put(1,messageSource.getMessage(ERROR_PRODUCTS.ERROR_GENERAL.getValue(), null, Locale.getDefault()));  
        }
        return err;
    }
    /**
     * Method that updates the prices of products
     * @param listDto
     * @param userRegister
     * @return Map <integer,String>
     * @throws GenericBZKException
     * @throws ParseException 
     */
    private Map<Integer, String> update(List<ProductsDTO> listDto, String userRegister) throws GenericBZKException, ParseException {
        Map<Integer, String> err = new HashMap<>();
        List<SystemCodes> codSysAccion = systemCodesRepository.getSystemCodesByGroup(SYSTEM_SYSACCION);
        SystemCodes codDelete = null;
        SystemCodes codEdit = null;
        Integer cont = 0;
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
        for (ProductsDTO dto : listDto) {
            if (dto.getAccion().equals(codDelete.getId().intValue())) {
                logger.debug("dto.getAccion().equals(codDelete.getId().intValue())");
                logger.debug("dto.getIdProduct():" + dto.getIdProduct());
                Products prod = productsRepository.findOne(Long.parseLong(dto.getIdProduct()));
                if (prod != null) {
                    prod.setExpirationDate(new Date());
                    prod.setUserExpiration(userRegister);
                    productsRepository.save(prod);
                    cont++;
                }
            } else if (dto.getAccion().equals(codEdit.getId().intValue())) {
                logger.debug("dto.getAccion().equals(codEdit.getId().intValue())");
                logger.debug("dto.getIdProduct():" + dto.getIdProduct());
                if (dto.getPrice().floatValue() <= 0) {
                    err.put(1, messageSource.getMessage(ERROR_PRODUCTS.ERROR_UNIT_PRICE.getValue(), null, Locale.getDefault()).replace("%d", dto.getTax().toString()));
                    return err;
                } else if (dto.getTax().floatValue() < 0) {
                    err.put(1, messageSource.getMessage(ERROR_PRODUCTS.ERROR_TAX.getValue(), null, Locale.getDefault()).replace("%d", dto.getTax().toString()));
                    return err;
                } else {
                    /**
                     * Price Update 1. We cancel the prices to update with user
                     * registration and date 12/31/2999 2. We create a new
                     * record from which new record
                     */
                    logger.debug("Long.parseLong(dto.getIdProduct()):" + dto.getIdProduct());
                    Products prod = productsRepository.findOne(Long.parseLong(dto.getIdProduct()));
                    if (prod != null) {
                        //1
                        prod.setExpirationDate(new Date());
                        prod.setUserExpiration(userRegister);
                        prod = productsRepository.save(prod);
                        //2
                        Products newProduct = new Products();
                        newProduct.setDateRegistration(new Date());
                        newProduct.setExpirationDate(DateUtils.date2999());
                        newProduct.setUserRegister(userRegister);
                        newProduct.setTax(dto.getTax().floatValue());
                        newProduct.setUnitPrice(dto.getPrice().floatValue());
                        newProduct.setType(prod.getType());
                        productsRepository.save(newProduct);
                        cont++;
                    } else {

                        throw new GenericBZKException("No exit product valid");
                    }
                }
            }
        }
        productsRepository.flush();
        if (err.isEmpty()) {
            err.put(GENERAL_MODAL_MESSAGE_KEY, messageSource.getMessage(FINAL_MESSAGE_UPDATE_PRODUCT, null, Locale.getDefault()).replace("%d", cont.toString()));
        }
        return err;
    }
}
