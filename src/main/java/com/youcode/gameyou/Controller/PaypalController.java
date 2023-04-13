//package com.youcode.gameyou.Controller;
//
//import com.youcode.gameyou.Request.PaymentOrderRequest;
//import com.youcode.gameyou.Service.PaypalService;
//import lombok.AllArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//import com.paypal.api.payments.Links;
//import com.paypal.api.payments.Payment;
//import com.paypal.base.rest.PayPalRESTException;
//
//@RestController
//@RequestMapping("/api/payment/paypal")
//@AllArgsConstructor
//public class PaypalController {
//
//    private final PaypalService service;
//
//    public static final String SUCCESS_URL = "/pay/success";
//    public static final String CANCEL_URL = "/pay/cancel";
//
//    @PostMapping("/pay")
//    public String payment(@RequestBody PaymentOrderRequest payOrder) {
//        try {
//            Payment payment = service.createPayment(payOrder.getTotal(), payOrder.getCurrency(), payOrder.getMethod(),
//                    payOrder.getIntent(), payOrder.getDescription(), payOrder.getCancelUrl() + CANCEL_URL,
//                    payOrder.getSuccessUrl() + SUCCESS_URL);
//            for(Links link : payment.getLinks()) {
//                if(link.getRel().equals("approval_url")) {
//                    System.out.println("link.getHref()");
//                    System.out.println(link.getHref());
//                    System.out.println("link.getHref()");
//                    return "redirect:"+link.getHref();
//                }
//            }
//
//        } catch (PayPalRESTException e) {
//            e.printStackTrace();
//        }
//        return "redirect";
//    }
//
//    @GetMapping(CANCEL_URL)
//    public String cancelPay() {
//        return "cancel";
//    }
//
//    @GetMapping(SUCCESS_URL)
//    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
//        try {
//            Payment payment = service.executePayment(paymentId, payerId);
//            System.out.println("payment.toJSON()");
//            System.out.println(payment.toJSON());
//            System.out.println("payment.toJSON()");
//            if (payment.getState().equals("approved")) {
//                return "success";
//            }
//        } catch (PayPalRESTException e) {
//            System.out.println(e.getMessage());
//        }
//        return "redirect";
//    }
//}