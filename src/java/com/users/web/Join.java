/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.users.web;

import java.sql.Connection;
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
public class Join {

    /**
     * Creates a new instance of Join
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
    private String NAMAMatkul;
    public void setNAMAMatkul(String NAMAMatkul) {
        this.NAMAMatkul = NAMAMatkul;
    }
    public String getNAMAMatkul() {
        return NAMAMatkul;
    }

    private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap(); 
    
     public ArrayList getGet_all_join() throws Exception{
        ArrayList list_of_join=new ArrayList();
             Connection connection=null;
        try {
            Koneksi obj_koneksi = new Koneksi();
            connection = obj_koneksi.get_connection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("Select a.NIM, a.Nama, a.ID_Matkul, b.ID_Matkul, b.Nama_Matkul, b.Semester FROM mahasiswa a LEFT JOIN matakuliah b ON a.ID_Matkul = b.ID_Matkul ;");
            while(rs.next()){
                Join obj_Join = new Join();
                obj_Join.setNIM(rs.getString("NIM"));
                obj_Join.setNAMA(rs.getString("Nama"));
                obj_Join.setNAMAMatkul(rs.getString("Nama_Matkul"));
                list_of_join.add(obj_Join);
            }
        } catch (Exception e) {
            System.out.println(e);
        }finally{
            if(connection!=null){
                connection.close();
            }
        }
        return list_of_join;
}

    public Join() {
    }
    
}
