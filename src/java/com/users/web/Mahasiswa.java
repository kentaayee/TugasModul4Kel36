/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.users.web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author BossNia
 */
@ManagedBean
@SessionScoped
public class Mahasiswa {

    /**
     * Creates a new instance of Mahasiswa
     */
    private String NIM;
    public void setNIM(String NIM) {
        this.NIM = NIM;
    }
    public String getNIM() {
        return NIM;
    }

    private String NAMA;
    public void setNAMA(String NAMA) {
        this.NAMA = NAMA;
    }
    public String getNAMA() {
        return NAMA;
    }
    
    private String IDMatkul;
    public void setIDMatkul(String IDMatkul) {
        this.IDMatkul = IDMatkul;
    }
    public String getIDMatkul() {
        return IDMatkul;
    
    }
    


private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
      public String Edit_Mahasiswa(){
     FacesContext fc = FacesContext.getCurrentInstance();
     Map<String,String > params = fc.getExternalContext().getRequestParameterMap();
     String Field_NIM = params.get("action");
     try {
          Koneksi obj_koneksi = new Koneksi();
          Connection connection = obj_koneksi.get_connection();
          Statement st = connection.createStatement();
          ResultSet rs = st.executeQuery("select * from mahasiswa where NIM="+Field_NIM);
          Mahasiswa obj_Mahasiswa = new Mahasiswa();
          rs.next();
          obj_Mahasiswa.setNIM(rs.getString("NIM"));
          obj_Mahasiswa.setNAMA(rs.getString("Nama"));
          obj_Mahasiswa.setIDMatkul(rs.getString("ID_Matkul"));
          sessionMap.put("EditMahasiswa", obj_Mahasiswa);  
      } catch (Exception e) {
            System.out.println(e);
      }
     return "/EditMahasiswa.xhtml?faces-redirect=true";   
}

public String Delete_Mahasiswa(){
      FacesContext fc = FacesContext.getCurrentInstance();
      Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
      String Field_NIM = params.get("action");
      try {
         Koneksi obj_koneksi = new Koneksi();
         Connection connection = obj_koneksi.get_connection();
         PreparedStatement ps = connection.prepareStatement("delete from mahasiswa where NIM=?");
         ps.setString(1, Field_NIM);
         System.out.println(ps);
         ps.executeUpdate();
        } catch (Exception e) {
         System.out.println(e);
        }
       return "/Mahasiswa.xhtml?faces-redirect=true";   
}

public String Update_Mahasiswa(){
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
	String Update_NIM= params.get("Update_NIM");
        try {
            Koneksi obj_koneksi = new Koneksi();
            Connection connection = obj_koneksi.get_connection();
            PreparedStatement ps = connection.prepareStatement("update mahasiswa set NIM=?, Nama=?, ID_Matkul=? where NIM=?");
            ps.setString(1, NIM);
            ps.setString(2, NAMA);
            ps.setString(3, IDMatkul);
            ps.setString(4, Update_NIM);
            System.out.println(ps);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
       return "/Mahasiswa.xhtml?faces-redirect=true";   
}
    
    public ArrayList getGet_all_mahasiswa() throws Exception{
        ArrayList list_of_mahasiswa=new ArrayList();
             Connection connection=null;
        try {
            Koneksi obj_koneksi = new Koneksi();
            connection = obj_koneksi.get_connection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("Select * from mahasiswa");
            while(rs.next()){
                Mahasiswa obj_Mahasiswa = new Mahasiswa();
                obj_Mahasiswa.setNIM(rs.getString("NIM"));
                obj_Mahasiswa.setNAMA(rs.getString("Nama"));
                obj_Mahasiswa.setIDMatkul(rs.getString("ID_Matkul"));
                list_of_mahasiswa.add(obj_Mahasiswa);
            }
        } catch (Exception e) {
            System.out.println(e);
        }finally{
            if(connection!=null){
                connection.close();
            }
        }
        return list_of_mahasiswa;
}
    
public String Tambah_Mahasiswa(){
        try {
            Connection connection=null;
            Koneksi obj_koneksi = new Koneksi();
            connection = obj_koneksi.get_connection();
            PreparedStatement ps=connection.prepareStatement("insert into mahasiswa(NIM, Nama, ID_Matkul) value('"+NIM+"','"+NAMA+"','"+IDMatkul+"')");
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return "/Mahasiswa.xhtml?faces-redirect=true";
    }
    public Mahasiswa() {
    }
    
}
