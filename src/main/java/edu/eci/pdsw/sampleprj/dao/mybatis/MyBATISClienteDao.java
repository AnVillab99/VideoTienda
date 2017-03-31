/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.pdsw.sampleprj.dao.ClienteDAO;
import edu.eci.pdsw.sampleprj.dao.PersistenceException;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.samples.entities.ItemRentado;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author andres
 */
public class MyBATISClienteDao implements ClienteDAO{
    @Inject
    private ClienteMapper clienteMapper;    
    
    @Override
    public void save(Cliente c) throws PersistenceException {
        try{
            clienteMapper.insertarCliente(c);
                    
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al registrar el cliente "+c.toString(),e);
        }        
    }

    @Override
    public Cliente load(long id) throws PersistenceException {
        try{
            return clienteMapper.consultarCliente(id);
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al consultar el cliente "+id,e);
        }
    }

    @Override
    public List<Cliente> load() throws PersistenceException {
         try{
            return clienteMapper.consultarClientes();
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al consultar el cliente ",e);
        }
    }
    @Override
    public ItemRentado loadItemRentado(int iditem) throws PersistenceException{
        try{
            return clienteMapper.consultarItemRentado(iditem);
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al consultar el item rentado con id",e);
        }
        
    }

    @Override
    public void saveAlquier(Date date, long docu, Item item, int numdias) throws PersistenceException {
         /*Calendar cal=Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, numdias);
        java.util.Date fecha=cal.getTime();
        java.sql.Date fechaFinal = new java.sql.Date(fecha.getTime()); */
        //java.sql.Date.valueOf(cal.getTime().toString());
        
        LocalDate ld=date.toLocalDate();
        
        LocalDate ld2=ld.plusDays(numdias);
        java.sql.Date fechaFinal;
        fechaFinal = java.sql.Date.valueOf(ld2);
         try{
            clienteMapper.agregarItemRentadoACliente(docu, item.getId(), date, fechaFinal);
        }catch(org.apache.ibatis.exceptions.PersistenceException e){
            
            throw new PersistenceException("Error al registrar alquiler",e); 
}
    }
    
}
