package com.brainup.readby.util

import com.paytm.pg.merchant.PaytmChecksum
import groovy.util.logging.Slf4j
import org.json.JSONObject
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

    public String paytmChecksum(String mId, String orderId, String mKey) {

        /* initialize an hash */
        TreeMap<String, String> paytmParams = new TreeMap<String, String>()
        paytmParams.put("MID", mId)
        paytmParams.put("CUST_ID", "CUST_" + "9587666665")
        paytmParams.put("ORDER_ID", orderId)
        paytmParams.put("INDUSTRY_TYPE_ID", "Retail")
        paytmParams.put("CHANNEL_ID", "WAP")
        paytmParams.put("TXN_AMOUNT", "2")
        paytmParams.put("CHANNEL_ID", "CHANNEL_ID")
        paytmParams.put("INDUSTRY_TYPE_ID", "INDUSTRY_TYPE_ID")
        paytmParams.put("WEBSITE","DEFAULT")
        paytmParams.put("MOBILE_NO", "9587666665")
        paytmParams.put("EMAIL", "asdf@asdf.com")
        paytmParams.put("CALLBACK_URL", "https://securegw.paytm.in/theia/paytmCallback?ORDER_ID="+orderId)

        String paytmChecksum = PaytmChecksum.generateSignature(paytmParams, mKey)
        log.info("generateSignature Returns: " + paytmChecksum)
        return paytmChecksum
    }

    public static Map getTransactionToken(String mId, String orderId, String mKey) throws Exception {

        JSONObject body = new JSONObject()
        /* for custom checkout value is 'Payment' and for intelligent router is 'UNI_PAY' */

        body.put("requestType", "Payment")
        body.put("mid", mId)

        /* Find your Website Name in your Paytm Dashboard at https://dashboard.paytm.com/next/apikeys */

       // body.put("websiteName", Paytm.WEBSITE)
        body.put("orderId", orderId)
       // body.put("callbackUrl", Paytm.CALLBACK_URL + orderId)
    }

    }
