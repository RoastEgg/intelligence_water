import com.hawksoft.platform.socket.SocketClient;
import com.hawksoft.platform.socket.SocketClient1;
import com.hawksoft.platform.socket.SocketService;
import org.junit.Test;

import java.io.IOException;

public class SocketTest {

    @Test
    public void startSocket(){
        SocketService service = new SocketService();
        service.init();
    }

    @Test
    public void sendJson(){
        SocketClient client = new SocketClient();
        String json = "{\"type\": \"water\", \"data\": [{\"stnId\": 1, \"waterLevel\": 1.3, \"c_time\": \"2017-1-1 19:21:1\"}]}";
        client.send(json);
    }

    @Test
    public void sendPic() throws IOException {
        SocketClient1 client1 = new SocketClient1();
        client1.send("E:\\test.jpg");
    }
}
