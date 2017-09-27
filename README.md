# enterprise-wechat-3rd-provider


## 创建应用套件
1. 创建应用套件时, 作为验证,腾讯会发送一个GET请求到我们配置的"系统事件接收URL",示例如下:
`https://www.xxx.com/enterprise-wechat-3rd-provider/callback?msg_signature=f57270852c3f046f781d4bd7a026f4bf09f2f301&timestamp=1506515395&nonce=47819876&echostr=3ITc3y%2B0Xt7geuN71qnAzXVlNifWvfoPLKs6Cz5VYi%2FgEYfifkdeT3E28R4ay2DGr8jYy144jFVA5tbAr%2Fzn2w%3D%3D`

这里的echostr是经过URL编码的,因此在获取这个值的时候需要做URL解码,为此,在Tomcat的server.xml中,加入`URIEncoding="UTF-8"`:
`<Connector connectionTimeout="20000" port="8181" protocol="HTTP/1.1" redirectPort="8443" URIEncoding="UTF-8"/> `
此时Tomcat会自动进行URL解码,因此我们的程序获得到的echostr已经经过URL解码了.
参考:https://stackoverflow.com/questions/16528304/spring-web-decoding-url-params

2. 在验证时,classpath中需要有commons-codec-1.9库,还需要用到腾讯提供的java库,从这里下载:https://work.weixin.qq.com/api/doc#10128,
验证时,调用腾讯Java库中的WXBizMsgCrypt( token,  encodingAesKey,  corpId).VerifyURL(),

其中token和encodingAesKey分别为创建应用套件时填写的token和encodingAesKey,corpId为服务商企业微信的corpId.

注意, 笔者在编译java库中的AesException.java文件时,遇到以下问题:

`error: illegal character: '\ufeff' in java`

解决方法:https://stackoverflow.com/questions/45697794/error-illegal-character-ufeff-in-java

另外,还遇到了这个问题:

`java.security.InvalidKeyException:illegal Key Size`

解决方法:https://stackoverflow.com/questions/19856324/exception-in-thread-main-java-security-invalidkeyexception-illegal-key-size-o