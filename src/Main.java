import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// ALL RIGHTS RESERVED BY SUFYAAN //

class CourierBillingSoftwareGUI extends JFrame {

    private JTextField dateField, senderField, receiverField, weightField;
    private JTextField lengthField, breadthField, heightField;
    private JRadioButton surfaceRadio, airRadio;
    private JLabel resultLabel; // Added label to display the type of weight

    private String date;
    private String senderName;
    private String receiverName;
    private double actualWeight;
    private double surfaceVolumeWeight;
    private double airVolumeWeight;

    private List<String> billingHistory;

    public CourierBillingSoftwareGUI() {
        setTitle("Courier Billing Software");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        billingHistory = new ArrayList<>();

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel dateLabel = new JLabel("Date (YYYY-MM-DD):");
        dateLabel.setBounds(10, 20, 150, 25);
        panel.add(dateLabel);

        dateField = new JTextField(20);
        dateField.setBounds(160, 20, 200, 25);
        panel.add(dateField);

        JLabel senderLabel = new JLabel("Sender Name:");
        senderLabel.setBounds(10, 50, 150, 25);
        panel.add(senderLabel);

        senderField = new JTextField(20);
        senderField.setBounds(160, 50, 200, 25);
        panel.add(senderField);

        JLabel receiverLabel = new JLabel("Receiver Name:");
        receiverLabel.setBounds(10, 80, 150, 25);
        panel.add(receiverLabel);

        receiverField = new JTextField(20);
        receiverField.setBounds(160, 80, 200, 25);
        panel.add(receiverField);

        JLabel weightLabel = new JLabel("Weight (kgs):");
        weightLabel.setBounds(10, 110, 150, 25);
        panel.add(weightLabel);

        weightField = new JTextField(20);
        weightField.setBounds(160, 110, 200, 25);
        panel.add(weightField);

        JLabel dimensionsLabel = new JLabel("Dimensions (L x B x H):");
        dimensionsLabel.setBounds(10, 140, 150, 25);
        panel.add(dimensionsLabel);

        lengthField = new JTextField(10);
        lengthField.setBounds(160, 140, 60, 25);
        panel.add(lengthField);

        breadthField = new JTextField(10);
        breadthField.setBounds(230, 140, 60, 25);
        panel.add(breadthField);

        heightField = new JTextField(10);
        heightField.setBounds(300, 140, 60, 25);
        panel.add(heightField);

        surfaceRadio = new JRadioButton("Surface");
        surfaceRadio.setBounds(10, 170, 150, 25);
        panel.add(surfaceRadio);

        airRadio = new JRadioButton("Air");
        airRadio.setBounds(160, 170, 150, 25);
        panel.add(airRadio);

        ButtonGroup shippingGroup = new ButtonGroup();
        shippingGroup.add(surfaceRadio);
        shippingGroup.add(airRadio);

        resultLabel = new JLabel("Result: ");
        resultLabel.setBounds(10, 200, 350, 25);
        panel.add(resultLabel);

        JButton calculateButton = new JButton("Calculate");
        calculateButton.setBounds(10, 230, 150, 25);
        panel.add(calculateButton);

        JButton clearButton = new JButton("Clear");
        clearButton.setBounds(170, 230, 150, 25);
        panel.add(clearButton);

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateAndDisplayBilling();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });
    }

    private void calculateAndDisplayBilling() {
        date = dateField.getText();
        senderName = senderField.getText();
        receiverName = receiverField.getText();

        try {
            actualWeight = Double.parseDouble(weightField.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid numeric weight.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double length, breadth, height;

        try {
            length = Double.parseDouble(lengthField.getText());
            breadth = Double.parseDouble(breadthField.getText());
            height = Double.parseDouble(heightField.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric dimensions.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        surfaceVolumeWeight = (length * breadth * height) / 4750;
        airVolumeWeight = (length * breadth * height) / 6000;

        if (actualWeight >= Math.max(surfaceVolumeWeight, airVolumeWeight)) {
            resultLabel.setText("Result: Actual Weight is higher");
        } else {
            if (surfaceRadio.isSelected()) {
                resultLabel.setText("Result: Surface Volume Weight is higher");
            } else if (airRadio.isSelected()) {
                resultLabel.setText("Result: Air Volume Weight is higher");
            } else {
                JOptionPane.showMessageDialog(this, "Please select a shipping type.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        // Save Billing Details to History
        String billingDetails = "Billing Details:\n" +
                "Date: " + date + "\n" +
                "Sender: " + senderName + "\n" +
                "Receiver: " + receiverName + "\n" +
                "Actual Weight: " + actualWeight + " kgs\n" +
                "Surface Volume Weight: " + surfaceVolumeWeight + " kgs\n" +
                "Air Volume Weight: " + airVolumeWeight + " kgs";

        billingHistory.add(billingDetails);

        JOptionPane.showMessageDialog(this, billingDetails, "Billing Details", JOptionPane.INFORMATION_MESSAGE);
    }

    private void clearFields() {
        dateField.setText("");
        senderField.setText("");
        receiverField.setText("");
        weightField.setText("");
        lengthField.setText("");
        breadthField.setText("");
        heightField.setText("");
        surfaceRadio.setSelected(false);
        airRadio.setSelected(false);
        resultLabel.setText("Result: ");
    }

    public static void main(String[] args) {
        new CourierBillingSoftwareGUI();
    }
}
