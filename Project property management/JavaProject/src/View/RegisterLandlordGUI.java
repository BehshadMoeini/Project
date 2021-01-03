package View;

import Controller.LandlordCtrl;
import Model.Entity.Landlord;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;


public class RegisterLandlordGUI extends JFrame{
    
    private static final int WIDTH = 900;
    private static final int HEIGHT = 600;
    
    private JLabel nameL, addressLine1L, addressLine2L, addressLine3L, countyL, landLPhoneL, landlordPPSL, landlordStatusL,  landlordIDL, blankL, blank2L;
    private JTextField nameTF, addressLine1TF, addressLine2TF, addressLine3TF, countyTF, landLPhoneTF, landlordPPSTF, landlordStatusTF, landlordIDTF;
    private String[] counties = {"صادقیه","طرشت","پونک","ستارخان","مرزداران","پاسداران","میرداماد","نارمک","تهرانپارس",
            "تهرانسر","شهرک غرب","اکباتان","رسالت","جنت آباد","شهران","یوسف آباد","سهروردی","شریعتی","منیریه","شهرری","نازی آباد",
            "پیروزی","اندرزگو","مشیریه","افسریه","جوادیه","خزانه","بهارستان","حسن آباد","جمهوری","انقلاب","مجیدیه"};
    private JComboBox countyComboBox;
    private JButton addB, cancelB;
    
    public RegisterLandlordGUI(){
        
        //Setting Font Properties
        Font myFont = new Font("Times New Roman", Font.BOLD, 20);
        
        //Instantiate the labels
        nameL = new JLabel(" : نام مالک" , SwingConstants.RIGHT);
        addressLine1L = new JLabel(" : آدرس", SwingConstants.RIGHT);
        addressLine2L = new JLabel(" : شرح امتیازات", SwingConstants.RIGHT);
        addressLine3L = new JLabel(" : مساحت", SwingConstants.RIGHT);
        countyL = new JLabel(" : شهر", SwingConstants.RIGHT);
        landLPhoneL = new JLabel(" : شماره تماس", SwingConstants.RIGHT);
        landlordPPSL = new JLabel(" : شماره سند ملک", SwingConstants.RIGHT);
        landlordStatusL = new JLabel(" : وضعیت مالک", SwingConstants.RIGHT);
        landlordIDL = new JLabel(" : آیدی مالک", SwingConstants.RIGHT);
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
        addressLine2TF = new JTextField(10);
        addressLine3TF = new JTextField(10);
        countyTF = new JTextField(10);
        landLPhoneTF = new JTextField(10);
        landlordPPSTF = new JTextField(10);
        landlordStatusTF = new JTextField(10);
        landlordIDTF = new JTextField(10);
        landlordIDTF.setEditable(false);

        //County Drop Down Menu
        countyComboBox = new JComboBox(counties);
        
        
         //Buttons:
        //Add Button
        addB = new JButton("اضافه کردن مالک");
        addB.setFont(myFont);
        addB.setToolTipText("کلیک کن بر روی دکمه ثبت.\n توجه: از  اطللاعات مطمئن باش");
        addB.addActionListener(ae->{
            Landlord l1 = new Landlord();
            l1.setName(nameTF.getText());
            l1.setAddress(addressLine1TF.getText() + "\n" + addressLine2TF.getText() + "\n" + addressLine3TF.getText() + "\n" + countyComboBox.getSelectedItem());
            l1.setPhoneNumber(landLPhoneTF.getText());
            l1.setPpsNumber(landlordPPSTF.getText());
            l1.setLandlordStatus(landlordStatusTF.getText());             
            HomeScreenGUI.landlordList.add(l1);   //Add to the landlord arrayList declared in the Home Screen
            landlordIDTF.setText(""+l1.getLandlordID());
            
            HomeScreenGUI saveData = new HomeScreenGUI();
            saveData.saveLandlordDatabase(l1.getLandlordID(), nameTF.getText(), addressLine1TF.getText() + "\n" + addressLine2TF.getText() + "\n" + addressLine3TF.getText() + "\n" + countyComboBox.getSelectedItem(),
                    landLPhoneTF.getText(), landlordPPSTF.getText(), landlordStatusTF.getText());
            JOptionPane.showMessageDialog(null, "مالک اضافه شد!");
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
        
        setTitle("صفحه ثبت مالک جدید");
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null); //Position application in center of screen
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        toFront();
    }
}
