import org.netxms.certificatemanager.CertificateManager;
import org.netxms.certificatemanager.CertificateManagerProvider;

public class Main {
    public static void main(String[] args) {
        CertificateManager manager =
            CertificateManagerProvider.provideCertificateManager();

        //System.out.println(manager);
    }
}
