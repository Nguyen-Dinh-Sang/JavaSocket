package musicinformationserver.service;

import musicinformationserver.util.ByteUtil;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Service {
    public void received(DataOutputStream dataOutputStream) {
        String result = "OK client " + 1;
        byte[] dataResult = ByteUtil.getByteUTF16(result);
        try {
            dataOutputStream.writeInt(dataResult.length);
            dataOutputStream.write(dataResult);
            dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
