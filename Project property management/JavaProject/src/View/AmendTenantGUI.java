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


public class AmendTenantGUI extends JFrame{
    
    private static final int WIDTH = 800;
    private static final int HEIGHT = 500;
    
    private JLabel nameL, addressLine1L, addressLine2L, addressLine3L, countyL, tenantPhoneL, tenantPPSL, tenantStatusL,  tenantIDL, blankL, blank2L;
    private JTextField nameTF, addressLine1TF, addressLine2TF, addressLine3TF, countyTF, tenantPhoneTF, tenantPPSTF, tenantStatusTF, tenantIDTF;
    private String[] counties = {"صادقیه","طرشت","پونک","ستارخان","مرزداران","پاسداران","میرداماد","نارمک","تهرانپارس",
            "تهرانسر","شهرک غرب","اکباتان","رسالت","جنت آباد","شهران","یوسف آباد","سهروردی","شریعتی","منیریه","شهرری","نازی آباد",
            "پیروزی","اندرزگو","مشیریه","افسریه","جوادیه","خزانه","بهارستان","حسن آباد","جمهوری","انقلاب","مجیدیه"};
    private JComboBox countyComboBox;
    private JButton addB, cancelB;
    
    public static Tenant tenant_;
    
    public AmendTenantGUI(Tenant tenant){
        
        tenant_ = tenant;
        
        //Setting Font Properties
        Font myFont = new Font("Times New Roman", Font.BOLD, 20);
        
        //Instantiate the labels
        nameL = new JLabel(" : اسم خریدار یا مستاجر" , SwingConstants.RIGHT);
        addressLine1L = new JLabel(" :‌ آدرس", SwingConstants.RIGHT);
        addressLine2L = new JLabel(" : شرح امتیازات", SwingConstants.RIGHT);
        addressLine3L = new JLabel(" : مساحت", SwingConstants.RIGHT);
        countyL = new JLabel(" : شهر", SwingConstants.RIGHT);
        tenantPhoneL = new JLabel(" : شماره تماس", SwingConstants.RIGHT);
        tenantPPSL = new JLabel(" : شماره سند", SwingConstants.RIGHT);
        tenantStatusL = new JLabel(" : قیمت و توضیحات اضافه", SwingConstants.RIGHT);
        tenantIDL = new JLabel(" : آیدی خریدار یا مستاجر", SwingConstants.RIGHT);
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
        
        nameTF.setText(tenant.getName());
        addressLine1TF.setText(tenant.getAddress());
        tenantPhoneTF.setText(tenant.getPhoneNumber());
        tenantPPSTF.setText(tenant.getPpsNumber());
        tenantStatusTF.setText(tenant.getTenantStatus());
        tenantIDTF.setText(""+tenant.getTenantID());
        
        //Buttons:
        //Add Button
        addB = new JButton("ثبت تغییرات");
        addB.setFont(myFont);
        addB.setToolTipText("بر روی ثبت کلیک کنید.\n توجه: از صحت اطلاعات مطمئن شوید");
        addB.addActionListener(ae->{
            tenant.setName(nameTF.getText());
            tenant.setAddress(addressLine1TF.getText());
            tenant.setPhoneNumber(tenantPhoneTF.getText());
            tenant.setPpsNumber(tenantPPSTF.getText());
            tenant.setTenantStatus(tenantStatusTF.getText()); 
            
            HomeScreenGUI check = new HomeScreenGUI();
            check.updateTenantDatabase(tenant);
            tenantIDTF.setText(""+tenant.getTenantID());
            JOptionPane.showMessageDialog(null, "طلاعات ویرایش شد!");
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
        
        setTitle("ویرایش جزییات مستاجر");
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null); //Position application in center of screen
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        toFront();
    }
}
