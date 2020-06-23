package com.brainup.readby.proxy

import com.brainup.readby.controller.ReadByRestController
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
    public Boolean getServiceResult(String addressForUrl, Map<String, String> map) {
        return callPublisher(addressForUrl,map)
    }

    public Boolean getServiceResultFallBack(String addressForUrl, Map<String, String> map) {
        try {
            Thread.sleep(10000)
            return callPublisher(addressForUrl,map)
        }catch(Exception ex){
            log.info "Retry for publisher call also failed with exception: ${ex.message}"
            return false
        }
    }

    private Boolean callPublisher(String addressForUrl, Map<String, String> map){
        log.info "Calling service on url :${url}"
        return restTemplate.getForObject("${url}/getFile?key={key}&bucket={bucket}&fileName={fileName}", Boolean.class, map)
    }
}

