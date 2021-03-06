/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicinformationclient.aes;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author DinhSang
 */
public class AES {
    SecretKey secKey;

    public AES(byte[] secKey) {
        System.out.println("Create AES");
        this.secKey= new SecretKeySpec(secKey, 0, secKey.length, "AES");
    }

    public byte[] maHoa(String test) {
        try {
            Cipher aesCipher = null;
            aesCipher = Cipher.getInstance("AES");
            aesCipher.init(Cipher.ENCRYPT_MODE, secKey);
            byte[] byteCipherText = aesCipher.doFinal(test.getBytes());
            System.err.println("Encrypt AES Success");
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
            System.err.println("Decrypt AES Success: " + new String(bytePlainText));
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
        return null;
    }
}