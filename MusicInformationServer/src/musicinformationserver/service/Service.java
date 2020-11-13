package musicinformationserver.service;

import musicinformationserver.ase.ASE;
import musicinformationserver.rsa.RSA;

public class Service {

    private TuongTac tuongTac;
    private ASE ase;

    public Service(TuongTac tuongTac) {
        this.tuongTac = tuongTac;
    }
    
    public void xyLy(byte[] data) {

        String message = "";
        if (ase != null) {
            message = ase.giaMa(data);
        }

        if (message.startsWith("SING###")) {
            String ok = "RESULT###" + "ok";
            tuongTac.send(ase.maHoa(ok));
        } else {
            if (message.startsWith("SONG###")) {

            } else {
                ase = new ASE();
                RSA rsa = new RSA(data);
                tuongTac.send(rsa.maHoa(ase.getSecretKey()));
            }
        }
    }

    public interface TuongTac {

        void send(byte[] data);
    }
}
