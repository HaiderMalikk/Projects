// NOTE this code will not run until you change the username and password

// TODO: add the login page and  form to authenticate a user.
package com.Email_App;

import java.io.File;
import java.util.Properties;

// swing imports NOTE try using a diffrent UI 
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class App {
	private static final boolean attachImage = true;

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Email App");
        ImageIcon icon = new ImageIcon("C:\\Users\\haide\\Documents\\Code\\Email_BOT\\LOGO.jpg");
        frame.setIconImage(icon.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        JTextField toTextField = new JTextField();
        JTextField subjectTextField = new JTextField();
        JTextArea messageTextArea = new JTextArea();
        JTextField imagePathTextField = new JTextField();
        JLabel fileNameLabel = new JLabel("File Name:");

        JButton sendButton = new JButton("Send");

        // To
        panel.add(new JLabel("To:"), gbc);
        gbc.gridy++;
        toTextField.setPreferredSize(new Dimension(300, 25));
        panel.add(toTextField, gbc);

        // Subject
        gbc.gridy++;
        panel.add(new JLabel("Subject:"), gbc);
        gbc.gridy++;
        subjectTextField.setPreferredSize(new Dimension(300, 25));
        panel.add(subjectTextField, gbc);

        // Message
        gbc.gridy++;
        panel.add(new JLabel("Message:"), gbc);
        gbc.gridy++;
        messageTextArea.setLineWrap(true);
        messageTextArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(messageTextArea);
        scrollPane.setPreferredSize(new Dimension(300, 150));
        panel.add(scrollPane, gbc);

        // Image Path
        gbc.gridy++;
        JLabel imagePathLabel = new JLabel("Click to attach FILE");
        imagePathLabel.setBorder(BorderFactory.createEmptyBorder());
        imagePathLabel.setForeground(Color.BLUE);
        imagePathLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Browse button action listener for the image path label
        imagePathLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Show file chooser dialog
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png", "gif");
                fileChooser.setFileFilter(filter);

                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String fileName = selectedFile.getName();  // Get the file name
                    String imagePath = selectedFile.getAbsolutePath();

                    // Format the image path with double backslashes
                    String formattedImagePath = imagePath.replace("\\", "\\\\");

                    // Display the formatted path in the text field
                    imagePathTextField.setText(formattedImagePath);

                    // Display the file name next to the image label
                    fileNameLabel.setText("File Name: " + fileName);
                }
            }
        });

        gbc.gridy++;
        panel.add(imagePathLabel, gbc);

        // File Name
        gbc.gridy++;
        panel.add(fileNameLabel, gbc);

        // Remove Image label
        gbc.gridy++;
        JLabel removeImageLabel = new JLabel("Remove Image");
        removeImageLabel.setForeground(Color.RED);
        removeImageLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Remove Image action listener
        removeImageLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                imagePathTextField.setText("");
                fileNameLabel.setText("File Name:");
            }
        });

        panel.add(removeImageLabel, gbc);

        // Send button
        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(sendButton, gbc);

        frame.getContentPane().add(panel);
        frame.setSize(400, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Send button action listener
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String to = toTextField.getText();
                String subject = subjectTextField.getText();
                String message = messageTextArea.getText();

                String attachmentPath = attachImage ? imagePathTextField.getText() : null;

                sendAttach(message, subject, to, "haidersemailbot@gmail.com", attachmentPath);

                // Reset inputs
                toTextField.setText("");
                subjectTextField.setText("");
                messageTextArea.setText("");
                imagePathTextField.setText("");
                fileNameLabel.setText("File Name:");
            }
        });
    }

	//this is responsible to send the message with attachment
 // This is responsible to send the message with attachment
 // This is responsible to send the message with attachment
 // This is responsible to send the message with attachment
    private static void sendAttach(String message, String subject, String to, String from, String attachmentPath) {
        String host = "smtp.gmail.com";
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("haidersemailbot@gmail.com", "PASSWORD HERE");
            }
        });

        session.setDebug(true);

        MimeMessage mimeMessage = new MimeMessage(session);

        try {
            mimeMessage.setFrom(new InternetAddress(from));
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            mimeMessage.setSubject(subject);

            MimeMultipart mimeMultipart = new MimeMultipart();

            // Text
            MimeBodyPart textMime = new MimeBodyPart();
            textMime.setText(message);
            mimeMultipart.addBodyPart(textMime);

            // Attachment
            if (attachmentPath != null) {
                MimeBodyPart fileMime = new MimeBodyPart();
                try {
                    File file = new File(attachmentPath);
                    if (file.exists()) {
                        FileDataSource source = new FileDataSource(file);
                        fileMime.setDataHandler(new DataHandler(source));
                        fileMime.setFileName(source.getName());
                        mimeMultipart.addBodyPart(fileMime);
                    } else {
                        System.out.println("File does not exist: " + attachmentPath);
                        // Optional: Print a message or handle this case differently
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    // Optional: Print a message or handle this case differently
                }
            }

            // Check if there's any attachment before setting the content
            if (attachmentPath != null) {
                mimeMessage.setContent(mimeMultipart);
            } else {
                // Set the text content directly if no attachment
                mimeMessage.setText(message);
            }

            try {
                Transport.send(mimeMessage);
                System.out.println("Sent success...");
                JOptionPane.showMessageDialog(null, "Email sent successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Email not sent. An error occurred.");
                JOptionPane.showMessageDialog(null, "Email not sent. An error occurred.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
