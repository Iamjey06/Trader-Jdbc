package com.joshua.traderDatabaseJdbc.Controller;

import com.joshua.traderDatabaseJdbc.Entity.Customers;
import com.joshua.traderDatabaseJdbc.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.LinkedList;
import java.util.List;

@Controller("customerController")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    public String insertCustomerRecord(){

        Customers customers = new Customers();
        customers.setCompanyName("E Solutions");
        customers.setContactName("Joshua Machemedze");
        customers.setAddress("26697 Unit B");
        customers.setPhone("0775142654");
        customers.setCityName("Harare");

        customerService.insertCustomerRecord(customers);

        return "Customer record as been inserted";
    }

    public void updateCustomerRecordById(){

        Customers customers = new Customers();
        customers.setCustomerId("AB374MAS");
        customers.setCompanyName("Aristocrat");
        customers.setContactName("Isabell Machemedze ");
        customers.setAddress("47 Babanon");
        customers.setPhone("0718393743");
        customers.setCityName("Masvingo");

        customerService.updateCustomerRecordById(customers);

    }

    public String deleteCustomerRecordById(String customerId){

        customerService.deleteCustomerRecordById(customerId);
        return "Customer Record has been deleted successfully";
    }
    
    public void insertMultiCustomerRecords(){

        List<Customers> customersList = new LinkedList<>();

        Customers customer1= new Customers();
        customer1.setCompanyName("Me4Yu");
        customer1.setContactName("Fortunate Mutanda");
        customer1.setAddress("26 Albanon");
        customer1.setPhone("0772789004");
        customer1.setCityName("Gwanda");

        Customers customer2= new Customers();
        customer2.setCompanyName("Toaster");
        customer2.setContactName("Kiluwa Katichi");
        customer2.setAddress("33 Cholocho");
        customer2.setPhone("0717839020");
        customer2.setCityName("Hwange");

        Customers customer3= new Customers();
        customer3.setCompanyName("Aristocrat");
        customer3.setContactName("Isabell");
        customer3.setAddress("47 Babanon");
        customer3.setPhone("0718393743");
        customer3.setCityName("Masvingo");

        customersList.add(customer1);
        customersList.add(customer2);
        customersList.add(customer3);

        customerService.insertMultiCustomerRecords(customersList);

    }

    public void updateMultiCustomerRecords(){
        
        List<Customers> customersList= new LinkedList<>();

        Customers customer1 = new Customers();
        customer1.setCustomerId("LU902HWA");
        customer1.setCompanyName("Pissoko");
        customer1.setContactName("Ted Mosby ");
        customer1.setAddress("47 Babanon");
        customer1.setPhone("0718393743");
        customer1.setCityName("Masvingo");

        Customers customer2 = new Customers();
        customer2.setCustomerId("UI376HAR");
        customer2.setCompanyName("Me4Yu");
        customer2.setContactName("Louis Machemedze");
        customer2.setAddress("47 Kanyon");
        customer2.setPhone("0775143765");
        customer2.setCityName("Hurungwe");

        customersList.add(customer1);
        customersList.add(customer2);

        customerService.updateMultiCustomerRecords(customersList);
    }

    public String deleteMultiCustomerRecords(){

        List<String> customerIds = new LinkedList<>();

        customerIds.add("D 374MAS");
        customerIds.add("SH265HAR");

        customerService.deleteMultiCustomerRecords(customerIds);

        return"Customer records deleted";
    }

    public String findAllCustomers(){
        List<Customers> customersList = customerService.findAllCustomers();
        int count = 0;

        for(Customers customers: customersList){

            System.out.println("CustomerId... "+ customers.getCustomerId()+" ..CompanyName... "+
                    customers.getCompanyName()+" ..ContactName... "+customers.getContactName()+
                    " ..Address... "+customers.getAddress()+" ..Phone... "+customers.getPhone()+
                    " ..City... "+ customers.getCity().getCityName());
            count ++;
        }
        System.out.println(count+" rows returned");

        return "Customers information provided";
    }

    public void findCustomersById(String customerId){
        List<Customers> customersList = customerService.findCustomersById(customerId);
        int count = 0;
        for(Customers customers: customersList){
            System.out.println("CustomerId... "+customers.getCustomerId()+" ..CompanyName... "
                    +customers.getCompanyName()+" ..Address... "+customers.getAddress()+" ..Phone... "
                    +customers.getPhone()+" ..City... "+customers.getCity().getCityName());
            count++;
        }
        System.out.println(count+" customer returned");
    }

    public void findCustomersByCityName(String cityName){
        List<Customers> customersList = customerService.findCustomersByCityName(cityName);
        int count=0;

        for(Customers customers: customersList){

            System.out.println("CompanyName... "+customers.getCompanyName()+" ..ContactName... "
                    +customers.getContactName()+" ..Address... "+customers.getAddress()+
                    " ..Phone... "+customers.getPhone());
            count++;
        }
        System.out.println(count+" customers available in city "+cityName);
    }

    public void findCustomerByContactName(String contactName){
        List<Customers> customersList = customerService.findCustomersByContactName(contactName);
        int count=0;
        for(Customers customers: customersList){

            System.out.println("CompanyName... "+customers.getCompanyName()+" ..ContactName... "
                    +customers.getContactName()+" ..Address... "+customers.getAddress()+
                    " ..Phone... "+customers.getPhone());
            count++;
        }
        System.out.println(count+" customers returned");

    }

}
