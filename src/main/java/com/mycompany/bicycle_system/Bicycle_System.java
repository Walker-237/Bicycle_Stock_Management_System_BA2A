/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.bicycle_system;
import UI.*;

/**
 *
 * @author Walker
 */
public class Bicycle_System {
    
    
    class User{
        public int id;      
        public String name,email,password,status;
        User(int id,String name,String email,String password,String status)
        {
            this.id = id;           
            this.name = name;
            this.email = email;
            this.password = password;
            this.status = status;
        }
    }
  
    class Customer extends User{  
        Customer(int id,String name,String email,String password,String status){
            super(id,name,email,password,status);
        }
       void reserveBicycle(){}
        void viewCatalogue(){}
        void manageTransaction(){}
        void manageCart(){}
    }
      
    class StockManager extends User{  
        StockManager(int id,String name,String email,String password,String status){
            super(id,name,email,password,status);
        }
       void updateBicycleStatus(){}
        void checkSellingStatistics(){}
        void setPrice(){}
        void setQuantity(){}
    }
          
    class Admin extends User{  
        Admin(int id,String name,String email,String password,String status){
            super(id,name,email,password,status);
        }
       void manageUSerAccount(){}
    }
    
    public static void main(String[] args) {
        Homepage w = new Homepage();
        w.setVisible(true);
    }
}
