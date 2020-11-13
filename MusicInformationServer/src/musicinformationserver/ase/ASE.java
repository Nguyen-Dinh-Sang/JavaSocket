/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicinformationserver.ase;

import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author DinhSang
 */
public class ASE {

    SecretKey secKey = null;

    public ASE() {
        createKey();
    }

    public void createKey() {
        try {
            KeyGenerator generator = null;
            generator = KeyGenerator.getInstance("AES");
            generator.init(128); // The AES key size in number of bits
            secKey = generator.generateKey();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public byte[] maHoa(String test) {
        try {
            Cipher aesCipher = null;
            aesCipher = Cipher.getInstance("AES");
            aesCipher.init(Cipher.ENCRYPT_MODE, secKey);
            byte[] byteCipherText = aesCipher.doFinal(test.getBytes());
            return byteCipherText;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String giaMa(byte[] data) {
        try {
            Cipher aesCipher = null;
            aesCipher = Cipher.getInstance("AES");
            aesCipher.init(Cipher.DECRYPT_MODE, secKey);
            byte[] bytePlainText = aesCipher.doFinal(data);
            String plainText = new String(bytePlainText);
            System.out.println("KetQua: " + plainText);
            return plainText;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

        return "";
    }

    public byte[] getSecretKey() {
        for (byte item : secKey.getEncoded()) {
            System.err.println("# "+ item);
        }
        return secKey.getEncoded();
    }
}
