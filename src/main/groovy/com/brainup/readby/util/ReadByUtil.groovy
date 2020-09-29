package com.brainup.readby.util

import com.paytm.pg.merchant.PaytmChecksum
import groovy.util.logging.Slf4j
import org.springframework.stereotype.Component

@Component
@Slf4j
class ReadByUtil {

    public String getRandomNumberString() {
        // It will generate 6 digit random Number.
        // from 0 to 999999
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        // this will convert any number sequence into 6 character.
        return String.format("%06d", number);
    }

    public String paytmChecksum(String mId,String orderId,String mKey){

        /* initialize an hash */
        TreeMap<String, String> paytmParams = new TreeMap<String, String>()
        paytmParams.put("MID", mId)
        paytmParams.put("ORDERID", orderId)

        String paytmChecksum = PaytmChecksum.generateSignature(paytmParams, mKey)
        log.info("generateSignature Returns: " + paytmChecksum)
        return paytmChecksum

    }
}
