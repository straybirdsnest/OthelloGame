package otakuplus.straybird.othellogame;

import com.google.api.client.http.*;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import otakuplus.straybird.othellogame.network.http.HttpTransportSingleton;
import otakuplus.straybird.othellogame.network.http.JsonFactorySingleton;

public class ApplicationLoginState implements ApplicationState {

	public void initialize() {
	}

	public void connect() {
	}

	public void login() {
		HttpTransport httpTransport = HttpTransportSingleton.getHttpTransportInstance();
        HttpRequestFactory requestFactory = httpTransport
                .createRequestFactory(new HttpRequestInitializer() {
                    public void initialize(HttpRequest request) {
                        request.setParser(new JsonObjectParser(JsonFactorySingleton.getJsonFactoryInstance()));
                    }
                });
        GenericUrl genericUrl = new GenericUrl("http://localhost:8080/api/login");
	}

	public void enterGameHall() {
	}

	public void enterGameTable() {
	}

	public void disconnect() {
	}

	public void destory() {
	}

}
