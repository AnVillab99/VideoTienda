/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.managedbeans;

import edu.eci.pdsw.samples.entities.*;
import edu.eci.pdsw.samples.services.ServiciosAlquiler;
import edu.eci.pdsw.samples.services.ServiciosAlquilerFactory;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author 2106913
 */
@ManagedBean(name = "AlquilerItems")
@SessionScoped
public class AlquilerItemsBean implements Serializable {

    ServiciosAlquiler sp = ServiciosAlquilerFactory.getInstance().getServiciosAlquiler();
    String nnombre=null;
    long nidentificacion=0 ;
    String ntelefono=null;
    String ndireccion=null;
    String nemail=null;
    boolean error=false;
    long multas =0;
    String vista="RegistroClientes";
    int nid=0;
    long valcot=0;
    public String cambiarVista(Cliente c){
        nidentificacion=c.getDocumento();
        ntelefono=c.getTelefono();
        ndireccion=c.getDireccion();
        nnombre=c.getNombre(    );
        nemail=c.getEmail();
        
        return "RegistroClienteItem";

    }
    public AlquilerItemsBean() {
        
    }
    public String getNnombre(){
        return  nnombre;
    }
    public void setNnombre(String nombre){
        nnombre=nombre;
    }
    public long getNidentificacion(){
        return  nidentificacion;
    }
    public void setNidentificacion(long id){
        nidentificacion=id;
    }
    public String getNtelefono(){
        return  ntelefono;
    }
    public void setNtelefono(String tel){
        ntelefono=tel;
    }
    public String getNdireccion(){
        return  ndireccion;
    }
    public void setNdireccion(String dir){
        ndireccion=dir;
    }
    public String getNemail(){
        return  nemail;
    }
    public void setNemail(String mail){
        nemail=mail;
    }
    
    public long getMultas(){
        try{
            multas =0;
            Cliente c = sp.consultarCliente(nidentificacion);
            ArrayList<ItemRentado> lista = c.getRentados();
            for(int i =0; i< lista.size(); i++){
                java.util.Date fecha = new Date();
                java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
                multas = multas + sp.consultarMultaAlquiler(lista.get(i).getItem().getId() , fechaSQL);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        return  multas;
    }
    public void setMultas(long mult){
        multas=mult;
    }
    
    /*public int getNid(){
        return  nid;
    }
    public void setNid(int num){
        nid=num;
    }*/
    
    public long getValcot(){
        return  valcot;
    }
    public void setValcot(long num){
        valcot=num;
    }
    
    public List<Cliente> getClientes() {
        List<Cliente> clientes= new ArrayList<Cliente>();
        try{
            clientes = sp.consultarClientes();
        }catch(Exception e){
           error=true; 
        }
        return  clientes;
    }
    
    public List<ItemRentado> getAlquiler() {
        List<ItemRentado> alquiler= new ArrayList<ItemRentado>();
        try{
            alquiler = sp.consultarItemsCliente(nidentificacion);
        }catch(Exception e){
           error=true; 
        }
        return  alquiler;
    }
    
    public void registrarCliente(){
        try{
            sp.registrarCliente(new Cliente(nnombre,nidentificacion,ntelefono,ndireccion,nemail));
            String nnombre=null;
            nidentificacion=0 ;
            ntelefono=null;
            ndireccion=null;
            nemail=null;
        }catch(Exception e){
            error=true;
        }
    }
    
    public void cotizar(int iditem, int dias){
        List<Item> agria = sp.consultarItemsDisponibles();
        boolean entra = false;
        for(int i=0; i< agria.size();i++){
            if(agria.get(i).getId() == iditem) entra = true;
        }
        if(entra){
            try{
                valcot =sp.consultarCostoAlquiler(iditem, dias);
            }
            catch(Exception e){
            }
        }
    }
    
    public void agregarAlquiler(int iditem, int dias){
        try{
            Cliente c = sp.consultarCliente(nidentificacion);
            Item it = sp.consultarItem(iditem);
            java.util.Date fecha = new Date();
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
            long docu = c.getDocumento();
            sp.registrarAlquilerCliente(fechaSQL, docu, it, dias);
            valcot=0;
        }
        catch(Exception e){
        }
    }
    
    

}
