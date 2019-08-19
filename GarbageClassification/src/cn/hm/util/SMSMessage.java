package cn.hm.util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

public class SMSMessage {

	// 获取随机验证码
	public static int getCode() {
		return (int) ((Math.random() * 9 + 1) * 100000);
	}

	public static String SendMessage(String phone, int code) throws ServerException, ClientException {
		String message = null;
		DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAICISZzX3Snh5M",
				"2zIUw3W8AZr52BEIkkxaZdW1nKLE5t");
		DefaultProfile.addEndpoint("cn-hangzhou", "Dysmsapi", "dysmsapi.aliyuncs.com");
		IAcsClient client = new DefaultAcsClient(profile);
		SendSmsRequest request = new SendSmsRequest();
		request.setMethod(MethodType.POST);
		request.setPhoneNumbers(phone);
		request.setVersion("2017-05-25"); 		
		request.setSignName("chicken");
		request.setTemplateCode("SMS_171114976");
		request.setTemplateParam("{\"code\":\"" + code + "\"}");
		SendSmsResponse response = client.getAcsResponse(request);
		if (response.getCode() != null && response.getCode().equals("OK")) {
			System.out.println("短信发送成功！");
			message = "短信发送成功！";
		} else {
			System.out.println("短信发送失败！");
			message= "短信发送失败！";
		}
		return message;
	}


}