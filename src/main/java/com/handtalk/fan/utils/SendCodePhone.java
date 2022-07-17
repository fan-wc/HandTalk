package com.handtalk.fan.utils;


import com.aliyun.tea.TeaModel;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.teautil.models.RuntimeOptions;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;

import java.util.Random;

public class SendCodePhone {
    /* 短信API产品名称（短信产品名固定，无需修改） */
    private static final String product = "Dysmsapi";

    /* 短信API产品域名，接口地址固定，无需修改 */
    private static final String domain = "dysmsapi.aliyuncs.com";

    /* 此处需要替换成开发者自己的accessKeyId和accessKeySecret(在阿里云访问控制台寻找) */
    private static final String accessKeyId = "LTAI5tBQCiTSqe5WD4byWjZz "; //TODO: 这里要写成你自己生成的
    private static final String accessKeySecret = "uO5Ftj1J4ziaUfOTdEn7Qjc7OCffdH ";//TODO: 这里要写成你自己生成的

    public static com.aliyun.dysmsapi20170525.Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        Config config = new Config()
                // 您的 AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 您的 AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = domain;
        return new com.aliyun.dysmsapi20170525.Client(config);
    }

    /* 短信发送 */
    public static SendSmsResponse sendSms(String phone) throws ClientException, Exception{
        String code = getMsgCode();
        com.aliyun.dysmsapi20170525.Client client = SendCodePhone.createClient(accessKeyId,accessKeySecret);
        com.aliyun.dysmsapi20170525.models.SendSmsRequest sendSmsRequest = new com.aliyun.dysmsapi20170525.models.SendSmsRequest()
                .setSignName("阿里云短信测试")
                .setTemplateCode("SMS_246085163")
                .setPhoneNumbers(phone)
                .setTemplateParam("{\"code\":\"" + code + "\"}");
        RuntimeOptions runtime = new RuntimeOptions();
        com.aliyun.dysmsapi20170525.models.SendSmsResponse resp = client.sendSmsWithOptions(sendSmsRequest, runtime);
        com.aliyun.teaconsole.Client.log(com.aliyun.teautil.Common.toJSONString(TeaModel.buildMap(resp)));


//        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);

//        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
//        IAcsClient acsClient = new DefaultAcsClient(profile);
//
//        /* 组装请求对象-具体描述见控制台-文档部分内容 */
//        SendSmsRequest request = new SendSmsRequest();
//        /* 必填:待发送手机号 */
//        request.setPhoneNumbers(phone);
//        /* 必填:短信签名-可在短信控制台中找到 */
//        request.setSignName("看得见的声音"); //TODO: 这里是你短信签名
//        /* 必填:短信模板code-可在短信控制台中找到 */
//        request.setTemplateCode("SMS_246085163"); //TODO: 这里是你的模板code
//        /* 可选:模板中的变量替换JSON串,如模板内容为"亲爱的用户,您的验证码为${code}"时,此处的值为 */
//        request.setTemplateParam("{\"code\":\"" + code + "\"}");
//        System.out.println("给电话为" + phone + "发送的验证码为：" + code);
//        // hint 此处可能会抛出异常，注意catch
//        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
//        if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
//            System.out.println("短信发送成功！验证码：" + code);
//        } else {
//            System.out.println("短信发送失败！");
//        }
//        return sendSmsResponse;
        return null;
    }

    /**
     * 随机生成验证码
     *
     * @return
     */
    private static String getMsgCode() {
        int n = 6;
        StringBuilder code = new StringBuilder();
        Random ran = new Random();
        for (int i = 0; i < n; i++) {
            code.append(Integer.valueOf(ran.nextInt(10)).toString());
        }
        return code.toString();
    }
}
