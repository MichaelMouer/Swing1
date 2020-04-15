package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrefsDialog extends JDialog {

    private JButton okButton;
    private JButton cancelButton;
    private JSpinner portSpinner;
    private SpinnerNumberModel spinnerModel;
    private JTextField userField;
    private JPasswordField passField;
    private PrefsListener prefsListener;

    public PrefsDialog(JFrame parent) {
        super(parent, "Preferences", false);

        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");

        spinnerModel = new SpinnerNumberModel(3306, 0, 9999, 1);
        portSpinner = new JSpinner(spinnerModel);

        userField = new JTextField(10);
        passField = new JPasswordField(10);

        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        gc.weightx = 1;
        gc.weighty = 1;
        gc.fill = GridBagConstraints.NONE;

        gc.gridy = 0;
//              ********************** First Row ************************
        gc.gridx = 0;
        add(new JLabel("User: "), gc);

        gc.gridx++;
        add(userField, gc);

//                ********************* Next Row************************
        gc.gridy++;
        gc.gridx = 0;
        add(new JLabel("Password: "), gc);

        passField.setEchoChar('*');
        gc.gridx++;
        add(passField, gc);

//                  ********************* Next Row************************
        gc.gridy++;
        gc.gridx = 0;
        add(new JLabel("Port: "), gc);

        gc.gridx++;
        add(portSpinner, gc);


//                ********************* Next Row************************
        gc.gridy++;
        gc.gridx = 0;
        add(okButton, gc);

        gc.gridx++;
        add(cancelButton, gc);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer value = (Integer)portSpinner.getValue();

                String user = userField.getText();
                 char[] password = passField.getPassword();

                if (prefsListener != null) {
                    prefsListener.preferencesSet(user, new String(password),
                            (Integer)portSpinner.getValue());
                }

                setVisible(false);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        setSize(400, 300);
        setLocationRelativeTo(parent);
    }

    public void setDefaults(String user, String password, int port) {
        userField.setText(user);
        passField.setText(password);
        portSpinner.setValue(port);
    }

    public void setPrefsListener(PrefsListener prefsListener) {
        this.prefsListener = prefsListener;
    }
}
