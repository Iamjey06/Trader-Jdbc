package com.joshua.traderDatabaseJdbc.Controller;

import com.joshua.traderDatabaseJdbc.Entity.Suppliers;
import com.joshua.traderDatabaseJdbc.Service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.LinkedList;
import java.util.List;

@Controller("supplierController")
public class SupplierController {

    @Autowired
    SupplierService supplierService;

    public void insertSupplierRecord(){

        Suppliers suppliers = new Suppliers();
        suppliers.setCompanyName("Kiddos ltd");
        suppliers.setContactName("Kabios Kabira");
        suppliers.setAddress("34 Rinda Road");
        suppliers.setPhone("0775142637");
        suppliers.setCityName("Hwange");

        supplierService.insertSupplierRecord(suppliers);

    }

    public void updateSupplierRecord(){

        Suppliers suppliers = new Suppliers();
        suppliers.setSupplierId("IO263KID");
        suppliers.setCompanyName("Kondwas");
        suppliers.setContactName("Keith Piyaso");
        suppliers.setAddress("322 Hatfield");
        suppliers.setPhone("0775142657");
        suppliers.setCityName("Gwanda");

        supplierService.updateSupplierRecord(suppliers);
    }

    public void deleteSupplierRecordsById(String supplierId){
        supplierService.deleteSupplierRecordById(supplierId);
    }


    public void deleteSupplierRecordByCompanyName(String companyName){
        supplierService.deleteSupplierRecordByCompanyName(companyName);
    }

    public void insertMultiSupplierRecords(){

        List<Suppliers> supplierList = new LinkedList<>();

        Suppliers suppliers = new Suppliers();
        suppliers.setCompanyName("Pits Ltd");
        suppliers.setContactName("Katanga Kolos");
        suppliers.setAddress("362 Lockswell");
        suppliers.setPhone("0772389645");
        suppliers.setCityName("Harare");

        Suppliers supplier1 = new Suppliers();
        supplier1.setCompanyName("HighGoal");
        supplier1.setContactName("Pots Paripi");
        supplier1.setAddress("373 Helenshood");
        supplier1.setPhone("0778392643");
        supplier1.setCityName("Masvingo");

        Suppliers supplier2 = new Suppliers();
        supplier2.setCompanyName("Joro");
        supplier2.setContactName("Lodwed Jobro");
        supplier2.setAddress("3748 Pasipedu");
        supplier2.setPhone("0712635473");
        supplier2.setCityName("Gwanda");

        supplierList.add(suppliers);
        supplierList.add(supplier1);
        supplierList.add(supplier2);

        supplierService.insertMultiSupplierRecords(supplierList);
    }

    public void updateMultiSupplierRecords(){

      List<Suppliers> supplierList =  new LinkedList<>();

      Suppliers suppliers = new Suppliers();
      suppliers.setSupplierId("S 264HIG");
      suppliers.setCompanyName("HighGoal");
      suppliers.setContactName("Potai Paripi");
      suppliers.setAddress("373 Helenshood");
      suppliers.setPhone("0778392643");
      suppliers.setCityName("Masvingo");

      Suppliers supplier1 = new Suppliers();
      supplier1.setSupplierId("AN964PIT");
      supplier1.setCompanyName("Pits Ltd");
      supplier1.setContactName("Katanga Kolos");
      supplier1.setAddress("362 Lockswell");
      supplier1.setPhone("0772389645");
      supplier1.setCityName("hurungwe");

      supplierList.add(suppliers);
      supplierList.add(supplier1);

      supplierService.updateMultiSupplierRecords(supplierList);
    }


    public void findAllSuppliers(){

        List<Suppliers> supplierList = supplierService.findAllSuppliers();

        for(Suppliers suppliers:supplierList){

            System.out.println("SupplierId... "+suppliers.getSupplierId()+" ..ContactName... "+suppliers.getContactName()
            +" ..CompanyName... "+ suppliers.getCompanyName()+" ..address... "+suppliers.getAddress()+" ..phone... "
            +suppliers.getPhone()+" ..CityName... "+suppliers.getCity().getCityName());
        }
    }

    public void findSupplierByCityName(String cityName){

        List<Suppliers> supplierList = supplierService.findSupplierByCityName(cityName);

        for(Suppliers suppliers:supplierList){

            System.out.println("SupplierId... "+suppliers.getSupplierId()+" ..ContactName... "+suppliers.getContactName()
            +" ..CompanyName... "+ suppliers.getCompanyName()+" ..address... "+suppliers.getAddress()+" ..phone... "
            +suppliers.getPhone()+" ..CityName... "+suppliers.getCity().getCityName());
        }
    }

    public void findSupplierByCompanyName(String companyName){

        List<Suppliers> supplierList = supplierService.findSupplierByCompanyName(companyName);

        for(Suppliers suppliers:supplierList){

            System.out.println("SupplierId... "+suppliers.getSupplierId()+" ..ContactName... "+suppliers.getContactName()
            +" ..CompanyName... "+ suppliers.getCompanyName()+" ..address... "+suppliers.getAddress()+" ..phone... "
            +suppliers.getPhone()+" ..CityName... "+suppliers.getCity().getCityName());
        }

    }

}
