//package com.futureboost.FutureBoostify.model;
//
//import jakarta.persistence.*;
// //if you want to manage the relationship between campaigns and payment
// // methods explicitly, you might need a CampaignPaymentMethod entity
//@Entity
//@Table(name = "campaign_payment_methods")
//public class CampaignPaymentMethod {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    @ManyToOne
//    @JoinColumn(name = "campaign_id", nullable = false)
//    private Campaign campaign;
//
//    @ManyToOne
//    @JoinColumn(name = "payment_method_id", nullable = false)
//    private PaymentMethod paymentMethod;
//    // Getters and Setters
//}
