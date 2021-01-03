package View;

import Model.Entity.Property;
import com.oracle.webservices.internal.api.message.PropertySet;
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


public class RecordNewPropertyGUI extends JFrame{
    
    private static final int WIDTH = 900;
    private static final int HEIGHT = 600;
    
    private JLabel addressLine1L, addressLine2L, addressLine3L, countyL, phoneL, propertyTypeL, rentAmountL, propertyStatusL, landlordL, propertyIDL, blankL, blank2L;
    private JTextField addressLine1TF, addressLine2TF, addressLine3TF, countyTF, phoneTF, propertyTypeTF, rentAmountTF, propertyStatusTF, landlordTF, propertyIDTF;
    private String[] counties = {"صادقیه","طرشت","پونک","ستارخان","مرزداران","پاسداران","میرداماد","نارمک","تهرانپارس",
            "تهرانسر","شهرک غرب","اکباتان","رسالت","جنت آباد","شهران","یوسف آباد","سهروردی","شریعتی","منیریه","شهرری","نازی آباد",
            "پیروزی","اندرزگو","مشیریه","افسریه","جوادیه","خزانه","بهارستان","حسن آباد","جمهوری","انقلاب","مجیدیه"};
    private JComboBox countyComboBox;
    private JButton addB, cancelB;
    
    public RecordNewPropertyGUI(){
        
        //Setting Font Properties
        Font myFont = new Font("Times New Roman", Font.BOLD, 20);
        
        //Instantiate the labels
        addressLine1L = new JLabel(" :‌ آدرس", SwingConstants.RIGHT);
        addressLine2L = new JLabel(" :‌ شرح امتیازات", SwingConstants.RIGHT);
        addressLine3L = new JLabel(" :‌ مساحت", SwingConstants.RIGHT);
        countyL = new JLabel(" :‌‌ شهر", SwingConstants.RIGHT);
        phoneL = new JLabel(" : شماره", SwingConstants.RIGHT);
        propertyTypeL = new JLabel(" :‌‌ نوع", SwingConstants.RIGHT);
        rentAmountL = new JLabel(" : هزینه", SwingConstants.RIGHT);
        propertyStatusL = new JLabel(" : وضعیت", SwingConstants.RIGHT);
        //landlordL = new JLabel("Landlord: ", SwingConstants.RIGHT);
        propertyIDL = new JLabel(" : آیدی مشخصه", SwingConstants.RIGHT);
        blankL = new JLabel("", SwingConstants.RIGHT);
        blank2L = new JLabel("", SwingConstants.RIGHT);
        
        //Label Font
        addressLine1L.setFont(myFont);
        addressLine2L.setFont(myFont);
        addressLine3L.setFont(myFont);
        countyL.setFont(myFont);
        phoneL.setFont(myFont);
        propertyTypeL.setFont(myFont);
        rentAmountL.setFont(myFont);
        propertyStatusL.setFont(myFont);
        propertyIDL.setFont(myFont);
        
        
        //Text Fields:
        addressLine1TF = new JTextField(10);
        addressLine2TF = new JTextField(10);
        addressLine3TF = new JTextField(10);
        countyTF = new JTextField(10);
        phoneTF = new JTextField(10);
        propertyTypeTF = new JTextField(10);
        rentAmountTF = new JTextField(10);
        propertyStatusTF = new JTextField(10);
        //landlordTF = new JTextField(10);
        propertyIDTF = new JTextField(10);
        propertyIDTF.setEditable(false);
        
         //County Drop Down Menu
        countyComboBox = new JComboBox(counties);
        
        
        //Buttons:
        //Add Button
        addB = new JButton("اضافه کردن مشخصات");
        addB.setFont(myFont);
        addB.setToolTipText("کلیک کردن بر روی دکمه ثبت.\n NOTE: Make sure details are correct");
        addB.addActionListener(ae->{
            Property p1 = new Property();
            p1.setAddress_(addressLine1TF.getText() + "\n" + addressLine2TF.getText() + "\n" + addressLine3TF.getText() + "\n" + countyComboBox.getSelectedItem());
            p1.setPhone_(phoneTF.getText());
            p1.setPropertyType_(propertyTypeTF.getText());
            p1.setRentAmount_(rentAmountTF.getText());
            p1.setPropertyStatus_(propertyStatusTF.getText());
            HomeScreenGUI.propertyList.add(p1);        //Add to the propertyList arrayList declared in the Home Screen
            HomeScreenGUI.propertyAvailable.add(p1);   //Add to the propertyAvailable arrayList declared in the Home Screen
            propertyIDTF.setText(""+p1.getPropertyID_());
            
            HomeScreenGUI check = new HomeScreenGUI();
            check.savePropertyDatabase(p1.getPropertyID_(),addressLine1TF.getText() + "\n" + addressLine2TF.getText() + "\n" + addressLine3TF.getText() + "\n" + countyComboBox.getSelectedItem(),
                   phoneTF.getText(),propertyTypeTF.getText(),rentAmountTF.getText(),propertyStatusTF.getText());
            JOptionPane.showMessageDialog(null, "مشخصات اضافه شد");
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
        add(addressLine1L);
        add(addressLine1TF);
        add(addressLine2L);
        add(addressLine2TF);
        add(addressLine3L);
        add(addressLine3TF);
        add(countyL);
        add(countyComboBox);
        add(phoneL);
        add(phoneTF);
        add(propertyTypeL);
        add(propertyTypeTF);
        add(rentAmountL);
        add(rentAmountTF);
        add(propertyStatusL);
        add(propertyStatusTF);
        add(propertyIDL);
        add(propertyIDTF);
        add(blankL);
        add(blank2L);
        add(addB);
        add(cancelB);
        
        setTitle("صفحه ثبت مشخصات جدید");
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null); //Position application in center of screen
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        toFront();
    }
}
