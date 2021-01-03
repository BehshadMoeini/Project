package View;

import Model.Entity.Tenant;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;


public class RegisterNewTenantGUI extends JFrame{
    
    
    private static final int WIDTH = 900;
    private static final int HEIGHT = 600;
    
    private JLabel nameL, addressLine1L, addressLine2L, addressLine3L, countyL, tenantPhoneL, tenantPPSL, tenantStatusL,  tenantIDL, blankL, blank2L;
    private JTextField nameTF, addressLine1TF, addressLine2TF, addressLine3TF, countyTF, tenantPhoneTF, tenantPPSTF, tenantStatusTF, tenantIDTF;
    private String[] counties = {"صادقیه","طرشت","پونک","ستارخان","مرزداران","پاسداران","میرداماد","نارمک","تهرانپارس",
            "تهرانسر","شهرک غرب","اکباتان","رسالت","جنت آباد","شهران","یوسف آباد","سهروردی","شریعتی","منیریه","شهرری","نازی آباد",
            "پیروزی","اندرزگو","مشیریه","افسریه","جوادیه","خزانه","بهارستان","حسن آباد","جمهوری","انقلاب","مجیدیه"};
    private JComboBox countyComboBox;
    private JButton addB, cancelB;
    
    
    public RegisterNewTenantGUI(){
        
        //Setting Font Properties
        Font myFont = new Font("Times New Roman", Font.BOLD, 20);
        
        //Instantiate the labels
        nameL = new JLabel(" : اسم خریدار یا مستاجر" , SwingConstants.RIGHT);
        addressLine1L = new JLabel(" : آدرس", SwingConstants.RIGHT);
        addressLine2L = new JLabel(" : شرح امتیازات", SwingConstants.RIGHT);
        addressLine3L = new JLabel(" : مساحت", SwingConstants.RIGHT);
        countyL = new JLabel(" :‌ شهر", SwingConstants.RIGHT);
        tenantPhoneL = new JLabel(" : شماره تماس", SwingConstants.RIGHT);
        tenantPPSL = new JLabel(" : شماره سند", SwingConstants.RIGHT);
        tenantStatusL = new JLabel(" : وضعیت مستاجر", SwingConstants.RIGHT);
        tenantIDL = new JLabel(" : آیدی مستاجر", SwingConstants.RIGHT);
        blankL = new JLabel("", SwingConstants.RIGHT);
        blank2L = new JLabel("", SwingConstants.RIGHT);
        
        //Label Font
        nameL.setFont(myFont);
        addressLine1L.setFont(myFont);
        addressLine2L.setFont(myFont);
        addressLine3L.setFont(myFont);
        countyL.setFont(myFont);
        tenantPhoneL.setFont(myFont);
        tenantPPSL.setFont(myFont);
        tenantStatusL.setFont(myFont);
        tenantIDL.setFont(myFont);
        
        
        //Text Fields:
        nameTF = new JTextField(10);
        addressLine1TF = new JTextField(10);
        addressLine2TF = new JTextField(10);
        addressLine3TF = new JTextField(10);
        countyTF = new JTextField(10);
        tenantPhoneTF = new JTextField(10);
        tenantPPSTF = new JTextField(10);
        tenantStatusTF = new JTextField(10);
        tenantIDTF = new JTextField(10);
        tenantIDTF.setEditable(false);

        //County Drop Down Menu
        countyComboBox = new JComboBox(counties);
        
        
        //Buttons:
        //Add Button
        addB = new JButton("اضافه کردن مستاجر");
        addB.setFont(myFont);
        addB.setToolTipText("ثبت اطلاعات را کلیک کن.\n توجه: از صحت اطلاعات مطمئن شو");
        addB.addActionListener(ae->{
            Tenant t1 = new Tenant();
            t1.setName(nameTF.getText());
            t1.setAddress(addressLine1TF.getText() + "\n" + addressLine2TF.getText() + "\n" + addressLine3TF.getText() + "\n" + countyComboBox.getSelectedItem());
            t1.setPhoneNumber(tenantPhoneTF.getText());
            t1.setPpsNumber(tenantPPSTF.getText());
            t1.setTenantStatus(tenantStatusTF.getText());             
            HomeScreenGUI.tenantsAvailable.add(t1);   //Add to the landlord arrayList declared in the Home Screen
            tenantIDTF.setText(""+t1.getTenantID());
            
            HomeScreenGUI check = new HomeScreenGUI();
            check.saveTenantDatabase(t1.getTenantID(),nameTF.getText(), addressLine1TF.getText() + "\n" + addressLine2TF.getText() + "\n" + addressLine3TF.getText() + "\n" + countyComboBox.getSelectedItem(),
                    tenantPhoneTF.getText(), tenantPPSTF.getText(), tenantStatusTF.getText());
            JOptionPane.showMessageDialog(null, "مستاجر اضافه شد!");
        });
        
        //Cancel Button
        cancelB = new JButton("لغو");
        cancelB.setFont(myFont);
        cancelB.addActionListener(ae->{
            setVisible(false);
            dispose();
        });
        
        setLayout(new GridLayout(11, 2));
        
        //Add components to the JFrame
        add(nameL);
        add(nameTF);
        add(addressLine1L);
        add(addressLine1TF);
        add(addressLine2L);
        add(addressLine2TF);
        add(addressLine3L);
        add(addressLine3TF);
        add(countyL);
        add(countyComboBox);
        add(tenantPhoneL);
        add(tenantPhoneTF);
        add(tenantPPSL);
        add(tenantPPSTF);
        add(tenantStatusL);
        add(tenantStatusTF);
        add(tenantIDL);
        add(tenantIDTF);
        add(blankL);
        add(blank2L);
        add(addB);
        add(cancelB);
        
        setTitle("صفحه ثبت مستاجر");
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null); //Position application in center of screen
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        toFront();
    }
}
