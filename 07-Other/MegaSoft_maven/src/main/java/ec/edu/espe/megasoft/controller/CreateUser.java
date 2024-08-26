/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.megasoft.controller;

/**
 *
 * @author Brayan Gualotuña, Dev Dynasty, DCCO-ESPE
 */
public class CreateUser {
    public static UserLogin createUser(String user, String password){
        
        UserLogin userLogin;
        
        userLogin = new UserLogin(user, password);
        
        
        return userLogin;
    }
    
    
}
