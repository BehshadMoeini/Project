package View;

import Model.Entity.Landlord;
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

public class AmendLandlordGUI extends JFrame{
    
    private static final int WIDTH = 800;
    private static final int HEIGHT = 500;
    
    private JLabel nameL, addressLine1L, addressLine2L, addressLine3L, countyL, landLPhoneL, landlordPPSL, landlordStatusL,  landlordIDL, blankL, blank2L;
    private JTextField nameTF, addressLine1TF, addressLine2TF, addressLine3TF, countyTF, landLPhoneTF, landlordPPSTF, landlordStatusTF, landlordIDTF;
    private String[] counties = {"صادقیه","طرشت","پونک","ستارخان","مرزداران","پاسداران","میرداماد","نارمک","تهرانپارس",
            "تهرانسر","شهرک غرب","اکباتان","رسالت","جنت آباد","شهران","یوسف آباد","سهروردی","شریعتی","منیریه","شهرری","نازی آباد",
            "پیروزی","اندرزگو","مشیریه","افسریه","جوادیه","خزانه","بهارستان","حسن آباد","جمهوری","انقلاب","مجیدیه"};
    private JComboBox countyComboBox;
    private JButton addB, cancelB;
    
    public static Landlord landlord_;
    
    public AmendLandlordGUI(Landlord landlord){
        
        landlord_ = landlord;
        
        //Setting Font Properties
        Font myFont = new Font("Times New Roman", Font.BOLD, 20);
        
        //Instantiate the labels
        nameL = new JLabel("نام مالک: " , SwingConstants.RIGHT);
        addressLine1L = new JLabel("آدرس: ", SwingConstants.RIGHT);
        addressLine2L = new JLabel(" شرح امتیازات : ", SwingConstants.RIGHT);
        addressLine3L = new JLabel("  مساحت: ", SwingConstants.RIGHT);
        countyL = new JLabel("شهر: ", SwingConstants.RIGHT);
        landLPhoneL = new JLabel(" شماره تماس: ", SwingConstants.RIGHT);
        landlordPPSL = new JLabel("  شماره سند: ", SwingConstants.RIGHT);
        landlordStatusL = new JLabel(" قیمت و توضیحات اضافه: ", SwingConstants.RIGHT);
        landlordIDL = new JLabel("  آیدی فروشنده: ", SwingConstants.RIGHT);
        blankL = new JLabel("", SwingConstants.RIGHT);
        blank2L = new JLabel("", SwingConstants.RIGHT);
        
        //Label Font
        nameL.setFont(myFont);
        addressLine1L.setFont(myFont);
        addressLine2L.setFont(myFont);
        addressLine3L.setFont(myFont);
        countyL.setFont(myFont);
        landLPhoneL.setFont(myFont);
        landlordPPSL.setFont(myFont);
        landlordStatusL.setFont(myFont);
        landlordIDL.setFont(myFont);
        
        //Text Fields:
        nameTF = new JTextField(10);
        addressLine1TF = new JTextField(10);
        landLPhoneTF = new JTextField(10);
        landlordPPSTF = new JTextField(10);
        landlordStatusTF = new JTextField(10);
        landlordIDTF = new JTextField(10);
        landlordIDTF.setEditable(false);
        
        countyComboBox = new JComboBox(counties);
        
        nameTF.setText(landlord_.getName());
        addressLine1TF.setText(landlord_.getAddress());
        landLPhoneTF.setText(landlord_.getPhoneNumber());
        landlordPPSTF.setText(landlord_.getPpsNumber());
        landlordStatusTF.setText(landlord_.getLandlordStatus());
        landlordIDTF.setText(""+landlord_.getLandlordID());
        
        //Buttons:
        //Add Button
        addB = new JButton("اعمال تغییرات");
        addB.setFont(myFont);
        addB.setToolTipText("کلیک بر روی دکمه ثبت");
        addB.addActionListener(ae->{
            landlord_.setName(nameTF.getText());
            landlord_.setAddress(addressLine1TF.getText());
            landlord_.setPhoneNumber(landLPhoneTF.getText());
            landlord_.setPpsNumber(landlordPPSTF.getText());
            landlord_.setLandlordStatus(landlordStatusTF.getText()); 
            landlordIDTF.setText(""+landlord_.getLandlordID());
            
            HomeScreenGUI check = new HomeScreenGUI();
            check.updateLandlordDatabase(landlord_);
            JOptionPane.showMessageDialog(null, "اطلاعات مالک ویرایش شد");
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
        add(landLPhoneL);
        add(landLPhoneTF);
        add(landlordPPSL);
        add(landlordPPSTF);
        add(landlordStatusL);
        add(landlordStatusTF);
        add(landlordIDL);
        add(landlordIDTF);
        add(blankL);
        add(blank2L);
        add(addB);
        add(cancelB);
        
        setTitle("اصلاح جزئیات مالک");
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null); //Position application in center of screen
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        toFront();
    }
}
