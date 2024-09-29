package com.food.phat.service;

import com.food.phat.dto.payment.InitPaymentRequest;
import com.food.phat.dto.payment.InitPaymentResponse;
import com.food.phat.dto.payment.IpnResponse;
import com.food.phat.entity.OrderStatus;
import com.food.phat.utils.*;
import com.food.phat.utils.Currency;
import com.food.phat.utils.Locale;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@PropertySource("classpath:application.properties")
@RequiredArgsConstructor
public class PaymentService {

    private final OrderService orderService;

    public static final String VERSION = "2.1.0";
    public static final String COMMAND = "pay";
    public static final String ORDER_TYPE = "190000";
    public static final long DEFAULT_MULTIPLIER = 100L;

    @Value("${payment.vnpay.tmn-code}")
    private String tmnCode;

    @Value("${payment.vnpay.timeout}")
    private Integer paymentTimeout;

    public InitPaymentResponse init(InitPaymentRequest request) {
        var amount = request.getAmount() * DEFAULT_MULTIPLIER;  // 1. amount * 100
        var txnRef = request.getTxnRef();                       // 2. bookingId
        var returnUrl = VNPayUtil.buildReturnUrl(txnRef);                 // 3. FE redirect by returnUrl

        var vnCalendar = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        var createdDate = DateUtil.formatVnTime(vnCalendar);
        vnCalendar.add(Calendar.MINUTE, paymentTimeout);
        var expiredDate = DateUtil.formatVnTime(vnCalendar);    // 4. expiredDate for secure

        var ipAddress = request.getIpAddress();
        var orderInfo = VNPayUtil.buildPaymentDetail(txnRef);

        Map<String, String> params = new HashMap<>();

        params.put(VNPayParams.VERSION, VERSION);
        params.put(VNPayParams.COMMAND, COMMAND);

        params.put(VNPayParams.TMN_CODE, tmnCode);
        params.put(VNPayParams.AMOUNT, String.valueOf(amount));
        params.put(VNPayParams.CURRENCY, Currency.VND.getValue());

        params.put(VNPayParams.TXN_REF, txnRef.toString());
        params.put(VNPayParams.RETURN_URL, returnUrl);

        params.put(VNPayParams.CREATED_DATE, createdDate);
        params.put(VNPayParams.EXPIRE_DATE, expiredDate);

        params.put(VNPayParams.IP_ADDRESS, ipAddress);

        params.put(VNPayParams.LOCALE, Locale.VIETNAM.getCode());

        params.put(VNPayParams.ORDER_INFO, orderInfo);
        params.put(VNPayParams.ORDER_TYPE, ORDER_TYPE);

        var initPaymentUrl = VNPayUtil.buildInitPaymentUrl(params);
        return InitPaymentResponse.builder()
                .vnpUrl(initPaymentUrl).build();
    }

    @Transactional
    public IpnResponse process(Map<String, String> params) {
        if(!VNPayUtil.verifyIpn(params)) return VnIpnResponseUtil.SIGNATURE_FAILED;

        try {
            Integer orderId = Integer.parseInt(params.get(VNPayParams.TXN_REF));
            orderService.modifyOrderStatus(orderId, OrderStatus.PAID);
        } catch(Exception e) {
            return VnIpnResponseUtil.ORDER_NOT_FOUND;
        }

        return VnIpnResponseUtil.UNKNOWN_ERROR;
    }

}










































