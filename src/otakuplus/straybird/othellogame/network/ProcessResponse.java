package otakuplus.straybird.othellogame.network;

public class ProcessResponse {
	public static int LOGIN = 100;
	public static int LOGOUT = 101;
	public static int GET_USER_INFO = 102;
	public static int GET_USER_ONLINE_LIST = 103;
	public static int REGISTER_USER = 104;
	public static int UPDATE_GAME_TABLE = 106;

	private int requestType;
	private Object requestBody;
	private boolean responseState = false;

	public int getRequestType() {
		return requestType;
	}

	public void setRequestType(int requestType) {
		this.requestType = requestType;
	}

	public Object getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(Object requestBody) {
		this.requestBody = requestBody;
	}

	public boolean getResponseState() {
		return responseState;
	}

	public void setResponseState(boolean responseState) {
		this.responseState = responseState;
	}

	public static void main(String[] args) {
	}

}
