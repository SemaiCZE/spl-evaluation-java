package cz.cuni.mff.d3s.spl.restapi;

import javax.xml.bind.annotation.XmlTransient;

@javax.xml.bind.annotation.XmlRootElement
public class ApiResponse {
	int code;
	Object payload;

	public ApiResponse(){}

	public ApiResponse(int code, Object payload){
		this.code = code;
		this.payload = payload;
	}

	@XmlTransient
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Object getPayload() {
		return payload;
	}

	public void setPayload(Object payload) {
		this.payload = payload;
	}
}
