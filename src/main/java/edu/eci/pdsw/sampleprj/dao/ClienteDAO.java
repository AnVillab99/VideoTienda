/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.sampleprj.dao;

import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.samples.entities.ItemRentado;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author hcadavid
 */
public interface ClienteDAO {
    
    public void save(Cliente c) throws PersistenceException;
    
    public Cliente load(long id) throws PersistenceException;
    
    public List<Cliente> load() throws PersistenceException;
    
    public ItemRentado loadItemRentado(int iditem) throws PersistenceException;
    
     public void saveAlquier(java.sql.Date date, long docu, Item item, int numdias)throws PersistenceException;
    
}
