package com.brainup.readby.proxy

import com.brainup.readby.controller.ReadByRestController
import com.brainup.readby.dto.SMSResponse
import com.brainup.readby.util.ReadByUtil
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
@Slf4j
class ServiceCall {

    @Autowired
    ReadByUtil readByUtil

    @Autowired
    private RestTemplate restTemplate

    @Value('${readby.otp.url}')
    private String url

    @HystrixCommand(fallbackMethod = "getServiceResultFallBack")
    public SMSResponse getServiceResult(String addressForUrl, Map<String, String> map) {
        return callPublisher(addressForUrl,map)
    }

    public SMSResponse getServiceResultFallBack(String addressForUrl, Map<String, String> map) {
        try {
            Thread.sleep(10000)
            return callPublisher(addressForUrl,map)
        }catch(Exception ex){
            log.info "Retry for publisher call also failed with exception: ${ex.message}"
            return false
        }
    }

    private SMSResponse callPublisher(String addressForUrl, Map<String, String> map){
        log.info "Calling service on url :${url}"
        return restTemplate.getForObject("${url}message=Your OTP IS ${map.get("randomPIN")}&senderId=TBTSIG&routeId=8&mobileNos=${map.get("mobileNo")}&smsContentType=english", SMSResponse.class,map)
    }
}

