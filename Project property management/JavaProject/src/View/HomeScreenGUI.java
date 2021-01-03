package View;

import Controller.LandlordCtrl;
import Controller.PropertyCtrl;
import Controller.TenantCtrl;
import Model.Entity.Landlord;
import Model.Entity.Property;
import Model.Entity.Rental;
import Model.Entity.Tenant;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class HomeScreenGUI extends JFrame{
    
    //JMenu fileMenu, landlordsMenu, propertiesMenu, tenantsMenu, rentalsMenu, helpMenu;

    ///Creating the ArrayLists
    public static ArrayList<Landlord> landlordList = new ArrayList<>();      //لیست املاک
    public static ArrayList<Property> propertyList = new ArrayList<>();      //لیست مشخصات
    public static ArrayList<Property> propertyLet = new ArrayList<>();       //مشخصات اجاره
    public static ArrayList<Property> propertyAvailable = new ArrayList<>(); //مشخصات موجود
    public static ArrayList<Tenant> tenantsAvailable = new ArrayList<>();    //مستاجر موجود
    public static ArrayList<Tenant> tenantList = new ArrayList<>();          //لیست مستاجر
    public static ArrayList<Rental> rentalList = new ArrayList<>();          //لیست مبلغ
    JTextArea area;

    ///Creating the Combo Boxes 
    String[] delPropertyLists = {"مشخصات در دسترس", "مشخصات خارج از دسترس"};
    JComboBox delPropertyLts = new JComboBox(delPropertyLists);
    JButton b = new JButton("وارد کردن");
    JButton c = new JButton("لغو");
    JLabel delProText = new JLabel("نوع مشخصاتی که میخواهید حذف شود را انتخاب کنید");

    String[] tenantLists = {"Tenants Available", "Tenants Not Available"};
    JComboBox tenantLts = new JComboBox(tenantLists);
    JButton d = new JButton("وارد کردن");
    JButton f = new JButton("لغو");
    JLabel tenText = new JLabel("انتخاب لیست مستاجر");

    String[] delTenantLists = {"Tenants Available", "Tenants Not Available"};
    JComboBox delTenantLts = new JComboBox(delTenantLists);
    JButton g = new JButton("وارد کردن");
    JButton h = new JButton("لغو");
    JLabel delTenText = new JLabel("Select Tenant List to delete from");

    String[] amdTenantLists = {"Tenants Available", "Tenants Not Available"};
    JComboBox amdTenantLts = new JComboBox(amdTenantLists);
    JButton i = new JButton("وارد کردن");
    JButton j = new JButton("لغو");
    JLabel amdTenText = new JLabel("Select Tenant List to delete from");

    String searchName;
    boolean found = false;
    
    
    public static void main(String args[]) throws IOException {
        //Run the Main Application GUI
        HomeScreenGUI ex = new HomeScreenGUI();
        ex.setVisible(true);
    }
    
    
    public HomeScreenGUI() {

        //Returns an image which gets pixel data from the specified file.
        Image storeImage = Toolkit.getDefaultToolkit().createImage("images/PropertyManagement.jpg"); 

        createMenuBar();


        setLayout(new BorderLayout());
        setTitle("سیستم مشاور املاک");
        JLabel background = new JLabel(new ImageIcon(storeImage));
        add(background);
        background.setLayout(new FlowLayout());
        setSize(1218, 684);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        area = new JTextArea();
    }
    
    private void createMenuBar(){
        
        JMenuBar menuBar = new JMenuBar();
        JMenu listMenu;

        //Load in the Icons
        ImageIcon exitIcon = new ImageIcon("images/exit.png");
        ImageIcon addIcon = new ImageIcon("images/add.png");
        ImageIcon deleteIcon = new ImageIcon("images/delete.png");
        ImageIcon editIcon = new ImageIcon("images/edit.png");
        ImageIcon searchIcon = new ImageIcon("images/search.png");
        ImageIcon displayIcon = new ImageIcon("images/display.png");
        ImageIcon listIcon = new ImageIcon("images/list.png");
        ImageIcon saveIcon = new ImageIcon("images/save.png");
        ImageIcon openIcon = new ImageIcon("images/open.png");
        ImageIcon aboutIcon = new ImageIcon("images/about.png");
        ImageIcon helpIcon = new ImageIcon("images/help.png");

        //Set up all the JMenus and their Hot keys (Hold ALT + key)

        JMenu landlords = new JMenu("مالک");
        landlords.setMnemonic(KeyEvent.VK_L);

        JMenu properties = new JMenu("مشخصات");
        properties.setMnemonic(KeyEvent.VK_P);

        JMenu tenants = new JMenu("مستاجر");
        tenants.setMnemonic(KeyEvent.VK_T);

        JMenu rentals = new JMenu("مبلغ");
        rentals.setMnemonic(KeyEvent.VK_R);

        JMenu help = new JMenu("راهنما");
        help.setMnemonic(KeyEvent.VK_H);
        

        /////////////Landlord Menu Items///////////////////
        //Add a Landlord
        JMenuItem registerLandlord = new JMenuItem("  ثبت مالک", addIcon);
        registerLandlord.setMnemonic(KeyEvent.VK_A);
        registerLandlord.setToolTipText("باز کردن پنجره ثبت مالک");
        registerLandlord.addActionListener(ae -> {
            RegisterLandlordGUI addNewLandlordScreen = new RegisterLandlordGUI();
            
        });
        
        //De-Register Landlord 
        JMenuItem deRegisterLandlord = new JMenuItem(" حذف  مالک", deleteIcon);
        deRegisterLandlord.setMnemonic(KeyEvent.VK_D);
        deRegisterLandlord.setToolTipText("بازکردن پنجره حذف مالک");
        deRegisterLandlord.addActionListener(ae -> {
            System.out.println("دکمه ثبت دوباره کلیک شد");
            Landlord removeLandLord = removeLandlord();
        });
        
        //Amend landlord's details
        JMenuItem amendDetails = new JMenuItem(" اصلاح جزییات", editIcon);
        amendDetails.setMnemonic(KeyEvent.VK_C);
        amendDetails.setToolTipText("بازکردن پنجره ویرایش مالک");
        amendDetails.addActionListener(ae -> {
            System.out.println("تغییرات انجام شد");
            Landlord searchedLandL = amendLandlord();
            if (searchedLandL != null) {
                AmendLandlordGUI amendLandlordGUI = new AmendLandlordGUI(searchedLandL);
            }
        });
        
        //Search for Landlord 
        JMenuItem searchLandlord = new JMenuItem("   جستجو مالک", searchIcon);
        searchLandlord.setMnemonic(KeyEvent.VK_S);
        searchLandlord.setToolTipText("جستجو");
        searchLandlord.addActionListener(ae -> {
            System.out.println("جستجو مالک کلیک شد");
            Landlord seaLandlord = searchLandlord();
        });
        
        //Display all Landlords
        JMenuItem displayLandlord = new JMenuItem("  نمایش مالک", displayIcon);
        displayLandlord.setMnemonic(KeyEvent.VK_L);
        displayLandlord.setToolTipText("جستجو");
        displayLandlord.addActionListener(ae -> {
            System.out.println("نمایش مالک");
            StringBuilder builder = new StringBuilder(landlordList.size());
            for (Landlord land : landlordList) {
                builder.append(land.toString() + "\n");
            }

            //JTextArea for displaying the list (StringBuilder)
            JTextArea textArea = new JTextArea(builder.toString());
            JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                    JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            scrollPane.setPreferredSize(new Dimension(500, 500));
            JOptionPane.showMessageDialog(null, scrollPane, "لیست مالکین", JOptionPane.OK_OPTION);

            for (Landlord c : landlordList) {
                System.out.println(c.toString());
            }
        });

        landlords.add(registerLandlord);
        landlords.add(deRegisterLandlord);
        landlords.add(amendDetails);
        landlords.add(searchLandlord);
        landlords.add(displayLandlord);
        
        ///////////////Properties MenuItems////////////////////
        //Record New Property
        JMenuItem recordNewProperty = new JMenuItem(" ثبت مشخصات جدید", addIcon);
        recordNewProperty.setMnemonic(KeyEvent.VK_R);
        recordNewProperty.setToolTipText("باز کردن ثبت مشخصات جدید");
        recordNewProperty.addActionListener(ae -> {
            RecordNewPropertyGUI addNewPropertyScreen = new RecordNewPropertyGUI();
        });
        
        //Delete Property 
        JMenuItem deleteProperty = new JMenuItem("  حذف جزییات", deleteIcon);
        deleteProperty.setMnemonic(KeyEvent.VK_D);
        deleteProperty.setToolTipText("حذف مشخصات از سیستم");
        deleteProperty.addActionListener(ae -> {
            System.out.println("حذف مشخصات کلیک شد");
            Property removeProperty = removeProperty();
        });
        
        //Search For Property 
        JMenuItem searchProperties = new JMenuItem("  جستجو مشخصات", searchIcon);
        searchProperties.setMnemonic(KeyEvent.VK_A);
        searchProperties.setToolTipText("ستجوی یک مشخصه");
        searchProperties.addActionListener(ae -> {
            System.out.println("جستجو مشخصه کلیک شد");
        });
        
        //Display all Properties 
        JMenuItem displayProperties = new JMenuItem("نمایش مشخصات", displayIcon);
        displayProperties.setMnemonic(KeyEvent.VK_A);
        displayProperties.setToolTipText("جستجو مشخصات");
        displayProperties.addActionListener(ae -> {
            System.out.println("نمایش مشخصات کلیک شد");

            StringBuilder builder = new StringBuilder(propertyList.size());
            for (Property pr : propertyList) {
                builder.append(pr.toString() + "\n");
            }

            //JTextArea for displaying the list (StringBuilder)
            JTextArea textArea = new JTextArea(builder.toString());
            JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                    JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            scrollPane.setPreferredSize(new Dimension(500, 500));
            JOptionPane.showMessageDialog(null, scrollPane, "لیست مشخات", JOptionPane.OK_OPTION);

            for (Property pro : propertyAvailable) {
                System.out.println(pro.toString());
            }

        });
        
        //Display all Properties Available
        JMenuItem propertiesAvailable = new JMenuItem("  نمایش مشخات در دسترس", displayIcon);
        propertiesAvailable.setMnemonic(KeyEvent.VK_A);
        propertiesAvailable.setToolTipText("جستجو مشخصات برای نمایش");
        propertiesAvailable.addActionListener(ae -> {
            System.out.println("نمایش مشخصات کلیک شد");

            StringBuilder builder = new StringBuilder(propertyAvailable.size());
            for (Property pr : propertyAvailable) {
                builder.append(pr.toString() + "\n");
            }

            //JTextArea for displaying the list (StringBuilder)
            JTextArea textArea = new JTextArea(builder.toString());
            JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                    JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            scrollPane.setPreferredSize(new Dimension(500, 500));
            JOptionPane.showMessageDialog(null, scrollPane, "لیست مشخصه های در دسترس", JOptionPane.OK_OPTION);

            for (Property pro : propertyAvailable) {
                System.out.println(pro.toString());
            }

        });
        
        
        //List Properties Available/Let
        JMenuItem listPropertiesAvailable = new JMenuItem("  یست مشخصات خارج از  دسترس", listIcon);
        listPropertiesAvailable.setMnemonic(KeyEvent.VK_L);
        listPropertiesAvailable.setToolTipText("لیست مشخصات در دسترس");
        listPropertiesAvailable.addActionListener(ae -> {
            System.out.println("List Properties Available/Let Clicked");
            StringBuilder builder = new StringBuilder(propertyList.size());
            for (Property prop : propertyList) {
                builder.append(prop.toString() + "\n");
            }

            //JTextArea for displaying the list (StringBuilder)
            JTextArea textArea = new JTextArea(builder.toString());
            JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                    JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            scrollPane.setPreferredSize(new Dimension(500, 500));
            JOptionPane.showMessageDialog(null, scrollPane, "لیست مشخات در دسترس نیست", JOptionPane.OK_OPTION);

            for (Property p : propertyList) {
                System.out.println(p.toString());
            }
        });

        properties.add(recordNewProperty);
        properties.add(deleteProperty);
        properties.add(searchProperties);
        properties.add(displayProperties);
        properties.add(propertiesAvailable);
        properties.add(listPropertiesAvailable);
        
        
        ////////////////Tenants Menu Items//////////////////////
        //Register New Tenant
        JMenuItem registerNewTenant = new JMenuItem("  ثبت مستاجر جدید", addIcon);
        registerNewTenant.setMnemonic(KeyEvent.VK_R);
        registerNewTenant.setToolTipText("پنجره ثبت مستاجر جدید");
        registerNewTenant.addActionListener(ae -> {
            RegisterNewTenantGUI addNewTenantScreen = new RegisterNewTenantGUI();
        });
        
        
        //Delete Tenant
        JMenuItem deleteTenant = new JMenuItem("  حذف مستاجر", deleteIcon);
        deleteTenant.setMnemonic(KeyEvent.VK_D);
        deleteTenant.setToolTipText("حذف مستاجر در سیستم");
        deleteTenant.addActionListener(ae -> {
            System.out.println("حذف مستاجر کلیک شد");
            Tenant removeTenant = removeTenant();
        });
        
        
        //Amend tenant's details
        JMenuItem amendTenant = new JMenuItem("  ویرایش جزییات مستاجر", editIcon);
        amendTenant.setMnemonic(KeyEvent.VK_C);
        amendTenant.setToolTipText("جستجو مستاجر و ویرایش آن");
        amendTenant.addActionListener(ae -> {
            System.out.println("تغییر مستاجر کلیک شد");
            Tenant amendTenantDetails = amendTenant();
            if (amendTenantDetails != null) {
                AmendTenantGUI amendTenantGUI = new AmendTenantGUI(amendTenantDetails);
            }
        });
        
        //Search For Tenant
        JMenuItem searchTenant = new JMenuItem("  جستجو مستاجر", searchIcon);
        searchTenant.setMnemonic(KeyEvent.VK_S);
        searchTenant.setToolTipText("جستجو مستاجر در سیستم");
        searchTenant.addActionListener(ae -> {
            System.out.println("جستجو مستاجر کلیک شد");
            Tenant searchTen = searchTenant();
        });
        
        //Display All Tenants
        JMenuItem displayTenants = new JMenuItem("  نمایش لیست مستجران", displayIcon);
        displayTenants.setMnemonic(KeyEvent.VK_P);
        displayTenants.setToolTipText("نمایش مستجر روی سیستم");
        displayTenants.addActionListener(ae -> {
            System.out.println("نمایش مستاجر کلیک شد");

            String s = tenantLts.getSelectedItem().toString();
            if (s == "مستاجر های در دسترس") {
                StringBuilder builder = new StringBuilder(tenantList.size());
                for (Tenant ten : tenantList) {
                    builder.append(ten.toString() + "\n");
                }

                //JTextArea for displaying the list (StringBuilder)
                JTextArea textArea = new JTextArea(builder.toString());
                JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);
                scrollPane.setPreferredSize(new Dimension(500, 500));
                JOptionPane.showMessageDialog(null, scrollPane, "لیست مستجر های در دسترس", JOptionPane.OK_OPTION);

                System.out.println("لیست مستجر های در دسترس :");
                for (Tenant t : HomeScreenGUI.tenantsAvailable) {
                    System.out.println("نام: " + t.getName() + "\nID آدرس: "
                            + t.getAddress() + "\nشماره تماس: " + t.getPhoneNumber() + "\nشماره سند: " + t.getPpsNumber()
                            + "\nآیدی مستجر: " + t.getTenantID() + "\nتوضیحات کامل: " + t.getTenantStatus());
                }

            }
        });
        
        
        //Display All Tenants
        JMenuItem tenantsAvailable = new JMenuItem("  نمایش مستاجر در دسترس", displayIcon);
        tenantsAvailable.setMnemonic(KeyEvent.VK_P);
        tenantsAvailable.setToolTipText("مایش مستاجر در سیستم");
        tenantsAvailable.addActionListener(ae -> {
            System.out.println("مایش مستاجر کلیک شد");

            String s = tenantLts.getSelectedItem().toString();
            if (s == "Tenants Available") {
                StringBuilder builder = new StringBuilder(HomeScreenGUI.tenantsAvailable.size());
                for (Tenant land : HomeScreenGUI.tenantsAvailable) {
                    builder.append(land.toString() + "\n");
                }

                //JTextArea for displaying the list (StringBuilder)
                JTextArea textArea = new JTextArea(builder.toString());
                JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);
                scrollPane.setPreferredSize(new Dimension(500, 500));
                JOptionPane.showMessageDialog(null, scrollPane, "لیست مستاجر های در دسترس", JOptionPane.OK_OPTION);

                System.out.println("لیست مستاجر های در دسترس :");
                for (Tenant t : HomeScreenGUI.tenantsAvailable) {
                    System.out.println("Name: " + t.getName() + "\nID number: "
                            + t.getAddress() + "\nPhone No: " + t.getPhoneNumber() + "\nPPS Number: " + t.getPpsNumber()
                            + "\nTenant ID: " + t.getTenantID() + "\nTenant Status: " + t.getTenantStatus());
                }

            }
        });

        tenants.add(registerNewTenant);
        tenants.add(deleteTenant);
        tenants.add(amendTenant);
        tenants.add(searchTenant);
        tenants.add(displayTenants);
        tenants.add(tenantsAvailable);
        
        
        //Process New Rental
        JMenuItem processNewRental = new JMenuItem("  پروسه کرایه جدید", addIcon);
        processNewRental.setMnemonic(KeyEvent.VK_P);
        processNewRental.setToolTipText("پردازش کرایه جدید در سیستم");
        processNewRental.addActionListener(ae -> {
            System.out.println("پردازش کرایه جدید اضافه شد");
            StringBuilder builder = new StringBuilder(rentalList.size());
            for (Rental land : rentalList) {
                builder.append(land.toString() + "\n");
            }

            // pick a tenant
            int tenIdx = Integer.parseInt(JOptionPane.showInputDialog("یک آیدی مستجر وارد کن "
                    + " بین 1 و " + HomeScreenGUI.tenantsAvailable.size()));
            // pick a property
            int propIdx = Integer.parseInt(JOptionPane.showInputDialog("یک آیدی مشخه وارد کن "
                    + " بین 1 و " + propertyAvailable.size()));

            // create a new rental object
            Rental propertyRental = new Rental(propertyAvailable.get(tenIdx - 1),
                    HomeScreenGUI.tenantsAvailable.get(propIdx - 1));
            propertyAvailable.get(propIdx - 1).takeProperty();
            propertyRental.setTerm("1 year");
            propertyRental.setRate(750.00);
            // add the tenant to the property list
            rentalList.add(propertyRental);
            // add taken tenant to tenantList
            tenantList.add(HomeScreenGUI.tenantsAvailable.get(propIdx - 1));
            // remove taken tenant from tenantAvailable list
            HomeScreenGUI.tenantsAvailable.remove(HomeScreenGUI.tenantsAvailable.get(propIdx - 1));
            // add taken property to propertyLet list
            propertyLet.add(propertyAvailable.get(tenIdx - 1));
            // remove taken property from propertyAvailable list
            propertyAvailable.remove(propertyAvailable.get(tenIdx - 1));
        });
        
        
        //Search For Rental
        JMenuItem searchRental = new JMenuItem("  جستجو کرایه", searchIcon);
        searchRental.setMnemonic(KeyEvent.VK_S);
        searchRental.setToolTipText("جستجو برای کرایه روی سیستم");
        searchRental.addActionListener(ae -> {
            System.out.println("جستجو کرایه کلیک شد");
        });
        
        //Display All Rentals
        JMenuItem displayRentals = new JMenuItem(" نمایش کرایه", displayIcon);
        displayRentals.setMnemonic(KeyEvent.VK_D);
        displayRentals.setToolTipText("نمایش کرایه روی سیستم");
        displayRentals.addActionListener(ae -> {
            System.out.println("نمایش کرایه کلیک شد");

            // note that this gives a very poor display layout
            // instead of using r.toString(), a better-organised display would be needed  
            area.setText("لیست کلاس\n");
            // adding scrollbar
            JScrollPane scroll = new JScrollPane(area, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                    JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            for (Rental r : rentalList) {
                area.append(r.toString());
                if (r.getProperty() instanceof Property) {
                    area.append(" نام کلاس: " + ((Property) (r.getProperty())).getAddress_() + "\n\n");
                }
            }
            // setting scrollbar dimension
            scroll.setPreferredSize(new Dimension(500, 500));
            JOptionPane.showMessageDialog(null, scroll);
        });
        
        
        rentals.add(processNewRental);
        rentals.add(searchRental);
        rentals.add(displayRentals);

//////////////////////Help Menu Items////////////////////////////
        //The about page
        JMenuItem about = new JMenuItem("  رباره ما", aboutIcon);
        about.setMnemonic(KeyEvent.VK_A);
        about.setToolTipText("اطلاعات این سیستم");
        about.addActionListener(ae -> {
            System.out.println("اطلاعات سیستم کلیک شده");
            JOptionPane.showMessageDialog(null, "سلام این یک برنامه املاک است \n");
        });
        
        
        //Help/documentation Page
        JMenuItem documentation = new JMenuItem("  راهنما", helpIcon);
        documentation.setMnemonic(KeyEvent.VK_H);
        documentation.setToolTipText(" باز کردن صفحه راهنما");
        documentation.addActionListener(ae -> {
            System.out.println("نمایش صفحه راهنما");
            JOptionPane.showMessageDialog(null, " ار با این برنامه بسیار راحت است");
        });

        help.add(about);
        help.add(documentation);
        
        //Add top menu bar items
        menuBar.add(landlords);
        menuBar.add(properties);
        menuBar.add(tenants);
        menuBar.add(rentals);
        menuBar.add(help);

        //Set the Menu bar
        setJMenuBar(menuBar);
        
    }   //   End of createMenuBar Method 
    
    
    
    
    public void saveLandlordDatabase(int id, String name, String addrss, String phoneNumber, String ppsNumber, String status){
        
        LandlordCtrl landlordCtrl = new LandlordCtrl();
        try {
            landlordCtrl.insert(id, name, addrss, phoneNumber, ppsNumber, status);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void updateLandlordDatabase(Landlord landlord){
        
        LandlordCtrl landlordCtrl = new LandlordCtrl();
        try {
            landlordCtrl.update(landlord);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void deleteLandlordDatabase(String name){
        
        LandlordCtrl landlordCtrl = new LandlordCtrl();
        try {
            landlordCtrl.delete(name);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void savePropertyDatabase(int id, String address, String phone, String type, String rentAmount, String status){
        
        PropertyCtrl propertyCtrl = new PropertyCtrl();
        
        try {
            propertyCtrl.insert(id, address, phone, type, rentAmount, status);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void deletePropertyDatabase(String address){
        
        PropertyCtrl propertyCtrl = new PropertyCtrl();
        
        
    }
    
    public void saveTenantDatabase(int id, String name, String addrss, String phoneNumber, String ppsNumber, String status){
        
        TenantCtrl tenantCtrl = new TenantCtrl();
        
        try {
            tenantCtrl.insert(id, name, addrss, phoneNumber, ppsNumber, status);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void updateTenantDatabase(Tenant tenant){
        
        TenantCtrl tenantCtrl = new TenantCtrl();
        
        try {
            tenantCtrl.update(tenant);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void deleteTenantDatabase(String name){
        
        TenantCtrl tenantCtrl = new TenantCtrl();
        
        try {
            tenantCtrl.delete(name);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private Landlord removeLandlord() {
        boolean foundMatch = false;
        ArrayList<String> landlordIdList = new ArrayList<String>();
        String landlIDString = "";
        String selectedLandlord = "";
        boolean check = false;
        Object[] landlordIds = {};
        String removeName = JOptionPane.showInputDialog("اسم مالک را برای حذف وارد کن: ");
        Landlord removedLandlord = new Landlord();

        if (removeName.equals("")) {
            JOptionPane.showMessageDialog(null, "همچین اسمی وجود ندارد!");
        } else {
            StringBuilder builder = new StringBuilder(landlordList.size());
            while (!foundMatch) {
                for (Landlord lan : landlordList) {
                    if (lan.getName().equalsIgnoreCase(removeName)) {
                        builder.append(lan.toString() + "\n");
                        landlIDString = Integer.toString(lan.getLandlordID());
                        landlordIdList.add(landlIDString);
                        landlordIds = landlordIdList.toArray();
                        foundMatch = true;
                    }
                }
                //JTextArea for displaying the list (StringBuilder)
                JTextArea textArea = new JTextArea(builder.toString());
                JScrollPane scrollPane = new JScrollPane(textArea);
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);
                scrollPane.setPreferredSize(new Dimension(500, 500));

                for (int i = 0; i < landlordList.size(); i++) {
                    if (removeName.equals(landlordList.get(i).getName())) {
                        landlordList.remove(i);
                        JOptionPane.showMessageDialog(null, removeName + " از لیست حذف شد");
                        check = true;
                        deleteLandlordDatabase(removeName);
                        break;
                    }
                }
                if(check == false)
                    JOptionPane.showMessageDialog(null, removeName + " وجود ندارد");
                break;
            }
        }
        return removedLandlord;
    }
    
    private Landlord amendLandlord() {
        boolean foundMatch = false;
        ArrayList<String> landlordIdList = new ArrayList<String>();
        String landlIDString = "";
        String selectedLandlord = "";
        Object[] landlordIds = {};
        String searchName = JOptionPane.showInputDialog("اسم مالک را وارد کن: ");
        Landlord searchedLandlord = new Landlord();

        if (searchName.equals("")) {
            JOptionPane.showMessageDialog(null, "هیچ اسمی وارد نشد!");
        } else {
            StringBuilder builder = new StringBuilder(landlordList.size());
            while (!foundMatch) {
                for (Landlord lan : landlordList) {
                    if (lan.getName().equalsIgnoreCase(searchName)) {
                        builder.append(lan.toString() + "\n");
                        landlIDString = Integer.toString(lan.getLandlordID());
                        landlordIdList.add(landlIDString);
                        landlordIds = landlordIdList.toArray();
                        foundMatch = true;
                    }
                }
                //JTextArea for displaying the list (StringBuilder)
                JTextArea textArea = new JTextArea(builder.toString());
                JScrollPane scrollPane = new JScrollPane(textArea);
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);
                scrollPane.setPreferredSize(new Dimension(500, 500));

                //Display list if not empty
                if (!(textArea.getText().trim().length() == 0)) {
                    JOptionPane.showMessageDialog(null, scrollPane, "لیست مالکان", JOptionPane.OK_OPTION);
                    //In here should go the select patient code
                    JFrame frame = new JFrame("لطفا مالک را انتخاب کن");
                    selectedLandlord = (String) JOptionPane.showInputDialog(frame,
                            "جزییات کدام مالک عوض شود? ",
                            "لطفا آیدی مالک را وارد کن:",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            landlordIds,
                            landlordIds[0]);
                } else if (textArea.getText().trim().length() == 0) {
                    JOptionPane.showMessageDialog(null, "همچین مالکی وجود ندارد: " + searchName);
                    foundMatch = true;
                    return null;
                }
                //Now I have the selected landlord ID I must now open a new window displaying their details and allow to change
                for (Landlord land : landlordList) {
                    if (selectedLandlord.equals("" + land.getLandlordID())) {
                        searchedLandlord = land;
                    }
                }
            }
        }
        return searchedLandlord;
    }
    
    private Landlord searchLandlord() {
        boolean foundMatch = false;
        ArrayList<String> landlordIdList = new ArrayList<String>();
        String landlIDString = "";
        String selectedLandlord = "";
        Object[] landlordIds = {};
        String searchName = JOptionPane.showInputDialog("نام مالک را وارد کن: ");
        Landlord searchedLandlord = new Landlord();

        if (searchName.equals("")) {
            JOptionPane.showMessageDialog(null, "اسمی وارد نشد!");
        } else {
            StringBuilder builder = new StringBuilder(landlordList.size());
            while (!foundMatch) {
                for (Landlord lan : landlordList) {
                    if (lan.getName().equalsIgnoreCase(searchName)) {
                        builder.append(lan.toString() + "\n");
                        landlIDString = Integer.toString(lan.getLandlordID());
                        landlordIdList.add(landlIDString);
                        landlordIds = landlordIdList.toArray();
                        foundMatch = true;
                    }
                }
                //JTextArea for displaying the list (StringBuilder)
                JTextArea textArea = new JTextArea(builder.toString());
                JScrollPane scrollPane = new JScrollPane(textArea);
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);
                scrollPane.setPreferredSize(new Dimension(500, 500));

                //Display list if not empty
                if (!(textArea.getText().trim().length() == 0)) {
                    JOptionPane.showMessageDialog(null, scrollPane, "لیست مالکان", JOptionPane.OK_OPTION);
                } else if (textArea.getText().trim().length() == 0) {
                    JOptionPane.showMessageDialog(null, "همچین مالکی وجود ندارد: " + searchName);
                    foundMatch = true;
                    return null;
                }
                //Now I have the selected landlord ID I must now open a new window displaying their details and allow to change
                for (Landlord land : landlordList) {
                    if (selectedLandlord.equals("" + land.getLandlordID())) {
                        searchedLandlord = land;
                    }
                }
            }
        }
        return searchedLandlord;
    }
    
    private Property removeProperty() {

        JFrame removePropertyMenu = new JFrame();
        removePropertyMenu.setVisible(true);
        removePropertyMenu.setSize(200, 200);
        removePropertyMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        removePropertyMenu.setLocationRelativeTo(null);

        JPanel removeProperty = new JPanel();
        removeProperty.add(delProText);
        removeProperty.add(delPropertyLts);
        removeProperty.add(b);
        removeProperty.add(c);

        removePropertyMenu.add(removeProperty);

        Property removedProperty = new Property();

        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String s = delPropertyLts.getSelectedItem().toString();
                if (s == "مشخصات در دسترس") {
                    boolean foundMatch = false;
                    ArrayList<String> propertyIdList = new ArrayList<String>();
                    String propertyIDString = "";
                    String selectedProperty = "";
                    Object[] propertyIds = {};
                    String removeProperty = JOptionPane.showInputDialog("لطفا آدرس مشخه را برای حذف وارد کن: ");

                    if (removeProperty.equals("")) {
                        JOptionPane.showMessageDialog(null, "هیج آدرسی وارد نشد !");
                    } else {
                        StringBuilder builder = new StringBuilder(propertyAvailable.size());
                        while (!foundMatch) {
                            for (Property prop : propertyAvailable) {
                                if (prop.getAddress_().equalsIgnoreCase(removeProperty)) {
                                    builder.append(prop.toString() + "\n");
                                    propertyIDString = Integer.toString(prop.getPropertyID_());
                                    propertyIdList.add(propertyIDString);
                                    propertyIds = propertyIdList.toArray();
                                    foundMatch = true;
                                }
                            }
                            //JTextArea for displaying the list (StringBuilder)
                            JTextArea textArea = new JTextArea(builder.toString());
                            JScrollPane scrollPane = new JScrollPane(textArea);
                            textArea.setLineWrap(true);
                            textArea.setWrapStyleWord(true);
                            scrollPane.setPreferredSize(new Dimension(500, 500));

                            for (int i = 0; i < propertyAvailable.size(); i++) {
                                if (removeProperty.equals(propertyAvailable.get(i).getAddress_())) {
                                    propertyAvailable.remove(i);
                                    JOptionPane.showMessageDialog(null, removeProperty + " حذف شد");
                                } else if (!removeProperty.equals(propertyAvailable.get(i).getAddress_())) {
                                    JOptionPane.showMessageDialog(null, removeProperty + "وجود ندارد");
                                }
                            }
                            break;
                        }
                    }
                } else {
                    boolean foundMatch = false;
                    ArrayList<String> tenantIdList = new ArrayList<String>();
                    String tenantIDString = "";
                    String selectedTenant = "";
                    boolean check = false;
                    Object[] tenantIds = {};
                    String removeProperty = JOptionPane.showInputDialog("آدرس مشخصه را برای حذف وارد کن: ");

                    if (removeProperty.equals("")) {
                        JOptionPane.showMessageDialog(null, "هیچ آدرسی وارد نشد!");
                    } else {
                        StringBuilder builder = new StringBuilder(propertyList.size());
                        while (!foundMatch) {
                            for (Property prop : propertyList) {
                                if (prop.getAddress_().equalsIgnoreCase(removeProperty)) {
                                    builder.append(prop.toString() + "\n");
                                    tenantIDString = Integer.toString(prop.getPropertyID_());
                                    tenantIdList.add(tenantIDString);
                                    tenantIds = tenantIdList.toArray();
                                    foundMatch = true;
                                }
                            }
                            //JTextArea for displaying the list (StringBuilder)
                            JTextArea textArea = new JTextArea(builder.toString());
                            JScrollPane scrollPane = new JScrollPane(textArea);
                            textArea.setLineWrap(true);
                            textArea.setWrapStyleWord(true);
                            scrollPane.setPreferredSize(new Dimension(500, 500));

                            for (int i = 0; i < propertyList.size(); i++) {
                                if (removeProperty.equals(propertyList.get(i).getAddress_())) {
                                    propertyList.remove(i);
                                    JOptionPane.showMessageDialog(null, removeProperty + " حذف شد");
                                    deletePropertyDatabase(removeProperty);
                                    check = true;
                                    break;
                                } 
                            }
                            if(check == false)
                                JOptionPane.showMessageDialog(null, removeProperty + " وجود ندارد");
                            break;
                        }
                    }
                }
            }

        });

        c.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removePropertyMenu.dispose();
            }
        });

        return removedProperty;

    }
    
    
    private Tenant removeTenant() {

        JFrame removeTenMenu = new JFrame();
        removeTenMenu.setVisible(true);
        removeTenMenu.setSize(200, 200);
        removeTenMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        removeTenMenu.setLocationRelativeTo(null);

        JPanel removeTenant = new JPanel();
        removeTenant.add(delTenText);
        removeTenant.add(delTenantLts);
        removeTenant.add(g);
        removeTenant.add(h);

        removeTenMenu.add(removeTenant);

        Tenant removedTenant = new Tenant();

        g.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String s = delTenantLts.getSelectedItem().toString();
                if (s == "مستاجر های در دسترس") {
                    boolean foundMatch = false;
                    ArrayList<String> tenantIdList = new ArrayList<String>();
                    String tenantIDString = "";
                    String selectedTenant = "";
                    boolean check = false;
                    Object[] tenantIds = {};
                    String removeName = JOptionPane.showInputDialog(" لطفا نام مستجر برای حذف را وارد کن: ");

                    if (removeName.equals("")) {
                        JOptionPane.showMessageDialog(null, "هیچ نامی وارد نشد!");
                    } else {
                        StringBuilder builder = new StringBuilder(tenantsAvailable.size());
                        while (!foundMatch) {
                            for (Tenant ten : tenantsAvailable) {
                                if (ten.getName().equalsIgnoreCase(removeName)) {
                                    builder.append(ten.toString() + "\n");
                                    tenantIDString = Integer.toString(ten.getTenantID());
                                    tenantIdList.add(tenantIDString);
                                    tenantIds = tenantIdList.toArray();
                                    foundMatch = true;
                                }
                            }
                            //JTextArea for displaying the list (StringBuilder)
                            JTextArea textArea = new JTextArea(builder.toString());
                            JScrollPane scrollPane = new JScrollPane(textArea);
                            textArea.setLineWrap(true);
                            textArea.setWrapStyleWord(true);
                            scrollPane.setPreferredSize(new Dimension(500, 500));

                            for (int i = 0; i < tenantsAvailable.size(); i++) {
                                if (removeName.equals(tenantsAvailable.get(i).getName())) {
                                    tenantsAvailable.remove(i);
                                    JOptionPane.showMessageDialog(null, removeName + " حذف شد");
                                    deleteTenantDatabase(removeName);
                                    check = true;
                                    break;
                                } 
                            }
                            if(check == false)
                                JOptionPane.showMessageDialog(null, removeName + " وجود ندارد");
                            break;
                        }
                    }
                } else {
                    boolean foundMatch = false;
                    ArrayList<String> tenantIdList = new ArrayList<String>();
                    String tenantIDString = "";
                    String selectedTenant = "";
                    Object[] tenantIds = {};
                    String removeName = JOptionPane.showInputDialog("لطفا نام مستاجر را برای حذف وارد کن: ");

                    if (removeName.equals("")) {
                        JOptionPane.showMessageDialog(null, "نامی وارد نشد!");
                    } else {
                        StringBuilder builder = new StringBuilder(tenantList.size());
                        while (!foundMatch) {
                            for (Tenant ten : tenantList) {
                                if (ten.getName().equalsIgnoreCase(removeName)) {
                                    builder.append(ten.toString() + "\n");
                                    tenantIDString = Integer.toString(ten.getTenantID());
                                    tenantIdList.add(tenantIDString);
                                    tenantIds = tenantIdList.toArray();
                                    foundMatch = true;
                                }
                            }
                            //JTextArea for displaying the list (StringBuilder)
                            JTextArea textArea = new JTextArea(builder.toString());
                            JScrollPane scrollPane = new JScrollPane(textArea);
                            textArea.setLineWrap(true);
                            textArea.setWrapStyleWord(true);
                            scrollPane.setPreferredSize(new Dimension(500, 500));

                            for (int i = 0; i < tenantList.size(); i++) {
                                if (removeName.equals(tenantList.get(i).getName())) {
                                    tenantList.remove(i);
                                    JOptionPane.showMessageDialog(null, removeName + " حذف شد");
                                } else if (!removeName.equals(tenantList.get(i).getName())) {
                                    JOptionPane.showMessageDialog(null, removeName + " وجود ندارد");
                                }
                            }
                            break;
                        }
                    }
                }
            }

        });

        h.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeTenMenu.dispose();
            }
        });

        return removedTenant;

    }
    
    
    private Tenant amendTenant() {

        JFrame amendTenMenu = new JFrame();
        amendTenMenu.setVisible(true);
        amendTenMenu.setSize(200, 200);
        amendTenMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        amendTenMenu.setLocationRelativeTo(null);

        JPanel amendTenant = new JPanel();
        amendTenant.add(amdTenText);
        amendTenant.add(amdTenantLts);
        amendTenant.add(i);
        amendTenant.add(j);

        amendTenMenu.add(amendTenant);

        Tenant amdTenant = new Tenant();         
        
        
        
        i.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String s = delTenantLts.getSelectedItem().toString();
                if (s == "مستجر های در دسترس") {
                    
                    boolean foundMatch = false;
        ArrayList<String> tenantIdList = new ArrayList<String>();
        String tenIDString = "";
        String selectedTenant = "";
        Object[] tenantIds = {};
        String searchTenant = JOptionPane.showInputDialog("نام مستاجر را وارد کن: ");
        Tenant searchedTenant = new Tenant();
        
        if (searchTenant.equals("")) {
            JOptionPane.showMessageDialog(null, "هیچ نامی وارد نشد!");
        } else {
            StringBuilder builder = new StringBuilder(tenantsAvailable.size());
            while (!foundMatch) {
                for (Tenant ten : tenantsAvailable) {
                    if (ten.getName().equalsIgnoreCase(searchTenant)) {
                        builder.append(ten.toString() + "\n");
                        tenIDString = Integer.toString(ten.getTenantID());
                        tenantIdList.add(tenIDString);
                        tenantIds = tenantIdList.toArray();
                        foundMatch = true;
                    }
        }
                //JTextArea for displaying the list (StringBuilder)
                JTextArea textArea = new JTextArea(builder.toString());
                JScrollPane scrollPane = new JScrollPane(textArea);
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);
                scrollPane.setPreferredSize(new Dimension(500, 500));
                
                //Display list if not empty
                if (!(textArea.getText().trim().length() == 0)) {
                    JOptionPane.showMessageDialog(null, scrollPane, "لیست مستاجر ها", JOptionPane.OK_OPTION);
                    //In here should go the select patient code
                    JFrame frame = new JFrame("لطفا مستاجر را انتخاب کن");
                    selectedTenant = (String) JOptionPane.showInputDialog(frame,
                            "جزییات کدام مستاجر تغییر کند? ",
                            "انتخاب آیدی مستاجر:",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            tenantIds,
                            tenantIds[0]);
                } else if (textArea.getText().trim().length() == 0) {
                    JOptionPane.showMessageDialog(null, "همچین مستاجری وجود ندارد: " + searchTenant);
                    foundMatch = true;
                    //return null;
                }
                //Now I have the selected tenant ID I must now open a new window displaying their details and allow to change
                for (Tenant tent : tenantsAvailable) {
                    if (selectedTenant.equals("" + tent.getTenantID())) {
                        searchedTenant = tent;
                    }
                }break;
            }
        }
        //return searchedTenant;
                    
                    
                    
                } else {
                    
                    boolean foundMatch = false;
        ArrayList<String> tenantIdList = new ArrayList<String>();
        String tenIDString = "";
        String selectedTenant = "";
        Object[] tenantIds = {};
        String searchTenant = JOptionPane.showInputDialog("نام مستاجر را وارد کن: ");
        Tenant searchedTenant = new Tenant();
        
        if (searchTenant.equals("")) {
            JOptionPane.showMessageDialog(null, "هیچ نامی وارد نشد!");
        } else {
            StringBuilder builder = new StringBuilder(tenantList.size());
            while (!foundMatch) {
                for (Tenant ten : tenantList) {
                    if (ten.getName().equalsIgnoreCase(searchTenant)) {
                        builder.append(ten.toString() + "\n");
                        tenIDString = Integer.toString(ten.getTenantID());
                        tenantIdList.add(tenIDString);
                        tenantIds = tenantIdList.toArray();
                        foundMatch = true;
                    }
        }
                //JTextArea for displaying the list (StringBuilder)
                JTextArea textArea = new JTextArea(builder.toString());
                JScrollPane scrollPane = new JScrollPane(textArea);
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);
                scrollPane.setPreferredSize(new Dimension(500, 500));
                
                //Display list if not empty
                if (!(textArea.getText().trim().length() == 0)) {
                    JOptionPane.showMessageDialog(null, scrollPane, "لیست مستجران", JOptionPane.OK_OPTION);
                    //In here should go the select patient code
                    JFrame frame = new JFrame("مستاجر را انتخاب کن");
                    selectedTenant = (String) JOptionPane.showInputDialog(frame,
                            "جزییات کدام مستجر تغییر کند? ",
                            "آیدی مستاجر را وارد کن:",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            tenantIds,
                            tenantIds[0]);
                } else if (textArea.getText().trim().length() == 0) {
                    JOptionPane.showMessageDialog(null, "همچین مستاجری نیست: " + searchTenant);
                    foundMatch = true;
                    //return null;
                }
                //Now I have the selected tenant ID I must now open a new window displaying their details and allow to change
                for (Tenant tent : tenantList) {
                    if (selectedTenant.equals("" + tent.getTenantID())) {
                        searchedTenant = tent;
                    }
                }
            }
        }
        
                    
                    
                }
            }

        });

        j.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                amendTenMenu.dispose();
            }
        });

        
        return amdTenant;
    }
    
    
    private Tenant searchTenant() {
        boolean foundMatch = false;
        ArrayList<String> tenantIdList = new ArrayList<String>();
        String tenantlIDString = "";
        String selectedTenant = "";
        Object[] tenantIds = {};
        String searchName = JOptionPane.showInputDialog("اسم مستاجر را وارد کن: ");
        Tenant searchedTenant = new Tenant();

        if (searchName.equals("")) {
            JOptionPane.showMessageDialog(null, "هیچچ اسمی وارد نشد!");
        } else {
            StringBuilder builder = new StringBuilder(landlordList.size());
            while (!foundMatch) {
                for (Tenant ten : tenantsAvailable) {
                    if (ten.getName().equalsIgnoreCase(searchName)) {
                        builder.append(ten.toString() + "\n");
                        tenantlIDString = Integer.toString(ten.getTenantID());
                        tenantIdList.add(tenantlIDString);
                        tenantIds = tenantIdList.toArray();
                        foundMatch = true;
                    }
                }
                //JTextArea for displaying the list (StringBuilder)
                JTextArea textArea = new JTextArea(builder.toString());
                JScrollPane scrollPane = new JScrollPane(textArea);
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);
                scrollPane.setPreferredSize(new Dimension(500, 500));

                //Display list if not empty
                if (!(textArea.getText().trim().length() == 0)) {
                    JOptionPane.showMessageDialog(null, scrollPane, "لیست مالکان", JOptionPane.OK_OPTION);
                } else if (textArea.getText().trim().length() == 0) {
                    JOptionPane.showMessageDialog(null, "همجین مالکی وجود ندارد: " + searchName);
                    foundMatch = true;
                    return null;
                }
                //Now I have the selected landlord ID I must now open a new window displaying their details and allow to change
                for (Tenant tent : tenantsAvailable) {
                    if (selectedTenant.equals("" + tent.getTenantID())) {
                        searchedTenant = tent;
                    }
                }
            }
        }
        return searchedTenant;
    }
}
