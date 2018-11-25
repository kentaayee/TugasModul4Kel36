/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.users.web;

import com.users.web.Koneksi;
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
public class Matakuliah {

    /**
     * Creates a new instance of Matakuliah
     */
    private String IDMatkul;
    public void setIDMatkul(String IDMatkul) {
        this.IDMatkul = IDMatkul;
    }
    public String getIDMatkul() {
        return IDMatkul;
    }

    private String NAMAMatkul;
    public void setNAMAMatkul(String NAMAMatkul) {
        this.NAMAMatkul = NAMAMatkul;
    }
    public String getNAMAMatkul() {
        return NAMAMatkul;
    }
    
    private String SMT;
    public void setSMT(String SMT) {
        this.SMT = SMT;
    }
    public String getSMT() {
        return SMT;
    }
    
    private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
      public String Edit_Matakuliah(){
     FacesContext fc = FacesContext.getCurrentInstance();
     Map<String,String > params = fc.getExternalContext().getRequestParameterMap();
     String Field_ID_Matkul = params.get("action");
     try {
          Koneksi obj_koneksi = new Koneksi();
          Connection connection = obj_koneksi.get_connection();
          Statement st = connection.createStatement();
          ResultSet rs = st.executeQuery("select * from matakuliah where ID_Matkul="+Field_ID_Matkul);
          Matakuliah obj_Matakuliah = new Matakuliah();
          rs.next();
          obj_Matakuliah.setIDMatkul(rs.getString("ID_Matkul"));
          obj_Matakuliah.setNAMAMatkul(rs.getString("Nama_Matkul"));
          obj_Matakuliah.setSMT(rs.getString("Semester"));
          sessionMap.put("EditMatakuliah", obj_Matakuliah);  
      } catch (Exception e) {
            System.out.println(e);
      }
     return "/EditMatakuliah.xhtml?faces-redirect=true";   
}

public String Delete_Matakuliah(){
      FacesContext fc = FacesContext.getCurrentInstance();
      Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
      String Field_ID_Matkul = params.get("action");
      try {
         Koneksi obj_koneksi = new Koneksi();
         Connection connection = obj_koneksi.get_connection();
         PreparedStatement ps = connection.prepareStatement("delete from matakuliah where ID_Matkul=?");
         ps.setString(1, Field_ID_Matkul);
         System.out.println(ps);
         ps.executeUpdate();
        } catch (Exception e) {
         System.out.println(e);
        }
       return "/Matakuliah.xhtml?faces-redirect=true";   
}

public String Update_Matakuliah(){
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
	String Update_ID_Matkul= params.get("Update_ID_Matkul");
        try {
            Koneksi obj_koneksi = new Koneksi();
            Connection connection = obj_koneksi.get_connection();
            PreparedStatement ps = connection.prepareStatement("update matakuliah set ID_Matkul=?, Nama_Matkul=?, Semester=? where ID_Matkul=?");
            ps.setString(1, IDMatkul);
            ps.setString(2, NAMAMatkul);
            ps.setString(3, SMT);
            ps.setString(4, Update_ID_Matkul);
            System.out.println(ps);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
       return "/Matakuliah.xhtml?faces-redirect=true";   
}
    
    public ArrayList getGet_all_matakuliah() throws Exception{
        ArrayList list_of_matakuliah=new ArrayList();
             Connection connection=null;
        try {
            Koneksi obj_koneksi = new Koneksi();
            connection = obj_koneksi.get_connection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("Select * from matakuliah");
            while(rs.next()){
                Matakuliah obj_Matakuliah = new Matakuliah();
                obj_Matakuliah.setIDMatkul(rs.getString("ID_Matkul"));
                obj_Matakuliah.setNAMAMatkul(rs.getString("Nama_Matkul"));
                obj_Matakuliah.setSMT(rs.getString("Semester"));
                list_of_matakuliah.add(obj_Matakuliah);
            }
        } catch (Exception e) {
            System.out.println(e);
        }finally{
            if(connection!=null){
                connection.close();
            }
        }
        return list_of_matakuliah;
}
    
public String Tambah_Matakuliah(){
        try {
            Connection connection=null;
            Koneksi obj_koneksi = new Koneksi();
            connection = obj_koneksi.get_connection();
            PreparedStatement ps=connection.prepareStatement("insert into matakuliah(ID_Matkul, Nama_Matkul, Semester) value('"+IDMatkul+"','"+NAMAMatkul+"','"+SMT+"')");
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return "/Matakuliah.xhtml?faces-redirect=true";
    }
    public Matakuliah() {
    }
    
}
