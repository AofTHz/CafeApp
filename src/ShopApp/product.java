/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ShopApp;

/**
 *
 * @author AofTHz
 */
class product {
    
    private String productName;
    private String Brand;
    private int Stock;
    private int Price;
    private String ExpireDate;
    public product(String productName,String Brand,int Stock,int Price,String ExpireDate){
        this.productName = productName;
        this.Brand = Brand;
        this.Stock = Stock;
        this.Price = Price;
        this.ExpireDate = ExpireDate;
    }
    public String getProductN(){
        return productName;
    }
    public String getBrand(){
        return Brand;
    }
    public int getStock(){
        return Stock;
    }
    public int getPrice(){
        return Price;
    }
    public String getExpDate(){
        return ExpireDate;
    }
}


   
    

