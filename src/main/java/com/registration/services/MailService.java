package com.registration.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import static javax.mail.Message.RecipientType.TO;

@Service
public class MailService {
    private JavaMailSender sender;

    @Autowired
    public void setSender(JavaMailSender sender) {
        this.sender = sender;
    }

    public void sendEmail(String to,String from,String username) throws Exception{
        MimeMessage message = sender.createMimeMessage();
       // MimeMessageHelper helper = new MimeMessageHelper(message);
        message.addRecipient(TO, new InternetAddress(to));
        message.addFrom(new InternetAddress[] { new InternetAddress( from)});
        message.setSubject("");
        message.setText("localhost:3000/suthenticate/"+encryption(username));
        sender.send(message);

    }
    public String encryption(String password){
        String encryptedText = "";
        try {
            String keyValue = "PriVateKey";
            Cipher encryptedcipher = Cipher.getInstance("AES");
            byte[] encryptkey = generateKey(keyValue);
            SecretKeySpec secretKey = new SecretKeySpec(encryptkey, "AES");
            encryptedcipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] inputbytes = password.getBytes();
            byte[] outputbytes = encryptedcipher.doFinal(inputbytes);
            encryptedText = Base64Utils.encodeToString(outputbytes);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException ex) {
            System.out.println(ex);
        }
        System.out.println(encryptedText);
        return encryptedText;
    }

    public static byte[] generateKey(String keyValue) throws NoSuchAlgorithmException {
        byte[] keys = keyValue.getBytes();
        MessageDigest sha = MessageDigest.getInstance("SHA-1");
        keys = sha.digest(keys);
        keys = Arrays.copyOf(keys, 32);
        return keys;
    }
    public String decryptData(String encryptedText)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
        String decryptedText = "";
        String keyValue = "PriVateKey";
        try {
            Cipher decryptedcipher = Cipher.getInstance("AES");
            byte[] decryptkey = generateKey(keyValue);
            SecretKeySpec secretKey = new SecretKeySpec(decryptkey, "AES");
            decryptedcipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] encryptedByte = Base64Utils.decode(encryptedText.getBytes());
            byte[] decryptedByte = decryptedcipher.doFinal(encryptedByte);
            decryptedText = new String(decryptedByte, "UTF-8");
        }
        catch (NoSuchPaddingException| NoSuchAlgorithmException| InvalidKeyException| BadPaddingException| IllegalBlockSizeException| UnsupportedEncodingException ex){
            System.out.println("Key is invalid could you please check the key!!!!");
        }
        return decryptedText;
    }
}
