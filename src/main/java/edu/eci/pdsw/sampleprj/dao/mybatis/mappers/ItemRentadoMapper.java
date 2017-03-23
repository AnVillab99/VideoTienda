/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.sampleprj.dao.mybatis.mappers;


import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.samples.entities.ItemRentado;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author andres
 */
public interface ItemRentadoMapper {
    public List<ItemRentado> getItemsRentados();        
    
    public ItemRentado consultarItemRentado(int id);
    
    public void insertarItemRentado(ItemRentado it);

}
